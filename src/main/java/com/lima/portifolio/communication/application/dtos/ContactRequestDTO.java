package com.lima.portifolio.communication.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContactRequestDTO {
    private String name;
    private String email;
    private String message;
}
