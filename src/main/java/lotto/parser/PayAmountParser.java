package lotto.parser;

import lotto.constants.ErrorMessage;
import lotto.domain.PayAmount;
import lotto.exception.LottoException;

public class PayAmountParser implements  InputParser<PayAmount> {
    @Override
    public PayAmount parse(String input) {
        if (input.isEmpty()) {
            throw new LottoException(ErrorMessage.PAY_AMOUNT_EMPTY);
        }

        try {
            String stripped = input.strip();
            return new PayAmount(Integer.parseInt(stripped));
        } catch (NumberFormatException e) {
            throw new LottoException(ErrorMessage.PAY_AMOUNT_NOT_INTEGER);
        }
    }
}
