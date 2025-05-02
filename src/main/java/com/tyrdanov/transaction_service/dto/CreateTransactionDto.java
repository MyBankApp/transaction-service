package com.tyrdanov.transaction_service.dto;

import java.math.BigDecimal;

import com.tyrdanov.transaction_service.enums.Currency;
import com.tyrdanov.transaction_service.enums.Status;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateTransactionDto {

    BigDecimal amount;

    Currency currency;

    Status status;

    String description;

    Long categoryId;
    
}
