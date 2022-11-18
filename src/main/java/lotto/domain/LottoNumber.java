package lotto.domain;

import static lotto.domain.ErrorMessage.INVALID_LOTTO_NUMBER_BOUND;

public class LottoNumber {

    private static final int MIN_LOTTO_NUMBER = 1;

    private static final int MAX_LOTTO_NUMBER = 45;

    private final int number;

    public LottoNumber(final int number) {
        validateNumber(number);
        this.number = number;
    }

    private void validateNumber(final int number) {
        if (isOutOfBound(number)) {
            throw new IllegalArgumentException(
                INVALID_LOTTO_NUMBER_BOUND.getMessage()
            );
        }
    }

    private boolean isOutOfBound(final int number) {
        return number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER;
    }
}
