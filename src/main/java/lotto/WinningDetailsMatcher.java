package lotto;

import java.util.List;
import java.util.stream.Collectors;

public class WinningDetailsMatcher {

    public WinningDetails matches(
        final WinningNumber winningNumber,
        final List<Lotto> lottos
    ) {
        final List<MatchResult> matchResults = lottos.stream()
            .map(winningNumber::matches)
            .collect(Collectors.toList());
        return new WinningDetails(matchResults);
    }
}
