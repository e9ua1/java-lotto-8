package lotto.domain.winning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

        @ParameterizedTest
        @ValueSource(ints = {0, 5, 7, 10})
        @DisplayName("번호가 6개가 아니면 예외가 발생한다")
        void createWinningNumbersWithInvalidSize(int size) {
            List<Integer> numbers = IntStream.rangeClosed(1, size)
                    .boxed()
                    .collect(Collectors.toList());

            assertThatThrownBy(() -> new WinningNumbers(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, 46, 100})
        @DisplayName("번호가 1~45 범위를 벗어나면 예외가 발생한다")
        void createWinningNumbersWithInvalidRange(int invalidNumber) {
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, invalidNumber);

            assertThatThrownBy(() -> new WinningNumbers(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }

        @Test
        @DisplayName("중복된 번호가 있으면 예외가 발생한다")
        void createWinningNumbersWithDuplicate() {
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
