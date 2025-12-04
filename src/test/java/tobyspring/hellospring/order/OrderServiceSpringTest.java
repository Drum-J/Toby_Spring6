package tobyspring.hellospring.order;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.hellospring.OrderConfig;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceSpringTest {

    @Autowired OrderService orderService;
    @Autowired
    private DataSource dataSource;

    @Test
    void createOrder() throws Exception {
        //given
        var order = orderService.createOrder("0100", BigDecimal.TEN);

        //when

        //then
        assertThat(order.getId()).isGreaterThan(0);
    }

    @Test
    void createOrders() throws Exception {
        //given
        List<OrderReq> orderReqs = List.of(
                new OrderReq("0200", BigDecimal.ONE),
                new OrderReq("0201", BigDecimal.TWO)
        );

        //when
        var orders = orderService.createOrders(orderReqs);

        //then
        assertThat(orders).hasSize(2);
        orders.forEach(order -> assertThat(order.getId()).isGreaterThan(0));
    }

    @Test
    void createDuplicatedOrders() throws Exception {
        //given
        List<OrderReq> orderReqs = List.of(
                new OrderReq("0300", BigDecimal.ONE),
                new OrderReq("0300", BigDecimal.TWO) // 주문 번호가 동일해서 예외 발생 유도
        );

        //when : 예외 발생 확인!
        assertThatThrownBy(() -> orderService.createOrders(orderReqs))
                .isInstanceOf(DataIntegrityViolationException.class);

        //then : 그러면 (0300, ONE) 인 Order 는 롤백이 제대로 되었는가?
        JdbcClient client = JdbcClient.create(dataSource);
        var count = client.sql("select count(*) from orders where no = '0300'").query(Long.class).single();

        assertThat(count).isEqualTo(0); // TransactionTemplate을 사용했을 때 정상적으로 롤백된다.
    }
}
