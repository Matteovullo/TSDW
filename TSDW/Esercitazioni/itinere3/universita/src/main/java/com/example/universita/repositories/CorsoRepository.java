package com.example.universita.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.universita.models.Corso;

public interface CorsoRepository extends JpaRepository<Corso, Long>{
    
}
