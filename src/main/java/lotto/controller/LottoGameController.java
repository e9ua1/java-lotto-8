package lotto.controller;

import java.util.List;
import java.util.function.Supplier;

import lotto.domain.money.PurchaseAmount;
import lotto.domain.ticket.LottoGenerator;
import lotto.domain.ticket.Lottos;
import lotto.domain.winning.BonusNumber;
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
        PurchaseAmount purchaseAmount = readPurchaseAmount();
        Lottos lottos = generateLottos(purchaseAmount);
        outputView.printLottos(lottos);

        WinningNumbers winningNumbers = readWinningNumbers();
        BonusNumber bonusNumber = readBonusNumber(winningNumbers);

        WinningStatistics statistics = lottos.calculateStatistics(winningNumbers, bonusNumber);
        printResult(statistics, purchaseAmount);
    }

    private PurchaseAmount readPurchaseAmount() {
        return retry(() -> {
            String input = inputView.readPurchaseAmount();
            int amount = InputParser.parseInt(input);
            return new PurchaseAmount(amount);
        });
    }

    private Lottos generateLottos(PurchaseAmount purchaseAmount) {
        Lottos lottos = lottoGenerator.generate(purchaseAmount);
        outputView.printPurchaseCount(lottos.size());
        return lottos;
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

    private void printResult(WinningStatistics statistics, PurchaseAmount purchaseAmount) {
        outputView.printStatistics(statistics);
        double returnRate = statistics.calculateReturnRate(purchaseAmount);
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
