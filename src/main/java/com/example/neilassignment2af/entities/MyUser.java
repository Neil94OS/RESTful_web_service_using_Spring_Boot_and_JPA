package com.example.neilassignment2af.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class MyUser {
    @Id
    @Email(message = "Invalid email address.")
    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    @JsonIgnore
    private String userPassword;

    @Column(nullable = false)
    @JsonIgnore
    private Boolean userLocked;

    @Column(nullable = false)
    @JsonIgnore
    private String userRole;

    @Column(nullable = false)
    @JsonIgnore
    private String userPhoneNumber;

    @Column(nullable = false, unique = true)
    @JsonIgnore
    private String userPPSN;

}