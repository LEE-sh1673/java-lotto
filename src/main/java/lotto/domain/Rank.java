package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    private final int countOfMatch;

    private final int winningPrice;

    Rank(final int countOfMatch, final int winningPrice) {
        this.countOfMatch = countOfMatch;
        this.winningPrice = winningPrice;
    }

    public static Rank of(final int countOfMatch, final boolean isBonusContains) {
        Rank rank = getRankByMatchCount(countOfMatch);

        if (rank == SECOND && !isBonusContains) {
            return Rank.THIRD;
        }
        return rank;
    }

    private static Rank getRankByMatchCount(final int countOfMatch) {
        return Arrays.stream(values())
            .filter(rank -> rank.countOfMatch == countOfMatch)
            .findFirst()
            .orElse(MISS);
    }
}
