package com.rentacar.repository;

import com.rentacar.model.Sube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubeRepository extends JpaRepository<Sube, Integer> {
}
