package com.beautify.demo.services;

import com.beautify.demo.objects.BookingType;

import java.util.List;

public interface BookingTypeService {

    List<BookingType> findAll();

    BookingType addService(BookingType service);

}
