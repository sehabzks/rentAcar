package com.rentacar.service;

import com.rentacar.model.Arac;
import com.rentacar.repository.AracRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AracService {

    @Autowired
    private AracRepository aracRepository;

    public List<Arac> tumAraclariGetir() {
        return aracRepository.findAll();
    }

    public Arac aracKaydet(Arac arac) {
        return aracRepository.save(arac);
    }

    public Arac aracGetir(Integer id) {
        return aracRepository.findById(id).orElse(null);
    }

    public List<Arac> aracAra(String plaka) {
        if (plaka == null) {
            return tumAraclariGetir();
        }
        String cleanPlaka = plaka.replaceAll("\\s+", "");
        return aracRepository.searchByPlakaIgnoreSpaces(cleanPlaka);
    }
}
