package com.inn.product.crud_product_management.serviceImpl;

import com.inn.product.crud_product_management.POJO.Product;
import com.inn.product.crud_product_management.Respository.ProductRepository;
import com.inn.product.crud_product_management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
        try {
            if(validateAddNewProduct(requestMap,false)){
                productRepository.save(getProductFromMap(requestMap,false));
                return new ResponseEntity<>("{\"message\":\"" + "Product Added Successfully." + "\"}", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("{\"message\":\"" + "Invalid Data." + "\"}", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"" + "Something Went Wrong at product Service." + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private boolean validateAddNewProduct(Map<String,String> requestMap, Boolean validateId){
        if (requestMap.containsKey("name") && requestMap.containsKey("price") && requestMap.containsKey("description")){
            if(validateId && requestMap.containsKey("productId")){
                return true;
            }else if(!validateId){
                return true;
            }
            return true;
        }
        return false;
    }
    private Product getProductFromMap(Map<String,String> requestMap, boolean isAdd){
        Product product = new Product();
        if(isAdd) {
            product.setId(Integer.valueOf(requestMap.get("productId")));
        }
        product.setName(requestMap.get("name"));
        product.setPrice(Float.valueOf(requestMap.get("price")));
        product.setDescription(requestMap.get("description"));
        return product;
    }
    @Override
    public ResponseEntity<List<Product>> getAllProduct() {
        try {
            return new ResponseEntity<>(productRepository.findAll(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @Override
    public ResponseEntity<String> updateProduct(Map<String,String> requestMap){
        try {
            if(validateAddNewProduct(requestMap,true)){
                Optional optional = productRepository.findById(Integer.valueOf(requestMap.get("productId")));
                if(!optional.isEmpty()){
                     productRepository.save(getProductFromMap(requestMap,true));
                    return new ResponseEntity<>("{\"message\":\"" + "Updated data successfully!!!" + "\"}", HttpStatus.OK);

                }else {
                    return new ResponseEntity<>("{\"message\":\"" + "Data not Exist!!!" + "\"}", HttpStatus.BAD_REQUEST);
                }
            }else{
                return new ResponseEntity<>("{\"message\":\"" + "Invalid Data." + "\"}", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"" + "Something Went Wrong at product Service." + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @Override
    public ResponseEntity<String> deleteProduct(int productId){
        try {
            if (productId > 0){
                productRepository.deleteById(productId);
                return new ResponseEntity<>("{\"message\":\"" + "Deleted Product Successfully." + "\" }", HttpStatus.OK);

            }else {
                return new ResponseEntity<>("{\"message\":\"" + "Invalid Product Id." + "\" }", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"" + "Something Went Wrong." + "\" }", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
