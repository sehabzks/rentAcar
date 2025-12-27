package com.rentacar.controller;

import com.rentacar.model.Musteri;
import com.rentacar.model.Personel;
import com.rentacar.repository.MusteriRepository;
import com.rentacar.repository.PersonelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private PersonelRepository personelRepository;

    @Autowired
    private MusteriRepository musteriRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String usernameOrEmail = credentials.get("username");
        String password = credentials.get("password");

        // 1. Check Personel (Admin)
        Personel personel = personelRepository.findByKullaniciAdi(usernameOrEmail);
        if (personel != null && personel.getSifre().equals(password)) {
            Map<String, Object> response = new HashMap<>();
            response.put("role", "ADMIN");
            response.put("name", personel.getAd() + " " + personel.getSoyad());
            return ResponseEntity.ok(response);
        }

        // 2. Check Musteri (User)
        Musteri musteri = musteriRepository.findByEmail(usernameOrEmail);
        if (musteri != null && musteri.getSifre().equals(password)) {
            Map<String, Object> response = new HashMap<>();
            response.put("role", "USER");
            response.put("name", musteri.getAd() + " " + musteri.getSoyad());
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).body("Geçersiz kullanıcı adı veya şifre");
    }
}
