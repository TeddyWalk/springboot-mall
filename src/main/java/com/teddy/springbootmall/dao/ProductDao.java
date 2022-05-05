package com.teddy.springbootmall.dao;

import com.teddy.springbootmall.constant.ProductCategory;
import com.teddy.springbootmall.dto.ProductRequest;
import com.teddy.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductCategory category, String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
