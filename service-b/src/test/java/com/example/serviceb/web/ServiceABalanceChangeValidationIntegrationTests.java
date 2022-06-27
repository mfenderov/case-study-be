package com.example.serviceb.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ServiceABalanceChangeValidationIntegrationTests extends IntegrationBaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testBalanceChangePositive() throws Exception {
        mockMvc.perform(post("/savings/a/balance").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"amount\":\"100\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testBalanceChangeDecimal() throws Exception {
        mockMvc.perform(post("/savings/a/balance").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"amount\":\"100.00\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testBalanceChangeNegative() throws Exception {
        mockMvc.perform(post("/savings/a/balance").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"amount\":\"-100\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testBalanceChangeEmpty() throws Exception {
        mockMvc.perform(post("/savings/a/balance").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"amount\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testBalanceChangeNull() throws Exception {
        mockMvc.perform(post("/savings/a/balance").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"amount\":null}"))
                .andExpect(status().isBadRequest());
    }


    @Test
    void testBalanceChangeInvalidLetters() throws Exception {
        mockMvc.perform(post("/savings/a/balance").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"amount\":\"abc\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testBalanceChangeInvalidNotANumber() throws Exception {
        mockMvc.perform(post("/savings/a/balance").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"amount\":\"100.00.00\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testBalanceGET() throws Exception {
        mockMvc.perform(get("/savings/a/balance"))
                .andExpect(status().isOk());
    }


}
