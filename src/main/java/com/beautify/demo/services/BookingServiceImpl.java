package com.beautify.demo.services;

import com.beautify.demo.objects.Booking;
import com.beautify.demo.repos.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingServiceImpl implements BookingService{

    private final BookingRepository bookingRepository;

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> findAllBySaloonId(Long id) {
        return bookingRepository.findAllBySaloonId(id);
    }
}
