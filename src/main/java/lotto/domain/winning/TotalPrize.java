package lotto.domain.winning;

import lotto.domain.money.Money;

public class TotalPrize {

    private final long amount;

    public TotalPrize(long amount) {
        this.amount = amount;
    }

    public double calculateReturnRate(Money purchaseAmount) {
        return purchaseAmount.calculateReturnRate(amount);
    }

    public long getAmount() {
        return amount;
    }
}
