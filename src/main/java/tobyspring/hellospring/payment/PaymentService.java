package tobyspring.hellospring.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.Clock;
import java.time.LocalDateTime;

//@Component
public class PaymentService {

    private final ExRateProvider exRateProvider;
    private final Clock clock;

    public PaymentService(ExRateProvider exRateProvider, Clock clock) {
        this.exRateProvider = exRateProvider;
        this.clock = clock;
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException, URISyntaxException {
        BigDecimal exchangeRate = exRateProvider.getExchangeRate(currency);

        return Payment.createPrepared(orderId, currency, foreignCurrencyAmount, exchangeRate, LocalDateTime.now(clock));
    }
}
