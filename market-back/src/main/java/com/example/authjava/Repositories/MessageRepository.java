package com.example.authjava.Repositories;


import com.example.authjava.Data.DTOs.MessageProjection;
import com.example.authjava.Data.Models.MessageModel;
import org.aspectj.bridge.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageModel, Long> {
    List<MessageModel> findAllByProductId(Long productId);

    @Query(
        nativeQuery = true,
        value = "SELECT m.content as content, m.sent_at as sentAt, m.sent_by as sentBy, u.first_name || ' ' || u.last_name as name " +
            "FROM messages as m, users as u " +
            "WHERE m.product_id=:productId " +
            "AND m.sent_by=u.id " +
            "ORDER BY m.sent_at;"
    )
    List<MessageProjection> findAllByProductIdGrouped(@Param("productId") Long productId);

    @Query(
        nativeQuery = true,
        value = "UPDATE messages m SET reaction_id=:reactionId WHERE m.id=:messageId"
    )
    @Modifying
    int updateMessageReaction(@Param("messageId") Long messageId, @Param("reactionId") Long reactionId);
}
