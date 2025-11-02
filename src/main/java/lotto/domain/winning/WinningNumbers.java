package lotto.domain.winning;

import java.util.List;

import lotto.domain.ticket.LottoNumbers;

public class WinningNumbers {

    private final LottoNumbers lottoNumbers;

    public WinningNumbers(List<Integer> numbers) {
        this.lottoNumbers = new LottoNumbers(numbers);
    }

    public boolean contains(int number) {
        return lottoNumbers.contains(number);
    }

    public int countMatches(LottoNumbers userNumbers) {
        return (int) lottoNumbers.countMatches(userNumbers);
    }
}
