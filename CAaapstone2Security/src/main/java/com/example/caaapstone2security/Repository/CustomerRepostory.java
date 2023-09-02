package com.example.caaapstone2security.Repository;

import com.example.caaapstone2security.Model.Customer;
import com.example.caaapstone2security.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepostory extends JpaRepository<Customer,Integer> {
    Customer findUserById (Integer Id);
    
    List<Customer> findAllByUser(User user);

}