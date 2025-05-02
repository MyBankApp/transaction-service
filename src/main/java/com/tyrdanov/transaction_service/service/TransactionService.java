package com.tyrdanov.transaction_service.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tyrdanov.transaction_service.dto.CreateTransactionDto;
import com.tyrdanov.transaction_service.dto.TransactionDto;
import com.tyrdanov.transaction_service.dto.UpdateTransactionDto;
import com.tyrdanov.transaction_service.exception.ResourceNotFoundException;
import com.tyrdanov.transaction_service.repository.CategoryRepository;
import com.tyrdanov.transaction_service.repository.TransactionRepository;
import com.tyrdanov.transaction_service.mapper.TransactionMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionMapper mapper;
    private final TransactionRepository repository;
    private final CategoryRepository categoryRepository;

    public List<TransactionDto> getAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public TransactionDto getById(UUID id) {
        final var transaction = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Transaction not found")
        );

        return mapper.toDto(transaction);
    }

    public TransactionDto create(CreateTransactionDto dto) {
        final var categoryId = dto.getCategoryId();
        final var category = categoryRepository.findById(categoryId).orElseThrow(
            () -> new ResourceNotFoundException("Category not found")
        );
        final var transaction = mapper.toModel(dto, category);

        transaction.setCategory(category);
        
        final var createdTransaction = repository.save(transaction);

        return mapper.toDto(createdTransaction);
    }

    public TransactionDto update(UpdateTransactionDto dto) {
        final var uuid = dto.getId();
        final var categoryId = dto.getCategoryId();
        final var transaction = repository.findById(uuid).orElseThrow(
            () -> new ResourceNotFoundException("Transaction not found")
        );
        final var category = categoryRepository.findById(categoryId).orElseThrow(
            () -> new ResourceNotFoundException("Category not found")
        );
        
        mapper.update(dto, category, transaction);

        final var updatedTransaction = repository.save(transaction);

        return mapper.toDto(updatedTransaction);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

}
