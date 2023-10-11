package lotto;

import java.util.Objects;

import lotto.exception.ErrorType;

public class LottoNumber {

    public static final int MIN_LOTTO_NUMBER = 1;

    public static final int MAX_LOTTO_NUMBER = 45;

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final LottoNumber other = (LottoNumber)o;
        return matchNumber(other);
    }

    private boolean matchNumber(final LottoNumber other) {
        return this.number == other.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.number);
    }
}
