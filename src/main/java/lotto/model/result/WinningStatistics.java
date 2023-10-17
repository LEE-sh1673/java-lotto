package lotto.model.result;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class WinningStatistics {

    private final List<PlayResult> playResults;

    private final BigDecimal profitRate;

    public WinningStatistics(
        final List<PlayResult> playResults,
        final BigDecimal profitRate
    ) {
        this.playResults = playResults;
        this.profitRate = profitRate;
    }

    public List<PlayResult> getResults() {
        return Collections.unmodifiableList(this.playResults);
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }
}
