package lotto;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;
import static lotto.MatchResult.ALL;
import static lotto.MatchResult.FIVE;
import static lotto.MatchResult.FIVE_WITH_BONUS;
import static lotto.MatchResult.FOUR;
import static lotto.MatchResult.NONE;
import static lotto.MatchResult.THREE;

public enum LottoRank {
    FIRST(ALL, 2_000_000_000),
    SECOND(FIVE_WITH_BONUS, 30_000_000),
    THIRD(FIVE, 1_500_000),
    FOURTH(FOUR, 50_000),
    FIFTH(THREE, 5_000),
    MISS(NONE, 0);

    private static final Map<MatchResult, LottoRank> matchResultToRank = Stream.of(values())
        .collect(toMap(LottoRank::getMatchType, rank -> rank));

    private final MatchResult matchResult;

    private final int winningAmount;

    LottoRank(final MatchResult matchResult, int winningAmount) {
        this.matchResult = matchResult;
        this.winningAmount = winningAmount;
    }

    public static Optional<LottoRank> fromMatchType(final MatchResult matchResult) {
        return Optional.ofNullable(matchResultToRank.get(matchResult));
    }

    public boolean isBonusMatches() {
        return matchResult.isBonusMatches();
    }

    public int getWinningAmount() {
        return winningAmount;
    }

    public int getNumberOfMatches() {
        return matchResult.getNumberOfMatches();
    }

    private MatchResult getMatchType() {
        return matchResult;
    }
}
