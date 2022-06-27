package com.example.serviceb.web;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BalanceChangeRequest {

    @NotNull
    private Double amount; // could be BigDecimal

}
