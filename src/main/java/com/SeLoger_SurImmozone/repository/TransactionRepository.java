package com.SeLoger_SurImmozone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SeLoger_SurImmozone.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
