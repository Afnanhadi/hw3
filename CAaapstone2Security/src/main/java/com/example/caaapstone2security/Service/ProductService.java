package com.example.caaapstone2security.Service;

import com.example.caaapstone2security.Api.ApiException;
import com.example.caaapstone2security.Model.Customer;
import com.example.caaapstone2security.Model.Product;
import com.example.caaapstone2security.Model.Store;

import com.example.caaapstone2security.Model.User;
import com.example.caaapstone2security.Repository.AuthRepostory;
import com.example.caaapstone2security.Repository.CustomerRepostory;
import com.example.caaapstone2security.Repository.ProductRepostory;
import com.example.caaapstone2security.Repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepostory productRepostory;
    private final CustomerRepostory customerRepostory;
    private final StoreRepository storeRepository;
    public List<Product> getAllProduct(){
        return productRepostory.findAll();
    }
    public List<Product> getMyProduct(Integer store_id) {
        Store store=storeRepository.findStoreById(store_id);
        return productRepostory.findAllByProducts(store);
    }
    public void addProduct(Product product,Integer store_id){
       Store store=storeRepository.findStoreById(store_id);
        product.setStore(store);
        productRepostory.save(product);
    }
    public void UpdateProduct(Product product, Integer product_id,Integer store_id){
        Product product1=productRepostory.findAllById(product_id);
        if(product1==null){
            throw new ApiException("not found id");
        }

        if (product.getStore().getId()!= store_id)
        {
            throw new ApiException("the store not found");
        }

        product1.setProducts(product.getProducts());
        product1.setProductsPrice(product.getProductsPrice());
        productRepostory.save(product1);
    }
    public void deleteProduct(Integer store_id,Integer product_id){
        Product product=productRepostory.findAllById(product_id);
        if(product==null){
            throw new ApiException("not found id");
        }

        if (product.getStore().getId() != store_id) {
            throw new ApiException("the store not found");
        }
            productRepostory.delete(product);
    }
    public void buyProduct(Product product, Customer customer, Integer Id, Integer IdCustomer) {
        Product product1 = productRepostory.findAllById(Id);
        if (product1 == null) {
            throw new ApiException("not found Id product");
        }
        Customer customer1 = customerRepostory.findUserById(IdCustomer);
        if (customer1 == null) {
            throw new ApiException("not found Id User");
        }
        if (customer1.getSalary() >= product1.getProductsPrice()) {
            customer1.setSalary(customer1.getSalary() - product1.getProductsPrice() );
            customer1.setPoints(customer1.getPoints() + 0.50);
            customerRepostory.save(customer1);
            productRepostory.save(product1);
        } else {
            throw new ApiException("The balance is insufficient");

        }
    }

    public void assigProductStore(Integer product_id , Integer store_id){
        Product product=productRepostory.findAllById(product_id);
        Store store=storeRepository.findStoreById(store_id);

        if(store == null || product == null){
            throw new ApiException("ID store or product not found");
        }
        if (product.getStore().getId() != store_id) {
            throw new ApiException("the store not found");
        }

        product.setStore(store);
        productRepostory.save(product);
    }
}