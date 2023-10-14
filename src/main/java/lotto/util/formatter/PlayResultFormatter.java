package lotto.util.formatter;

import java.util.List;
import java.util.StringJoiner;

import lotto.model.PlayResult;

public class PlayResultFormatter {

    private static final String TITLE = "당첨 통계";

    private static final String TITLE_BODY_SEPARATOR = "---";

    private static final String RESULT_LINE_FORMAT = "%d개 일치%s - %d개";

    private static final String BONUS_CONTAINS_FORMAT = ", 보너스 볼 일치";

    private static final String WINNING_AMOUNT_FORMAT = " (%,d원)";

    private static final String JOINING_DELIMITER = "\n";

    public static String print(final List<PlayResult> playResults) {
        final StringJoiner sj = new StringJoiner(JOINING_DELIMITER);

        sj.add(TITLE);
        sj.add(TITLE_BODY_SEPARATOR);

        for (PlayResult playResult : playResults) {
            sj.add(formatLine(playResult));
        }
        return sj.toString();
    }

    private static String formatLine(final PlayResult result) {
        final StringBuilder sb = new StringBuilder();
        final int numberOfMatches = result.getNumberOfMatches();
        final long count = result.getCount();

        if (result.isBonusMatches()) {
            sb.append(BONUS_CONTAINS_FORMAT);
        }
        sb.append(String.format(WINNING_AMOUNT_FORMAT, result.getWinningAmount()));
        return String.format(RESULT_LINE_FORMAT, numberOfMatches, sb, count);
    }
}
