package lotto.model;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class LottoGame {

    private final List<MatchingType> matchingTypes;

    public LottoGame(
        final WinningNumber winningNumber,
        final List<Lotto> lottoTickets
    ) {
        this.matchingTypes = winningNumber.compareAll(lottoTickets);
    }

    public MatchingResult play() {
        return new MatchingResult(mapCountByType());
    }

    private EnumMap<MatchingType, Long> mapCountByType() {
        final EnumMap<MatchingType, Long> countByType = matchingTypes.stream()
            .collect(groupingBy(
                type -> type, () -> new EnumMap<>(MatchingType.class), counting()
            ));
        Arrays.stream(MatchingType.values()).forEach(type -> countByType.putIfAbsent(type, 0L));
        return countByType;
    }
}
