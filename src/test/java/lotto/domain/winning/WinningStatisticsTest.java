package lotto.domain.winning;

import java.util.EnumMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import lotto.domain.money.Money;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("WinningStatistics 테스트")
class WinningStatisticsTest {

    @Nested
    @DisplayName("총 상금을 계산할 때")
    class CalculateTotalPrizeTest {

        @Test
        @DisplayName("5등 1개 당첨 시 5,000원을 반환한다")
        void calculateTotalPrizeWithFifth() {
            Map<Rank, Long> rankCounts = new EnumMap<>(Rank.class);
            rankCounts.put(Rank.FIFTH, 1L);
            WinningStatistics statistics = new WinningStatistics(rankCounts);

            TotalPrize totalPrize = statistics.calculateTotalPrize();

            assertThat(totalPrize.getAmount()).isEqualTo(5_000);
        }

        @Test
        @DisplayName("1등 1개 당첨 시 2,000,000,000원을 반환한다")
        void calculateTotalPrizeWithFirst() {
            Map<Rank, Long> rankCounts = new EnumMap<>(Rank.class);
            rankCounts.put(Rank.FIRST, 1L);
            WinningStatistics statistics = new WinningStatistics(rankCounts);

            TotalPrize totalPrize = statistics.calculateTotalPrize();

            assertThat(totalPrize.getAmount()).isEqualTo(2_000_000_000);
        }

        @Test
        @DisplayName("여러 등수 당첨 시 총 상금을 반환한다")
        void calculateTotalPrizeWithMultipleRanks() {
            Map<Rank, Long> rankCounts = new EnumMap<>(Rank.class);
            rankCounts.put(Rank.FIFTH, 2L);
            rankCounts.put(Rank.FOURTH, 1L);
            WinningStatistics statistics = new WinningStatistics(rankCounts);

            TotalPrize totalPrize = statistics.calculateTotalPrize();

            assertThat(totalPrize.getAmount()).isEqualTo(60_000);
        }

        @Test
        @DisplayName("당첨되지 않으면 0원을 반환한다")
        void calculateTotalPrizeWithNoWinning() {
            Map<Rank, Long> rankCounts = new EnumMap<>(Rank.class);
            WinningStatistics statistics = new WinningStatistics(rankCounts);

            TotalPrize totalPrize = statistics.calculateTotalPrize();

            assertThat(totalPrize.getAmount()).isEqualTo(0);
        }
    }

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

            TotalPrize totalPrize = statistics.calculateTotalPrize();
            double returnRate = totalPrize.calculateReturnRate(purchaseAmount);

            assertThat(returnRate).isEqualTo(62.5);
        }

        @Test
        @DisplayName("1등 당첨 시 수익률을 계산한다")
        void calculateReturnRateWithFirst() {
            Map<Rank, Long> rankCounts = new EnumMap<>(Rank.class);
            rankCounts.put(Rank.FIRST, 1L);
            WinningStatistics statistics = new WinningStatistics(rankCounts);
            Money purchaseAmount = new Money(8000);

            TotalPrize totalPrize = statistics.calculateTotalPrize();
            double returnRate = totalPrize.calculateReturnRate(purchaseAmount);

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

            TotalPrize totalPrize = statistics.calculateTotalPrize();
            double returnRate = totalPrize.calculateReturnRate(purchaseAmount);

            assertThat(returnRate).isEqualTo(750.0);
        }

        @Test
        @DisplayName("당첨되지 않으면 수익률은 0이다")
        void calculateReturnRateWithNoWinning() {
            Map<Rank, Long> rankCounts = new EnumMap<>(Rank.class);
            WinningStatistics statistics = new WinningStatistics(rankCounts);
            Money purchaseAmount = new Money(8000);

            TotalPrize totalPrize = statistics.calculateTotalPrize();
            double returnRate = totalPrize.calculateReturnRate(purchaseAmount);

            assertThat(returnRate).isEqualTo(0.0);
        }
    }
}
