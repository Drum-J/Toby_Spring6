package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

public class CachedExRateProvider implements ExRateProvider {
    private final ExRateProvider target;

    private BigDecimal cachedExchangeRate;
    private LocalDateTime cacheExpiryTime;

    public CachedExRateProvider(ExRateProvider target) {
        this.target = target;
    }

    @Override
    public BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException {
        if (cachedExchangeRate == null || cacheExpiryTime.isBefore(LocalDateTime.now())) {
            cachedExchangeRate = target.getExchangeRate(currency);
            cacheExpiryTime = LocalDateTime.now().plusSeconds(3);

            System.out.println("Cache Updated");
        }

        return cachedExchangeRate;
    }
}
