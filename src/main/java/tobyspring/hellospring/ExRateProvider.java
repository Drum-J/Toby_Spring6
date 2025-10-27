package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

public interface ExRateProvider {
    BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException;
}
