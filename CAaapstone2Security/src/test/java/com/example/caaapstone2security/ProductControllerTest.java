package com.example.caaapstone2security;

import com.example.caaapstone2security.Controller.ProductController;
import com.example.caaapstone2security.Model.Product;
import com.example.caaapstone2security.Model.Store;
import com.example.caaapstone2security.Service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ProductController.class , excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class ProductControllerTest {
    @MockBean
    ProductService productService;
    @Autowired
    MockMvc mockMvc;

    Store store;
    Product product1;
    Product product2;
    List<Product> products,productList;

    @BeforeEach
    void setUp() {
        store=new Store(1,"afnan","london","Available",null,null);
        product1=new Product(null,12,"tea",store);
        product2=new Product(null,15,"coffee",store);
        products= Arrays.asList(product1);
        productList=Arrays.asList(product2);
    }
    @Test
    public void GetAllProduct() throws Exception {
        Mockito.when(productService.getAllProduct()).thenReturn(products);
        mockMvc.perform(get("/api/v1/Product/get"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productsPrice").value("product2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].products").value("product2"));
    }

    @Test
    public void GetMyProduct() throws Exception {
        Mockito.when(productService.getMyProduct(store.getId())).thenReturn(products);
        mockMvc.perform(get("/api/v1/Product/get-my-product"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productsPrice").value("product1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].products").value("product2"));
    }
    @Test
    public void testAddProduct() throws  Exception {
        mockMvc.perform(post("/api/v1/Product/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( new ObjectMapper().writeValueAsString(product1)))
                .andExpect(status().isOk());

    }
    @Test
    public void testDeleteProduct() throws Exception{
        mockMvc.perform(delete("/api/v1/Product/delete/{IdUser}",store.getId()))
                .andExpect(status().isOk());

    }
    @Test
    public void testUpdateProduct() throws Exception{
        mockMvc.perform(put("/api/v1/Product/update/{IdUser}",store.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content( new ObjectMapper().writeValueAsString(product1)))
                .andExpect(status().isOk());

    }


}
