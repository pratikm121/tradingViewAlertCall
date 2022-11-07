package nl.trading.view.alert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TradingViewAlertCallApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradingViewAlertCallApplication.class, args);
    }

}
