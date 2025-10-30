package tobyspring.hellospring.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

import static java.math.BigDecimal.*;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {

    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
    void convertedAmount() throws IOException, URISyntaxException {
        testAmount(valueOf(500), valueOf(5000));
        testAmount(valueOf(1_000), valueOf(10_000));
        testAmount(valueOf(3000), valueOf(30_000));

        // 원화환산금액 유효시간 계산
        // assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
        // assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }

    private static void testAmount(BigDecimal exchangeRate, BigDecimal convertedAmount) throws IOException, URISyntaxException {
        // WebApiExRateProvider는 우리가 제어할 수 없는 외부의 API 라는 문제가 있다.
        // PaymentService paymentService = new PaymentService(new WebApiExRateProvider());
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exchangeRate));

        Payment payment = paymentService.prepare(1L, "USD", TEN);

        assertThat(payment.getExchangeRate()).isEqualByComparingTo(exchangeRate);
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);

    }
}