package lotto.domain;

import static lotto.domain.LottoAttributes.LOTTO_PRICE;
import static lotto.domain.LottoAttributes.MIN_LOTTO_NUMBER;
import static lotto.domain.LottoAttributes.MAX_LOTTO_NUMBER;
import static lotto.domain.LottoAttributes.LOTTO_NUMBERS_SIZE;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoMachine {

    public static Lottos publish(final int amount) {
        Money money = new Money(amount);
        int numberOfLotto = money.getAmount() / LOTTO_PRICE;
        List<Lotto> lottos = Stream.generate(() -> new Lotto(createNonDuplicateNumbers()))
            .limit(numberOfLotto)
            .collect(Collectors.toList());
        return new Lottos(lottos);
    }

    private static List<Integer> createNonDuplicateNumbers() {
        return Randoms.pickUniqueNumbersInRange(
            MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER, LOTTO_NUMBERS_SIZE
        );
    }
}
