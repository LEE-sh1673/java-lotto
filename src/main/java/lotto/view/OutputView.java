package lotto.view;

import java.text.DecimalFormat;
import java.util.List;

import lotto.model.Lotto;
import lotto.model.result.PlayResult;
import lotto.model.result.WinningStatistics;

public class OutputView {

    private static final String STATISTICS_TITLE = "당첨 통계";

    public static final String STATISTICS_LINE = "---";

    private static final String BONUS_MATCHES = ", 보너스 볼 일치";

    private static final DecimalFormat PROFIT_RATE_FORMAT = new DecimalFormat("#,##0.0");

    public static void printError(final Exception e) {
        System.out.println(e.getMessage());
    }

    public static void printLottoTickets(final List<Lotto> lottoTickets) {
        System.out.println();
        printFormatMessage(Format.LOTTO_TICKET_SIZE, lottoTickets.size());
        lottoTickets.forEach(System.out::println);
    }

    public static void printStatistics(final WinningStatistics statistics) {
        System.out.println();
        System.out.println(STATISTICS_TITLE);
        System.out.println(STATISTICS_LINE);
        statistics.getResults().forEach(OutputView::printPlayResult);
        printFormatMessage(Format.PROFIT_RATE, formatProfitRate(statistics));
    }

    private static void printPlayResult(final PlayResult result) {
        printFormatMessage(Format.PLAY_RESULT,
            result.getNumberOfMatches(),
            formatAmountWithBonus(result),
            result.getCount()
        );
    }

    private static String formatAmountWithBonus(final PlayResult result) {
        final StringBuilder sb = new StringBuilder();

        if (result.isBonusMatches()) {
            sb.append(BONUS_MATCHES);
        }
        sb.append(formatWinningAmount(result));
        return sb.toString();
    }

    private static String formatWinningAmount(final PlayResult result) {
        return Format.WINNING_AMOUNT.message(result.getWinningAmount());
    }

    private static String formatProfitRate(final WinningStatistics statistics) {
        return PROFIT_RATE_FORMAT.format(statistics.getProfitRate());
    }

    private static void printFormatMessage(final Format format, final Object... messages) {
        System.out.println(format.message(messages));
    }

    private enum Format {
        LOTTO_TICKET_SIZE("%d개를 구매했습니다."),
        PLAY_RESULT("%d개 일치%s - %d개"),
        WINNING_AMOUNT(" (%,d원)"),
        PROFIT_RATE("총 수익률은 %s%%입니다.");

        private final String format;

        Format(final String format) {
            this.format = format;
        }

        public String message(final Object... messages) {
            return String.format(this.format, messages);
        }
    }
}
