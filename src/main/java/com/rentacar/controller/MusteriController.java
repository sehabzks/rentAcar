package com.rentacar.controller;

import com.rentacar.model.Musteri;
import com.rentacar.service.MusteriService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/musteriler")
public class MusteriController {

    private final MusteriService musteriService;

    public MusteriController(MusteriService musteriService) {
        this.musteriService = musteriService;
    }

    @GetMapping
    public List<Musteri> tumMusterileriGetir() {
        return musteriService.tumMusterileriGetir();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Musteri> musteriGetir(@PathVariable Integer id) {
        try {
            Musteri musteri = musteriService.musteriGetir(id);
            return ResponseEntity.ok(musteri);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> musteriEkle(@RequestBody Musteri musteri) {
        try {
            Musteri yeniMusteri = musteriService.musteriKaydet(musteri);
            return ResponseEntity.status(HttpStatus.CREATED).body(yeniMusteri);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> musteriSil(@PathVariable Integer id) {
        try {
            musteriService.musteriSil(id);
            return ResponseEntity.ok("Müşteri silindi.");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
