package lotto.domain.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Money 테스트")
class MoneyTest {

    @Nested
    @DisplayName("금액을 생성할 때")
    class CreateTest {

        @Test
        @DisplayName("양수 금액으로 생성한다")
        void createMoneyWithPositiveAmount() {
            Money money = new Money(1000);

            assertThat(money).isNotNull();
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1000, -500})
        @DisplayName("금액이 0 이하면 예외가 발생한다")
        void createMoneyWithNonPositiveAmount(int amount) {
            assertThatThrownBy(() -> new Money(amount))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }

        @ParameterizedTest
        @ValueSource(ints = {500, 1500, 2300, 999})
        @DisplayName("금액이 1,000원 단위가 아니면 예외가 발생한다")
        void createMoneyWithInvalidUnit(int amount) {
            assertThatThrownBy(() -> new Money(amount))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]")
                    .hasMessageContaining("1,000");
        }

        @ParameterizedTest
        @ValueSource(ints = {1000, 2000, 5000, 10000})
        @DisplayName("1,000원 단위 금액으로 생성한다")
        void createMoneyWithValidUnit(int amount) {
            Money money = new Money(amount);

            assertThat(money).isNotNull();
        }
    }

    @Nested
    @DisplayName("로또 구매 개수를 계산할 때")
    class CalculateLottoCountTest {

        @Test
        @DisplayName("1,000원으로 1개를 구매한다")
        void calculateLottoCountWithThousand() {
            Money money = new Money(1000);

            int count = money.calculateLottoCount();

            assertThat(count).isEqualTo(1);
        }

        @Test
        @DisplayName("8,000원으로 8개를 구매한다")
        void calculateLottoCountWithEightThousand() {
            Money money = new Money(8000);

            int count = money.calculateLottoCount();

            assertThat(count).isEqualTo(8);
        }
    }

    @Nested
    @DisplayName("수익률을 계산할 때")
    class CalculateReturnRateTest {

        @Test
        @DisplayName("당첨 금액 5,000원, 구입 금액 8,000원일 때 62.5%를 반환한다")
        void calculateReturnRate() {
            Money money = new Money(8000);

            double returnRate = money.calculateReturnRate(5000);

            assertThat(returnRate).isEqualTo(62.5);
        }

        @Test
        @DisplayName("당첨 금액 0원일 때 0%를 반환한다")
        void calculateReturnRateWithZeroPrize() {
            Money money = new Money(8000);

            double returnRate = money.calculateReturnRate(0);

            assertThat(returnRate).isEqualTo(0.0);
        }

        @Test
        @DisplayName("당첨 금액 2,000,000,000원, 구입 금액 8,000원일 때 25,000,000%를 반환한다")
        void calculateReturnRateWithFirstPrize() {
            Money money = new Money(8000);

            double returnRate = money.calculateReturnRate(2_000_000_000L);

            assertThat(returnRate).isEqualTo(25_000_000.0);
        }
    }
}
