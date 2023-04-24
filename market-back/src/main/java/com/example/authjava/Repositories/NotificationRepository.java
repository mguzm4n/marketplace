package com.example.authjava.Repositories;

import com.example.authjava.Data.Models.NotificationModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface NotificationRepository extends JpaRepository<NotificationModel, Long> {
}
