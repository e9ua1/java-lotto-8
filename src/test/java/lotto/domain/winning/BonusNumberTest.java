package lotto.domain.winning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("BonusNumber 테스트")
class BonusNumberTest {

    @Nested
    @DisplayName("보너스 번호를 생성할 때")
    class CreateTest {

        @Test
        @DisplayName("1~45 범위의 번호로 보너스 번호를 생성한다")
        void createBonusNumber() {
            WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

            BonusNumber bonusNumber = new BonusNumber(7, winningNumbers);

            assertThat(bonusNumber.getValue()).isEqualTo(7);
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, 46, 100})
        @DisplayName("번호가 1~45 범위를 벗어나면 예외가 발생한다")
        void createBonusNumberWithInvalidRange(int invalidNumber) {
            WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

            assertThatThrownBy(() -> new BonusNumber(invalidNumber, winningNumbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }

        @Test
        @DisplayName("당첨 번호와 중복되면 예외가 발생한다")
        void createBonusNumberWithDuplicate() {
            WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

            assertThatThrownBy(() -> new BonusNumber(3, winningNumbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]")
                    .hasMessageContaining("중복");
        }
    }
}
