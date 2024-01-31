package com.example.neilassignment2af.repositories;

import com.example.neilassignment2af.dao.dto.PropertyAndTenants;
import com.example.neilassignment2af.entities.Property;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {
    List<Property> findAll();

    @Query(value="SELECT p FROM Property p where SIZE(p.tenants) < p.propertyCapacity")
    List<Property> findAllPropertiesWithTenantSpace();

    @Query(value ="select new com.example.neilassignment2af.dao.dto.PropertyAndTenants(p , size(p.tenants)) from Property p where p.propertyId=:id")
    Optional<PropertyAndTenants> findPropertyAndTenants(@Param("id") int propertyId);

    @Query(value ="SELECT SUM(p.propertyCost) from Property p where p.propertyCapacity = SIZE(p.tenants)")
    int findTotalRentOfOccupiedProperties();

    @Query("SELECT p FROM Property p WHERE SIZE(p.tenants) = 0")
    List<Property> findAllEmptyProperties();

    @Modifying
    @Transactional
    @Query("update Property p set p.propertyCost = :cost where p.propertyId = :id")
    int updateRent(@Param("id") int id, @Param("cost") @NotNull(message = "Property cost cannot be null") @Min(value = 0, message = ("The cost cannot be below 0.")) Integer cost);
}
