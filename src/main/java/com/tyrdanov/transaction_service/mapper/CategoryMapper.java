package com.tyrdanov.transaction_service.mapper;

import java.util.List;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import com.tyrdanov.transaction_service.dto.CategoryDto;
import com.tyrdanov.transaction_service.dto.CreateCategoryDto;
import com.tyrdanov.transaction_service.model.Category;
import com.tyrdanov.transaction_service.model.Transaction;

@Mapper
public interface CategoryMapper {

    @Mapping(target = "transactionUuids", source = ".", qualifiedByName = "getTransactionUuids")
    CategoryDto toDto(Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "transactions", ignore = true)
    Category toModel(CreateCategoryDto dto);

    void update(CategoryDto dto, List<Transaction> transactions, @MappingTarget Category category);

    @Named("getTransactionUuids")
    default List<UUID> getTransactionUuids(Category category) {
        return category
                .getTransactions()
                .stream()
                .map(Transaction::getId)
                .toList();
    }

}
