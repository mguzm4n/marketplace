package com.example.authjava.Data.DTOs;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public interface ProductProjection {
    Long getId();
    String getName();
    String getDescription();
    Timestamp getUploadedAt();
    BigDecimal getPrice();
    String getPublisher();
    String getPublisherId();
}
