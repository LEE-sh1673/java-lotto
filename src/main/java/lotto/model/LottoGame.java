package lotto.model;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

import lotto.model.result.PlayResult;
import lotto.model.result.WinningStatistics;

public class LottoGame {

    private final EnumMap<WinningType, Long> countByType;

    public LottoGame(
        final WinningNumber winningNumber,
        final List<Lotto> lottoTickets
    ) {
        this.countByType = mapToCountByType(winningNumber.compareAll(lottoTickets));
    }

    private EnumMap<WinningType, Long> mapToCountByType(
        final List<WinningType> winningTypes
    ) {
        final EnumMap<WinningType, Long> countByType = winningTypes.stream()
            .collect(
                groupingBy(
                    type -> type,
                    () -> new EnumMap<>(WinningType.class),
                    counting()
                ));
        Arrays.stream(WinningType.values()).forEach(type -> countByType.putIfAbsent(type, 0L));
        return countByType;
    }

    public WinningStatistics play() {
        return new WinningStatistics(mapToPlayResults());
    }

    private List<PlayResult> mapToPlayResults() {
        return Arrays.stream(WinningType.values())
            .filter(matchResult -> !matchResult.isNone())
            .sorted(Collections.reverseOrder())
            .map(this::mapToResult)
            .collect(toList());
    }

    private PlayResult mapToResult(final WinningType winningType) {
        return new PlayResult(
            winningType.getNumberOfMatches(),
            winningType.getWinningAmount(),
            winningType.isBonusMatches(),
            countByType.get(winningType)
        );
    }
}
