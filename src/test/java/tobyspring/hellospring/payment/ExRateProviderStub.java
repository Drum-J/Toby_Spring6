package tobyspring.hellospring.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

public class ExRateProviderStub implements ExRateProvider {

    private BigDecimal exchangeRate;

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public ExRateProviderStub(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException {
        return exchangeRate;
    }
}
