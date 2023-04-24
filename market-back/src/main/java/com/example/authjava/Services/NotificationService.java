package com.example.authjava.Services;

import com.example.authjava.Data.Models.NotificationModel;
import com.example.authjava.Repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void notify(Long fromUserId, Long toUserId, NotificationType type) {
        notificationRepository.save(
            new NotificationModel(
                null,
                toUserId,
                fromUserId,
                type.toString(),
                null
            )
        );
    }
}
