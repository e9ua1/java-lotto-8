package lotto.view;

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
        System.out.printf(PURCHASE_COUNT_FORMAT + "%n", count);
    }

    public void printLottos(Lottos lottos) {
        lottos.forEach(this::printLotto);
    }

    private void printLotto(Lotto lotto) {
        System.out.printf(LOTTO_NUMBERS_FORMAT + "%n", lotto.toDisplayString());
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
        System.out.printf(RANK_FORMAT + "%n",
                rank.getDescription(),
                rank.getPrize(),
                count);
    }

    public void printReturnRate(double returnRate) {
        System.out.printf(RETURN_RATE_FORMAT + "%n", returnRate);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
