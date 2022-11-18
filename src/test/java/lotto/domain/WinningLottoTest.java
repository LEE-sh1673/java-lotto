package lotto.domain;

import static lotto.domain.ErrorMessage.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class WinningLottoTest {

    static Lotto lotto;

    static WinningLotto winningLotto;

    @BeforeAll
    static void setUp() {
        lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        winningLotto = new WinningLotto(lotto, new LottoNumber(45));
    }

    @DisplayName("당첨 번호와 중복된 보너스 번호가 있으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void createWinningLottoNumbersContainBonus(int number) {
        assertThatThrownBy(
            () -> new WinningLotto(lotto, new LottoNumber(number)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(DUPLICATED_BONUS_NUMBER.getMessage());
    }

    @DisplayName("당첨 번호와 로또 번호를 비교하여 당첨 내역을 구할 수 있다.")
    @ParameterizedTest
    @MethodSource("provideNumbersAndRank")
    void getRankSecondCompareLottoWithWinningLotto(Lotto lotto, Rank rank) {
        assertThat(winningLotto.compare(lotto)).isEqualTo(rank);
    }

    public static Stream<Arguments> provideNumbersAndRank() {
        return Stream.of(
            Arguments.of(new Lotto(List.of(11, 12, 13, 14, 15, 16)), Rank.MISS),
            Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), Rank.FIRST),
            Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 45)), Rank.SECOND),
            Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 7)), Rank.THIRD),
            Arguments.of(new Lotto(List.of(1, 2, 3, 4, 7, 8)), Rank.FOURTH),
            Arguments.of(new Lotto(List.of(1, 2, 3, 4, 7, 45)), Rank.FOURTH)
        );
    }
}
