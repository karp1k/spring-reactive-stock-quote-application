package guru.springframework.springreactivestockquoteapplication.repositories;

import guru.springframework.springreactivestockquoteapplication.domain.Quote;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

/**
 * @author kas
 */
public interface QuoteRepository extends ReactiveMongoRepository<Quote, String> {

//    @Query("select q from Quote q")
    @Tailable
    Flux<Quote> findWithTailableCursorBy();
}
