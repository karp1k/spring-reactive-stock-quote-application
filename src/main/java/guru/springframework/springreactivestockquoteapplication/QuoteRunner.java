package guru.springframework.springreactivestockquoteapplication;

import guru.springframework.springreactivestockquoteapplication.client.StockQuoteClient;
import guru.springframework.springreactivestockquoteapplication.domain.Quote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * @author kas
 */
@Slf4j
//@Component
public class QuoteRunner implements CommandLineRunner {

    private final StockQuoteClient client;

    public QuoteRunner(StockQuoteClient client) {
        this.client = client;
    }

    @Override
    public void run(String... args) throws Exception {
        Flux<Quote> quoteFlex = client.getQuoteStream();
        quoteFlex.subscribe(q -> log.info(q.toString()));
    }
}
