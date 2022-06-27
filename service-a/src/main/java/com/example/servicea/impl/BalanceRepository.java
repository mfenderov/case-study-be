package com.example.servicea.impl;

import com.example.servicea.impl.entity.BalanceChange;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BalanceRepository {

    private final JdbcTemplate jdbcTemplate;
    private final BalanceChangeRepository balanceChangeRepository;

    @Transactional
    public Double changeBalance(BalanceChange balanceChange) {
        balanceChangeRepository.save(balanceChange);

        Double balanceAmount = getBalanceAmount();
        double newBalance = balanceAmount + balanceChange.getAmount();
        jdbcTemplate.update("UPDATE balance SET balance = ?", newBalance);
        return newBalance;
    }

    public Double getBalanceAmount() {
        return jdbcTemplate.queryForObject("SELECT balance FROM balance", Double.class);
    }
}
