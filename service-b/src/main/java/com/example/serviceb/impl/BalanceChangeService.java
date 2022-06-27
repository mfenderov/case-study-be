package com.example.serviceb.impl;

import com.example.serviceb.impl.entity.BalanceChange;
import com.example.serviceb.web.BalanceChangeRequest;
import java.text.DecimalFormat;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceChangeService {

    private final BalanceRepository balanceRepository;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Transactional
    public String changeBalance(BalanceChangeRequest balanceChangeRequest) {
        BalanceChange balanceChange = BalanceChange.builder()
                .amount(balanceChangeRequest.getAmount())
                .build();
        Double changedBalance = balanceRepository.changeBalance(balanceChange);
        return df.format(changedBalance);
    }

}
