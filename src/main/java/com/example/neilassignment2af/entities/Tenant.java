package com.example.neilassignment2af.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tenants")
public class Tenant {
    @Id
    @GeneratedValue
    private int tenantId;

    @Column(nullable = false)
    private String tenantName;

    @Column(unique = true, nullable = false)
    @Email(message = "Invalid email address.")
    private String tenantEmail;

    @Column(unique = true, nullable = false)
    private String tenantPhoneNumber;

    @ManyToOne
    @JoinColumn(name = "propertyId")
    private Property property;

    public Tenant(String tenantName, String tenantEmail, String tenantPhoneNumber,Property property){
        this.tenantName = tenantName;
        this.tenantEmail = tenantEmail;
        this.tenantPhoneNumber = tenantPhoneNumber;
        this.property= property;
    }

}
