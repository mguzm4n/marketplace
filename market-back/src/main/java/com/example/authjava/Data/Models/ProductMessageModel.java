package com.example.authjava.Data.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product_messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductMessageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long productId, messageId;
}
