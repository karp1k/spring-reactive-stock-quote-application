package guru.springframework.springreactivestockquoteapplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;

import javax.annotation.PostConstruct;

/**
 * @author kas
 */
@Configuration
public class MongoConfig {

    @Autowired
    ReactiveMongoOperations reactiveMongoOperations;

    @PostConstruct
    void init() {
        CollectionOptions options = CollectionOptions.empty().capped().size(100000).maxDocuments(1000);
        reactiveMongoOperations.createCollection("quote", options).subscribe();
    }
}
