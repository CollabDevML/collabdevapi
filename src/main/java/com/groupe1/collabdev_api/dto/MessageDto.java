package com.groupe1.collabdev_api.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MessageDto {
    private String sender;
    private String content;
}
