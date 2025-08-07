package com.groupe1.collabdev_api.controllers;

import com.groupe1.collabdev_api.dto.request_dto.RequestAuthentification;
import com.groupe1.collabdev_api.exceptions.UserNotFoundException;
import com.groupe1.collabdev_api.services.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

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
            boolean isAuthenticatedUser = authenticationService.authenticate(user);
            if (isAuthenticatedUser) {

                Map<String, String> res = new HashMap<>();

                res.put("token", "yes");
                res.put("role", String.valueOf(user.getRole()));
                return
                    new ResponseEntity<>(
                            res,
                            HttpStatus.OK
                    );
            } else {
                return
                        new ResponseEntity<>(
                                "Authentification échouée!",
                                HttpStatus.OK
                        );
            }
        } catch (UserNotFoundException e) {
            return
                    new ResponseEntity<>(
                            e.getMessage(),
                            HttpStatus.NOT_FOUND
                    );
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
