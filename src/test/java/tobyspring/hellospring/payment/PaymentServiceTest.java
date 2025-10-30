package tobyspring.hellospring.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {

    Clock clock;

    @BeforeEach
    void beforeEach() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
    void convertedAmount() {
        testAmount(valueOf(500), valueOf(5000), this.clock);
        testAmount(valueOf(1_000), valueOf(10_000), this.clock);
        testAmount(valueOf(3000), valueOf(30_000), this.clock);
    }

    @Test
    void validUntil() {
        // given
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(valueOf(1_000)), clock);
        Payment payment = paymentService.prepare(1L, "USD", TEN);

        // when
        // valid unitl 이 prepare() 30분 뒤로 설정 됐는가?
        LocalDateTime now = LocalDateTime.now(clock);
        LocalDateTime expectedValidUntil = now.plusMinutes(30);

        // then
        assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }

    private static void testAmount(BigDecimal exchangeRate, BigDecimal convertedAmount, Clock clock) {
        // WebApiExRateProvider는 우리가 제어할 수 없는 외부의 API 라는 문제가 있다.
        // PaymentService paymentService = new PaymentService(new WebApiExRateProvider());
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exchangeRate), clock);

        Payment payment = paymentService.prepare(1L, "USD", TEN);

        assertThat(payment.getExchangeRate()).isEqualByComparingTo(exchangeRate);
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }
}