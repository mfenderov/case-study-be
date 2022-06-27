package com.example.serviceb.web;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ServiceABalanceChangeIntegrationTest extends IntegrationBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testBalanceChange() throws Exception {
        getBalance()
                .andExpect(content().string("0.00"));

        changeBalance(100)
                .andExpect(content().string("100.00"));

        getBalance().andExpect(content().string("100.00"));

        changeBalance(122.22)
                .andExpect(content().string("222.22"));
        changeBalance(77.78)
                .andExpect(content().string("300.00"));
        getBalance()
                .andExpect(content().string("300.00"));

        changeBalance(0.00)
                .andExpect(content().string("300.00"));
        getBalance()
                .andExpect(content().string("300.00"));

        changeBalance(-150.22)
                .andExpect(content().string("149.78"));
        getBalance()
                .andExpect(content().string("149.78"));

        changeBalance(-149.78)
                .andExpect(content().string("0.00"));
        getBalance()
                .andExpect(content().string("0.00"));

        changeBalance(-123.45)
                .andExpect(content().string("-123.45"));
        getBalance()
                .andExpect(content().string("-123.45"));
    }


    private ResultActions getBalance() throws Exception {
        return mockMvc.perform(get("/savings/a/balance"))
                .andExpect(status().isOk());
    }

    private ResultActions changeBalance(double amount) throws Exception {
        String payload = String.format("{\"amount\":\"%s\"}", amount);
        return mockMvc.perform(post("/savings/a/balance").contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk());
    }
}
