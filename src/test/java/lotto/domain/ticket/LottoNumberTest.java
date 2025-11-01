package lotto.domain.ticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("LottoNumber 테스트")
class LottoNumberTest {

    @Nested
    @DisplayName("로또 번호를 생성할 때")
    class CreateTest {

        @ParameterizedTest
        @ValueSource(ints = {1, 25, 45})
        @DisplayName("1~45 범위의 번호로 생성한다")
        void createLottoNumber(int value) {
            LottoNumber lottoNumber = new LottoNumber(value);

            assertThat(lottoNumber.getValue()).isEqualTo(value);
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, 46, 100})
        @DisplayName("번호가 1~45 범위를 벗어나면 예외가 발생한다")
        void createLottoNumberWithInvalidRange(int invalidValue) {
            assertThatThrownBy(() -> new LottoNumber(invalidValue))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]")
                    .hasMessageContaining("1부터 45");
        }
    }

    @Nested
    @DisplayName("범위 검증을 할 때")
    class IsInRangeTest {

        @ParameterizedTest
        @ValueSource(ints = {1, 25, 45})
        @DisplayName("1~45 범위의 번호는 true를 반환한다")
        void isInRangeWithValidNumber(int value) {
            assertThat(LottoNumber.isInRange(value)).isTrue();
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, 46, 100})
        @DisplayName("1~45 범위를 벗어나면 false를 반환한다")
        void isInRangeWithInvalidNumber(int value) {
            assertThat(LottoNumber.isInRange(value)).isFalse();
        }
    }
}
