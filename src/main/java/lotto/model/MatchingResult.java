package lotto.model;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

import lotto.model.result.PlayResult;
import lotto.model.result.WinningStatistics;

public class MatchingResult {

    private final EnumMap<MatchingType, Long> countByType;

    public MatchingResult(final EnumMap<MatchingType, Long> countByType) {
        this.countByType = countByType;
    }

    public WinningStatistics map(final LottoMoney lottoMoney) {
        return new WinningStatistics(mapPlayResults(), lottoMoney);
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
}
