package com.example.serviceb.impl;

import java.text.DecimalFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceReadService {

    private final BalanceRepository balanceRepository;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public String getBalance() {
        Double balanceAmount = balanceRepository.getBalanceAmount();
        return df.format(balanceAmount);

    }

}
