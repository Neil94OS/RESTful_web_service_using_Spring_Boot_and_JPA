package com.example.neilassignment2af.controllers;

import com.example.neilassignment2af.entities.Tenant;
import com.example.neilassignment2af.repositories.PropertyRepository;
import com.example.neilassignment2af.repositories.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import com.example.neilassignment2af.entities.Property;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphQLController {
    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private TenantRepository tenantRepository;

    // http://localhost:8080/graphql

    /*
    query Properties {
    properties {
        propertyId
        propertyAddress
        propertyEircode
        propertyCapacity
        propertyCost
        tenants {
            tenantId
            tenantName
            tenantEmail
            tenantPhoneNumber
        }
    }
}
     */
    @QueryMapping(value = "properties")
    List<Property> getAllProperties(){
        return propertyRepository.findAll();
    }

    /*
    query FindProperty {
    findProperty(id: 1) {
        propertyId
        propertyAddress
        propertyEircode
        propertyCapacity
        propertyCost
        tenants {
            tenantId
            tenantName
            tenantEmail
            tenantPhoneNumber
        }
    }
}
     */
    @QueryMapping
    Optional<Property> findProperty(@Argument int id){
        return propertyRepository.findById(id);
    }

    /*
    mutation AddTenant {
    addTenant(
        tenantName: "TEST"
        tenantEmail: "test@gmail.com"
        tenantPhoneNumber: "0258745123"
        property: {
            propertyId: "4"
            propertyAddress: "1 Old street"
            propertyEircode: "D12 F987"
            propertyCapacity: 5
            propertyCost: 3500
        }
    ) {
        tenantId
        tenantEmail
        tenantName
        tenantPhoneNumber
    }
}
     */
    // staff@gmail.com, manager@gmail.com Secret123
    @MutationMapping
    @Secured(value={"ROLE_MANAGER","ROLE_OFFICE_STAFF"})
    Tenant addTenant(@Argument("tenantName") String tenantName, @Argument("tenantEmail") String tenantEmail, @Argument("tenantPhoneNumber") String tenantPhoneNumber, @Argument("property") Property property){
        return tenantRepository.save(new Tenant(tenantName, tenantEmail, tenantPhoneNumber, property));

    }

    /*
        mutation DeleteProperty {
        deleteProperty(id: 1)
        }
     */
    // manager@gmail.com Secret123
    @MutationMapping
    @Secured(value="ROLE_MANAGER")
    void deleteProperty(@Argument("id") int propertyId){
       propertyRepository.deleteById(propertyId);

    }
}
