package com.rentacar.repository;

import com.rentacar.model.Sinif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SinifRepository extends JpaRepository<Sinif, Integer> {
}
