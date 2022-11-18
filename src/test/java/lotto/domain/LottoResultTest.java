package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.EnumMap;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoResultTest {

    static EnumMap<Rank, Integer> lottoRanks;

    static WinningLotto winningLotto;

    static LottoResult lottoResult;

    static List<Lotto> lottos = List.of(
        new Lotto(List.of(8, 21, 23, 41, 42, 43)),
        new Lotto(List.of(3, 5, 11, 16, 32, 38)),
        new Lotto(List.of(7, 11, 16, 35, 36, 44)),
        new Lotto(List.of(1, 8, 11, 31, 41, 42)),
        new Lotto(List.of(13, 14, 16, 38, 42, 45)),
        new Lotto(List.of(7, 11, 30, 40, 42, 43)),
        new Lotto(List.of(2, 13, 22, 32, 38, 45)),
        new Lotto(List.of(1, 3, 5, 14, 22, 45))
    );

    @BeforeAll
    static void setUp() {
        lottoRanks = new EnumMap<>(Rank.class);
        winningLotto = new WinningLotto(
            new Lotto(List.of(1, 2, 3, 4, 5, 6)),
            new LottoNumber(7)
        );

        for (Lotto lotto : lottos) {
            lottoRanks.merge(winningLotto.compare(lotto), 1, Integer::sum);
        }
        lottoResult = new LottoResult(lottoRanks);
    }

    @DisplayName("구매한 로또에서 특정 등수에 당첨된 로또의 개수를 구할 수 있다.")
    @ParameterizedTest
    @MethodSource("provideRankWithCount")
    void getCountOfRankMatchedWinningLotto(Rank rank, int expected) {
        assertThat(lottoResult.getCount(rank))
            .isEqualTo(expected);
    }

    private static Stream<Arguments> provideRankWithCount() {
        return Stream.of(
            Arguments.of(Rank.FIRST, 0),
            Arguments.of(Rank.SECOND, 0),
            Arguments.of(Rank.THIRD, 0),
            Arguments.of(Rank.FOURTH, 0),
            Arguments.of(Rank.FIFTH, 1),
            Arguments.of(Rank.MISS, 7)
        );
    }

    @DisplayName("총 상금을 구할 수 있다.")
    @Test
    void calculateRateOfReturn() {
        assertThat(lottoResult.calculateTotalPrize())
            .isEqualTo(5_000L);
    }

    @DisplayName("총 수익률을 구할 수 있다.")
    @Test
    void calculateProfitRate() {
        assertThat(lottoResult.calculateProfitRate())
            .isEqualTo(62.5);
    }

}
