package lotto.util;

import lotto.constants.ErrorFormat;
import lotto.constants.ErrorMessage;
import lotto.constants.LottoConstant;
import lotto.exception.LottoException;
import lotto.exception.NumberOutOfRangeException;
import lotto.exception.UtilClassException;

import java.util.HashSet;
import java.util.List;

public class LottoNumberValidator {
    private LottoNumberValidator() {
        throw new UtilClassException();
    }

    public static void validateRange(int number) {
        if (number < LottoConstant.LOTTO_MIN_NUMBER || number > LottoConstant.LOTTO_MAX_NUMBER) {
            throw new NumberOutOfRangeException(ErrorFormat.LOTTO_NUMBER_OUT_OF_RANGE, number);
        }
    }

    public static void validateList(List<Integer> numbers) {
        numbers.forEach(LottoNumberValidator::validateRange);
        validateLottoLength(numbers);
        validateNumberDuplication(numbers);
    }

    private static void validateNumberDuplication(List<Integer> numbers) {
        HashSet<Integer> set = new HashSet<>(numbers);

        if (set.size() != numbers.size()) {
            throw new LottoException(ErrorMessage.LOTTO_NUMBER_DUPLICATES);
        }
    }

    private static void validateLottoLength(List<Integer> numbers) {
        if (numbers.size() != LottoConstant.LOTTO_NUMBERS_LENGTH) {
            throw new LottoException(
                    ErrorFormat.LOTTO_LENGTH_NOT_CORRECT, LottoConstant.LOTTO_NUMBERS_LENGTH
            );
        }
    }
}
