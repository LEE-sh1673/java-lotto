package lotto.model;

import static java.util.stream.Collectors.toMap;

import java.util.Map;
import java.util.stream.Stream;

public enum WinningType {
    ALL(6, false, WinningAmount.FIRST),
    FIVE_WITH_BONUS(5, true, WinningAmount.SECOND),
    FIVE(5, false, WinningAmount.THIRD),
    FOUR(4, false, WinningAmount.FOURTH),
    THREE(3, false, WinningAmount.FIFTH),
    NONE(0, false, WinningAmount.NONE);

    private final int numberOfMatches;

    private final boolean bonusMatches;

    private final WinningAmount amount;

    private static final Map<Integer, WinningType> matchesToResult = Stream.of(values())
        .filter((matchResult) -> !matchResult.isBonusMatches())
        .collect(toMap(WinningType::getNumberOfMatches, matchResult -> matchResult));

    WinningType(
        final int numberOfMatches,
        final boolean bonusMatches,
        final WinningAmount amount
    ) {
        this.numberOfMatches = numberOfMatches;
        this.bonusMatches = bonusMatches;
        this.amount = amount;
    }

    public static WinningType of(
        final int numberOfMatches,
        final boolean bonusMatches
    ) {
        final WinningType winningType
            = matchesToResult.getOrDefault(numberOfMatches, WinningType.NONE);

        if (bonusMatches && winningType == WinningType.FIVE) {
            return WinningType.FIVE_WITH_BONUS;
        }
        return winningType;
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
