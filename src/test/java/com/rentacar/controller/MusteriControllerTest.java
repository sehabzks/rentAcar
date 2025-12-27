package com.rentacar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentacar.model.Musteri;
import com.rentacar.service.MusteriService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MusteriController.class)
public class MusteriControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MusteriService musteriService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void tumMusterileriGetir_ReturnsOk() throws Exception {
        Musteri m1 = new Musteri();
        m1.setAd("Ali");
        Musteri m2 = new Musteri();
        m2.setAd("Veli");

        when(musteriService.tumMusterileriGetir()).thenReturn(Arrays.asList(m1, m2));

        mockMvc.perform(get("/api/musteriler"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void musteriGetir_ReturnsOk() throws Exception {
        Musteri m = new Musteri();
        m.setId(1);
        m.setAd("Ayşe");

        when(musteriService.musteriGetir(1)).thenReturn(m);

        mockMvc.perform(get("/api/musteriler/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ad").value("Ayşe"));
    }

    @Test
    public void musteriEkle_ReturnsCreated() throws Exception {
        Musteri m = new Musteri();
        m.setAd("Ahmet");
        m.setEmail("ahmet@test.com");

        when(musteriService.musteriKaydet(any(Musteri.class))).thenReturn(m);

        mockMvc.perform(post("/api/musteriler")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(m)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.ad").value("Ahmet"));
    }

    @Test
    public void musteriGetir_NotFound_Returns404() throws Exception {
        when(musteriService.musteriGetir(99)).thenThrow(new RuntimeException("Bulunamadı"));

        mockMvc.perform(get("/api/musteriler/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void musteriSil_Success_ReturnsOk() throws Exception {
        mockMvc.perform(delete("/api/musteriler/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Müşteri silindi."));
    }
}
