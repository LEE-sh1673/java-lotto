package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    MISS(0, false, 0);

    private final int countOfMatch;

    private final boolean bonusContains;

    private final int winningPrice;

    Rank(final int countOfMatch, final boolean bonusContains, final int winningPrice) {
        this.countOfMatch = countOfMatch;
        this.bonusContains = bonusContains;
        this.winningPrice = winningPrice;
    }

    public static Rank of(final int countOfMatch, final boolean bonusContains) {
        return Arrays.stream(values())
            .filter(rank -> rank.equalsWith(countOfMatch, bonusContains))
            .findFirst()
            .orElse(Rank.MISS);
    }

    private boolean equalsWith(final int countOfMatch, final boolean bonusContains) {
        return this.countOfMatch == countOfMatch
            && this.bonusContains == bonusContains;
    }
}
