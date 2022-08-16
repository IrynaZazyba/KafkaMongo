package by.zazyba.consumer.service;

import by.zazyba.consumer.domain.Booking;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface BookService {

    Booking createBooking(Booking book) throws JsonProcessingException;

    void deleteBooking(Booking booking) throws JsonProcessingException;
}
