package com.beautify.demo.repos;

import com.beautify.demo.objects.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);

    @Query("select u\n" +
            "from User u\n" +
            "left join fetch u.roles r\n" +
            "where r.name = 'Owner'")
    List<User> findOwners();

}
