package lotto.domain.ticket;

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

@DisplayName("LottoNumbers 테스트")
class LottoNumbersTest {

    @Nested
    @DisplayName("로또 번호를 생성할 때")
    class CreateTest {

        @Test
        @DisplayName("6개의 번호로 로또 번호를 생성한다")
        void createLottoNumbersWithSixNumbers() {
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

            LottoNumbers lottoNumbers = new LottoNumbers(numbers);

            assertThat(lottoNumbers).isNotNull();
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 5, 7, 10})
        @DisplayName("번호가 6개가 아니면 예외가 발생한다")
        void createLottoNumbersWithInvalidSize(int size) {
            List<Integer> numbers = IntStream.rangeClosed(1, size)
                    .boxed()
                    .collect(Collectors.toList());

            assertThatThrownBy(() -> new LottoNumbers(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]")
                    .hasMessageContaining("6개");
        }

        @Test
        @DisplayName("중복된 번호가 있으면 예외가 발생한다")
        void createLottoNumbersWithDuplicate() {
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, 5);

            assertThatThrownBy(() -> new LottoNumbers(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]")
                    .hasMessageContaining("중복");
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, 46, 100})
        @DisplayName("번호가 1~45 범위를 벗어나면 예외가 발생한다")
        void createLottoNumbersWithInvalidRange(int invalidNumber) {
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, invalidNumber);

            assertThatThrownBy(() -> new LottoNumbers(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]")
                    .hasMessageContaining("1부터 45");
        }
    }

    @Nested
    @DisplayName("번호 포함 여부를 확인할 때")
    class ContainsTest {

        @Test
        @DisplayName("포함된 번호는 true를 반환한다")
        void containsNumber() {
            LottoNumbers lottoNumbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));

            boolean result = lottoNumbers.contains(3);

            assertThat(result).isTrue();
        }

        @Test
        @DisplayName("포함되지 않은 번호는 false를 반환한다")
        void doesNotContainNumber() {
            LottoNumbers lottoNumbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));

            boolean result = lottoNumbers.contains(7);

            assertThat(result).isFalse();
        }
    }
}
