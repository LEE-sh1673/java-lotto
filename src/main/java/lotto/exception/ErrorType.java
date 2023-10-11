package lotto.exception;

public enum ErrorType {
    LOTTO_NUMBER_OUT_OF_RANGE("로또 번호는 1부터 45 사이의 숫자여야 합니다.");

    private static final String PREFIX = "[ERROR]";

    private final String message;

    ErrorType(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + " " + message;
    }
}
