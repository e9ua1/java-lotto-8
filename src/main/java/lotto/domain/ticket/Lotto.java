package lotto.domain.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lotto.domain.winning.BonusNumber;
import lotto.domain.winning.WinningNumbers;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        LottoNumbers lottoNumbers = new LottoNumbers(numbers);
        this.numbers = new ArrayList<>(lottoNumbers.getNumbers());
        Collections.sort(this.numbers);
    }

    public String toDisplayString() {
        return numbers.toString();
    }

    public int countMatches(WinningNumbers winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean containsBonus(BonusNumber bonusNumber) {
        return numbers.contains(bonusNumber.getValue());
    }
}
