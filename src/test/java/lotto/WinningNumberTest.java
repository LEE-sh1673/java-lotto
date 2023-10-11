package lotto;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WinningNumberTest {

    private static final String ERROR_MESSAGE = "[ERROR]";

    private final WinningNumber winningNumber = new WinningNumber(
        List.of(1, 2, 3, 4, 5, 6), 7
    );

    @Test
    @DisplayName("당첨 번호에 보너스 번호가 포함되어 있으면 예외가 발생한다.")
    void createWinningNumberByDuplicatedBonus() {
        assertThatThrownBy(() -> new WinningNumber(List.of(1, 2, 3, 4, 5, 6), 6))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(ERROR_MESSAGE);
    }

    @ParameterizedTest
    @MethodSource("provideLottoAndMatchResult")
    @DisplayName("로또 번호를 비교하여 일치하는 개수를 구할 수 있다.")
    void countNumberOfMatchesWithLotto(
        final Lotto lotto,
        final MatchResult expected
    ) {
        assertEquals(expected, winningNumber.matches(lotto));
    }

    public static Stream<Arguments> provideLottoAndMatchResult() {
        return Stream.of(
            Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), MatchResult.ALL),
            Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 7)), MatchResult.FIVE_WITH_BONUS),
            Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 8)), MatchResult.FIVE),
            Arguments.of(new Lotto(List.of(1, 2, 3, 4, 8, 9)), MatchResult.FOUR),
            Arguments.of(new Lotto(List.of(1, 2, 3, 8, 9, 10)), MatchResult.THREE),
            Arguments.of(new Lotto(List.of(1, 2, 8, 9, 10, 11)), MatchResult.NONE)
        );
    }
}
