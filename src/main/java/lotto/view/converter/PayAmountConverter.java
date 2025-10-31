package lotto.view.converter;

import lotto.constants.ErrorCode;
import lotto.constants.PromptMessage;
import lotto.domain.PayAmount;
import lotto.exception.LottoException;

public class PayAmountConverter extends AbstractInputConverter<PayAmount> {
    public PayAmountConverter(PromptMessage promptMessage) {
        super(promptMessage);
    }

    @Override
    public PayAmount parse(String input) {
        try {
            String stripped = input.strip();
            return new PayAmount(Integer.parseInt(stripped));
        } catch (NumberFormatException e) {
            throw new LottoException(ErrorCode.PAY_AMOUNT_NOT_INTEGER);
        }
    }
}
