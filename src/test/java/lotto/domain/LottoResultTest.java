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
        new Lotto(List.of(11, 12, 13, 14, 15, 16)), // MISS
        new Lotto(List.of(1, 2, 3, 4, 5, 6)),
        new Lotto(List.of(1, 2, 3, 4, 5, 6)), // ~ FIRST
        new Lotto(List.of(1, 2, 3, 4, 5, 45)), // ~ SECOND
        new Lotto(List.of(1, 2, 3, 4, 5, 7)),
        new Lotto(List.of(1, 2, 3, 4, 5, 7)),
        new Lotto(List.of(1, 2, 3, 4, 5, 7)), // ~ THIRD
        new Lotto(List.of(1, 2, 3, 4, 7, 8)),
        new Lotto(List.of(1, 2, 3, 4, 7, 45)), // ~ FOURTH
        new Lotto(List.of(1, 2, 3, 7, 8, 45)) // ~ FIFTH
    );

    @BeforeAll
    static void setUp() {
        lottoRanks = new EnumMap<>(Rank.class);
        winningLotto = new WinningLotto(
            new Lotto(List.of(1, 2, 3, 4, 5, 6)),
            new LottoNumber(45)
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
            Arguments.of(Rank.FIRST, 2),
            Arguments.of(Rank.SECOND, 1),
            Arguments.of(Rank.THIRD, 3),
            Arguments.of(Rank.FOURTH, 2),
            Arguments.of(Rank.FIFTH, 1),
            Arguments.of(Rank.MISS, 1)
        );
    }

    @DisplayName("총 상금을 구할 수 있다.")
    @Test
    void calculateRateOfReturn() {
        long totalPrize = lottoResult.calculateTotalPrize();
        assertThat(totalPrize).isEqualTo(4_034_605_000L);
    }

}
