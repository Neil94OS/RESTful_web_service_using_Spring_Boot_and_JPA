package com.example.neilassignment2af.repositories;

import com.example.neilassignment2af.entities.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MyUser, String> {

    List<MyUser> findAll();

    Optional<MyUser> findByEmail(String username);

}

