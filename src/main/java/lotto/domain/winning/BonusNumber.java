package lotto.domain.winning;

public class BonusNumber {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

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
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
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
