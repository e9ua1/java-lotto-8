package lotto.domain.ticket;

import lotto.domain.winning.BonusNumber;
import lotto.domain.winning.Rank;
import lotto.domain.winning.WinningNumbers;
import lotto.domain.winning.WinningStatistics;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public int size() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }

    public WinningStatistics calculateStatistics(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        Map<Rank, Long> rankCounts = lottos.stream()
                .map(lotto -> calculateRank(lotto, winningNumbers, bonusNumber))
                .filter(Rank::isWinning)
                .collect(Collectors.groupingBy(
                        rank -> rank,
                        () -> new EnumMap<>(Rank.class),
                        Collectors.counting()
                ));

        return new WinningStatistics(rankCounts);
    }

    private Rank calculateRank(Lotto lotto, WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        int matchCount = lotto.countMatches(winningNumbers);
        boolean hasBonus = lotto.containsBonus(bonusNumber);
        return Rank.of(matchCount, hasBonus);
    }
}
