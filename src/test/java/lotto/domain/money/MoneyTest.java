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
    @DisplayName("금액을 조회할 때")
    class GetAmountTest {

        @Test
        @DisplayName("저장된 금액을 반환한다")
        void getAmount() {
            Money money = new Money(5000);

            int amount = money.getAmount();

            assertThat(amount).isEqualTo(5000);
        }
    }
}
