package com.example.neilassignment2af.controllers;

import com.example.neilassignment2af.controllers.dtos.NewPropertyDTO;
import com.example.neilassignment2af.controllers.dtos.NewPropertyRentDTO;
import com.example.neilassignment2af.controllers.handlers.ResourceNotFoundException;
import com.example.neilassignment2af.dao.dto.PropertyAndTenants;
import com.example.neilassignment2af.entities.Property;
import com.example.neilassignment2af.repositories.PropertyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/properties")
public class PropertyController {
    @Autowired private PropertyRepository propertyRepository;

    // http://localhost:8080/properties returns 200 OK with list of objects
    @GetMapping({"/",""})
    List<Property> findAll(){
        return propertyRepository.findAll();
    }

    // http://localhost:8080/properties/1 returns 200 OK with object
    // http://localhost:8080/properties/111 returns 404 NOT FOUND with error message
    @GetMapping("/{id}")
    Property findById(@PathVariable("id") int propertyId){
        Optional<Property> propertyOptional = propertyRepository.findById(propertyId);
        if(propertyOptional.isPresent())
            return propertyOptional.get();
        throw new ResourceNotFoundException("Property with ID "+propertyId + " is not present");
    }

    // http://localhost:8080/properties/empty returns 200 OK with list of objects
    // can remove property 6 and 7 to test no empty properties,  returns 404 NOT FOUND with error message
    @GetMapping({"/empty"})
    List<Property> findAllEmptyProperties(){
        List <Property> property = propertyRepository.findAllEmptyProperties();
        if(property.isEmpty())
            throw new ResourceNotFoundException("There are no empty properties at the moment");
        return property;
    }

    // http://localhost:8080/properties/totalrent returns 200 OK with result
    @GetMapping({"/totalrent"})
    int findTotalRentOfOccupiedProperties(){
        return propertyRepository.findTotalRentOfOccupiedProperties();
    }

    // http://localhost:8080/properties/withspace returns 200 OK with list of objects
    // can -1 from capacity for properties 1 and 3  and remove properties 6 and 7 to test no properties with free space,  returns 404 NOT FOUND with error message
    @GetMapping({"/withspace"})
    List<Property> findAllPropertiesWithTenantSpaces(){
        List <Property> property = propertyRepository.findAllPropertiesWithTenantSpace();
        if(property.isEmpty())
            throw new ResourceNotFoundException("There are no properties with available space at the moment");
        return property;
    }

    // http://localhost:8080/properties/tenants/1 returns 200 OK with record object
    // http://localhost:8080/properties/tenants/111 returns 404 NOT FOUND with error message
    @GetMapping("/tenants/{id}")
    PropertyAndTenants findPropertyAndTenants(@PathVariable("id") int propertyId){
        Optional<PropertyAndTenants> propertyOptional = propertyRepository.findPropertyAndTenants(propertyId);
        if(propertyOptional.isPresent())
            return propertyOptional.get();
        throw new ResourceNotFoundException("No record of tenants found for property with ID "+propertyId);
    }

    // http://localhost:8080/properties/1 returns 200 OK
    // http://localhost:8080/properties/111  returns 404 NOT FOUND with error message

    // manager@gmail.com, Secret123
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable("id") int propertyId){
        if(propertyRepository.existsById(propertyId))
            propertyRepository.deleteById(propertyId);
        else
            throw new ResourceNotFoundException("Property with ID "+propertyId + " is not present");
    }

    // http://localhost:8080/properties
    /* Json data {
        "propertyAddress": "11 Highlands",
        "propertyEircode": "Q23 H876",
        "propertyCapacity": 4,
        "propertyCost": 3000
    }*/

    // manager@gmail.com, Secret123
    @PostMapping({"/",""})
    Property addNewProperty(@Valid  @RequestBody NewPropertyDTO newPropertyDTO){
        return propertyRepository.save(new Property(newPropertyDTO.propertyAddress(), newPropertyDTO.propertyEircode(), newPropertyDTO.propertyCapacity(), newPropertyDTO.propertyCost()));
    }

    // http://localhost:8080/properties/2 returns 200 OK and object

    // http://localhost:8080/properties/111 returns 4040 NOT FOUND and message

    /* {
       "newPropertyCost": 2675
    } */

    // manager@gmail.com, Secret123
    @PatchMapping("{id}")
    Property updatedRent(@Valid @RequestBody NewPropertyRentDTO newPropertyRentDTO, @PathVariable("id") int propertyId){
        if(propertyRepository.existsById(propertyId)){
            propertyRepository.updateRent(propertyId, newPropertyRentDTO.newPropertyCost());
            return propertyRepository.findById(propertyId).get();
        }
        throw new ResourceNotFoundException("Property with ID "+propertyId + " is not present");
    }

}
