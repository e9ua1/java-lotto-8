package lotto.domain.ticket;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import lotto.domain.winning.BonusNumber;
import lotto.domain.winning.Rank;
import lotto.domain.winning.WinningNumbers;
import lotto.domain.winning.WinningStatistics;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = List.copyOf(lottos);
    }

    public WinningStatistics calculateStatistics(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        Map<Rank, Long> rankCounts = lottos.stream()
                .map(lotto -> lotto.calculateRank(winningNumbers, bonusNumber))
                .filter(Rank::isWinning)
                .collect(Collectors.groupingBy(
                        rank -> rank,
                        () -> new EnumMap<>(Rank.class),
                        Collectors.counting()
                ));

        return new WinningStatistics(rankCounts);
    }

    public void forEach(Consumer<Lotto> action) {
        lottos.forEach(action);
    }

    public int size() {
        return lottos.size();
    }
}
