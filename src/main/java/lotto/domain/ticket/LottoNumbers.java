package lotto.domain.ticket;

import java.util.HashSet;
import java.util.List;

public class LottoNumbers {

    private static final int LOTTO_NUMBER_SIZE = 6;

    private final List<Integer> numbers;

    public LottoNumbers(List<Integer> numbers) {
        validate(numbers);
        this.numbers = List.copyOf(numbers);
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        validateRange(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        if (numbers.size() != new HashSet<>(numbers).size()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호에 중복된 숫자가 있습니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        if (numbers.stream().anyMatch(number -> !LottoNumber.isInRange(number))) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public long countMatches(LottoNumbers other) {
        return numbers.stream()
                .filter(other::contains)
                .count();
    }

    public List<Integer> getSortedNumbers() {
        return numbers.stream()
                .sorted()
                .toList();
    }
}
