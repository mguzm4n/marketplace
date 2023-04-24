package com.example.authjava.Data.DTOs;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class ProductDto {
    private Long productId;
    private String name, description, publisher;
    private BigDecimal price;
    private Date uploadedAt;
}
