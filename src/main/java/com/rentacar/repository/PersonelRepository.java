package com.rentacar.repository;

import com.rentacar.model.Personel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonelRepository extends JpaRepository<Personel, Integer> {
    Personel findByKullaniciAdi(String kullaniciAdi);
}
