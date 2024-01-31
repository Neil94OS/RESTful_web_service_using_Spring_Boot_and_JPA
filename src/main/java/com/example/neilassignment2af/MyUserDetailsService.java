package com.example.neilassignment2af;

import com.example.neilassignment2af.entities.MyUser;
import com.example.neilassignment2af.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> myUserOptional = userRepository.findByEmail(username);
        if(myUserOptional.isEmpty())
            throw new UsernameNotFoundException(username);
        MyUser myUser = myUserOptional.get();
        return User.withUsername(username)
                .password(myUser.getUserPassword())
                .accountLocked(false)
                .accountExpired(false)
                .roles(myUser.getUserRole())
                .disabled(myUser.getUserLocked())
                .build();

    }
}
