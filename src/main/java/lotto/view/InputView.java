package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Console;
import lotto.validator.NumbericValidator;

public class InputView {

    public static int readLottoMoney() {
        return readNumber(Message.INPUT_LOTTO_MONEY);
    }

    public static List<Integer> readNumbers() {
        final String input = readLine(Message.INPUT_WINNING_NUMBER);
        NumbericValidator.validateNumbers(input);
        return Arrays.stream(input.split(","))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    public static int readBonusNumber() {
        return readNumber(Message.INPUT_BONUS_NUMBER);
    }

    private static int readNumber(final Message message) {
        final String input = readLine(message);
        NumbericValidator.validateNumber(input);
        return Integer.parseInt(input);
    }

    private static String readLine(final Message message) {
        System.out.println();
        System.out.println(message.message);
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
