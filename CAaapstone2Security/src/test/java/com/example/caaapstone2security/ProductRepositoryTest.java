package com.example.caaapstone2security;

import com.example.caaapstone2security.Model.Product;
import com.example.caaapstone2security.Model.Store;
import com.example.caaapstone2security.Model.User;
import com.example.caaapstone2security.Repository.ProductRepostory;
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
public class ProductRepositoryTest {
    @Autowired
    ProductRepostory productRepostory;

    Store store;
    Product product;
    Product product1;
    Product product2;
    List<Product> products;

    @BeforeEach
    void setUp() {
        store=new Store(1,"afnan","london","Available",null,null);
        product1=new Product(null,12,"tea",store);
        product2=new Product(null,15,"coffee",store);
    }
    @Test
      public void findAllByProducts(){
        productRepostory.save(product1);
        productRepostory.save(product2);
        products=productRepostory.findAllByProducts(store);
          Assertions.assertThat(products.get(0).getStore().getId()).isEqualTo((store.getId()));
      }
    @Test
    public void findTodoById(){
        productRepostory.save(product1);
        product=productRepostory.findAllById(product1.getId());
        Assertions.assertThat(product).isEqualTo(product1);
    }
}
