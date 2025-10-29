package tobyspring.hellospring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

public class Client {
    public static void main(String[] args) throws IOException, URISyntaxException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);
        /*
        PaymentService paymentService2 = beanFactory.getBean(PaymentService.class);

        // 싱글톤 오브젝트 확인
        System.out.println(paymentService);
        System.out.println(paymentService2);
        System.out.println(paymentService == paymentService2);
        */

        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}
