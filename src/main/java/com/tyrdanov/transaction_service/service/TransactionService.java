package com.tyrdanov.transaction_service.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tyrdanov.transaction_service.dto.CreateTransactionDto;
import com.tyrdanov.transaction_service.dto.TransactionDto;
import com.tyrdanov.transaction_service.dto.UpdateTransactionDto;
import com.tyrdanov.transaction_service.exception.ResourceNotFoundException;
import com.tyrdanov.transaction_service.repository.TransactionRepository;
import com.tyrdanov.transaction_service.mapper.TransactionMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;
    private final TransactionMapper mapper;

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
        final var transaction = mapper.toModel(dto);
        final var createdTransaction = repository.save(transaction);

        return mapper.toDto(createdTransaction);
    }

    public TransactionDto update(UpdateTransactionDto dto) {
        final var uuid = dto.getId();
        final var transaction = repository.findById(uuid).orElseThrow(
            () -> new ResourceNotFoundException("Transaction not found")
        );
        
        mapper.update(dto, transaction);

        final var createdTransaction = repository.save(transaction);

        return mapper.toDto(createdTransaction);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

}
