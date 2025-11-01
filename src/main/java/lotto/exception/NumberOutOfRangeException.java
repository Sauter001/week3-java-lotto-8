package lotto.exception;

import lotto.constants.ErrorFormat;

public class NumberOutOfRangeException extends LottoException {
    public NumberOutOfRangeException(ErrorFormat format, int number) {
        super(format, number);
    }
}
