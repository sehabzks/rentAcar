package com.rentacar.repository;

import com.rentacar.model.Musteri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusteriRepository extends JpaRepository<Musteri, Integer> {
    Musteri findByEmail(String email);
}
