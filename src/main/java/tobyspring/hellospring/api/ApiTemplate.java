package tobyspring.hellospring.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class ApiTemplate {
    private final ApiExecutor apiExecutor;
    private final ExRateExtractor extractor;

    public ApiTemplate() {
        this.apiExecutor = new SimpleApiExecutor();
        this.extractor = new ErApiExRateExtractor();
    }

    public ApiTemplate(ApiExecutor apiExecutor, ExRateExtractor extractor) {
        this.apiExecutor = apiExecutor;
        this.extractor = extractor;
    }

    public BigDecimal getExRate(String url) {
        return this.getExRate(url, this.apiExecutor, this.extractor);
    }

    public BigDecimal getExRate(String url, ApiExecutor apiExecutor) {
        return this.getExRate(url, apiExecutor, this.extractor);
    }

    public BigDecimal getExRate(String url, ExRateExtractor extractor) {
        return this.getExRate(url, this.apiExecutor, extractor);
    }

    public BigDecimal getExRate(String url, ApiExecutor apiExecutor, ExRateExtractor extractor) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = apiExecutor.execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try {
            return extractor.extract(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
