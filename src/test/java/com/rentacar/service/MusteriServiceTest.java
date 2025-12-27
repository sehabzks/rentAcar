package com.rentacar.service;

import com.rentacar.model.Musteri;
import com.rentacar.repository.MusteriRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MusteriServiceTest {

    @Mock
    private MusteriRepository musteriRepository;

    @InjectMocks
    private MusteriService musteriService;

    @Test
    public void musteriKaydet_Success() {
        Musteri musteri = new Musteri();
        musteri.setEmail("test@test.com");
        musteri.setAd("Test");

        when(musteriRepository.findByEmail("test@test.com")).thenReturn(null);
        when(musteriRepository.save(any(Musteri.class))).thenReturn(musteri);

        Musteri saved = musteriService.musteriKaydet(musteri);

        assertNotNull(saved);
        assertEquals("test@test.com", saved.getEmail());
    }

    @Test
    public void musteriKaydet_DuplicateEmail_ShouldThrowException() {
        Musteri musteri = new Musteri();
        musteri.setEmail("duplicate@test.com");

        Musteri existing = new Musteri();
        existing.setId(1);
        existing.setEmail("duplicate@test.com");

        when(musteriRepository.findByEmail("duplicate@test.com")).thenReturn(existing);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            musteriService.musteriKaydet(musteri);
        });

        assertTrue(exception.getMessage().contains("zaten kullanılıyor"));
        verify(musteriRepository, never()).save(any(Musteri.class));
    }

    @Test
    public void musteriGetir_Success() {
        Musteri musteri = new Musteri();
        musteri.setId(1);

        when(musteriRepository.findById(1)).thenReturn(Optional.of(musteri));

        Musteri result = musteriService.musteriGetir(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    public void musteriGetir_NotFound_ShouldThrowException() {
        when(musteriRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            musteriService.musteriGetir(99);
        });
    }

    @Test
    public void musteriSil_Success() {
        when(musteriRepository.existsById(1)).thenReturn(true);

        assertDoesNotThrow(() -> musteriService.musteriSil(1));

        verify(musteriRepository, times(1)).deleteById(1);
    }
}
