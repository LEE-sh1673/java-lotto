package lotto.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

import lotto.model.Lotto;
import lotto.model.PlayResult;
import lotto.model.WinningNumber;
import lotto.model.WinningType;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class LottoDrawer {

    private final EnumMap<WinningType, Long> countByType;

    public LottoDrawer(final WinningNumber winningNumber, final List<Lotto> lottos) {
        this.countByType = mapToCountByType(winningNumber.compareAll(lottos));
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
        Arrays.stream(WinningType.values())
            .forEach(type -> countByType.putIfAbsent(type, 0L));
        return countByType;
    }

    public List<PlayResult> draw() {
        return Arrays.stream(WinningType.values())
            .filter(matchResult -> !matchResult.isNone())
            .sorted(Collections.reverseOrder())
            .map(this::mapToPlayResult)
            .collect(toList());
    }

    private PlayResult mapToPlayResult(final WinningType winningType) {
        return new PlayResult(
            winningType.getNumberOfMatches(),
            winningType.getWinningAmount(),
            winningType.isBonusMatches(),
            countByType.get(winningType)
        );
    }
}
