package com.ams.dev.sale.point.Repositories;

import com.ams.dev.sale.point.Entities.Category;
import com.ams.dev.sale.point.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,String> {
    Optional<Category> findByName(String name);
}
