package guru.springframework.springreactivestockquoteapplication.client;

import guru.springframework.springreactivestockquoteapplication.domain.Quote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;

/**
 * @author kas
 */
@Slf4j
@Component
public class StockQuoteClient {

    private final String quoteServiceHost;
    private final String quoteServicePort;
    private final String quoteServicePath;
    private String url;

    public StockQuoteClient(@Value("${quote-service.host}") String quoteServiceHost,
                            @Value("${quote-service.port}") String quoteServicePort,
                            @Value("${quote-service.path}") String quoteServicePath) {
        this.quoteServiceHost = quoteServiceHost;
        this.quoteServicePort = quoteServicePort;
        this.quoteServicePath = quoteServicePath;
    }

    @PostConstruct
    void init() {
        url = "http://" + quoteServiceHost + ":" + quoteServicePort;
        log.info("Url is {}", url);
    }

    Flux<Quote> getQuoteStream() {
        return WebClient.builder()
                .baseUrl(url)
                .build()
                .get()
                .uri(quoteServicePath)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(Quote.class);
    }
}
