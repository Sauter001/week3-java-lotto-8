package lotto.exception;

import lotto.constants.ErrorFormat;
import lotto.constants.ErrorMessage;
import lotto.constants.UIConstant;

public class LottoException extends IllegalArgumentException {
    private static final String DEFAULT_ERROR_MESSAGE = "에러가 발생했습니다.";

    public LottoException() {
        super(formatMessage(DEFAULT_ERROR_MESSAGE));
    }

    public LottoException(ErrorMessage errorMessage) {
        super(formatMessage(errorMessage.getMessage()));
    }

    public LottoException(ErrorFormat format, Object... args) {
        super(formatMessage(String.format(format.getMessageFormat(), args)));
    }

    private static String formatMessage(String message) {
        return String.format(UIConstant.FORMAT_ERROR, message);
    }
}
