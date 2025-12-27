package com.rentacar.service;

import com.rentacar.model.Arac;
import com.rentacar.repository.AracRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AracServiceTest {

    @Mock
    private AracRepository aracRepository;

    @InjectMocks
    private AracService aracService;

    @Test
    public void tumAraclariGetir_ShouldReturnList() {
        Arac arac1 = new Arac();
        arac1.setPlaka("34ABC123");
        Arac arac2 = new Arac();
        arac2.setPlaka("35XYZ789");

        when(aracRepository.findAll()).thenReturn(Arrays.asList(arac1, arac2));

        List<Arac> result = aracService.tumAraclariGetir();

        assertEquals(2, result.size());
        verify(aracRepository, times(1)).findAll();
    }

    @Test
    public void aracKaydet_ShouldReturnSavedArac() {
        Arac arac = new Arac();
        arac.setPlaka("34ABC123");

        when(aracRepository.save(any(Arac.class))).thenReturn(arac);

        Arac savedArac = aracService.aracKaydet(arac);

        assertNotNull(savedArac);
        assertEquals("34ABC123", savedArac.getPlaka());
        verify(aracRepository, times(1)).save(arac);
    }

    @Test
    public void aracGetir_ShouldReturnArac_WhenFound() {
        Arac arac = new Arac();
        arac.setId(1);
        arac.setPlaka("34ABC123");

        when(aracRepository.findById(1)).thenReturn(Optional.of(arac));

        Arac result = aracService.aracGetir(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }
}
