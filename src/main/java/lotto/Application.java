package lotto;

import lotto.controller.LottoGameController;
import lotto.domain.ticket.LottoGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoGenerator lottoGenerator = new LottoGenerator();

        LottoGameController controller = new LottoGameController(inputView, outputView, lottoGenerator);
        controller.run();
    }
}
