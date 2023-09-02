package com.example.caaapstone2security.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {
    @Email
    private String email;
    @Pattern(regexp = "^[A-Za-z\\s]{1,}[0-9\\s]{1,}", message = "Please enter a valid password")
    private String password;
    @Column(unique = true)
    @NotEmpty
    private String username;
    private String role;
    private Double points;
    private Integer salary;
}
