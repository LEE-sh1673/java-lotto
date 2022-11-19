package lotto.controller;

import java.util.EnumMap;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoNumber;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGame {

    public void play() {
        try {
            // 로또 발행
            Money money = new Money(InputView.readPurchaseAmount());
            Lottos lottos = new Lottos(LottoMachine.publish(money));
            OutputView.printLottos(lottos);

            // 로또 당첨 내역 계산
            WinningLotto winningLotto = new WinningLotto(
                new Lotto(InputView.readLottoNumbers()),
                new LottoNumber(InputView.readBonusNumber())
            );
            EnumMap<Rank, Integer> lottoRanks = new EnumMap<>(Rank.class);

            for (Lotto lotto : lottos) {
                lottoRanks.merge(winningLotto.compare(lotto), 1, Integer::sum);
            }
            LottoResult lottoResult = new LottoResult(lottoRanks);
            OutputView.printLottoResult(lottoResult);

            // 수익률 계산
            OutputView.printProfitRate(lottoResult.calculateProfitRate());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
