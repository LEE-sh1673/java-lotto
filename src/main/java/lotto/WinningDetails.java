package lotto;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public class WinningDetails {

    private final EnumMap<MatchResult, Integer> matchResultMap;

    WinningDetails(final List<MatchResult> matchResults) {
        this.matchResultMap = new EnumMap<>(MatchResult.class);

        for (final MatchResult result : matchResults) {
            matchResultMap.merge(result, 1, Integer::sum);
        }
    }

    public List<WinningDetailsDto> getResult() {
        return Arrays.stream(MatchResult.values())
            .filter(matchResult -> matchResult != MatchResult.NONE)
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    private WinningDetailsDto mapToDto(final MatchResult matchResult) {
        return new WinningDetailsDto(
            matchResult.getNumberOfMatches(),
            matchResult.getWinningAmount(),
            matchResultMap.getOrDefault(matchResult, 0)
        );
    }
}
