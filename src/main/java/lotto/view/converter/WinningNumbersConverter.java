package lotto.view.converter;

import lotto.domain.WinningNumbers;

public class WinningNumbersConverter extends AbstractInputConverter<WinningNumbers> {
    protected WinningNumbersConverter(String prompt) {
        super(prompt);
    }

    @Override
    protected WinningNumbers parse(String input) {
        return null;
    }
}
