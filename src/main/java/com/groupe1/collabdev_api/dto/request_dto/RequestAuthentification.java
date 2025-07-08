package com.groupe1.collabdev_api.dto.request_dto;

import com.groupe1.collabdev_api.entities.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestAuthentification {
    private String email;
    private String motDePasse;
    private Role role;
}
