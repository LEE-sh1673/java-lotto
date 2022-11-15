package lotto.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputValidationUtilsTest {
	private static final String ERROR_MESSAGE = "[ERROR]";

	@DisplayName("입력이 1,000 단위로 나누어 떨어지는 수인지 검증할 수 있다.")
	@ParameterizedTest
	@ValueSource(ints = {-1, 0, 11, 12, 7004, 4_444_001})
	void validatePrice_입력이_1000_단위로_나누어떨어지는_수인지_검증(final int money) {
		Assertions.assertThatThrownBy(
				() -> InputValidationUtils.validatePrice(money)
			).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining(ERROR_MESSAGE);
	}

	@DisplayName("입력된 숫자가 1~45 사이의 숫자인지 검증할 수 있다.")
	@ParameterizedTest
	@ValueSource(ints = {0, 46, -1})
	void validateNumber_입력된_숫자가_1에서_45_사이의_숫자인지_검증(final int number) {
		Assertions.assertThatThrownBy(
				() -> InputValidationUtils.validateNumber(number)
			).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining(ERROR_MESSAGE);
	}
}