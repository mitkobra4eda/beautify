package com.beautify.demo.services;

import com.beautify.demo.objects.Saloon;

import java.util.List;
import java.util.Optional;

public interface SaloonService{

    List<Saloon> getSaloons();
    Optional<Saloon> getSpecificSaloon(long id);
    Saloon addSaloon(Saloon saloon);
    void deleteSaloon(long id);
    List<Saloon> filterByCity(int id);

}
