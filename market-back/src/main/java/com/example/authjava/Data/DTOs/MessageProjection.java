package com.example.authjava.Data.DTOs;

import java.sql.Timestamp;

public interface MessageProjection {
    String getContent();
    Timestamp getSentAt();
    Long getSentBy();
    String getName();
}
