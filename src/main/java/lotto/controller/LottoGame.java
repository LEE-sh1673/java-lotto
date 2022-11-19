package lotto.controller;

import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGame {

    private final LottoService lottoService;

    public LottoGame() {
        lottoService = new LottoService();
    }

    public void play() {
        try {
            process();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void process() {
        OutputView.printLottos(
            lottoService.publishLottos(InputView.readPurchaseAmount())
        );
        OutputView.printLottoResult(lottoService.getLottoResult(
            InputView.readLottoNumbers(),
            InputView.readBonusNumber())
        );
        OutputView.printProfitRate(lottoService.calculateProfitRate());
    }
}
