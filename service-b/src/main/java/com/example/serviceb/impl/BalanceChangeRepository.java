package com.example.serviceb.impl;


import com.example.serviceb.impl.entity.BalanceChange;
import org.springframework.data.jpa.repository.JpaRepository;

interface BalanceChangeRepository extends JpaRepository<BalanceChange, Integer> {
}
