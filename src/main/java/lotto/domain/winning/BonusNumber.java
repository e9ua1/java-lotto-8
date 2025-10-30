package lotto.domain.winning;

public class BonusNumber {
    private final int number;

    public BonusNumber(int number, WinningNumbers winningNumbers) {
        this.number = number;
    }

    public int getValue() {
        return number;
    }
}
