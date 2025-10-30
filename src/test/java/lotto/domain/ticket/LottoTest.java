package lotto.domain.ticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTest {

    @Nested
    @DisplayName("로또를 생성할 때")
    class CreateTest {

        @Test
        @DisplayName("6개의 번호로 로또를 생성한다")
        void createLottoWithSixNumbers() {
            // given
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

            // when
            Lotto lotto = new Lotto(numbers);

            // then
            assertThat(lotto).isNotNull();
        }
    }
}
