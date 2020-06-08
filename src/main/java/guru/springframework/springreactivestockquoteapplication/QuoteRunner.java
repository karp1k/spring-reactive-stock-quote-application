package guru.springframework.springreactivestockquoteapplication;

import guru.springframework.springreactivestockquoteapplication.domain.Quote;
import guru.springframework.springreactivestockquoteapplication.repositories.QuoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.util.concurrent.CountDownLatch;

/**
 * @author kas
 */
@Component
@Slf4j
public class QuoteRunner {

    private final QuoteRepository repository;
    private volatile int count = 0;

    public QuoteRunner(QuoteRepository repository) {
        this.repository = repository;
    }

    @Scheduled(fixedRate = 20000)
    public void run() throws Exception {
        if (count != 1) { // work around for the TailableCursor proper work
            CountDownLatch countDownLatch = new CountDownLatch(1);
            Flux<Quote> quoteFlex = repository.findWithTailableCursorBy();
            Disposable disposable = quoteFlex.subscribe(q -> {
                count = 1;
                log.info("+++++++++++++++++ {}", q.toString());
            }, e -> log.error(e.getMessage()), countDownLatch::countDown);
            countDownLatch.await();

            disposable.dispose();
        }
    }
}
