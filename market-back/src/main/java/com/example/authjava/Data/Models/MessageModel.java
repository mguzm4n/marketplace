package com.example.authjava.Data.Models;


import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class MessageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NonNull
    Long sentBy, productId;

    @NonNull
    String content;

    @Nullable
    Long reactionId;

    @Column(insertable = false)
    Timestamp sentAt;
}
