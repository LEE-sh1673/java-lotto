package lotto.domain;

public class Money {

    private static final int MIN_AMOUNT = 1_000;

    private final int amount;

    public Money(final int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(final int amount) {
        if (amount < MIN_AMOUNT) {
            throw new IllegalArgumentException(
                "[ERROR] 금액은 최소 1,000원입니다."
            );
        }
    }
}
