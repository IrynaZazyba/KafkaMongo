package by.zazyba.consumer.service.impl;


import by.zazyba.consumer.domain.Booking;
import by.zazyba.consumer.repository.BookingRepository;
import by.zazyba.consumer.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
@Profile("!dev")
public class BookingServiceImpl implements BookService {

    private final BookingRepository bookingRepository;

    @Override
    public Booking createBooking(final Booking booking) throws JsonProcessingException {
        if (Objects.isNull(booking.getId())) {
            booking.setId(UUID.randomUUID());
        }
        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(final Booking booking) throws JsonProcessingException {
        bookingRepository.delete(booking);
    }
}
