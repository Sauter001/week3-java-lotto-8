package lotto.parser;

import lotto.constants.ErrorMessage;
import lotto.domain.WinningNumbers;
import lotto.exception.LottoException;

import java.util.Arrays;
import java.util.List;

public class WinningNumbersParser implements InputParser<WinningNumbers> {
    private static final String DELIMITER = ",";

    @Override
    public WinningNumbers parse(String input) {
        if (input.endsWith(DELIMITER)) {
            throw new LottoException(ErrorMessage.WINNING_NUMBER_CANNOT_SPLIT);
        }

        try {
            List<Integer> winningNumberList = Arrays.stream(input.split(DELIMITER))
                    .map(String::strip)
                    .map(Integer::parseInt)
                    .toList();

            return new WinningNumbers(winningNumberList);
        } catch (NumberFormatException e) {
            throw new LottoException(ErrorMessage.WINNING_NUMBER_NOT_INTEGER);
        }
    }
}
