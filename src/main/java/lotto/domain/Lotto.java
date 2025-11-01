package lotto.domain;

import lotto.constants.ErrorFormat;
import lotto.constants.ErrorMessage;
import lotto.exception.LottoException;
import lotto.util.LottoNumberValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/*
 * 제공된 Lotto 클래스를 사용해야 합니다.
 * Lotto에 numbers 이외의 필드(인스턴스 변수)를 추가할 수 없습니다.
 * numbers의 접근 제어자인 private은 변경할 수 없습니다.
 * Lotto의 패키지를 변경할 수 있습니다.
 * Lotto 생성 시 6개가 아닌 번호가 들어오면 IllegalArgumentException을 발생시켜야 합니다.
 */
public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = sortNumbers(numbers);
    }

    private List<Integer> sortNumbers(List<Integer> numbers) {
        return numbers.stream().sorted().toList();
    }

    public List<Integer> getNumbers() {
        // 불변 리스트 반환
        return Collections.unmodifiableList(numbers);
    }

    private void validate(List<Integer> numbers) {
        numbers.forEach(LottoNumberValidator::validateRange);
        validateLottoLength(numbers);
        validateNumberDuplication(numbers);
    }

    private void validateNumberDuplication(List<Integer> numbers) {
        HashSet<Integer> set = new HashSet<>(numbers);

        if (set.size() != numbers.size()) {
            throw new LottoException(ErrorMessage.LOTTO_NUMBER_DUPLICATES);
        }
    }

    private void validateLottoLength(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new LottoException(ErrorMessage.LOTTO_LENGTH_NOT_CORRECT);
        }
    }
}
