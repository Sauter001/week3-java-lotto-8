package lotto.exception;

import lotto.constants.ErrorMessage;
import lotto.constants.UIConstant;

public class LottoException extends IllegalArgumentException {
    private static final String DEFAULT_ERROR_MESSAGE = "에러가 발생했습니다.";

    public LottoException() {
        super(String.format(UIConstant.FORMAT_ERROR, DEFAULT_ERROR_MESSAGE));
    }

    public LottoException(ErrorMessage errorMessage) {
        super(String.format(UIConstant.FORMAT_ERROR, errorMessage.getMessage()));
    }
}
