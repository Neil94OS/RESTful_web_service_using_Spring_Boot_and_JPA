package com.example.neilassignment2af.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "properties")
public class Property {
    @Id
    @GeneratedValue
    private int propertyId;

    @Column(unique = true, nullable = false)
    private String propertyAddress;

    @Column(unique = true, nullable = false)
    private String propertyEircode;

    @Column(nullable = false)
    private int propertyCapacity;

    @Column(nullable = false)
    private int propertyCost;

    @OneToMany( mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @JsonIgnore
    private List<Tenant> tenants;

    public Property(String propertyAddress, String propertyEircode, int propertyCapacity, int propertyCost){
        this.propertyAddress = propertyAddress;
        this.propertyEircode = propertyEircode;
        this.propertyCapacity = propertyCapacity;
        this.propertyCost = propertyCost;

    }

}
