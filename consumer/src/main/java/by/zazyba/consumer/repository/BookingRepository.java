package by.zazyba.consumer.repository;

import by.zazyba.consumer.domain.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookingRepository extends MongoRepository<Booking, UUID> {
}
