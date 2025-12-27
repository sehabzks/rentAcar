package com.rentacar.repository;

import com.rentacar.model.Kiralama;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KiralamaRepository extends JpaRepository<Kiralama, Integer> {
}
