package lotto.domain;

import static lotto.domain.LottoAttributes.LOTTO_PRICE;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Set;

public class LottoResult {

    private final EnumMap<Rank, Integer> lottoRanks;

    public LottoResult(final EnumMap<Rank, Integer> lottoRanks) {
        this.lottoRanks = lottoRanks;
    }

    public int getCount(final Rank rank) {
        return lottoRanks.getOrDefault(rank, 0);
    }

    public long calculateTotalPrize() {
        return Arrays.stream(Rank.values())
            .map(this::calculateWinningPrice)
            .reduce(0L, Long::sum);
    }

    private long calculateWinningPrice(final Rank rank) {
        return (long) getCount(rank) * rank.getWinningPrice();
    }

    public double calculateProfitRate() {
        return 100 * (calculateTotalPrize() / calculateTotalPurchaseAmount());
    }

    private double calculateTotalPurchaseAmount() {
        return lottoRanks.values()
            .stream()
            .map(count -> count * LOTTO_PRICE * 1.0)
            .reduce(0.0, Double::sum);
    }

    public Set<Rank> keySet() {
        return lottoRanks.keySet();
    }
}
