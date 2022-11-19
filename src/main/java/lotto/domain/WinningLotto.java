package lotto.domain;

import static lotto.domain.ErrorMessage.DUPLICATED_BONUS_NUMBER;

import java.util.List;

public class WinningLotto {

    private final Lotto numbers;

    private final LottoNumber bonusNumber;

    public WinningLotto(final List<Integer> numbers, final int bonusNumber) {
        validateBonusDuplicated(numbers, bonusNumber);
        this.numbers = new Lotto(numbers);
        this.bonusNumber = new LottoNumber(bonusNumber);
    }

    private void validateBonusDuplicated(final List<Integer> numbers, final int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(
                DUPLICATED_BONUS_NUMBER.getMessage()
            );
        }
    }

    public Rank compare(final Lotto lotto) {
        return Rank.of(numbers.countMatchedNumber(lotto), lotto.contains(bonusNumber));
    }
}
