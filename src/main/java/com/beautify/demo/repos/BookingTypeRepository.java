package com.beautify.demo.repos;

import com.beautify.demo.objects.BookingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingTypeRepository extends JpaRepository<BookingType, Long> {
}
