package lotto.domain;

import lotto.constants.ErrorMessage;
import lotto.constants.Rank;
import lotto.exception.LottoException;

import java.util.List;

public class WinningCriteria {
    private final WinningNumbers winningNumbers;
    private final BonusNumber bonusNumber;

    public WinningCriteria(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        validateNotDuplication(winningNumbers, bonusNumber);
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public Rank determineFrom(Lotto lotto) {
        int matchCount = this.winningNumbers.countMatchedNumberFrom(lotto);
        boolean matchesBonus = lotto.hasBonusNumber(this.bonusNumber);
        return Rank.of(matchCount, matchesBonus);
    }

    private void validateNotDuplication(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        if (winningNumbers.hasBonusNumber(bonusNumber)) {
            throw new LottoException(ErrorMessage.BONUS_NUMBER_DUPLICATED_WITH_WINNING_NUMBER);
        }
    }
}
