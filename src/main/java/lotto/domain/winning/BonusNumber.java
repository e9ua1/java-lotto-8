package lotto.domain.winning;

import lotto.domain.ticket.LottoNumber;

public class BonusNumber {

    private final int number;

    public BonusNumber(int number, WinningNumbers winningNumbers) {
        validate(number, winningNumbers);
        this.number = number;
    }

    private void validate(int number, WinningNumbers winningNumbers) {
        validateRange(number);
        validateNotDuplicate(number, winningNumbers);
    }

    private void validateRange(int number) {
        if (!LottoNumber.isInRange(number)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    private void validateNotDuplicate(int number, WinningNumbers winningNumbers) {
        if (winningNumbers.contains(number)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public int getValue() {
        return number;
    }
}
