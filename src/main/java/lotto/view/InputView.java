package lotto.view;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static lotto.domain.ErrorMessage.INVALID_NUMBER_FORMAT;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {

    private static final String READ_PURCHASE_AMOUNT_MESSAGE = "구입금액을 입력해주세요.";

    private static final String READ_LOTTO_NUMBER_MESSAGE = "\n당첨 번호를 입력해 주세요.";

    private static final String READ_BONUS_NUMBER_MESSAGE = "\n보너스 번호를 입력해 주세요.";

    public static int readPurchaseAmount() {
        System.out.println(READ_PURCHASE_AMOUNT_MESSAGE);
        return toNumber(readLine());
    }

    public static List<Integer> readLottoNumbers() {
        System.out.println(READ_LOTTO_NUMBER_MESSAGE);
        return Arrays.stream(readLine().split(","))
            .map(InputView::toNumber)
            .collect(Collectors.toList());
    }

    public static int readBonusNumber() {
        System.out.println(READ_BONUS_NUMBER_MESSAGE);
        return toNumber(Console.readLine());
    }

    private static int toNumber(final String input) {
        int number;
        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER_FORMAT.getMessage());
        }
        return number;
    }
}
