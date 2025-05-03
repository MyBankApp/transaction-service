package com.tyrdanov.transaction_service.dto;

import java.math.BigDecimal;
import java.util.UUID;

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
public class UpdateTransactionDto {
    
    UUID id;

    BigDecimal amount;

    Currency currency;

    Status status;

    String description;

    Long categoryId;

}
