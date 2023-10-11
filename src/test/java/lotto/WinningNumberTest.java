package lotto;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

    @Test
    @DisplayName("로또 번호를 비교하여 일치하는 개수를 구할 수 있다.")
    void countNumberOfMatchesWithLotto() {
        final LottoRank rank = winningNumber.matches(
            new Lotto(List.of(1, 3, 5, 14, 22, 45))
        );
        assertEquals(5_000, rank.getWinningAmount());
        assertEquals(3, rank.getNumberOfMatches());
        assertFalse(rank.isBonusMatches());
    }
}
