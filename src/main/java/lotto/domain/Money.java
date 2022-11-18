package lotto.domain;

public class Money {

    private static final int MIN_AMOUNT = 1_000;

    private final int amount;

    public Money(final int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(final int amount) {
        validateIsLessThanMinimum(amount);
        validateUnit(amount);
    }

    private void validateIsLessThanMinimum(final int amount) {
        if (isLessThanMinimum(amount)) {
            throw new IllegalArgumentException(
                "[ERROR] 금액은 최소 1,000원입니다."
            );
        }
    }

    private boolean isLessThanMinimum(final int amount) {
        return amount < MIN_AMOUNT;
    }

    private void validateUnit(final int amount) {
        if (!isDivisibleWithThousand(amount)) {
            throw new IllegalArgumentException(
                "[ERROR] 금액은 1,000원으로 나누어 떨어지는 수여야 합니다."
            );
        }
    }

    private boolean isDivisibleWithThousand(final int amount) {
        return amount != 0 && amount % MIN_AMOUNT == 0;
    }
}
