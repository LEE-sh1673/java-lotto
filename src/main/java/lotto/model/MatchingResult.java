package lotto.model;

import static java.math.RoundingMode.HALF_EVEN;
import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

import lotto.model.result.PlayResult;
import lotto.model.result.WinningStatistics;

public class MatchingResult {

    private final EnumMap<MatchingType, Long> countByType;

    private static final BigDecimal REWARD_RATE_FACTOR = new BigDecimal(100);

    public static final int REWARD_RATE_INIT_SCALE = 1;

    public MatchingResult(final EnumMap<MatchingType, Long> countByType) {
        this.countByType = countByType;
    }

    public WinningStatistics map(final LottoMoney lottoMoney) {
        final List<PlayResult> playResults = mapPlayResults();
        final BigDecimal profitRate = mapProfitRate(playResults, lottoMoney);
        return new WinningStatistics(playResults, profitRate);
    }

    private List<PlayResult> mapPlayResults() {
        return Arrays.stream(MatchingType.values())
            .filter(matchingType -> !matchingType.isNone())
            .sorted(Collections.reverseOrder())
            .map(this::mapResult)
            .collect(toList());
    }

    private PlayResult mapResult(final MatchingType matchingType) {
        return new PlayResult(
            matchingType.getNumberOfMatches(),
            matchingType.getWinningAmount(),
            matchingType.isBonusMatches(),
            countByType.get(matchingType)
        );
    }

    private BigDecimal mapProfitRate(
        final List<PlayResult> playResults,
        final LottoMoney money
    ) {
        if (money.equalsZero()) {
            return BigDecimal.ZERO;
        }
        return calculateTotalAmount(playResults)
            .multiply(REWARD_RATE_FACTOR)
            .divide(money.getAmount(), REWARD_RATE_INIT_SCALE, HALF_EVEN);
    }

    private BigDecimal calculateTotalAmount(final List<PlayResult> playResults) {
        return playResults.stream()
            .map(this::calculateAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateAmount(final PlayResult playResult) {
        return BigDecimal.valueOf(playResult.getCount() * playResult.getWinningAmount());
    }
}
