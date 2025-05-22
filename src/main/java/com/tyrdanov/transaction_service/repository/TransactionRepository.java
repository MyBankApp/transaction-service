package com.tyrdanov.transaction_service.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tyrdanov.transaction_service.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query("SELECT t FROM Transaction t WHERE t.senderId = :userId OR t.receiverId = :userId")
    List<Transaction> findAllByUserId(@Param("userId") Long userId);

}
