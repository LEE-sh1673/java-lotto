package lotto.domain;

import static lotto.domain.ErrorMessage.DUPLICATED_BONUS_NUMBER;

public class WinningLotto {

    private final Lotto numbers;

    private final LottoNumber bonusNumber;

    public WinningLotto(final Lotto numbers, final LottoNumber bonusNumber) {
        validateBonusDuplicated(numbers, bonusNumber);
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusDuplicated(final Lotto numbers, final LottoNumber bonusNumber) {
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
