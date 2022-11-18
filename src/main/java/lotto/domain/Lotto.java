package lotto.domain;

import static lotto.domain.ErrorMessage.INVALID_LOTTO_NUMBERS_SIZE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {

    private static final int MAX_LOTTO_NUMBERS_SIZE = 6;

    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = mapToLottoNumbers(numbers);
    }

    private static List<LottoNumber> mapToLottoNumbers(final List<Integer> numbers) {
        return numbers.stream()
            .map(LottoNumber::new)
            .collect(Collectors.toList());
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
    }

    // TODO: 추가 기능 구현
    private void validateSize(final List<Integer> numbers) {
        if (numbers.size() != MAX_LOTTO_NUMBERS_SIZE) {
            throw new IllegalArgumentException(
                INVALID_LOTTO_NUMBERS_SIZE.getMessage()
            );
        }
    }

    private void validateDuplicate(final List<Integer> numbers) {
        Set<Integer> nonDuplicatedNumbers = new HashSet<>(numbers);
        if (nonDuplicatedNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(
                ErrorMessage.DUPLICATED_LOTTO_NUMBER.getMessage()
            );
        }
    }

    public int countMatchedNumber(final Lotto other) {
        return (int)numbers.stream()
            .filter(other::contains)
            .count();
    }

    public boolean contains(final LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }
}
