package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningCriteriaTest {
    private static final WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

    @Test
    @DisplayName("정상 생성")
    void createWinningCriteria() {
        BonusNumber bonusNumber = new BonusNumber(7);
        assertThatNoException().isThrownBy(() -> new WinningCriteria(winningNumbers, bonusNumber));
    }

    // === 예외 처리 ===

    @Test
    @DisplayName("당첨 번호와 보너스 번호 중복 시 에러")
    void winningNumbersContainBonusNumber() {
        BonusNumber bonusNumber = new BonusNumber(6);
        assertThatThrownBy(() -> new WinningCriteria(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
