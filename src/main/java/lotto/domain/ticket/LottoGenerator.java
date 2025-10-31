package lotto.domain.ticket;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.money.PurchaseAmount;

public class LottoGenerator {

    private static final int LOTTO_NUMBER_START = 1;
    private static final int LOTTO_NUMBER_END = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    public Lottos generate(PurchaseAmount purchaseAmount) {
        int count = purchaseAmount.calculateLottoCount();

        List<Lotto> lottos = IntStream.range(0, count)
                .mapToObj(i -> generateLotto())
                .collect(Collectors.toList());

        return new Lottos(lottos);
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
