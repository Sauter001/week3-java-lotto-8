package lotto.util;

import lotto.constants.LottoConstant;
import lotto.constants.LottoNumberType;
import lotto.exception.UtilClassException;

import java.util.HashSet;
import java.util.List;

public final class LottoNumberValidator {
    private LottoNumberValidator() {
        throw new UtilClassException();
    }

    public static void validateRange(int number, LottoNumberType type) {
        if (number < LottoConstant.LOTTO_MIN_NUMBER || number > LottoConstant.LOTTO_MAX_NUMBER) {
            throw type.createRangeException(number);
        }
    }

    public static void validateList(List<Integer> numbers, LottoNumberType type) {
        numbers.forEach(number -> validateRange(number, type));
        validateLottoLength(numbers, type);
        validateNumberDuplication(numbers, type);
    }

    private static void validateNumberDuplication(List<Integer> numbers, LottoNumberType type) {
        HashSet<Integer> set = new HashSet<>(numbers);

        if (set.size() != numbers.size()) {
            throw type.createDuplicationException();
        }
    }

    private static void validateLottoLength(List<Integer> numbers, LottoNumberType type) {
        if (numbers.size() != LottoConstant.LOTTO_NUMBERS_LENGTH) {
            throw type.createInvalidLengthException();
        }
    }
}
