package com.teddy.springbootmall.dao;

import com.teddy.springbootmall.dto.ProductQueryParams;
import com.teddy.springbootmall.dto.ProductRequest;
import com.teddy.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    Integer countProduct(ProductQueryParams productQueryParams);
    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
