package lotto.exception;

public enum ErrorType {
    LOTTO_NUMBER_OUT_OF_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    MONEY_CANT_DIVIDED_BY_1000("구입 금액은 1,000단위로 입력해야 합니다.");

    private static final String PREFIX = "[ERROR]";

    private final String message;

    ErrorType(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + " " + message;
    }
}
