package lotto.model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import camp.nextstep.edu.missionutils.Randoms;

public class LottoPublisher {

    public static List<Lotto> publish(final LottoMoney money) {
        return IntStream.range(0, getNumberOfLotto(money))
            .mapToObj((i) -> publishLotto())
            .collect(Collectors.toList());
    }

    private static int getNumberOfLotto(final LottoMoney money) {
        return money.getNumberOfLotto();
    }

    private static Lotto publishLotto() {
        return new Lotto(createLottoNumbers());
    }

    private static List<Integer> createLottoNumbers() {
        return createUniqueNumbers().stream()
            .sorted(Comparator.comparingInt(Integer::intValue))
            .collect(Collectors.toList());
    }

    private static List<Integer> createUniqueNumbers() {
        return Randoms.pickUniqueNumbersInRange(
            LottoNumber.MIN_LOTTO_NUMBER,
            LottoNumber.MAX_LOTTO_NUMBER,
            Lotto.MAXIMUM_LOTTO_SIZE
        );
    }
}
