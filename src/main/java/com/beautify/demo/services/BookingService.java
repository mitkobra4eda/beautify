package com.beautify.demo.services;

import com.beautify.demo.objects.Booking;

import java.util.List;

public interface BookingService {

    List<Booking> findAll();
    List<Booking> findAllBySaloonId(Long id);

}
