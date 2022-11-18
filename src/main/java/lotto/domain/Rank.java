package lotto.domain;

import java.util.Arrays;

public enum Rank {

    FIRST(6, 2_000_000_000),
    MISS(0, 0);

    private final int countOfMatch;

    private final int winningPrice;

    Rank(final int countOfMatch, final int winningPrice) {
        this.countOfMatch = countOfMatch;
        this.winningPrice = winningPrice;
    }

    public static Rank of(final int countOfMatch) {
        return Arrays.stream(Rank.values())
            .filter(rank -> rank.countOfMatch == countOfMatch)
            .findFirst()
            .orElse(Rank.MISS);
    }
}
