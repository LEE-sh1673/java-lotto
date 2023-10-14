package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Console;
import lotto.model.LottoMoney;
import lotto.model.WinningNumber;
import lotto.validator.NumbericValidator;

public class InputView {

    public static LottoMoney createLottoMoney() {
        return LottoMoney.of(readLottoMoney());
    }

    private static int readLottoMoney() {
        System.out.println(Message.INPUT_LOTTO_MONEY.message);
        return readNumber();
    }

    public static WinningNumber createWinningNumber() {
        return new WinningNumber(readNumbers(), readBonusNumber());
    }

    private static List<Integer> readNumbers() {
        System.out.println(Message.INPUT_WINNING_NUMBER.message);
        final String input = Console.readLine();
        NumbericValidator.validateNumbers(input);
        return Arrays.stream(input.split(","))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    private static int readBonusNumber() {
        System.out.println(Message.INPUT_BONUS_NUMBER.message);
        return readNumber();
    }

    private static int readNumber() {
        final String input = readLine();
        NumbericValidator.validateNumber(input);
        return Integer.parseInt(input);
    }

    private static String readLine() {
        return Console.readLine();
    }

    private enum Message {
        INPUT_LOTTO_MONEY("구입금액을 입력해 주세요."),
        INPUT_WINNING_NUMBER("당첨 번호를 입력해 주세요."),
        INPUT_BONUS_NUMBER("보너스 번호를 입력해 주세요.");

        private final String message;

        Message(final String message) {
            this.message = message;
        }
    }
}
