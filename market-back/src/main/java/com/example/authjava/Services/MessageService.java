package com.example.authjava.Services;

import com.example.authjava.Data.DTOs.MessageProjection;
import com.example.authjava.Data.Models.MessageModel;
import com.example.authjava.Data.Models.ProductMessageModel;
import com.example.authjava.Repositories.MessageRepository;
import com.example.authjava.Repositories.ProductMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Array;
import java.util.*;

@Service
@AllArgsConstructor
public class MessageService {
    private final ProductMessageRepository productMessageRepository;
    private final MessageRepository messageRepository;

    @Transactional
    public MessageModel create(Long productId, Long userId, String content) {
        MessageModel message = messageRepository.save(
            new MessageModel(
                userId,
                productId,
                content
            )
        );
        return message;
    }

    public Map<Long, List<MessageProjection>> findAllByProductId(Long productId) {
        var groupedMessages = messageRepository.findAllByProductIdGrouped(productId);
        Map<Long, List<MessageProjection>> senderMessagesMap = new HashMap<>();
        for (var message : groupedMessages) {
            Long sentById = message.getSentBy();
            if (senderMessagesMap.containsKey(message.getSentBy())) {
                senderMessagesMap.get(sentById).add(message);
            } else {
                List<MessageProjection> messages = new ArrayList<>();
                messages.add(message);
                senderMessagesMap.put(sentById, messages);
            }
        }
        return senderMessagesMap;
    }
}
