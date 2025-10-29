package tobyspring.hellospring.exrate;

import tobyspring.hellospring.payment.ExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

public class SimpleExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException {
        if (currency.equals("USD")) {
            return BigDecimal.valueOf(1000);
        }

        throw new IllegalArgumentException("지원되지 않는 통화입니다: " + currency);
    }
}
