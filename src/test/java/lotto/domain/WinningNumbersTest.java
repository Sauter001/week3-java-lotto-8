package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningNumbersTest {
    private static final Lotto defaultLotto = new Lotto(List.of(1, 2, 3, 5, 7, 45));

    @Test
    @DisplayName("당첨 번호 경계값(45) 테스트")
    void winningNumbersNumberThreshold() {
        assertThatNoException().isThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 5, 7, 45)));
    }

    @Test
    @DisplayName("로또 번호 완전 매칭")
    void matchLottoNumberFull() {
        WinningNumbers  winningNumbers = new WinningNumbers(List.of(1, 2, 3, 5, 7, 45));

        assertThat(winningNumbers.countMatchedNumberFrom(defaultLotto)).isEqualTo(6);
    }

    @Test
    @DisplayName("로또 번호 완전 불일치")
    void mismatchWholeLottoNumber() {
        WinningNumbers  winningNumbers = new WinningNumbers(List.of(4, 6, 9, 10, 25, 31));

        assertThat(winningNumbers.countMatchedNumberFrom(defaultLotto)).isEqualTo(0);
    }

    @Test
    @DisplayName("로또 번호 일부 일치")
    void mismatchPartialLottoNumber() {
        WinningNumbers  winningNumbers = new WinningNumbers(List.of(1, 2, 3, 6, 9, 45));

        assertThat(winningNumbers.countMatchedNumberFrom(defaultLotto)).isEqualTo(4);
    }

    // === 예외 상황 ===

    @Test
    @DisplayName("당첨 번호 개수 6개 초과면 예외")
    void winningNumbersNumbersExceed6() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호 개수 6개 미만이면 예외")
    void winningNumbersNumbersLessThan6() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호가 로또 번호 범위 밖이면 예외")
    void winningNumbersNumberOutOfRange() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 50)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new WinningNumbers(List.of(0, 2, 3, 4, 5, 40)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void winningNumbersNumberDuplication() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
