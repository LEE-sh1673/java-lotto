package lotto.util;

import java.math.BigDecimal;
import java.util.List;

import lotto.model.PlayResult;
import lotto.model.LottoMoney;

import static java.math.RoundingMode.HALF_EVEN;

public class WinningDetailsCalculator {

    private static final BigDecimal REWARD_RATE_FACTOR = new BigDecimal(100);

    public static final int REWARD_RATE_INIT_SCALE = 1;

    private final List<PlayResult> playResults;

    private final LottoMoney money;

    public WinningDetailsCalculator(
        final List<PlayResult> playResults,
        final LottoMoney money) {
        this.playResults = playResults;
        this.money = money;
    }

    public BigDecimal calculateProfitRate() {
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
            .map(BigDecimal::new)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private double calculateAmount(final PlayResult playResult) {
        return playResult.getCount() * playResult.getWinningAmount();
    }
}
