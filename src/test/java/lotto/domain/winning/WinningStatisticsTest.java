package lotto.domain.winning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import lotto.domain.money.Money;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("WinningStatistics 테스트")
class WinningStatisticsTest {

    @Nested
    @DisplayName("수익률을 계산할 때")
    class CalculateReturnRateTest {

        @Test
        @DisplayName("당첨 금액을 구입 금액으로 나눈 백분율을 반환한다")
        void calculateReturnRate() {
            Map<Rank, Long> rankCounts = new EnumMap<>(Rank.class);
            rankCounts.put(Rank.FIFTH, 1L);
            WinningStatistics statistics = new WinningStatistics(rankCounts);

            Money purchaseAmount = new Money(8000);

            double returnRate = statistics.calculateReturnRate(purchaseAmount);

            assertThat(returnRate).isEqualTo(62.5);
        }

        @Test
        @DisplayName("1등 당첨 시 수익률을 계산한다")
        void calculateReturnRateWithFirst() {
            Map<Rank, Long> rankCounts = new EnumMap<>(Rank.class);
            rankCounts.put(Rank.FIRST, 1L);
            WinningStatistics statistics = new WinningStatistics(rankCounts);

            Money purchaseAmount = new Money(8000);

            double returnRate = statistics.calculateReturnRate(purchaseAmount);

            assertThat(returnRate).isEqualTo(25_000_000.0);
        }

        @Test
        @DisplayName("여러 등수 당첨 시 총 상금으로 수익률을 계산한다")
        void calculateReturnRateWithMultipleRanks() {
            Map<Rank, Long> rankCounts = new EnumMap<>(Rank.class);
            rankCounts.put(Rank.FIFTH, 2L);
            rankCounts.put(Rank.FOURTH, 1L);
            WinningStatistics statistics = new WinningStatistics(rankCounts);

            Money purchaseAmount = new Money(8000);

            double returnRate = statistics.calculateReturnRate(purchaseAmount);

            assertThat(returnRate).isEqualTo(750.0);
        }

        @Test
        @DisplayName("당첨되지 않으면 수익률은 0이다")
        void calculateReturnRateWithNoWinning() {
            Map<Rank, Long> rankCounts = new EnumMap<>(Rank.class);
            WinningStatistics statistics = new WinningStatistics(rankCounts);

            Money purchaseAmount = new Money(8000);

            double returnRate = statistics.calculateReturnRate(purchaseAmount);

            assertThat(returnRate).isEqualTo(0.0);
        }
    }
}
