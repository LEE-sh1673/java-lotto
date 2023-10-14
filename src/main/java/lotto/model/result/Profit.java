package lotto.model.result;

import static java.math.RoundingMode.HALF_EVEN;

import java.math.BigDecimal;

import lotto.model.LottoMoney;

public class Profit {

    private static final BigDecimal REWARD_RATE_FACTOR = new BigDecimal(100);

    public static final int REWARD_RATE_INIT_SCALE = 1;

    private final BigDecimal profitRate;

    public Profit(
        final BigDecimal totalAmount,
        final LottoMoney money
    ) {
        this.profitRate = mapProfitRate(totalAmount, money);
    }

    private BigDecimal mapProfitRate(
        final BigDecimal totalAmount,
        final LottoMoney money) {

        if (money.equalsZero()) {
            return BigDecimal.ZERO;
        }
        return totalAmount
            .multiply(REWARD_RATE_FACTOR)
            .divide(money.getAmount(), REWARD_RATE_INIT_SCALE, HALF_EVEN);
    }

    public BigDecimal toBigDecimal() {
        return profitRate;
    }
}
