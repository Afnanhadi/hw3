package com.example.caaapstone2security.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Product {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    //@NotNull
    private Integer productsPrice;
    private  String products;


    @ManyToOne
    @JoinColumn(name = "store_id" ,referencedColumnName = "id")
    @JsonIgnore
    private Store store;
}
