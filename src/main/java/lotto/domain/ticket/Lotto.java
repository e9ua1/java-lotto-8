package lotto.domain.ticket;

import java.util.List;

import lotto.domain.winning.BonusNumber;
import lotto.domain.winning.Rank;
import lotto.domain.winning.WinningNumbers;

public class Lotto {

    private final LottoNumbers numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = new LottoNumbers(numbers);
    }

    public String toDisplayString() {
        return numbers.getSortedNumbers().toString();
    }

    public Rank calculateRank(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        int matchCount = countMatches(winningNumbers);
        boolean hasBonus = containsBonus(bonusNumber);
        return Rank.of(matchCount, hasBonus);
    }

    public int countMatches(WinningNumbers winningNumbers) {
        return winningNumbers.countMatches(numbers);
    }

    public boolean containsBonus(BonusNumber bonusNumber) {
        return numbers.contains(bonusNumber.getValue());
    }
}
