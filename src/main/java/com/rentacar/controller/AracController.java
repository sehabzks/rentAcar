package com.rentacar.controller;

import com.rentacar.model.Arac;
import com.rentacar.service.AracService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/araclar")
@CrossOrigin(origins = "*")
public class AracController {

    @Autowired
    private AracService aracService;

    @GetMapping
    public List<Arac> tumAraclariGetir() {
        return aracService.tumAraclariGetir();
    }

    @GetMapping("/ara")
    public List<Arac> aracAra(@RequestParam String plaka) {
        String normalizedPlaka = plaka.replaceAll("\\s+", "").toUpperCase();
        return aracService.aracAra(normalizedPlaka);
    }

    @PostMapping
    public Arac aracEkle(@RequestBody Arac arac) {
        return aracService.aracKaydet(arac);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Arac> aracGetir(@PathVariable Integer id) {
        Arac arac = aracService.aracGetir(id);
        if (arac != null) {
            return ResponseEntity.ok(arac);
        }
        return ResponseEntity.notFound().build();
    }
}
