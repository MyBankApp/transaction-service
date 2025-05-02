package com.tyrdanov.transaction_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyrdanov.transaction_service.dto.CategoryDto;
import com.tyrdanov.transaction_service.dto.CreateCategoryDto;
import com.tyrdanov.transaction_service.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {
    
    private final CategoryService service;

    @GetMapping
    public List<CategoryDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CategoryDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public CategoryDto create(@RequestBody CreateCategoryDto dto) {
        return service.create(dto);
    }

    @PutMapping
    public CategoryDto update(@RequestBody CategoryDto dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
