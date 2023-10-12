package lotto;

public enum LottoRank {
    FIRST(2_000_000_000),
    SECOND(30_000_000),
    THIRD(1_500_000),
    FOURTH(50_000),
    FIFTH(5_000),
    NONE(0);

    private final int winningAmount;

    LottoRank(final int winningAmount) {
        this.winningAmount = winningAmount;
    }

    int getWinningAmount() {
        return winningAmount;
    }
}
