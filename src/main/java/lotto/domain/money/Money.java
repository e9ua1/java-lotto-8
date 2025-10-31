package lotto.domain.money;

public class Money {

    private final int amount;

    public Money(int amount) {
        validatePositive(amount);
        this.amount = amount;
    }

    private void validatePositive(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 0보다 커야 합니다.");
        }
    }

    public double calculateReturnRate(long totalPrize) {
        return (double) totalPrize / amount * 100;
    }

    protected int getAmount() {
        return amount;
    }
}
