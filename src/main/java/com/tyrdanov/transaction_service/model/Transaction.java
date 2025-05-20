package com.tyrdanov.transaction_service.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.tyrdanov.transaction_service.enums.Currency;
import com.tyrdanov.transaction_service.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id; 

    @Column(columnDefinition = "NUMERIC")
    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    Currency currency;

    @Enumerated(EnumType.STRING)
    Status status = Status.PENDING;

    @CreationTimestamp
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Column(columnDefinition = "TEXT")
    String description;

    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    Category category;

    @Column(name = "sender_id")
    Long senderId;

    @Column(name = "receiver_id")
    Long receiverId;
}
