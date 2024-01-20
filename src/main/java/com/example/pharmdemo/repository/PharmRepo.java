package com.example.pharmdemo.repository;


import com.example.pharmdemo.models.Drugs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PharmRepo extends JpaRepository<Drugs, Long> {

    Optional<Drugs> findById(Long id);

    Optional<Drugs> findByName(String name);
}
