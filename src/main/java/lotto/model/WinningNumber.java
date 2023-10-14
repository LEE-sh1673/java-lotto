package lotto.model;

import java.util.List;
import java.util.stream.Collectors;

import lotto.exception.ErrorType;

public class WinningNumber {

    private final Lotto winningLotto;

    private final LottoNumber bonusNumber;

    public WinningNumber(final List<Integer> winningNumbers, final int bonusNumber) {
        validate(winningNumbers, bonusNumber);
        this.winningLotto = new Lotto(winningNumbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    private void validate(final List<Integer> winningNumbers, final int bonusNumber) {
        if (isBonusContains(winningNumbers, bonusNumber)) {
            throw new IllegalArgumentException(
                ErrorType.WINNING_NUMBER_DUPLICATED.getMessage()
            );
        }
    }

    private static boolean isBonusContains(
        final List<Integer> winningNumbers,
        int bonusNumber
    ) {
        return winningNumbers.contains(bonusNumber);
    }

    public List<WinningType> compareAll(List<Lotto> lottos) {
        return lottos.stream()
            .map(this::compare)
            .collect(Collectors.toList());
    }

    WinningType compare(final Lotto lotto) {
        return WinningType.of(
            winningLotto.countNumberOfContains(lotto),
            lotto.containsNumber(bonusNumber)
        );
    }
}
