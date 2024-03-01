package com.example.magazzon;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MagazzonProductsRepository extends JpaRepository<MagazzonProductsModel, Long>{
    List<MagazzonProductsModel> findByQuantityGreaterThan(Integer quantity);
}
