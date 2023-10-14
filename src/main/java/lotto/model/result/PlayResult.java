package lotto.model.result;

public class PlayResult {

    private final int numberOfMatches;

    private final int winningAmount;

    private final boolean bonusMatches;

    private final long count;

    public PlayResult(
        final int numberOfMatches,
        final int winningAmount,
        final boolean bonusMatches,
        final long count
    ) {
        this.numberOfMatches = numberOfMatches;
        this.winningAmount = winningAmount;
        this.bonusMatches = bonusMatches;
        this.count = count;
    }

    public int getNumberOfMatches() {
        return numberOfMatches;
    }

    public int getWinningAmount() {
        return winningAmount;
    }

    public boolean isBonusMatches() {
        return bonusMatches;
    }

    public long getCount() {
        return count;
    }
}
