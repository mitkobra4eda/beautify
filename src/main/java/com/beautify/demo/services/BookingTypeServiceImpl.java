package com.beautify.demo.services;

import com.beautify.demo.objects.BookingType;
import com.beautify.demo.repos.BookingTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BookingTypeServiceImpl implements BookingTypeService{

    @Autowired
    private BookingTypeRepository bookingTypeRepository;

    @Override
    public List<BookingType> findAll() {
        return bookingTypeRepository.findAll();
    }

    public BookingType addService(BookingType service){
        return bookingTypeRepository.save(service);
    }



}
