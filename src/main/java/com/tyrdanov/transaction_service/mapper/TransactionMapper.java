package com.tyrdanov.transaction_service.mapper;

import java.time.LocalDateTime;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.tyrdanov.transaction_service.dto.CreateTransactionDto;
import com.tyrdanov.transaction_service.dto.TransactionDto;
import com.tyrdanov.transaction_service.dto.UpdateTransactionDto;
import com.tyrdanov.transaction_service.model.Transaction;

@Mapper
public interface TransactionMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", source = ".", qualifiedByName = "getCreatedAt")
    Transaction toModel(CreateTransactionDto dto);

    TransactionDto toDto(Transaction transaction);

    @Mapping(target = "createdAt", ignore = true)
    void update(UpdateTransactionDto dto, @MappingTarget Transaction transaction);

    @Named("getCreatedAt")
    default LocalDateTime getCreatedAt(CreateTransactionDto dto) {
        return LocalDateTime.now();
    }
}
