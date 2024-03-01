package com.example.magazzino.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.magazzino.models.Prodotti;

public interface ProdottiRepository extends JpaRepository<Prodotti, Long>{
    
}
