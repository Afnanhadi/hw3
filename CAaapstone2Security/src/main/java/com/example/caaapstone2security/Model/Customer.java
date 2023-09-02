package com.example.caaapstone2security.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Customer{
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;


  //  @NotEmpty
   // @Email
   // @Column(columnDefinition = "varchar(20) not null")
    private String email;

    private Double points;
   // @NotNull
    private Integer salary;

    @OneToOne(cascade=CascadeType.ALL,mappedBy  ="customer")
    @PrimaryKeyJoinColumn
    private User user;




}