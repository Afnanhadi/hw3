package com.example.caaapstone2security;

import com.example.caaapstone2security.Model.Product;
import com.example.caaapstone2security.Model.Store;
import com.example.caaapstone2security.Repository.ProductRepostory;
import com.example.caaapstone2security.Repository.StoreRepository;
import com.example.caaapstone2security.Service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;

    @Mock
    ProductRepostory productRepostory;

    @Mock
    StoreRepository storeRepository;

    Store store;
    Product product1;
    Product product2;
    List<Product> products;

   @BeforeEach
    void setUp(){ ///عشان يتاكد ان الداتا بيز مو فارغه عندي داتا بيز جاهزه/
        store=new Store(1,"afnan","london","Available",null,null);
        product1=new Product(null,12,"tea",store);
        product2=new Product(null,12,"tea",store);

        products=new ArrayList<>();

        products.add(product1);

        products.add(product2);

   }
    @Test
    public void getAll(){
        when(productRepostory.findAll()).thenReturn(products);
        List<Product> productsList=productService.getAllProduct();

        Assertions.assertEquals(productsList,products);

        Mockito.verify(productRepostory,times(1)).findAll();
    }
    @Test
    public void getMyProduct(){
        when(storeRepository.findStoreById(store.getId())).thenReturn(store);
        when(productRepostory.findAllByProducts(store)).thenReturn(products);
        List<Product> ProductsList=productService.getMyProduct(store.getId());

        verify(productRepostory,times(1)).findAllByProducts(store);
        verify(storeRepository,times(1)).findStoreById(store.getId());
    }
    @Test
    public void add1Product(){
        when(storeRepository.findStoreById(store.getId())).thenReturn(store);
        productService.addProduct(product1,store.getId());
        verify(productRepostory,times(1)).save(product1);
        verify(storeRepository,times(1)).findStoreById(store.getId());
    }
    @Test
    public void updateProduct(){
        when(productRepostory.findAllById(product2.getId())).thenReturn(product2);
        when(storeRepository.findStoreById(store.getId())).thenReturn(store);

        productService.UpdateProduct(product2,store.getId(),product2.getId());
        verify(productRepostory,times(1)).findAllById(product2.getId());
        verify(storeRepository,times(1)).findStoreById(store.getId());
        verify(productRepostory,times(1)).save(product2);
    }
    @Test
    public void deleteProduct(){
        when(productRepostory.findAllById(product2.getId())).thenReturn(product2);

        productService.deleteProduct(store.getId(),product2.getId());
        verify(productRepostory,times(1)).findAllById(product2.getId());
        verify(productRepostory,times(1)).delete(product2);
    }
}
