package lotto.exception;

public enum ErrorType {
    LOTTO_NUMBER_OUT_OF_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    INVALID_LOTTO_SIZE("로또 번호는 6자리의 숫자를 입력해야 합니다."),
    LOTTO_NUMBER_DUPLICATE("로또 번호는 중복될 수 없습니다."),
    MONEY_CANT_DIVIDED_BY_1000("구입 금액은 1,000단위로 입력해야 합니다."),
    WINNING_NUMBER_DUPLICATE("보너스 번호와 당첨 번호는 중복될 수 없습니다."),
    INVALID_NUMBER_FORMAT("올바른 숫자 형식이 아닙니다.");

    private final String message;

    private static final String BASE_MESSAGE = "[ERROR] %s";

    ErrorType(final String message) {
        this.message = String.format(BASE_MESSAGE, message);
    }

    public String getMessage() {
        return message;
    }
}
