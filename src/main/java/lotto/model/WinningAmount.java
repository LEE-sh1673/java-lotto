package lotto.model;

public enum WinningAmount {
    FIRST(2_000_000_000),
    SECOND(30_000_000),
    THIRD(1_500_000),
    FOURTH(50_000),
    FIFTH(5_000),
    NONE(0);

    private final int amount;

    WinningAmount(final int winningAmount) {
        this.amount = winningAmount;
    }

    int getAmount() {
        return amount;
    }
}
