package lotto.domain.winning;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("WinningNumbers 테스트")
class WinningNumbersTest {

    @Nested
    @DisplayName("당첨 번호를 생성할 때")
    class CreateTest {

        @Test
        @DisplayName("6개의 번호로 당첨 번호를 생성한다")
        void createWinningNumbersWithSixNumbers() {
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

            WinningNumbers winningNumbers = new WinningNumbers(numbers);

            assertThat(winningNumbers).isNotNull();
        }

        @Test
        @DisplayName("잘못된 형식의 번호는 LottoNumbers에서 검증된다")
        void createWinningNumbersWithInvalidNumbers() {
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

            assertThatThrownBy(() -> new WinningNumbers(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }
    }

    @Nested
    @DisplayName("번호 포함 여부를 확인할 때")
    class ContainsTest {

        @Test
        @DisplayName("포함된 번호는 true를 반환한다")
        void containsNumber() {
            WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

            boolean result = winningNumbers.contains(3);

            assertThat(result).isTrue();
        }

        @Test
        @DisplayName("포함되지 않은 번호는 false를 반환한다")
        void doesNotContainNumber() {
            WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

            boolean result = winningNumbers.contains(7);

            assertThat(result).isFalse();
        }
    }
}
