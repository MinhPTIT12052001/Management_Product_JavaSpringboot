package com.inn.product.crud_product_management.Respository;

import com.inn.product.crud_product_management.POJO.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
