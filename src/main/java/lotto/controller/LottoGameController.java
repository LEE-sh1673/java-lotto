package lotto.controller;

import java.util.List;

import lotto.model.Lotto;
import lotto.model.LottoMoney;
import lotto.model.WinningNumber;
import lotto.model.result.WinningStatistics;
import lotto.model.LottoGame;
import lotto.model.LottoPublisher;
import lotto.model.result.Profit;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGameController {

    public void run() {
        try {
            play();
        } catch (Exception e) {
            OutputView.printError(e);
        }
    }

    private void play() {
        final LottoMoney lottoMoney = InputView.createLottoMoney();
        final List<Lotto> lottoTickets = createLottoTickets(lottoMoney);
        final WinningNumber winningNumber = InputView.createWinningNumber();

        final LottoGame lottoGame = new LottoGame(winningNumber, lottoTickets);
        final WinningStatistics statistics = lottoGame.play();
        final Profit profit = new Profit(statistics.calculateTotalAmount(), lottoMoney);

        OutputView.printStatistics(statistics);
        OutputView.printProfit(profit);
    }

    private List<Lotto> createLottoTickets(final LottoMoney money) {
        List<Lotto> lottoTickets = LottoPublisher.publish(money);
        OutputView.printLottoTickets(lottoTickets);
        return lottoTickets;
    }
}
