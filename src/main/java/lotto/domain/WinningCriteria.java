package lotto.domain;

import lotto.constants.ErrorMessage;
import lotto.exception.LottoException;

public class WinningCriteria {
    private final WinningNumbers winningNumbers;
    private final BonusNumber bonusNumber;

    public WinningCriteria(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        validateNotDuplication(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validateNotDuplication(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        if (winningNumbers.hasBonusNumber(bonusNumber)) {
            throw new LottoException(ErrorMessage.BONUS_NUMBER_DUPLICATED_WITH_WINNING_NUMBER);
        }
    }
}
