package com.example.authjava.Repositories;

import com.example.authjava.Data.DTOs.ProductDto;
import com.example.authjava.Data.DTOs.ProductProjection;
import com.example.authjava.Data.Models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
    @Query(
        nativeQuery=true,
        value = "SELECT p.id as id, p.name as name, p.description as description, p.uploaded_at as uploadedAt, p.price as price, u.first_name || ' '  || u.last_name as publisher, u.id as publisherId " +
            "FROM products p, users u " +
            "WHERE p.owner_id = u.id;"
    )
    List<ProductProjection> findAllWithPublisherName();

    @Query(
        nativeQuery=true,
        value = "SELECT p.id as id, p.name as name, p.description as description, p.uploaded_at as uploadedAt, p.price as price, u.first_name || ' '  || u.last_name as publisher, u.id as publisherId " +
            "FROM products p, users u " +
            "WHERE p.id = :productId AND p.owner_id = u.id;"
    )
    ProductProjection findOneWithPublisherName(@Param("productId") Long productId);
}
