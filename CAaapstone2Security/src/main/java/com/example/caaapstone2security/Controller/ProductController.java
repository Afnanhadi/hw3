package com.example.caaapstone2security.Controller;

import com.example.caaapstone2security.Api.ApiResponse;
import com.example.caaapstone2security.Model.Customer;
import com.example.caaapstone2security.Model.Product;
import com.example.caaapstone2security.Model.Store;
import com.example.caaapstone2security.Model.User;
import com.example.caaapstone2security.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getAllProduct(){
        return ResponseEntity.status(200).body(productService.getAllProduct());
    }

    @GetMapping("/get-my-product")
    public ResponseEntity getMyProduct(@AuthenticationPrincipal Store store ){
        return ResponseEntity.status(200).body(productService.getMyProduct(store.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid Product product,@AuthenticationPrincipal Store store){
        productService.addProduct(product,store.getId());
        return ResponseEntity.status(200).body(new ApiResponse("add Product"));
    }
    @PutMapping("/update/{IdUser}")
    public ResponseEntity UpdateProduct(@RequestBody @Valid Product product, @PathVariable Integer IdUser , @AuthenticationPrincipal Store store){

        productService.UpdateProduct(product,IdUser,store.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Update Product"));
    }
    @DeleteMapping("/delete/{IdUser}")
    public ResponseEntity deleteProduct(@PathVariable  Integer IdUser,@AuthenticationPrincipal Store store){
        productService.deleteProduct(IdUser,store.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Delete Product"));
    }
    @GetMapping("/buy/{Id}/{IdCustomer}")
    public ResponseEntity buyProduct(Product product, Customer customer, @PathVariable Integer Id, @PathVariable Integer IdCustomer){
        productService.buyProduct(product,customer,Id,IdCustomer);
        return ResponseEntity.status(200).body(new ApiResponse("buy Product"));
    }
    @PutMapping("/{product_id}/assig_coach/{store_id}")
    public ResponseEntity  assigProductStore(@PathVariable Integer product_id , @PathVariable Integer store_id ){
        productService.assigProductStore(product_id,store_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("assig done"));
    }

}