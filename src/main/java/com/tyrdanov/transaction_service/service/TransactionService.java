package com.tyrdanov.transaction_service.service;

import java.util.List;
import java.util.UUID;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
@CacheConfig(cacheNames = "transactions")
public class TransactionService {

    private final TransactionMapper mapper;
    private final TransactionRepository repository;
    private final CategoryRepository categoryRepository;

    @Cacheable(cacheNames = "allTransactions")
    public List<TransactionDto> getAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::toTransactionDto)
                .toList();
    }

    @Cacheable(key = "#id")
    public TransactionDto getById(UUID id) {
        final var transaction = repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

        return mapper.toTransactionDto(transaction);
    }

    @Caching(put = @CachePut(key = "#result.id"), evict = @CacheEvict(cacheNames = "allTransactions", allEntries = true))
    public TransactionDto create(CreateTransactionDto dto) {
        final var categoryId = dto.getCategoryId();
        final var category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        final var transaction = mapper.toModelFromDto(dto, category);

        transaction.setCategory(category);

        final var createdTransaction = repository.save(transaction);

        return mapper.toTransactionDto(createdTransaction);
    }

    @Caching(put = @CachePut(key = "#dto.id"), evict = @CacheEvict(cacheNames = "allTransactions", allEntries = true))
    public TransactionDto update(UpdateTransactionDto dto) {
        final var uuid = dto.getId();
        final var categoryId = dto.getCategoryId();
        final var transaction = repository
                .findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));
        final var category = categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        mapper.update(dto, category, transaction);

        final var updatedTransaction = repository.save(transaction);

        return mapper.toTransactionDto(updatedTransaction);
    }

    @Caching(evict = {
            @CacheEvict(key = "#id"),
            @CacheEvict(cacheNames = "allTransactions", allEntries = true)
    })
    public void delete(UUID id) {
        repository.deleteById(id);
    }

}
