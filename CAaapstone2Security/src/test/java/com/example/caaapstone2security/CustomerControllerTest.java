package com.example.caaapstone2security;

import com.example.caaapstone2security.Api.ApiResponse;
import com.example.caaapstone2security.Controller.CustomerController;
import com.example.caaapstone2security.Model.Customer;
import com.example.caaapstone2security.Model.Store;
import com.example.caaapstone2security.Model.User;
import com.example.caaapstone2security.Service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CustomerController.class , excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class CustomerControllerTest {
    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    
    Customer customer1,customer2;
    User user;
    ApiResponse apiResponse;
   List<Customer> customers,customerList;
    @BeforeEach
    void setUp() {
        user=new User(1,"afnan","hu123asx","CUSTOMER",null,null);
        customer1=new Customer(0,"afnanhad@gmail.com",20.3,200,user);
        customer2=new Customer(2,"amjad@gmail.com",3.6,200000,user);
        customers= Arrays.asList(customer1);
        customerList=Arrays.asList(customer2);
    }


    @Test
    public void testAddCustomer() throws  Exception {
        mockMvc.perform(post("/api/v1/customer/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( new ObjectMapper().writeValueAsString(customer1)))
                .andExpect(status().isOk());

    }
    @Test
    public void testDeleteCustomer() throws Exception{
        mockMvc.perform(delete("/api/v1/customer/delete/{Id}",user.getId()))
                .andExpect(status().isOk());

    }
    }

