package by.zazyba.producer.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class Booking {

    private UUID id;
    private String passengerName;
    private String passengerContactNumber;
    private OffsetDateTime pickupTime;
    private Boolean asap = true;
    private Integer waitingTime;
    private Integer noOfPassengers;
    private BigDecimal price;
    private Integer rating;
    private Instant createdOn;
    private Instant lastModifiedOn;
    private List<TripWaypoint> tripWayPoints = new ArrayList<>();
}
