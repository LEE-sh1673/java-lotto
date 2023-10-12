package lotto;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum MatchResult {
    ALL(6, false, LottoRank.FIRST),
    FIVE_WITH_BONUS(5, true, LottoRank.SECOND),
    FIVE(5, false, LottoRank.THIRD),
    FOUR(4, false, LottoRank.FOURTH),
    THREE(3, false, LottoRank.FIFTH),
    NONE(0, false, LottoRank.NONE);

    private static final Map<Integer, MatchResult> matchesToResult = Stream.of(values())
        .filter((matchResult) -> !matchResult.isBonusMatches())
        .collect(toMap(MatchResult::getNumberOfMatches, matchResult -> matchResult));

    private final int numberOfMatches;

    private final boolean bonusMatches;

    private final LottoRank rank;

    MatchResult(
        final int numberOfMatches,
        final boolean bonusMatches,
        final LottoRank rank
    ) {
        this.numberOfMatches = numberOfMatches;
        this.bonusMatches = bonusMatches;
        this.rank = rank;
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

    public int getWinningAmount() {
        return rank.getWinningAmount();
    }
}
