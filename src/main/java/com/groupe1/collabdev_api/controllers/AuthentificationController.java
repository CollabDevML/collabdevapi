package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.request_dto.RequestAuthentification;
import com.groupe1.collabdev_api.dto.response_dto.ResponseAuthentification;
import com.groupe1.collabdev_api.services.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentification")
@Tag(name = "Authentification Api",
        description = "pour s'authentifier")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthentificationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<?> seConnecter(
            @RequestBody RequestAuthentification user
    ) {
        try {
            ResponseAuthentification userInfo = authenticationService.authenticate(user);
            if (userInfo != null) {
                return
                        new ResponseEntity<>(
                                userInfo,
                                HttpStatus.OK
                        );
            } else {
                return
                        new ResponseEntity<>(
                                "Authentification échouée!",
                                HttpStatus.OK
                        );
            }
        } catch (RuntimeException e) {
            return
                    new ResponseEntity<>(
                            e.getMessage(),
                            HttpStatus.FORBIDDEN
                    );
        } catch (Exception e) {
            return
                    new ResponseEntity<>(
                            e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR
                    );
        }
    }
}
