package lotto;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningDetailsMatcherTest {

    @Test
    @DisplayName("당첨 번호와 로또 번호를 비교할 수 있다.")
    void compareWinningNumberWithLotto() {
        WinningDetailsMatcher matcher = new WinningDetailsMatcher();

        final WinningNumber winningNumber = new WinningNumber(
            List.of(1, 2, 3, 4, 5, 6), 7
        );

        final Lotto lotto = new Lotto(
            List.of(1, 3, 5, 14, 22, 45)
        );
        WinningDetails winningDetails = matcher.matches(winningNumber, List.of(lotto));
        final List<WinningDetailsDto> matchResult = winningDetails.getResult();
        Assertions.assertEquals(5, matchResult.size());
    }
}
