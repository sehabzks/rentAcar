package com.rentacar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentacar.model.Arac;
import com.rentacar.service.AracService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AracController.class)
public class AracControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AracService aracService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void tumAraclariGetir_ShouldReturnList() throws Exception {
        Arac arac = new Arac();
        arac.setPlaka("34ABC123");

        when(aracService.tumAraclariGetir()).thenReturn(Arrays.asList(arac));

        mockMvc.perform(get("/api/araclar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].plaka").value("34ABC123"));
    }

    @Test
    public void aracEkle_ShouldReturnSavedArac() throws Exception {
        Arac arac = new Arac();
        arac.setPlaka("34ABC123");
        arac.setYil(2024);

        when(aracService.aracKaydet(any(Arac.class))).thenReturn(arac);

        mockMvc.perform(post("/api/araclar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(arac)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.plaka").value("34ABC123"));
    }

    @Test
    public void aracAra_ShouldReturnList() throws Exception {
        Arac arac = new Arac();
        arac.setPlaka("34ABC123");

        // The service now strips spaces, so we should mock with the stripped value
        when(aracService.aracAra("34ABC123")).thenReturn(Arrays.asList(arac));

        mockMvc.perform(get("/api/araclar/ara").param("plaka", "34 ABC 123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].plaka").value("34ABC123"));
    }
}
