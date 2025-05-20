package com.tyrdanov.transaction_service.dto;

import java.math.BigDecimal;

import com.tyrdanov.transaction_service.enums.Currency;
import com.tyrdanov.transaction_service.enums.Status;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateTransactionDto {

    BigDecimal amount;

    Currency currency;

    Status status = Status.PENDING;

    String description;

    Long senderId;

    Long receiverId;

    Long categoryId;
    
}
