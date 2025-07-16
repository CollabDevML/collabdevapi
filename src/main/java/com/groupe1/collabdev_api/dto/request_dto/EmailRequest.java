package com.groupe1.collabdev_api.dto.request_dto;

public record EmailRequest(
        String dest,
        String subject,
        String text) {
}