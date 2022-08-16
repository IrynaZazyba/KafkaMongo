package by.zazyba.producer.kafka;

import by.zazyba.producer.config.BookingQueueEnum;
import by.zazyba.producer.domain.Booking;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@RequiredArgsConstructor
@Component
public class BookingProducer {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, Booking> kafkaTemplate;

    @Value("${kafka.book.add.topic.name}")
    private String bookAddTopic;

    @Value("${kafka.book.edit.topic.name}")
    private String bookEditTopic;

    @Value("${kafka.book.delete.topic.name}")
    private String bookDeleteTopic;

    @Value("${kafka.book.audit.topic.name}")
    private String auditTopic;

    public String sendMessage(Booking booking, BookingQueueEnum queueName) throws JsonProcessingException {
        ListenableFuture<SendResult<String, Booking>> future = kafkaTemplate.send(getQueue(queueName), booking);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Booking>>() {
            @Override
            public void onSuccess(SendResult<String, Booking> result) {
                log.info("Sent message=[" + booking +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("Unable to send message=["
                        + booking + "] due to : " + ex.getMessage());
            }
        });
        kafkaTemplate.flush();

        log.info("book {}", booking);
        return "message sent";
    }

    private String getQueue(BookingQueueEnum queueName) {
        switch (queueName) {
            case ADD:
                return bookAddTopic;
            case EDIT:
                return bookEditTopic;
            case DELETE:
                return bookDeleteTopic;
            default:
                return auditTopic;
        }
    }

}
