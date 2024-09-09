package com.ams.dev.sale.point.Repositories;

import com.ams.dev.sale.point.Entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale,String> {
}
