package guru.springframework.springreactivestockquoteapplication.repositories;

import guru.springframework.springreactivestockquoteapplication.domain.Quote;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author kas
 */
public interface QuoteRepository extends ReactiveMongoRepository<Quote, String> {
}
