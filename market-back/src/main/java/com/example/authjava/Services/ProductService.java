package com.example.authjava.Services;

import com.example.authjava.Data.DTOs.ProductDto;
import com.example.authjava.Data.DTOs.ProductProjection;
import com.example.authjava.Data.DTOs.ProductRequest;
import com.example.authjava.Data.Models.ProductModel;
import com.example.authjava.Repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public ProductModel create(ProductRequest productRequest) {
        ProductModel productModel = new ProductModel(
            productRequest.getName(),
            productRequest.getDescription(),
            productRequest.getUserId(),
            productRequest.getPrice()
        );
        productRepository.save(productModel);
        return productModel;
    }


    public ProductProjection findByProductId(Long productId) {
        return productRepository.findOneWithPublisherName(productId);
    }
    public List<ProductProjection> findAllWithPublisherName() {
        return productRepository.findAllWithPublisherName();
    }
}
