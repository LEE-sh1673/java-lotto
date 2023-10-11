package lotto;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum MatchResult {
    ALL(6, false),
    FIVE_WITH_BONUS(5, true),
    FIVE(5, false),
    FOUR(4, false),
    THREE(3, false),
    NONE(0, false);

    private static final Map<Integer, MatchResult> matchesToResult = Stream.of(values())
        .filter((matchResult) -> !matchResult.isBonusMatches())
        .collect(toMap(MatchResult::getNumberOfMatches, Function.identity()));

    private final int numberOfMatches;

    private final boolean bonusMatches;

    MatchResult(int numberOfMatches, boolean bonusMatches) {
        this.numberOfMatches = numberOfMatches;
        this.bonusMatches = bonusMatches;
    }

    public static MatchResult of(
        final int numberOfMatches,
        final boolean bonusMatches
    ) {
        final MatchResult matchResult
            = matchesToResult.getOrDefault(numberOfMatches, MatchResult.NONE);

        if (bonusMatches && matchResult == MatchResult.FIVE) {
            return MatchResult.FIVE_WITH_BONUS;
        }
        return matchResult;
    }

    public int getNumberOfMatches() {
        return numberOfMatches;
    }

    public boolean isBonusMatches() {
        return bonusMatches;
    }
}
