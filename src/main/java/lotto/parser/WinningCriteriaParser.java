package lotto.parser;

import lotto.domain.BonusNumber;
import lotto.domain.WinningCriteria;
import lotto.domain.WinningNumbers;

public class WinningCriteriaParser implements InputParser<WinningCriteria> {
    private final WinningNumbers winningNumbers;

    public WinningCriteriaParser(WinningNumbers winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    @Override
    public WinningCriteria parse(String input) {
        BonusNumberParser bonusNumberParser = new BonusNumberParser();
        BonusNumber bonusNumber = bonusNumberParser.parse(input);
        return new WinningCriteria(this.winningNumbers, bonusNumber);
    }
}
