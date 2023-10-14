package lotto.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.model.result.PlayResult;

class LottoGameTest {

    private LottoGame lottoGame;

    @BeforeEach
    void setUp() {
        final List<Lotto> lottoTickets = List.of(
            new Lotto(List.of(8, 21, 23, 41, 42, 43)),
            new Lotto(List.of(3, 5, 11, 16, 32, 38)),
            new Lotto(List.of(7, 11, 16, 35, 36, 44)),
            new Lotto(List.of(1, 8, 11, 31, 41, 42)),
            new Lotto(List.of(13, 14, 16, 38, 42, 45)),
            new Lotto(List.of(7, 11, 30, 40, 42, 43)),
            new Lotto(List.of(2, 13, 22, 32, 38, 45)),
            new Lotto(List.of(1, 3, 5, 14, 22, 45))
        );
        final WinningNumber winningNumber = new WinningNumber(
            List.of(1, 2, 3, 4, 5, 6), 7
        );
        lottoGame = new LottoGame(winningNumber, lottoTickets);
    }

    @Test
    @DisplayName("로또 번호와 당첨 번호를 비교할 수 있다.")
    void drawLotto() {
        final List<PlayResult> playResults = lottoGame.play().getResults();

        assertEquals(5, playResults.size());

        PlayResult playResultOfMatchThree = playResults.get(0);
        assertEquals(1, playResultOfMatchThree.getCount());
        assertEquals(5000, playResultOfMatchThree.getWinningAmount());
        assertFalse(playResultOfMatchThree.isBonusMatches());
        assertEquals(3, playResultOfMatchThree.getNumberOfMatches());
    }
}