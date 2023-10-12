package lotto;

public class WinningDetailsDto {

    private final int numberOfMatches;

    private final int winningAmount;

    private final int count;

    public WinningDetailsDto(
        final int numberOfMatches,
        final int winningAmount,
        final int count) {
        this.numberOfMatches = numberOfMatches;
        this.winningAmount = winningAmount;
        this.count = count;
    }

    public int getNumberOfMatches() {
        return numberOfMatches;
    }

    public int getWinningAmount() {
        return winningAmount;
    }

    public int getCount() {
        return count;
    }
}
