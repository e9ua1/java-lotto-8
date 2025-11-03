package lotto.domain.winning;

import java.util.Arrays;

public enum Rank {

    FIRST(6, false, 2_000_000_000, "6개 일치"),
    SECOND(5, true, 30_000_000, "5개 일치, 보너스 볼 일치"),
    THIRD(5, false, 1_500_000, "5개 일치"),
    FOURTH(4, false, 50_000, "4개 일치"),
    FIFTH(3, false, 5_000, "3개 일치"),
    NONE(0, false, 0, "낙첨");

    private static final int MINIMUM_WINNING_COUNT = 3;

    private final int matchCount;
    private final boolean bonusMatch;
    private final int prize;
    private final String description;

    Rank(int matchCount, boolean bonusMatch, int prize, String description) {
        this.matchCount = matchCount;
        this.bonusMatch = bonusMatch;
        this.prize = prize;
        this.description = description;
    }

    public static Rank of(int matchCount, boolean bonusMatch) {
        if (matchCount < MINIMUM_WINNING_COUNT) {
            return NONE;
        }

        return Arrays.stream(values())
                .filter(rank -> rank != NONE)
                .filter(rank -> rank.matches(matchCount, bonusMatch))
                .findFirst()
                .orElse(NONE);
    }

    private boolean matches(int matchCount, boolean bonusMatch) {
        if (this.matchCount != matchCount) {
            return false;
        }

        return this.bonusMatch == bonusMatch;
    }

    public int getPrize() {
        return prize;
    }

    public boolean isWinning() {
        return this != NONE;
    }

    public String getDescription() {
        return description;
    }
}
