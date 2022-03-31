package com.beautify.demo.services;

import com.beautify.demo.objects.Role;
import com.beautify.demo.objects.User;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;

public interface UserService{

    List<User> findAll();
    User getSpecificUser(Authentication authentication);
    Optional<User> getSpecificUserById(Long id);
    String getFirstName(Authentication authentication);
    String getFullName(Authentication authentication);
    List<User> getOwners();
    User addUser(User user);
    void deleteUser(Long id);

}
