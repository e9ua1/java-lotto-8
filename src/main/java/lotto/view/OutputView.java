package lotto.view;

import java.util.List;

import lotto.domain.ticket.Lotto;
import lotto.domain.ticket.Lottos;
import lotto.domain.winning.Rank;
import lotto.domain.winning.WinningStatistics;

public class OutputView {

    private static final String PURCHASE_COUNT_FORMAT = "\n%d개를 구매했습니다.";
    private static final String LOTTO_NUMBERS_FORMAT = "%s";
    private static final String STATISTICS_HEADER = "\n당첨 통계\n---";
    private static final String RANK_FORMAT = "%s (%,d원) - %d개";
    private static final String RETURN_RATE_FORMAT = "총 수익률은 %.1f%%입니다.";

    public void printPurchaseCount(int count) {
        System.out.println(String.format(PURCHASE_COUNT_FORMAT, count));
    }

    public void printLottos(Lottos lottos) {
        List<Lotto> lottoList = lottos.getLottos();
        for (Lotto lotto : lottoList) {
            printLotto(lotto);
        }
    }

    private void printLotto(Lotto lotto) {
        System.out.println(String.format(LOTTO_NUMBERS_FORMAT, lotto.getNumbers()));
    }

    public void printStatistics(WinningStatistics statistics) {
        System.out.println(STATISTICS_HEADER);
        printRank(statistics, Rank.FIFTH);
        printRank(statistics, Rank.FOURTH);
        printRank(statistics, Rank.THIRD);
        printRank(statistics, Rank.SECOND);
        printRank(statistics, Rank.FIRST);
    }

    private void printRank(WinningStatistics statistics, Rank rank) {
        long count = statistics.getCountByRank(rank);
        System.out.println(String.format(RANK_FORMAT,
                rank.getDescription(),
                rank.getPrize(),
                count));
    }

    public void printReturnRate(double returnRate) {
        System.out.println(String.format(RETURN_RATE_FORMAT, returnRate));
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
