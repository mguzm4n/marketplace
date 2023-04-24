package com.example.authjava.Services;

import com.example.authjava.Repositories.MessageRepository;
import com.example.authjava.Repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ReactionService {
    private final NotificationService notificationService;
    private final MessageRepository messageRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void updateReaction(Long messageId, Long newReactionId, Long whoUpdatesId) {
        var msg = messageRepository.findById(messageId).orElseThrow();
        var product = productRepository.findById(msg.getProductId()).orElseThrow();

        if (whoUpdatesId != product.getOwnerId()) {
            // Err: Can't update obviously other's people product messages.
        }

        NotificationType type;
        if (msg.getReactionId() == null) {
            type = NotificationType.REACTION_SELECTED;
        } else {
            type = NotificationType.REACTION_UPDATED;
        }

        messageRepository.updateMessageReaction(messageId, newReactionId);
        notificationService.notify(whoUpdatesId, msg.getSentBy(), type);
    }

    @Transactional
    public void deleteReaction(Long messageId, Long whoDeletesId) {
        var msg = messageRepository.findById(messageId).orElseThrow();
        messageRepository.updateMessageReaction(messageId, null);
        notificationService.notify(whoDeletesId, msg.getSentBy(), NotificationType.REACTION_UPDATED);
    }
}
