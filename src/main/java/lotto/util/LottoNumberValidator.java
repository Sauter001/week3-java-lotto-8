package lotto.util;

import lotto.constants.ErrorFormat;
import lotto.constants.LottoConstant;
import lotto.exception.LottoException;
import lotto.exception.UtilClassException;

public final class LottoNumberValidator {
    public static void validateRange(int number) {
        if (number < LottoConstant.LOTTO_MIN_NUMBER || number > LottoConstant.LOTTO_MAX_NUMBER) {
            throw new LottoException(ErrorFormat.LOTTO_NUMBER_OUT_OF_RANGE, number);
        }
    }

    private LottoNumberValidator() {
        throw new UtilClassException();
    }
}
