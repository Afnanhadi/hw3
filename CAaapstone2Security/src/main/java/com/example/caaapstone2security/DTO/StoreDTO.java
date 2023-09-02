package com.example.caaapstone2security.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StoreDTO {
    @Pattern(regexp = "^[A-Za-z\\s]{1,}[0-9\\s]{1,}", message = "Please enter a valid password")
    private String password;
    @Column(unique = true)
    @NotEmpty
    private String username;
    private String role;
    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty
    private String nameStore;
    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null")
    private String address;
    @Column(columnDefinition = "varchar(20) not null check(state='Available' or state='Locked')")
    @NotEmpty
    private String state;

}
