package by.zazyba.consumer.service;

import by.zazyba.consumer.domain.Booking;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class Consumer {

    private static final String bookingAddTopic = "${kafka.book.add.topic.name}";
    private static final String bookingEditTopic = "${kafka.book.edit.topic.name}";
    private static final String bookingDeleteTopic = "${kafka.book.delete.topic.name}";
    private static final String auditTopic = "${kafka.book.audit.topic.name}";

    private final BookService bookingService;

    private final ObjectMapper objectMapper;
    private final ModelMapper modelMapper;
    private final BookService bookService;

    @KafkaListener(topics = bookingAddTopic)
    public void consumeAddBooking(String message) throws JsonProcessingException {
        log.info("message book add consumed {}", message);
        Booking booking = objectMapper.readValue(message, Booking.class);
        bookingService.createBooking(booking);

          //todo convert from/to dto
//        BookingDto bookingDto = objectMapper.readValue(message, BookingDto.class);
//        Booking booking = modelMapper.map(bookingDto, Booking.class);
//        bookingService.createBooking(booking);
    }

    @KafkaListener(topics = bookingEditTopic)
    public void consumeEditBooking(ConsumerRecord<Long, Booking> record) throws JsonProcessingException {
        log.info("message book update consumed {}", record.value());
        log.info("key={}, partition={}, topic={}", record.key(), record.partition(), record.topic());

        bookingService.createBooking(record.value());
    }

    @KafkaListener(topics = bookingDeleteTopic)
    public void consumeDeleteBooking(String message) throws JsonProcessingException {
        log.info("message book delete consumed {}", message);
        Booking booking = objectMapper.readValue(message, Booking.class);
        bookingService.deleteBooking(booking);
    }

    @KafkaListener(topics = auditTopic)
    public void consumeMessageToAudit(String message) throws JsonProcessingException {
        log.info("message book audit consumed {}", message);
    }
}
