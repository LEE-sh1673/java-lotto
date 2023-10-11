package lotto;

import lotto.exception.ErrorType;

public class LottoNumber {

    private static final int MIN_LOTTO_NUMBER = 1;

    private static final int MAX_LOTTO_NUMBER = 45;

    private final int number;

    public LottoNumber(final int number) {
        validate(number);
        this.number = number;
    }

    private void validate(final int number) {
        if (isOutOfRange(number)) {
            throw new IllegalArgumentException(
                ErrorType.LOTTO_NUMBER_OUT_OF_RANGE.getMessage()
            );
        }
    }

    private boolean isOutOfRange(final int number) {
        return number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER;
    }

    public int getNumber() {
        return this.number;
    }
}
