package lotto.controller;

import java.util.List;

import lotto.model.Lotto;
import lotto.model.LottoGame;
import lotto.model.LottoMoney;
import lotto.model.LottoPublisher;
import lotto.model.MatchingResult;
import lotto.model.WinningNumber;
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
        final LottoMoney lottoMoney = createLottoMoney();
        final List<Lotto> lottoTickets = LottoPublisher.publish(lottoMoney);
        OutputView.printLottoTickets(lottoTickets);

        final LottoGame lottoGame = createLottoGame(createWinningNumber(), lottoTickets);
        final MatchingResult matchingResult = lottoGame.play();
        final WinningStatistics statistics = matchingResult.map(lottoMoney);

        OutputView.printStatistics(statistics);
    }

    private static LottoMoney createLottoMoney() {
        return LottoMoney.of(InputView.readLottoMoney());
    }

    private static WinningNumber createWinningNumber() {
        return new WinningNumber(
            InputView.readNumbers(),
            InputView.readBonusNumber()
        );
    }

    private static LottoGame createLottoGame(
        final WinningNumber winningNumber,
        final List<Lotto> lottoTickets
    ) {
        return new LottoGame(winningNumber, lottoTickets);
    }
}
