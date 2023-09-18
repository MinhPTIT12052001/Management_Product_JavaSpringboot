package com.inn.product.crud_product_management.service;

import com.inn.product.crud_product_management.POJO.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProductService {
    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap);
    public ResponseEntity<List<Product>> getAllProduct();
    public ResponseEntity<String> updateProduct(Map<String,String> requestMap);
    public ResponseEntity<String> deleteProduct(int productId);
}
