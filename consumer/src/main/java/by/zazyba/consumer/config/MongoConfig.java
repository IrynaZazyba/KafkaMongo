package by.zazyba.consumer.config;

import com.mongodb.Block;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.connection.ClusterSettings;
import org.bson.UuidRepresentation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.Collections.singletonList;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        MongoCredential credential = MongoCredential.createCredential("admin", "admin", "secret".toCharArray());
        Block<ClusterSettings.Builder> localhost = builder -> builder.hosts(singletonList(new ServerAddress("localhost", 27017)));
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyToClusterSettings(localhost)
                .credential(credential)
                .uuidRepresentation(UuidRepresentation.STANDARD)
                .build();
        return MongoClients.create(settings);
    }

    @Bean
    public MongoDatabase db() {
        return mongoClient().getDatabase("admin");
    }
}
