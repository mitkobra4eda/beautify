package com.beautify.demo.services;

import com.beautify.demo.objects.Role;
import com.beautify.demo.objects.User;
import com.beautify.demo.repos.RoleRepository;
import com.beautify.demo.repos.UserRepository;
import com.beautify.demo.userdetails.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private CustomUserDetails userDetails;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getSpecificUser(Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        String email = principal.getUsername();
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getSpecificUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public String getFirstName(Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        return principal.getFirstName();
    }

    @Override
    public String getFullName(Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        return principal.getFullName();
    }

    @Override
    public List<User> getOwners() {
        return userRepository.findOwners();
    }

    @Override
    public User addUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        Role roleUser = roleRepository.findByName("Customer");
        user.addRole(roleUser);

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
