package com.tyrdanov.transaction_service.mapper;

import java.time.LocalDateTime;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.tyrdanov.transaction_service.dto.CreateTransactionDto;
import com.tyrdanov.transaction_service.dto.TransactionDto;
import com.tyrdanov.transaction_service.dto.UpdateTransactionDto;
import com.tyrdanov.transaction_service.model.Category;
import com.tyrdanov.transaction_service.model.Transaction;

@Mapper
public interface TransactionMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", source = "dto", qualifiedByName = "getCreatedAt")
    @Mapping(target = "amount", source = "dto.amount")
    @Mapping(target = "currency", source = "dto.currency")
    @Mapping(target = "status", source = "dto.status")
    @Mapping(target = "description", source = "dto.description")
    @Mapping(target = "category", source = "category")
    Transaction toModel(CreateTransactionDto dto, Category category);

    @Mapping(target = "categoryId", source = ".", qualifiedByName = "getCategoryId")
    TransactionDto toDto(Transaction transaction);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "transaction.id", source = "dto.id")
    void update(UpdateTransactionDto dto, Category category, @MappingTarget Transaction transaction);

    @Named("getCategoryId")
    default Long getCategoryId(Transaction transaction) {
        return transaction.getCategory().getId();
    }

    @Named("getCreatedAt")
    default LocalDateTime getCreatedAt(CreateTransactionDto dto) {
        return LocalDateTime.now();
    }
}
