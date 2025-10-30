package tobyspring.hellospring.payment;

import java.math.BigDecimal;

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
    public BigDecimal getExchangeRate(String currency) {
        return exchangeRate;
    }
}
