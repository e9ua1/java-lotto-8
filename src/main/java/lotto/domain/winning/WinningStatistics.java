package lotto.domain.winning;

import java.util.EnumMap;
import java.util.Map;

public class WinningStatistics {

    private final Map<Rank, Long> rankCounts;

    public WinningStatistics(Map<Rank, Long> rankCounts) {
        this.rankCounts = new EnumMap<>(rankCounts);
    }

    public long getCountByRank(Rank rank) {
        return rankCounts.getOrDefault(rank, 0L);
    }

    public TotalPrize calculateTotalPrize() {
        long amount = rankCounts.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
        return new TotalPrize(amount);
    }
}
