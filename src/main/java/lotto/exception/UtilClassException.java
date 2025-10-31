package lotto.exception;

import lotto.constants.UIConstant;

public class UtilClassException extends IllegalStateException {
    public UtilClassException() {
        super(String.format("%s %s", UIConstant.ERROR_PREFIX, "Utility class"));
    }
}
