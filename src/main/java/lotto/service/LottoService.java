package lotto.service;

import java.util.List;
import lotto.domain.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;

public class LottoService {

    private Lottos lottos;

    private LottoResult lottoResult;

    public Lottos publishLottos(final int purchaseAmount) {
        lottos = LottoMachine.publish(purchaseAmount);
        return lottos;
    }

    public LottoResult getLottoResult(final List<Integer> lottoNumbers,
        final int bonusNumber) {

        WinningLotto winningLotto = new WinningLotto(lottoNumbers, bonusNumber);
        lottoResult = winningLotto.compare(lottos);
        return lottoResult;
    }

    public double calculateProfitRate() {
        return lottoResult.calculateProfitRate();
    }
}
