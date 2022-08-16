package by.zazyba.producer.advisor;

import by.zazyba.producer.domain.Booking;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@RequiredArgsConstructor
@Component
@Aspect
public class AuditQueueFilter {

    private static final String FILTER_NAME = "auditQueueFilter";
    private final KafkaTemplate<String, Booking> kafkaTemplate;

    @Value("${kafka.book.audit.topic.name}")
    private String auditTopic;

    @Pointcut("execution(public !void by.zazyba.*.kafka.*.sendMessage(..)))")
    protected void any() {
        // all sendMessage methods
    }

    @After("any()")
    public Object agencyFilter(final JoinPoint point) throws Throwable {
        Booking obj = (Booking) Arrays.stream(point.getArgs()).findFirst().orElse(null);
        kafkaTemplate.send(auditTopic, obj);
        return null;
    }

}
