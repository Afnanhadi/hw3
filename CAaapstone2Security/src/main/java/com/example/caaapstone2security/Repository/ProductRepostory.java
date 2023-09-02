package com.example.caaapstone2security.Repository;

import com.example.caaapstone2security.Model.Product;
import com.example.caaapstone2security.Model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepostory extends JpaRepository<Product,Integer> {
    Product findAllById (Integer Id);
    List<Product> findAllByProducts (Store store);

}