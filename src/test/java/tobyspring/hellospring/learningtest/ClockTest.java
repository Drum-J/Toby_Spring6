package tobyspring.hellospring.learningtest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ClockTest {
    // Clock을 이용해서 LocalDateTime.now?
    @Test
    void clock() throws Exception {
        //given
        Clock clock = Clock.systemDefaultZone();

        //when
        LocalDateTime dt1 = LocalDateTime.now(clock);
        Thread.sleep(1); //mac과 window의 차이로 인해 sleep 추가
        LocalDateTime dt2 = LocalDateTime.now(clock);

        //then
        // 이 경우 항상 현재 시간을 가져오기 때문에 dt2가 dt1 이후의 시간을 나타낸다.
        Assertions.assertThat(dt2).isAfter(dt1);
    }

    // Clock을 Test에서 사용할 때 내가 원하는 시간을 지정해서 현재 시간을 가져오게 할 수 있는가?
    @Test
    void fixedClock() throws Exception {
        //given
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault()); // 시간 고정

        //when
        LocalDateTime dt1 = LocalDateTime.now(clock);
        Thread.sleep(1);
        LocalDateTime dt2 = LocalDateTime.now(clock);

        LocalDateTime dt3 = LocalDateTime.now(clock).plusHours(1);

        //then
        Assertions.assertThat(dt2).isEqualTo(dt1);
        Assertions.assertThat(dt3).isEqualTo(dt1.plusHours(1));
        // dt3가 좀 더 늦게 시간을 조회했으나 항상 고정된 시간을 보기 때문에 plusHours(1)를 해도 정확하게 비교할 수 있다.
    }
}
