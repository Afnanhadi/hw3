package com.example.caaapstone2security;

import com.example.caaapstone2security.Model.Customer;
import com.example.caaapstone2security.Model.User;
import com.example.caaapstone2security.Repository.CustomerRepostory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class CustomerRepositoryTest {
    @Autowired
    CustomerRepostory customerRepostory;
    User user;

    Customer customer1;
    Customer customer2;
  Customer customer;
    List<Customer> customerList;

    @BeforeEach
    void setUp() {
        user=new User(1,"afnan","asdf1234","CUSTOMER",null,null);
        customer1=new Customer(null,"afnan@gmail.com",12.8,10,user);
        customer2=new Customer(null,"amjad@gmail.com",1333.4,200,user);
    }
    @Test
    public void findUserById(){
        customerRepostory.save(customer1);
       customer= customerRepostory.findUserById(customer1.getId());
        Assertions.assertThat(customer).isEqualTo(customer1);
    }
    @Test
    public void findAllByUser(){
        customerRepostory.save(customer1);
        customerRepostory.save(customer2);
        customerList=customerRepostory.findAllByUser(user);
        Assertions.assertThat(customerList.get(0).getUser()).isEqualTo(user.getId());
    }
}
