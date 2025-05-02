package com.tyrdanov.transaction_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tyrdanov.transaction_service.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
