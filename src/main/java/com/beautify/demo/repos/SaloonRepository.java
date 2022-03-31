package com.beautify.demo.repos;

import com.beautify.demo.objects.Saloon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaloonRepository extends JpaRepository<Saloon, Long> {

    List<Saloon> findByCityName(int id);


}
