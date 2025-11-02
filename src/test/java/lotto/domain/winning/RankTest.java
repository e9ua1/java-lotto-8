package lotto.domain.winning;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Rank 테스트")
class RankTest {

    @Nested
    @DisplayName("등수를 판정할 때")
    class OfTest {

        @Test
        @DisplayName("6개 일치하면 1등이다")
        void matchSixNumbersIsFirstRank() {
            Rank rank = Rank.of(6, false);

            assertThat(rank).isEqualTo(Rank.FIRST);
        }

        @Test
        @DisplayName("5개 일치하고 보너스 번호가 일치하면 2등이다")
        void matchFiveNumbersWithBonusIsSecondRank() {
            Rank rank = Rank.of(5, true);

            assertThat(rank).isEqualTo(Rank.SECOND);
        }

        @Test
        @DisplayName("5개 일치하고 보너스 번호가 불일치하면 3등이다")
        void matchFiveNumbersWithoutBonusIsThirdRank() {
            Rank rank = Rank.of(5, false);

            assertThat(rank).isEqualTo(Rank.THIRD);
        }

        @Test
        @DisplayName("4개 일치하면 4등이다")
        void matchFourNumbersIsFourthRank() {
            Rank rank = Rank.of(4, false);

            assertThat(rank).isEqualTo(Rank.FOURTH);
        }

        @Test
        @DisplayName("3개 일치하면 5등이다")
        void matchThreeNumbersIsFifthRank() {
            Rank rank = Rank.of(3, false);

            assertThat(rank).isEqualTo(Rank.FIFTH);
        }

        @Test
        @DisplayName("3개 미만 일치하면 낙첨이다")
        void matchLessThanThreeNumbersIsNone() {
            Rank rank = Rank.of(2, false);

            assertThat(rank).isEqualTo(Rank.NONE);
        }

        @Test
        @DisplayName("0개 일치하면 낙첨이다")
        void matchZeroNumbersIsNone() {
            Rank rank = Rank.of(0, false);

            assertThat(rank).isEqualTo(Rank.NONE);
        }
    }

    @Nested
    @DisplayName("상금을 조회할 때")
    class GetPrizeTest {

        @Test
        @DisplayName("1등 상금은 2,000,000,000원이다")
        void getFirstRankPrize() {
            assertThat(Rank.FIRST.getPrize()).isEqualTo(2_000_000_000);
        }

        @Test
        @DisplayName("2등 상금은 30,000,000원이다")
        void getSecondRankPrize() {
            assertThat(Rank.SECOND.getPrize()).isEqualTo(30_000_000);
        }

        @Test
        @DisplayName("3등 상금은 1,500,000원이다")
        void getThirdRankPrize() {
            assertThat(Rank.THIRD.getPrize()).isEqualTo(1_500_000);
        }

        @Test
        @DisplayName("4등 상금은 50,000원이다")
        void getFourthRankPrize() {
            assertThat(Rank.FOURTH.getPrize()).isEqualTo(50_000);
        }

        @Test
        @DisplayName("5등 상금은 5,000원이다")
        void getFifthRankPrize() {
            assertThat(Rank.FIFTH.getPrize()).isEqualTo(5_000);
        }

        @Test
        @DisplayName("낙첨 상금은 0원이다")
        void getNoneRankPrize() {
            assertThat(Rank.NONE.getPrize()).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("당첨 여부를 확인할 때")
    class IsWinningTest {

        @Test
        @DisplayName("1등은 당첨이다")
        void firstRankIsWinning() {
            assertThat(Rank.FIRST.isWinning()).isTrue();
        }

        @Test
        @DisplayName("5등은 당첨이다")
        void fifthRankIsWinning() {
            assertThat(Rank.FIFTH.isWinning()).isTrue();
        }

        @Test
        @DisplayName("낙첨은 당첨이 아니다")
        void noneRankIsNotWinning() {
            assertThat(Rank.NONE.isWinning()).isFalse();
        }
    }
}
