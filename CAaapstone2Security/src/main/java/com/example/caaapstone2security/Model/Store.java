package com.example.caaapstone2security.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Store  {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty
    private String nameStore;
    @NotEmpty
    @Column(columnDefinition = "varchar(20) not null")
    private String address;
    @Column(columnDefinition = "varchar(20) not null check(state='Available' or state='Locked')")
    @NotEmpty
    private String state;


    @OneToOne(cascade=CascadeType.ALL,mappedBy  ="store")
    @PrimaryKeyJoinColumn
    private User user;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "store")
    private Set<Product> products;
}