package guru.springframework.springreactivestockquoteapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringReactiveStockQuoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringReactiveStockQuoteApplication.class, args);
    }

}
