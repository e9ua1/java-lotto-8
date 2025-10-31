package lotto.domain.winning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import lotto.domain.money.Money;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("TotalPrize 테스트")
class TotalPrizeTest {

    @Nested
    @DisplayName("수익률을 계산할 때")
    class CalculateReturnRateTest {

        @Test
        @DisplayName("총 상금 5,000원, 구입 금액 8,000원일 때 62.5%를 반환한다")
        void calculateReturnRate() {
            TotalPrize totalPrize = new TotalPrize(5000);
            Money purchaseAmount = new Money(8000);

            double returnRate = totalPrize.calculateReturnRate(purchaseAmount);

            assertThat(returnRate).isEqualTo(62.5);
        }

        @Test
        @DisplayName("총 상금 0원일 때 0%를 반환한다")
        void calculateReturnRateWithZeroPrize() {
            TotalPrize totalPrize = new TotalPrize(0);
            Money purchaseAmount = new Money(8000);

            double returnRate = totalPrize.calculateReturnRate(purchaseAmount);

            assertThat(returnRate).isEqualTo(0.0);
        }

        @Test
        @DisplayName("총 상금 2,000,000,000원, 구입 금액 8,000원일 때 25,000,000%를 반환한다")
        void calculateReturnRateWithLargePrize() {
            TotalPrize totalPrize = new TotalPrize(2_000_000_000L);
            Money purchaseAmount = new Money(8000);

            double returnRate = totalPrize.calculateReturnRate(purchaseAmount);

            assertThat(returnRate).isEqualTo(25_000_000.0);
        }
    }

    @Nested
    @DisplayName("총 상금 금액을 조회할 때")
    class GetAmountTest {

        @Test
        @DisplayName("설정한 금액을 반환한다")
        void getAmount() {
            TotalPrize totalPrize = new TotalPrize(5000);

            long amount = totalPrize.getAmount();

            assertThat(amount).isEqualTo(5000);
        }
    }
}
