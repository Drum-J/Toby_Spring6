package tobyspring.hellospring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class SortTest {

    Sort sort;

    @BeforeEach
    void beforeEach() {
        // given
        sort = new Sort();

        // 모든 테스트는 다른 테스트에 영향을 받지 않고 독립적으로 수행이 되어야 한다.
        // 매번 새로운 인스턴스( new SortTest()) 를 JUnit 이 만들어 준다.
        System.out.println(this);
    }


    @Test
    void sort() {
        // 실행 (when)
        List<String> list = sort.sortByLength(Arrays.asList("aa", "b"));

        // 검증 (then)
        Assertions.assertThat(list).isEqualTo(List.of("b", "aa"));
    }

    @Test
    void sort3Items() {
        //when
        List<String> list = sort.sortByLength(Arrays.asList("aa", "ccc", "b"));

        //then
        Assertions.assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
    }

    @Test
    void alreadySorted() {
        //when
        List<String> list = sort.sortByLength(Arrays.asList("b", "aa", "ccc"));

        //then
        Assertions.assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
    }
}
