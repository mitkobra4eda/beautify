package com.beautify.demo.services;

import com.beautify.demo.objects.City;
import com.beautify.demo.objects.Saloon;
import com.beautify.demo.repos.SaloonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SaloonServiceImpl implements SaloonService{

    @Autowired
    private SaloonRepository saloonRepository;

    @Override
    public List<Saloon> getSaloons() {
        return saloonRepository.findAll();
    }

    @Override
    public Optional<Saloon> getSpecificSaloon(long id) {
        return saloonRepository.findById(id);
    }

    @Override
    public Saloon addSaloon(Saloon saloon) {
        return saloonRepository.save(saloon);
    }

    @Override
    public void deleteSaloon(long id) {
        saloonRepository.deleteById(id);
    }

    @Override
    public List<Saloon> filterByCity(int id) {
        return saloonRepository.findByCityName(id);
    }



}
