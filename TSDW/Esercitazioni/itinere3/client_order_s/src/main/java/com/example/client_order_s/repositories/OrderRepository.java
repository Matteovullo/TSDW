package com.example.client_order_s.repositories;

import org.springframework.data.jpa.repository.*;
import com.example.client_order_s.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
    
}
