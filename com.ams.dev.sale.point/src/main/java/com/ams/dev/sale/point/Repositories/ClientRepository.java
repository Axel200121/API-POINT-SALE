package com.ams.dev.sale.point.Repositories;

import com.ams.dev.sale.point.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,String>{

    Optional<Client> findByEmail(String email);

}
