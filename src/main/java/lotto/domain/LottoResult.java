package lotto.domain;

import java.util.EnumMap;

public class LottoResult {

    private final EnumMap<Rank, Integer> lottoRanks;

    public LottoResult(final EnumMap<Rank, Integer> lottoRanks) {
        this.lottoRanks = lottoRanks;
    }

    public int getCount(final Rank rank) {
        return lottoRanks.getOrDefault(rank, 0);
    }
}
