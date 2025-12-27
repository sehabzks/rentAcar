package com.rentacar.repository;

import com.rentacar.model.Arac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AracRepository extends JpaRepository<Arac, Integer> {
    @org.springframework.data.jpa.repository.Query("SELECT a FROM Arac a WHERE LOWER(REPLACE(a.plaka, ' ', '')) LIKE LOWER(CONCAT('%', :plaka, '%'))")
    List<Arac> searchByPlakaIgnoreSpaces(@org.springframework.data.repository.query.Param("plaka") String plaka);
}
