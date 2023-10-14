package lotto.view;

import java.text.DecimalFormat;
import java.util.List;
import java.util.StringJoiner;

import lotto.model.Lotto;
import lotto.model.result.PlayResult;
import lotto.model.result.WinningStatistics;
import lotto.model.result.Profit;

public class OutputView {

    private static final String JOINING_DELIMITER = "\n";

    private static final DecimalFormat PROFIT_RATE_FORMAT = new DecimalFormat("#,##0.0");

    public static void printLottoTickets(List<Lotto> lottoTickets) {
        System.out.println();
        System.out.printf(formatNumberOfTickets(lottoTickets.size()));
        lottoTickets.forEach(System.out::println);
    }

    private static String formatNumberOfTickets(final int numberOfTickets) {
        return String.format(
            Constants.OUTPUT_LOTTO_TICKET_COUNT_FORMAT.message,
            numberOfTickets
        );
    }

    public static void printStatistics(final WinningStatistics statistics) {
        final StringJoiner sj = new StringJoiner(JOINING_DELIMITER);
        sj.add(Constants.OUTPUT_STATISTICS_MESSAGE.message);
        sj.add(Constants.OUTPUT_STATISTICS_LINE.message);

        for (PlayResult result : statistics.getResults()) {
            sj.add(formatLine(result));
        }
        System.out.println(sj);
    }

    private static String formatLine(final PlayResult result) {
        return String.format(
            Constants.OUTPUT_STATISTICS_LINE_FORMAT.message,
            result.getNumberOfMatches(),
            formatAmountWithBonus(result),
            result.getCount()
        );
    }

    private static String formatAmountWithBonus(final PlayResult result) {
        final StringBuilder sb = new StringBuilder();

        if (result.isBonusMatches()) {
            sb.append(Constants.OUTPUT_BONUS_MATCHES_FORMAT.message);
        }
        sb.append(formatWinningAmount(result));
        return sb.toString();
    }

    private static String formatWinningAmount(final PlayResult result) {
        return String.format(
            Constants.OUTPUT_WINNING_AMOUNT_FORMAT.message,
            result.getWinningAmount()
        );
    }

    public static void printProfit(final Profit profit) {
        System.out.printf((Constants.OUTPUT_PROFIT_FORMAT.message) + "%n", formatProfit(profit));
    }

    private static String formatProfit(final Profit profit) {
        return PROFIT_RATE_FORMAT.format(profit.toBigDecimal());
    }

    public static void printError(final Exception e) {
        System.out.println(e.getMessage());
    }

    private enum Constants {
        OUTPUT_LOTTO_TICKET_COUNT_FORMAT("%d개를 구매했습니다."),
        OUTPUT_STATISTICS_MESSAGE("당첨 통계"),
        OUTPUT_STATISTICS_LINE_FORMAT("%d개 일치%s - %d개"),
        OUTPUT_STATISTICS_LINE("---"),
        OUTPUT_BONUS_MATCHES_FORMAT(", 보너스 볼 일치"),
        OUTPUT_WINNING_AMOUNT_FORMAT(" (%,d원)"),
        OUTPUT_PROFIT_FORMAT("총 수익률은 %s%%입니다.");

        private final String message;

        Constants(String message) {
            this.message = message;
        }
    }
}
