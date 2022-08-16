package by.zazyba.consumer.service.impl;


import by.zazyba.consumer.domain.Booking;
import by.zazyba.consumer.service.BookService;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.bson.BSONEncoder;
import org.bson.BasicBSONEncoder;
import org.bson.BsonValue;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Slf4j
@AllArgsConstructor
@Service
@Profile("dev")
public class BookingMongoServiceImpl implements BookService {

    private final static String MONGO_BOOKING_COLLECTION = "booking";
    private final MongoDatabase db;

    @Override
    public Booking createBooking(final Booking booking) {
        booking.setId(UUID.randomUUID());
        Gson gson = new Gson();
        MongoCollection<Document> collection = db.getCollection(MONGO_BOOKING_COLLECTION);

        Document obj = Document.parse(gson.toJson(booking));
        InsertOneResult insertOneResult = collection.insertOne(obj);
        log.info(insertOneResult.getInsertedId().toString());

        return booking;
    }

    @Override
    public void deleteBooking(final Booking booking) {
        //todo

//        MongoCollection<Document> collection = db.getCollection(MONGO_BOOKING_COLLECTION);
//        Bson query = eq("id", booking.getId());
//        DeleteResult result = collection.deleteOne(query);
//        System.out.println(result.getDeletedCount());
    }
}
