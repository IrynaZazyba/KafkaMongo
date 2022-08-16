package by.zazyba.consumer.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@Document(collection = "waypoint")
public class TripWaypoint {

    private UUID tripWayPointId;
    private Booking booking;
    private Boolean lastStop;
    private String locality;
    private Double lat;
    private Double lng;
    private Instant tripWayPointTimestamp;

}
