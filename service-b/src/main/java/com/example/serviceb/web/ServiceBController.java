package com.example.serviceb.web;

import com.example.serviceb.impl.BalanceChangeService;
import com.example.serviceb.impl.BalanceReadService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("savings/b/balance")
@RequiredArgsConstructor
public class ServiceBController {

    private final BalanceChangeService balanceChangeService;
    private final BalanceReadService balanceReadService;

    @GetMapping
    public ResponseEntity<String> getBalance() {
        String balance = balanceReadService.getBalance();
        return ResponseEntity.ok(balance);
    }

    @PostMapping
    public ResponseEntity<String> postBalance(@RequestBody @Valid BalanceChangeRequest balanceChangeRequest) {
        String balance = balanceChangeService.changeBalance(balanceChangeRequest);
        return ResponseEntity.ok(balance);
    }

}
