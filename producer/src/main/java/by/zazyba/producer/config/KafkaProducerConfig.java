package by.zazyba.producer.config;

import by.zazyba.producer.domain.Booking;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class KafkaProducerConfig {

    private final KafkaProperties kafkaProperties;

    @Bean
    public ProducerFactory<String, Booking> producerFactory() {
        // get configs on application.properties/yml
        Map<String, Object> properties = kafkaProperties.buildProducerProperties();
        properties.put("security.protocol", "SASL_PLAINTEXT");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<String, Booking> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public NewTopic bookAdd() {
        return TopicBuilder
                .name("t.book.add")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic bookEdit() {
        return TopicBuilder
                .name("t.book.add")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic bookDelete() {
        return TopicBuilder
                .name("t.book.add")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic bookAudit() {
        return TopicBuilder
                .name("t.audit")
                .partitions(1)
                .replicas(1)
                .build();
    }

}
