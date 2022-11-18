package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Rank;

public class OutputView {

    private static final String NUMBER_OF_LOTTO_FORMAT = "%d개를 구매했습니다.\n";

    private static final String LOTTO_RESULT_HEADER_MESSAGE = "\n당첨 통계\n---";

    private static final String LOTTO_RESULT_FORMAT = "%s개 일치 (%,d원) - %d개\n";

    private static final String PROFIT_RATE_FORMAT = "총 수익률은 %,.1f%%입니다.";

    public static void printLottos(final Lottos lottos) {
        System.out.printf(NUMBER_OF_LOTTO_FORMAT, lottos.size());
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public static void printLottoResult(final LottoResult lottoResult) {
        System.out.println(LOTTO_RESULT_HEADER_MESSAGE);
        lottoResult.keySet()
            .stream()
            .filter(rank -> rank != Rank.MISS)
            .forEach(rank -> printLottoResult(lottoResult, rank));
    }

    private static void printLottoResult(final LottoResult lottoResult, final Rank rank) {
        System.out.printf(LOTTO_RESULT_FORMAT,
            rank.getCountOfMatch(), rank.getWinningPrice(), lottoResult.getCount(rank)
        );
    }

    public static void printProfitRate(final double profitRate) {
        System.out.printf(PROFIT_RATE_FORMAT, profitRate);
    }
}
