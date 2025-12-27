package com.rentacar.service;

import com.rentacar.model.Musteri;
import com.rentacar.repository.MusteriRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusteriService {

    private final MusteriRepository musteriRepository;

    public MusteriService(MusteriRepository musteriRepository) {
        this.musteriRepository = musteriRepository;
    }

    public List<Musteri> tumMusterileriGetir() {
        return musteriRepository.findAll();
    }

    public Musteri musteriGetir(Integer id) {
        return musteriRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Müşteri bulunamadı: " + id));
    }

    public Musteri musteriKaydet(Musteri musteri) {
        // Basit validasyon: Aynı email ile kayıt var mı?
        if (musteri.getEmail() != null) {
            Musteri existing = musteriRepository.findByEmail(musteri.getEmail());
            if (existing != null && (musteri.getId() == null || !existing.getId().equals(musteri.getId()))) {
                throw new RuntimeException("Bu email adresi zaten kullanılıyor: " + musteri.getEmail());
            }
        }
        return musteriRepository.save(musteri);
    }

    public void musteriSil(Integer id) {
        if (!musteriRepository.existsById(id)) {
            throw new RuntimeException("Silinecek müşteri bulunamadı: " + id);
        }
        musteriRepository.deleteById(id);
    }
}
