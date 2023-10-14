package lotto.application;

import java.math.BigDecimal;
import java.util.List;

import lotto.model.Lotto;
import lotto.model.LottoMoney;
import lotto.model.PlayResult;
import lotto.model.WinningNumber;
import lotto.util.LottoDrawer;
import lotto.util.LottoPublisher;
import lotto.util.WinningDetailsCalculator;

public class LottoGame {

    public List<Lotto> publishLotto(final int money) {
        return LottoPublisher.publish(money);
    }

    public WinningNumber publishWinningNumber(
        final List<Integer> numbers,
        final int bonusNumber
    ) {
        return new WinningNumber(numbers, bonusNumber);
    }

    public List<PlayResult> draw(
        final WinningNumber winningNumber,
        final List<Lotto> lottos
    ) {
        final LottoDrawer drawer = new LottoDrawer(winningNumber, lottos);
        return drawer.draw();
    }

    public BigDecimal calculateProfitRate(
        final List<PlayResult> playResults,
        final int amount
    ) {
        final WinningDetailsCalculator calculator
            = new WinningDetailsCalculator(playResults, LottoMoney.of(amount));
        return calculator.calculateProfitRate();
    }
}
