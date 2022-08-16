package by.zazyba.producer.service.impl;

import by.zazyba.producer.config.BookingQueueEnum;
import by.zazyba.producer.domain.Booking;
import by.zazyba.producer.service.BookService;
import by.zazyba.producer.kafka.BookingProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookingProducer producer;

    @Override
    public String createBook(final Booking book) throws JsonProcessingException {
        return producer.sendMessage(book, BookingQueueEnum.ADD);
    }

    @Override
    public String deleteBook(final Booking book) throws JsonProcessingException {
        return producer.sendMessage(book, BookingQueueEnum.DELETE);
    }

    @Override
    public String editBook(final Booking book) throws JsonProcessingException {
        return producer.sendMessage(book, BookingQueueEnum.EDIT);
    }
}
