package com.example.authjava.Repositories;

import com.example.authjava.Data.Models.ProductMessageModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductMessageRepository extends CrudRepository<ProductMessageModel, Long> {
    @Query(
        nativeQuery = true,
        value = "INSERT INTO product_messages(product_id, message_id) VALUES (:productId, :messageId);"
    )
    void save(@Param(value = "productId") Long productId, @Param(value = "messageId") Long messageId);
}
