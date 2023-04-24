package com.example.authjava.Data.Models;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

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

    @Column(insertable = false)
    Timestamp sentAt;
}
