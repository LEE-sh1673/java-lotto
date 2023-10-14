package lotto.util;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.model.Lotto;
import lotto.model.PlayResult;
import lotto.model.WinningNumber;
import lotto.util.formatter.PlayResultFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LottoDrawerTest {

    private LottoDrawer lottoDrawer;

    @BeforeEach
    void setUp() {
        final List<Lotto> lottos = List.of(
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
        lottoDrawer = new LottoDrawer(winningNumber, lottos);
    }

    @Test
    @DisplayName("로또 번호와 당첨 번호를 비교할 수 있다.")
    void drawLotto() {
        final List<PlayResult> playResults = lottoDrawer.draw();

        assertEquals(5, playResults.size());

        PlayResult playResultOfMatchThree = playResults.get(0);
        assertEquals(1, playResultOfMatchThree.getCount());
        assertEquals(5000, playResultOfMatchThree.getWinningAmount());
        assertEquals(false, playResultOfMatchThree.isBonusMatches());
        assertEquals(3, playResultOfMatchThree.getNumberOfMatches());

        System.out.println(PlayResultFormatter.print(playResults));
    }
}