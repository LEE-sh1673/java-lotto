package lotto.domain;

import static lotto.domain.ErrorMessage.INVALID_LOTTO_NUMBER_BOUND;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        LottoNumber other = (LottoNumber) obj;
        return this.number == other.number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
