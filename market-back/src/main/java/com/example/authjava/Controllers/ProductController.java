package com.example.authjava.Controllers;

import com.example.authjava.Data.DTOs.ProductDto;
import com.example.authjava.Data.DTOs.ProductProjection;
import com.example.authjava.Data.DTOs.ProductRequest;
import com.example.authjava.Data.Models.ProductModel;
import com.example.authjava.Repositories.ProductRepository;
import com.example.authjava.Services.ProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    @PostMapping
    public ResponseEntity<ProductModel> create(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok(productService.create(productRequest));
    }

    @GetMapping
    public ResponseEntity<List<ProductProjection>> findAll() {
        return ResponseEntity.ok(productService.findAllWithPublisherName());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductProjection> findByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.findByProductId(productId));
    }

}
