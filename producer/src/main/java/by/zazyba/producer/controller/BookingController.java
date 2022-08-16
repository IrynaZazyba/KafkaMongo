package by.zazyba.producer.controller;

import by.zazyba.producer.domain.Booking;
import by.zazyba.producer.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookingController {

    private final BookService bookService;

    @PostMapping
    public String createBook(@RequestBody Booking booking) throws JsonProcessingException {
        log.info("create food order request received");
        return bookService.createBook(booking);
    }

    @DeleteMapping
    public String deleteBook(@RequestBody Booking booking) throws JsonProcessingException {
        bookService.deleteBook(booking);
        return "deleted";
    }

    @PutMapping
    public String editBook(@RequestBody Booking booking) throws JsonProcessingException {
        bookService.editBook(booking);
        return "edited";
    }

}
