package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

public class Client {
    public static void main(String[] args) throws IOException, URISyntaxException {
        ObjectFactory objectFactory = new ObjectFactory();
        PaymentService paymentService = objectFactory.paymentService();

        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}
