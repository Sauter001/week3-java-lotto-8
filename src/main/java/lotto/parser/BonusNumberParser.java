package lotto.parser;

import lotto.constants.ErrorMessage;
import lotto.domain.BonusNumber;
import lotto.exception.LottoException;

public class BonusNumberParser implements InputParser<BonusNumber> {
    @Override
    public BonusNumber parse(String input) {
        if (input.isBlank()) {
            throw new LottoException(ErrorMessage.BONUS_NUMBER_EMPTY);
        }

        try {
            String stripped = input.strip();
            return new BonusNumber(Integer.parseInt(stripped));
        } catch (NumberFormatException e) {
            throw new LottoException(ErrorMessage.BONUS_NUMBER_NOT_INTEGER);
        }
    }
}
