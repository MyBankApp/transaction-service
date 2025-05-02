package com.tyrdanov.transaction_service.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.tyrdanov.transaction_service.enums.Currency;
import com.tyrdanov.transaction_service.enums.Status;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionDto {

    UUID id;

    BigDecimal amount;

    Currency currency;

    Status status;

    LocalDateTime createdAt;

    String description;

    Long categoryId;
    
}
