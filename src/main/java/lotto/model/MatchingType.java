package lotto.model;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.stream.Stream;

public enum MatchingType {
    ALL(6, false, WinningAmount.FIRST),
    FIVE_WITH_BONUS(5, true, WinningAmount.SECOND),
    FIVE(5, false, WinningAmount.THIRD),
    FOUR(4, false, WinningAmount.FOURTH),
    THREE(3, false, WinningAmount.FIFTH),
    NONE(0, false, WinningAmount.NONE);

    private final int numberOfMatches;

    private final boolean bonusMatches;

    private final WinningAmount amount;

    private static final Map<Integer, MatchingType> matchesToResult = Stream.of(values())
        .filter((matchResult) -> !matchResult.isBonusMatches())
        .collect(toMap(MatchingType::getNumberOfMatches, matchResult -> matchResult));

    MatchingType(
        final int numberOfMatches,
        final boolean bonusMatches,
        final WinningAmount amount
    ) {
        this.numberOfMatches = numberOfMatches;
        this.bonusMatches = bonusMatches;
        this.amount = amount;
    }

    public static MatchingType of(
        final int numberOfMatches,
        final boolean bonusMatches
    ) {
        final MatchingType matchingType
            = matchesToResult.getOrDefault(numberOfMatches, MatchingType.NONE);

        if (bonusMatches && matchingType == MatchingType.FIVE) {
            return MatchingType.FIVE_WITH_BONUS;
        }
        return matchingType;
    }

    public int getNumberOfMatches() {
        return numberOfMatches;
    }

    public boolean isBonusMatches() {
        return bonusMatches;
    }

    public int getWinningAmount() {
        return amount.getAmount();
    }

    public boolean isNone() {
        return this == NONE;
    }
}
