package lotto.domain.money;

public class PurchaseAmount extends Money {

    private static final int LOTTO_PRICE = 1000;

    public PurchaseAmount(int amount) {
        super(amount);
        validateUnit(amount);
    }

    private void validateUnit(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
        }
    }

    public int calculateLottoCount() {
        return getAmount() / LOTTO_PRICE;
    }
}
