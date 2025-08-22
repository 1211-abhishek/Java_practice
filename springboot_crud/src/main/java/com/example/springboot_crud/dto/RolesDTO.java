package com.example.springboot_crud.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RolesDTO {

    private int roleId;

    @NotBlank
    private String roleName;
}
