package com.teddy.springbootmall.service;

import com.teddy.springbootmall.constant.ProductCategory;
import com.teddy.springbootmall.dto.ProductRequest;
import com.teddy.springbootmall.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductCategory category, String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

}
