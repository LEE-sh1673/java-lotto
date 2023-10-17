package lotto.model.result;

import static java.math.RoundingMode.HALF_EVEN;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import lotto.model.LottoMoney;

public class WinningStatistics {

    private final List<PlayResult> playResults;

    private final BigDecimal profitRate;

    private static final BigDecimal REWARD_RATE_FACTOR = new BigDecimal(100);

    public static final int REWARD_RATE_INIT_SCALE = 1;

    public WinningStatistics(
        final List<PlayResult> playResults,
        final LottoMoney lottoMoney
    ) {
        this.playResults = playResults;
        this.profitRate = mapProfitRate(lottoMoney);
    }

    private BigDecimal mapProfitRate(final LottoMoney money) {
        if (money.equalsZero()) {
            return BigDecimal.ZERO;
        }
        return calculateTotalAmount()
            .multiply(REWARD_RATE_FACTOR)
            .divide(money.getAmount(), REWARD_RATE_INIT_SCALE, HALF_EVEN);
    }

    private BigDecimal calculateTotalAmount() {
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

    public BigDecimal getProfitRate() {
        return profitRate;
    }
}
