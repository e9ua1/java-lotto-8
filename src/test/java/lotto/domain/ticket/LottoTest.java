package lotto.domain.ticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import lotto.domain.winning.BonusNumber;
import lotto.domain.winning.WinningNumbers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Lotto 테스트")
class LottoTest {

    @Nested
    @DisplayName("로또를 생성할 때")
    class CreateTest {

        @Test
        @DisplayName("로또 번호의 개수가 6개가 아니면 예외가 발생한다")
        void createLottoWithInvalidSize() {
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }

        @Test
        @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다")
        void createLottoWithDuplicateNumbers() {
            assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1, 46, 100})
        @DisplayName("로또 번호가 1~45 범위를 벗어나면 예외가 발생한다")
        void createLottoWithOutOfRangeNumber(int invalidNumber) {
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, invalidNumber);

            assertThatThrownBy(() -> new Lotto(numbers))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }

        @Test
        @DisplayName("유효한 6개의 번호로 로또를 생성한다")
        void createLottoWithValidNumbers() {
            List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

            Lotto lotto = new Lotto(numbers);

            assertThat(lotto).isNotNull();
        }
    }

    @Nested
    @DisplayName("로또 번호를 출력 형식으로 변환할 때")
    class ToDisplayStringTest {

        @Test
        @DisplayName("생성 시점에 오름차순으로 정렬된다")
        void numbersAreSortedOnCreation() {
            List<Integer> unsortedNumbers = List.of(45, 1, 23, 10, 5, 30);

            Lotto lotto = new Lotto(unsortedNumbers);

            assertThat(lotto.toDisplayString()).isEqualTo("[1, 5, 10, 23, 30, 45]");
        }
    }

    @Nested
    @DisplayName("당첨 번호와 비교할 때")
    class CountMatchesTest {

        @Test
        @DisplayName("일치하는 번호 개수를 반환한다")
        void countMatchingNumbers() {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 7, 8, 9));

            int matchCount = lotto.countMatches(winningNumbers);

            assertThat(matchCount).isEqualTo(3);
        }

        @Test
        @DisplayName("일치하는 번호가 없으면 0을 반환한다")
        void countMatchingNumbersWithNoMatch() {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            WinningNumbers winningNumbers = new WinningNumbers(List.of(7, 8, 9, 10, 11, 12));

            int matchCount = lotto.countMatches(winningNumbers);

            assertThat(matchCount).isEqualTo(0);
        }

        @Test
        @DisplayName("모든 번호가 일치하면 6을 반환한다")
        void countMatchingNumbersWithAllMatch() {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

            int matchCount = lotto.countMatches(winningNumbers);

            assertThat(matchCount).isEqualTo(6);
        }
    }

    @Nested
    @DisplayName("보너스 번호를 확인할 때")
    class ContainsBonusTest {

        @Test
        @DisplayName("보너스 번호가 포함되어 있으면 true를 반환한다")
        void containsBonusNumber() {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            WinningNumbers winningNumbers = new WinningNumbers(List.of(7, 8, 9, 10, 11, 12));
            BonusNumber bonusNumber = new BonusNumber(3, winningNumbers);

            boolean result = lotto.containsBonus(bonusNumber);

            assertThat(result).isTrue();
        }

        @Test
        @DisplayName("보너스 번호가 포함되어 있지 않으면 false를 반환한다")
        void doesNotContainBonusNumber() {
            Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            WinningNumbers winningNumbers = new WinningNumbers(List.of(7, 8, 9, 10, 11, 12));
            BonusNumber bonusNumber = new BonusNumber(13, winningNumbers);

            boolean result = lotto.containsBonus(bonusNumber);

            assertThat(result).isFalse();
        }
    }
}
