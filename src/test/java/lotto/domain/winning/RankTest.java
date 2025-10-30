package lotto.domain.winning;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @Nested
    @DisplayName("등수를 판정할 때")
    class OfTest {

        @Test
        @DisplayName("6개 일치하면 1등이다")
        void firstRank() {
            Rank rank = Rank.of(6, false);

            assertThat(rank).isEqualTo(Rank.FIRST);
        }

        @Test
        @DisplayName("5개 일치하고 보너스 번호가 일치하면 2등이다")
        void secondRank() {
            Rank rank = Rank.of(5, true);

            assertThat(rank).isEqualTo(Rank.SECOND);
        }

        @Test
        @DisplayName("5개 일치하고 보너스 번호가 불일치하면 3등이다")
        void thirdRank() {
            Rank rank = Rank.of(5, false);

            assertThat(rank).isEqualTo(Rank.THIRD);
        }

        @Test
        @DisplayName("4개 일치하면 4등이다")
        void fourthRank() {
            Rank rank = Rank.of(4, false);

            assertThat(rank).isEqualTo(Rank.FOURTH);
        }

        @Test
        @DisplayName("3개 일치하면 5등이다")
        void fifthRank() {
            Rank rank = Rank.of(3, false);

            assertThat(rank).isEqualTo(Rank.FIFTH);
        }

        @Test
        @DisplayName("3개 미만 일치하면 낙첨이다")
        void noneRank() {
            Rank rank = Rank.of(2, false);

            assertThat(rank).isEqualTo(Rank.NONE);
        }

        @Test
        @DisplayName("0개 일치하면 낙첨이다")
        void noneRankWithZeroMatch() {
            Rank rank = Rank.of(0, false);

            assertThat(rank).isEqualTo(Rank.NONE);
        }
    }
}
