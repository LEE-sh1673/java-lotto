package lotto.util.formatter;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class ProfitRateFormatter {

    private static final DecimalFormat BIG_DECIMAL_FORMAT = new DecimalFormat("#,##0.0");

    private static final String PROFIT_RATE_FORMAT = "총 수익률은 %s%%입니다.";

    public static String print(final BigDecimal profitRate) {
        return String.format(PROFIT_RATE_FORMAT, formatProfitRate(profitRate));
    }

    private static String formatProfitRate(BigDecimal profitRate) {
        return BIG_DECIMAL_FORMAT.format(profitRate);
    }
}
