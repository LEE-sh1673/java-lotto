package lotto.util.formatter;

import java.util.List;
import java.util.StringJoiner;

import lotto.model.Lotto;

public class PublishedLottoFormatter {

    private static final String LOTTO_SIZE_FORMAT = "%d개를 구매했습니다.";

    public static String print(final List<Lotto> lottos) {
        final StringJoiner sj = new StringJoiner("\n");
        sj.add(String.format(LOTTO_SIZE_FORMAT, lottos.size()));
        lottos.stream().map(Lotto::toString).forEach(sj::add);
        return sj.toString();
    }
}
