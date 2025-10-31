package lotto.controller;

import java.util.List;
import java.util.function.Supplier;

import lotto.domain.money.Money;
import lotto.domain.ticket.LottoGenerator;
import lotto.domain.ticket.Lottos;
import lotto.domain.winning.BonusNumber;
import lotto.domain.winning.TotalPrize;
import lotto.domain.winning.WinningNumbers;
import lotto.domain.winning.WinningStatistics;
import lotto.view.InputParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGameController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerator lottoGenerator;

    public LottoGameController(InputView inputView, OutputView outputView, LottoGenerator lottoGenerator) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerator = lottoGenerator;
    }

    public void run() {
        Money purchaseAmount = readPurchaseAmount();
        Lottos lottos = generateLottos(purchaseAmount);
        printLottos(lottos);

        WinningNumbers winningNumbers = readWinningNumbers();
        BonusNumber bonusNumber = readBonusNumber(winningNumbers);

        WinningStatistics statistics = lottos.calculateStatistics(winningNumbers, bonusNumber);
        printResult(statistics, purchaseAmount);
    }

    private Money readPurchaseAmount() {
        return retry(() -> {
            String input = inputView.readPurchaseAmount();
            int amount = InputParser.parseInt(input);
            return new Money(amount);
        });
    }

    private Lottos generateLottos(Money purchaseAmount) {
        Lottos lottos = lottoGenerator.generate(purchaseAmount);
        outputView.printPurchaseCount(lottos.size());
        return lottos;
    }

    private void printLottos(Lottos lottos) {
        outputView.printLottos(lottos);
    }

    private WinningNumbers readWinningNumbers() {
        return retry(() -> {
            String input = inputView.readWinningNumbers();
            List<Integer> numbers = InputParser.parseNumbers(input);
            return new WinningNumbers(numbers);
        });
    }

    private BonusNumber readBonusNumber(WinningNumbers winningNumbers) {
        return retry(() -> {
            String input = inputView.readBonusNumber();
            int number = InputParser.parseInt(input);
            return new BonusNumber(number, winningNumbers);
        });
    }

    private void printResult(WinningStatistics statistics, Money purchaseAmount) {
        outputView.printStatistics(statistics);
        TotalPrize totalPrize = statistics.calculateTotalPrize();
        double returnRate = totalPrize.calculateReturnRate(purchaseAmount);
        outputView.printReturnRate(returnRate);
    }

    private <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
