package lotto.controller;

import java.util.List;

import lotto.model.Lotto;
import lotto.model.LottoGame;
import lotto.model.LottoMoney;
import lotto.model.LottoPublisher;
import lotto.model.result.WinningStatistics;
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
        final List<Lotto> lottoTickets = LottoPublisher.publish(lottoMoney);
        OutputView.printLottoTickets(lottoTickets);

        final LottoGame lottoGame = new LottoGame(
            InputView.createWinningNumber(),
            lottoTickets
        );
        final WinningStatistics statistics = lottoGame.play(lottoMoney);
        OutputView.printStatistics(statistics);
    }
}
