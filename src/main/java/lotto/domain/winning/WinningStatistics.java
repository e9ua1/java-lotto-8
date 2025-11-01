package lotto.domain.winning;

import java.util.EnumMap;
import java.util.Map;

import lotto.domain.money.PurchaseAmount;

public class WinningStatistics {

    private final Map<Rank, Long> rankCounts;

    public WinningStatistics(Map<Rank, Long> rankCounts) {
        this.rankCounts = new EnumMap<>(rankCounts);
    }

    public long getCountByRank(Rank rank) {
        return rankCounts.getOrDefault(rank, 0L);
    }

    public long calculateTotalPrize() {
        return rankCounts.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
    }

    public double calculateReturnRate(PurchaseAmount purchaseAmount) {
        long totalPrize = calculateTotalPrize();
        return purchaseAmount.calculateReturnRate(totalPrize);
    }
}
