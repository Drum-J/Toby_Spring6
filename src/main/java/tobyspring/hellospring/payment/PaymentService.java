package tobyspring.hellospring.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

//@Component
public class PaymentService {

    private final ExRateProvider exRateProvider;

    public PaymentService(ExRateProvider exRateProvider) {
        this.exRateProvider = exRateProvider;
    }

    public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException, URISyntaxException {
        BigDecimal exchangeRate = exRateProvider.getExchangeRate(currency);
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exchangeRate);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exchangeRate, convertedAmount, validUntil);
    }
}
