package lotto.exception;

import lotto.constants.ErrorFormat;
import lotto.constants.LottoConstant;

public class NumberOutOfRangeException extends LottoException {
    public NumberOutOfRangeException(ErrorFormat format, int number) {
        super(format, LottoConstant.LOTTO_MIN_NUMBER, LottoConstant.LOTTO_MAX_NUMBER, number);
    }
}
