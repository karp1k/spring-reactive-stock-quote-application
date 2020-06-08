package guru.springframework.springreactivestockquoteapplication;

import guru.springframework.springreactivestockquoteapplication.client.StockQuoteClient;

import guru.springframework.springreactivestockquoteapplication.domain.Quote;
import guru.springframework.springreactivestockquoteapplication.repositories.QuoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.CountDownLatch;

/**
 * @author kas
 */
@Slf4j
@Service
public class QuoteMonitorService {


    private final StockQuoteClient client;
    private final QuoteRepository repository;

    public QuoteMonitorService(StockQuoteClient client, QuoteRepository repository) {
        this.client = client;
        this.repository = repository;
    }

    @EventListener
    public void listen(ContextRefreshedEvent event) {
        client.getQuoteStream().log("quote-monitor")
                .subscribe(quote -> {
                    Mono<Quote> savedQuoteMono = repository.save(quote);
                    savedQuoteMono.subscribe(savedQuote -> log.info("Saved quote with id: {}", savedQuote.getId()));
                });
    }
}
