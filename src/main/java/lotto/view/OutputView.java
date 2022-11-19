package lotto.view;

import java.util.Arrays;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Rank;

public class OutputView {

    private static final String NUMBER_OF_LOTTO_FORMAT = "%d개를 구매했습니다.\n";

    private static final String LOTTO_RESULT_HEADER_MESSAGE = "\n당첨 통계\n---";

    private static final String LOTTO_RESULT_MATCH_COUNT_FORMAT = "%s개 일치";

    private static final String LOTTO_RESULT_BONUS_MATCH_FORMAT = ", 보너스 볼 일치";

    private static final String LOTTO_RESULT_WINNING_PRICE_FORMAT = "(%,d원)";

    private static final String LOTTO_RESULT_LOTTO_COUNT_FORMAT = "- %d개\n";

    private static final String PROFIT_RATE_FORMAT = "총 수익률은 %,.1f%%입니다.";

    public static void printLottos(final Lottos lottos) {
        System.out.printf(NUMBER_OF_LOTTO_FORMAT, lottos.size());
        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public static void printLottoResult(final LottoResult lottoResult) {
        System.out.println(LOTTO_RESULT_HEADER_MESSAGE);

        Arrays.stream(Rank.values())
            .filter(rank -> rank != Rank.MISS)
            .forEach(rank -> printLottoResult(lottoResult, rank));
    }

    private static void printLottoResult(final LottoResult lottoResult, final Rank rank) {
        String countOfMatch = formatCountOfMatch(rank);
        String winningPrice = formatWinningPrice(rank);
        String lottoCount = formatLottoCount(lottoResult.getCount(rank));
        System.out.printf(String.join(" ", countOfMatch, winningPrice, lottoCount));
    }

    private static String formatCountOfMatch(final Rank rank) {
        String format
            = String.format(LOTTO_RESULT_MATCH_COUNT_FORMAT, rank.getCountOfMatch());

        if (rank == Rank.SECOND) {
            format += LOTTO_RESULT_BONUS_MATCH_FORMAT;
        }
        return format;
    }

    private static String formatWinningPrice(final Rank rank) {
        return String.format(LOTTO_RESULT_WINNING_PRICE_FORMAT, rank.getWinningPrice());
    }

    private static String formatLottoCount(final int lottoCount) {
        return String.format(LOTTO_RESULT_LOTTO_COUNT_FORMAT, lottoCount);
    }

    public static void printProfitRate(final double profitRate) {
        System.out.printf(PROFIT_RATE_FORMAT, profitRate);
    }
}
