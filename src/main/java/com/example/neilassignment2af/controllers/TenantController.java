package com.example.neilassignment2af.controllers;

import com.example.neilassignment2af.controllers.dtos.NewPropertyForTenantDTO;
import com.example.neilassignment2af.controllers.dtos.NewTenantDTO;
import com.example.neilassignment2af.controllers.handlers.ConflictException;
import com.example.neilassignment2af.controllers.handlers.ResourceNotFoundException;
import com.example.neilassignment2af.entities.Property;
import com.example.neilassignment2af.entities.Tenant;
import com.example.neilassignment2af.repositories.PropertyRepository;
import com.example.neilassignment2af.repositories.TenantRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tenants")
public class TenantController {
    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    // http://localhost:8080/tenants returns 200 OK with list of objects

    // staff@gmail.com, manager@gmail.com, Secret123
    @GetMapping({"/",""})
    List<Tenant> findAll(){
        return tenantRepository.findAll();
    }

    // http://localhost:8080/tenants/6 returns 200 OK with object
    // http://localhost:8080/tenants/111 returns 404 NOT FOUND with error message

    // staff@gmail.com, manager@gmail.com, Secret123
    @GetMapping("/{id}")
    Tenant findByTenantId(@PathVariable("id") int tenantId){
        Optional<Tenant> tenantOptional = tenantRepository.findByTenantId(tenantId);
        if(tenantOptional.isPresent())
            return tenantOptional.get();
        throw new ResourceNotFoundException("Tenant with ID "+tenantId + " is not present");
    }

    // http://localhost:8080/tenants/property/1 returns 200 OK with list of objects
    // http://localhost:8080/tenants/property/111 returns 404 NOT FOUND with error message

    // staff@gmail.com, manager@gmail.com, Secret123
    @GetMapping("/property/{id}")
    List <Tenant> findAllByProperty_PropertyId(@PathVariable("id") int propertyId){
        List<Tenant> tenants = tenantRepository.findAllByProperty_PropertyId(propertyId);
        if(tenants.isEmpty())
            throw new ResourceNotFoundException("No tenants in property with ID " +propertyId);
        return tenants;
    }

    // http://localhost:8080/tenants/2 returns 200 OK
    // http://localhost:8080/tenants/222  returns 404 NOT FOUND with error message

    // staff@gmail.com, manager@gmail.com, Secret123
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable("id") int tenantId){
        if(tenantRepository.existsById(tenantId))
            tenantRepository.deleteById(tenantId);
        else
            throw new ResourceNotFoundException("Tenant with ID "+tenantId + " is not present");
    }

    // http://localhost:8080/tenants

    /* Json data {
        "tenantName": "Brian Malone",
        "tenantEmail": "newtenant@gmail.com",
        "tenantPhoneNumber": "0872214689",
        "propertyId": 1
    }*/

    // staff@gmail.com, manager@gmail.com, Secret123
    @PostMapping({"/",""})
    Tenant addNewTenant(@Valid  @RequestBody NewTenantDTO newTenantDTO){
        List<Property> list = propertyRepository.findAllPropertiesWithTenantSpace();

        if (list.contains(propertyRepository.findById(newTenantDTO.propertyId()).get())) {
            return tenantRepository.save(new Tenant(newTenantDTO.tenantName(), newTenantDTO.tenantEmail(), newTenantDTO.tenantPhoneNumber(), propertyRepository.findById(newTenantDTO.propertyId()).get()));
        } else {
            throw new ConflictException("Capacity full in this property, tenant not added");
        }

    }

    // http://localhost:8080/tenants/1 returns 200 OK and object

    // http://localhost:8080/tenants/111 returns 4040 NOT FOUND and message
    /* {
       "newPropertyId": 7
    } */

    // staff@gmail.com, manager@gmail.com, Secret123
    @PatchMapping("{id}")
    Tenant updatedProperty(@Valid @RequestBody NewPropertyForTenantDTO newPropertyForTenantDTO, @PathVariable("id") int tenantId) {
        if (tenantRepository.existsById(tenantId)) {
            List<Property> list = propertyRepository.findAllPropertiesWithTenantSpace();
            if (list.contains(propertyRepository.findById(newPropertyForTenantDTO.newPropertyId()).get())) {
                tenantRepository.moveTenant(tenantId, propertyRepository.findById(newPropertyForTenantDTO.newPropertyId()));
                return tenantRepository.findByTenantId(tenantId).get();
            } else {
                throw new ConflictException("Capacity full in this property, tenant not added");
            }
        }
        throw new ResourceNotFoundException("Tenant with ID " + tenantId + " is not present");
    }
}
