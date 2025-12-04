package tobyspring.hellospring.order;

import jakarta.persistence.*;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;

//@Entity
//@Table(name = "orders")
public class Order {

    // GeneratedValue의 strategy 를 IDENTITY로 설정 시 여전히 org.hibernate.exception.ConstraintViolationException 발생
    // 해당 strategy를 설정하지 않은 경우 DataIntegrityViolationException 발생
    //@Id @GeneratedValue//(strategy = IDENTITY)
    private Long id;

    //@Column(unique = true)
    private String no;

    private BigDecimal total;

    protected Order() {

    }

    public Order(String no, BigDecimal total) {
        this.no = no;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public String getNo() {
        return no;
    }

    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", no='" + no + '\'' +
                ", total=" + total +
                '}';
    }
}
