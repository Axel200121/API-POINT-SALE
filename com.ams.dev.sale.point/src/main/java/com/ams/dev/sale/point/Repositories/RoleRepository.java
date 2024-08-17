package com.ams.dev.sale.point.Repositories;

import com.ams.dev.sale.point.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,String> {

    Optional<Role> findByName(String name);

}
