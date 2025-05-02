package com.tyrdanov.transaction_service.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyrdanov.transaction_service.dto.CreateTransactionDto;
import com.tyrdanov.transaction_service.dto.TransactionDto;
import com.tyrdanov.transaction_service.dto.UpdateTransactionDto;
import com.tyrdanov.transaction_service.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {
    
    private final TransactionService service;

    @GetMapping
    public List<TransactionDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public TransactionDto getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public TransactionDto create(@RequestBody CreateTransactionDto dto) {
        return service.create(dto);
    }

    @PutMapping
    public TransactionDto update(@RequestBody UpdateTransactionDto dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }

}
