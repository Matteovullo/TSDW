package com.example.client_order_s.repositories;

import org.springframework.data.jpa.repository.*;
import com.example.client_order_s.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
    
}