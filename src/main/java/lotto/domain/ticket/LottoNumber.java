package lotto.domain.ticket;

public class LottoNumber {

    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private final int value;

    public LottoNumber(int value) {
        validateRange(value);
        this.value = value;
    }

    private void validateRange(int value) {
        if (MIN_LOTTO_NUMBER > value || value > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    public static boolean isInRange(int value) {
        return MIN_LOTTO_NUMBER <= value && value <= MAX_LOTTO_NUMBER;
    }

    public int getValue() {
        return value;
    }
}
