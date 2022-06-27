package com.example.servicea.impl;


import com.example.servicea.impl.entity.BalanceChange;
import org.springframework.data.jpa.repository.JpaRepository;

interface BalanceChangeRepository extends JpaRepository<BalanceChange, Integer> {
}
