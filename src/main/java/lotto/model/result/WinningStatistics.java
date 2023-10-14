package lotto.model.result;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import lotto.model.result.PlayResult;

public class WinningStatistics {

    private final List<PlayResult> playResults;

    public WinningStatistics(List<PlayResult> playResults) {
        this.playResults = playResults;
    }

    public BigDecimal calculateTotalAmount() {
        return playResults.stream()
            .map(this::calculateAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateAmount(final PlayResult playResult) {
        return BigDecimal.valueOf(playResult.getCount() * playResult.getWinningAmount());
    }

    public List<PlayResult> getResults() {
        return Collections.unmodifiableList(this.playResults);
    }
}
