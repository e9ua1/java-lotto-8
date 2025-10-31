package lotto.domain.ticket;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.domain.money.Money;

public class LottoGenerator {

    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_NUMBER_START = 1;
    private static final int LOTTO_NUMBER_END = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    public Lottos generate(Money money) {
        int count = calculateLottoCount(money);

        List<Lotto> lottos = IntStream.range(0, count)
                .mapToObj(i -> generateLotto())
                .collect(Collectors.toList());

        return new Lottos(lottos);
    }

    private int calculateLottoCount(Money money) {
        return money.getAmount() / LOTTO_PRICE;
    }

    private Lotto generateLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(
                LOTTO_NUMBER_START,
                LOTTO_NUMBER_END,
                LOTTO_NUMBER_COUNT
        );
        return new Lotto(numbers);
    }
}
