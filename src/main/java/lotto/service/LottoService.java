package lotto.service;

import java.util.EnumMap;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;

public class LottoService {

    private Lottos lottos;

    private LottoResult lottoResult;

    public Lottos publishLottos(final int purchaseAmount) {
        Money money = new Money(purchaseAmount);
        lottos = new Lottos(LottoMachine.publish(money));
        return lottos;
    }

    public LottoResult getLottoResult(final List<Integer> lottoNumbers,
        final int bonusNumber) {

        WinningLotto winningLotto = new WinningLotto(lottoNumbers, bonusNumber);
        EnumMap<Rank, Integer> lottoRanks = new EnumMap<>(Rank.class);
        for (Lotto lotto : lottos) {
            lottoRanks.merge(winningLotto.compare(lotto), 1, Integer::sum);
        }
        lottoResult = new LottoResult(lottoRanks);
        return lottoResult;
    }

    public double calculateProfitRate() {
        return lottoResult.calculateProfitRate();
    }
}
