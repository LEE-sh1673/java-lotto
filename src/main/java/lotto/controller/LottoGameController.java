package lotto.controller;

import java.math.BigDecimal;
import java.util.List;

import lotto.application.LottoGame;
import lotto.exception.ErrorType;
import lotto.model.Lotto;
import lotto.model.PlayResult;
import lotto.model.WinningNumber;
import lotto.util.formatter.PlayResultFormatter;
import lotto.util.formatter.ProfitRateFormatter;
import lotto.util.formatter.PublishedLottoFormatter;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGameController {

    private final LottoGame lottoGame;

    private final InputView inputView;

    private final OutputView outputView;

    public LottoGameController() {
        lottoGame = new LottoGame();
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void play() {
        try {
            process(readAmount());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private int readAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return readNumber();
    }

    private void process(final int amount) {
        final List<PlayResult> playResults = lottoGame.draw(
            publishWinningNumber(),
            publishLottos(amount)
        );
        final BigDecimal profitRate = lottoGame.calculateProfitRate(playResults, amount);

        outputView.printWinningStatistic(
            PlayResultFormatter.print(playResults),
            ProfitRateFormatter.print(profitRate)
        );
    }

    private WinningNumber publishWinningNumber() {
        return lottoGame.publishWinningNumber(readWinningNumber(), readBonusNumber());
    }

    private List<Lotto> publishLottos(int amount) {
        List<Lotto> lottos = lottoGame.publishLotto(amount);
        outputView.printPublishedLotto(PublishedLottoFormatter.print(lottos));
        return lottos;
    }

    private int readBonusNumber() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
        return readNumber();
    }

    private int readNumber() {
        try {
            return inputView.readInt();
        } catch (Exception e) {
            throw new IllegalArgumentException(
                ErrorType.LOTTO_NUMBER_OUT_OF_RANGE.getMessage()
            );
        }
    }

    private List<Integer> readWinningNumber() {
        System.out.println("\n당첨 번호를 입력해 주세요.");
        try {
            return inputView.readNumbers();
        } catch (Exception e) {
            throw new IllegalArgumentException(
                ErrorType.LOTTO_NUMBER_OUT_OF_RANGE.getMessage()
            );
        }
    }
}
