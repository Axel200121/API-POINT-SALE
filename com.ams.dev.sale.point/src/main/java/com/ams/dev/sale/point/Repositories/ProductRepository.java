package com.ams.dev.sale.point.Repositories;

import com.ams.dev.sale.point.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
