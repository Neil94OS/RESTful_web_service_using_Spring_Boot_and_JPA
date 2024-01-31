package com.example.neilassignment2af.repositories;

import com.example.neilassignment2af.entities.Property;
import com.example.neilassignment2af.entities.Tenant;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Integer> {
    List<Tenant> findAll();
    Optional<Tenant> findByTenantId(int id);
    List<Tenant> findAllByProperty_PropertyId(int id);

    @Modifying
    @Transactional
    @Query("update Tenant t set t.property = :property where t.tenantId = :id")
    int moveTenant(@Param("id") int id, @Param("property") Optional<Property> property);
}
