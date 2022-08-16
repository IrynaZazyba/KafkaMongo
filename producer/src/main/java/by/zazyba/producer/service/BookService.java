package by.zazyba.producer.service;


import by.zazyba.producer.domain.Booking;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface BookService {

    String createBook(Booking book) throws JsonProcessingException;

    String deleteBook(Booking book) throws JsonProcessingException;

    String editBook(Booking book) throws JsonProcessingException;

}
