package lotto.domain.money;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("PurchaseAmount 테스트")
class PurchaseAmountTest {

    @Nested
    @DisplayName("구입 금액을 생성할 때")
    class CreateTest {

        @Test
        @DisplayName("1,000원 단위 금액으로 생성한다")
        void createPurchaseAmountWithValidUnit() {
            PurchaseAmount purchaseAmount = new PurchaseAmount(8000);

            assertThat(purchaseAmount).isNotNull();
        }

        @ParameterizedTest
        @ValueSource(ints = {0, -1000, -500})
        @DisplayName("금액이 0 이하면 예외가 발생한다")
        void createPurchaseAmountWithNonPositiveAmount(int amount) {
            assertThatThrownBy(() -> new PurchaseAmount(amount))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]");
        }

        @ParameterizedTest
        @ValueSource(ints = {500, 1500, 2300, 999})
        @DisplayName("금액이 1,000원 단위가 아니면 예외가 발생한다")
        void createPurchaseAmountWithInvalidUnit(int amount) {
            assertThatThrownBy(() -> new PurchaseAmount(amount))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("[ERROR]")
                    .hasMessageContaining("1,000");
        }

        @ParameterizedTest
        @ValueSource(ints = {1000, 2000, 5000, 10000})
        @DisplayName("다양한 1,000원 단위로 생성한다")
        void createPurchaseAmountWithVariousValidUnits(int amount) {
            PurchaseAmount purchaseAmount = new PurchaseAmount(amount);

            assertThat(purchaseAmount).isNotNull();
        }
    }

    @Nested
    @DisplayName("로또 구매 개수를 계산할 때")
    class CalculateLottoCountTest {

        @Test
        @DisplayName("1,000원으로 1개를 구매한다")
        void calculateLottoCountWithThousand() {
            PurchaseAmount purchaseAmount = new PurchaseAmount(1000);

            int count = purchaseAmount.calculateLottoCount();

            assertThat(count).isEqualTo(1);
        }

        @Test
        @DisplayName("8,000원으로 8개를 구매한다")
        void calculateLottoCountWithEightThousand() {
            PurchaseAmount purchaseAmount = new PurchaseAmount(8000);

            int count = purchaseAmount.calculateLottoCount();

            assertThat(count).isEqualTo(8);
        }

        @Test
        @DisplayName("5,000원으로 5개를 구매한다")
        void calculateLottoCountWithFiveThousand() {
            PurchaseAmount purchaseAmount = new PurchaseAmount(5000);

            int count = purchaseAmount.calculateLottoCount();

            assertThat(count).isEqualTo(5);
        }
    }

    @Nested
    @DisplayName("수익률을 계산할 때")
    class CalculateReturnRateTest {

        @Test
        @DisplayName("구입 금액으로 수익률을 계산한다")
        void calculateReturnRate() {
            PurchaseAmount purchaseAmount = new PurchaseAmount(8000);

            double returnRate = purchaseAmount.calculateReturnRate(5000);

            assertThat(returnRate).isEqualTo(62.5);
        }
    }
}
