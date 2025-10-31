package lotto.domain.ticket;

import lotto.domain.winning.BonusNumber;
import lotto.domain.winning.Rank;
import lotto.domain.winning.WinningNumbers;
import lotto.domain.winning.WinningStatistics;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Lottos 테스트")
class LottosTest {

    @Nested
    @DisplayName("당첨 결과를 계산할 때")
    class CalculateStatisticsTest {

        @Test
        @DisplayName("각 로또의 당첨 등수를 계산한다")
        void calculateStatistics() {
            Lotto lotto1 = new Lotto(List.of(1, 2, 3, 4, 5, 6));
            Lotto lotto2 = new Lotto(List.of(1, 2, 3, 4, 5, 7));
            Lotto lotto3 = new Lotto(List.of(1, 2, 3, 4, 8, 9));
            Lottos lottos = new Lottos(List.of(lotto1, lotto2, lotto3));

            WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
            BonusNumber bonusNumber = new BonusNumber(7, winningNumbers);

            WinningStatistics statistics = lottos.calculateStatistics(winningNumbers, bonusNumber);

            assertThat(statistics.getCountByRank(Rank.FIRST)).isEqualTo(1);
            assertThat(statistics.getCountByRank(Rank.SECOND)).isEqualTo(1);
            assertThat(statistics.getCountByRank(Rank.FOURTH)).isEqualTo(1);
        }

        @Test
        @DisplayName("낙첨된 로또는 통계에 포함하지 않는다")
        void calculateStatisticsWithoutNone() {
            Lotto lotto1 = new Lotto(List.of(1, 2, 3, 10, 11, 12));
            Lotto lotto2 = new Lotto(List.of(1, 2, 13, 14, 15, 16));
            Lottos lottos = new Lottos(List.of(lotto1, lotto2));

            WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
            BonusNumber bonusNumber = new BonusNumber(7, winningNumbers);

            WinningStatistics statistics = lottos.calculateStatistics(winningNumbers, bonusNumber);

            assertThat(statistics.getCountByRank(Rank.NONE)).isEqualTo(0);
        }

        @Test
        @DisplayName("모든 로또가 낙첨이면 당첨 통계가 비어있다")
        void calculateStatisticsAllNone() {
            Lotto lotto1 = new Lotto(List.of(10, 11, 12, 13, 14, 15));
            Lotto lotto2 = new Lotto(List.of(20, 21, 22, 23, 24, 25));
            Lottos lottos = new Lottos(List.of(lotto1, lotto2));

            WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
            BonusNumber bonusNumber = new BonusNumber(7, winningNumbers);

            WinningStatistics statistics = lottos.calculateStatistics(winningNumbers, bonusNumber);

            assertThat(statistics.getCountByRank(Rank.FIRST)).isEqualTo(0);
            assertThat(statistics.getCountByRank(Rank.SECOND)).isEqualTo(0);
            assertThat(statistics.getCountByRank(Rank.THIRD)).isEqualTo(0);
            assertThat(statistics.getCountByRank(Rank.FOURTH)).isEqualTo(0);
            assertThat(statistics.getCountByRank(Rank.FIFTH)).isEqualTo(0);
        }
    }
}
