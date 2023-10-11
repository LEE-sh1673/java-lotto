package lotto;

import java.util.List;

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
        if (isContainsBonus(winningNumbers, bonusNumber)) {
            throw new IllegalArgumentException(
                ErrorType.WINNING_NUMBER_DUPLICATED.getMessage()
            );
        }
    }

    private static boolean isContainsBonus(
        final List<Integer> winningNumbers,
        int bonusNumber
    ) {
        return winningNumbers.contains(bonusNumber);
    }

    public LottoRank matches(final Lotto lotto) {
        return LottoRank.FIFTH;
    }
}
