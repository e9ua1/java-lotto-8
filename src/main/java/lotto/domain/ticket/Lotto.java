package lotto.domain.ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lotto.domain.winning.BonusNumber;
import lotto.domain.winning.WinningNumbers;

public class Lotto {

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validateNumbers(numbers);
        this.numbers = createSortedNumbers(numbers);
    }

    private void validateNumbers(List<Integer> numbers) {
        new LottoNumbers(numbers);
    }

    private List<Integer> createSortedNumbers(List<Integer> numbers) {
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        return sortedNumbers;
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
