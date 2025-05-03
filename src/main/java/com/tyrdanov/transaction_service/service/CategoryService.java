package com.tyrdanov.transaction_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tyrdanov.transaction_service.dto.CategoryDto;
import com.tyrdanov.transaction_service.dto.CreateCategoryDto;
import com.tyrdanov.transaction_service.exception.ResourceNotFoundException;
import com.tyrdanov.transaction_service.mapper.CategoryMapper;
import com.tyrdanov.transaction_service.repository.CategoryRepository;
import com.tyrdanov.transaction_service.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryMapper mapper;
    private final CategoryRepository repository;
    private final TransactionRepository transactionRepository;

    public List<CategoryDto> getAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public CategoryDto getById(Long id) {
        final var category = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Category not found")
        );

        return mapper.toDto(category);
    }

    public CategoryDto create(CreateCategoryDto dto) {
        final var category = mapper.toModel(dto);
        final var createdCategory = repository.save(category);

        return mapper.toDto(createdCategory);
    }

    public CategoryDto update(CategoryDto dto) {
        final var id = dto.getId();
        final var uuids = dto.getTransactionUuids();
        final var category = repository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Category not found")
        );
        final var transactions = transactionRepository.findAllById(uuids);

        mapper.update(dto, transactions, category);

        final var updatedCategory = repository.save(category);

        return mapper.toDto(updatedCategory);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
