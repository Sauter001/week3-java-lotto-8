package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BonusNumberTest {

    public static final int MIN_NUM = 1;
    public static final int MAX_NUM = 45;

    @Test
    @DisplayName("정상 생성")
    void createBonusNumber() {
        for (int num = MIN_NUM; num <= MAX_NUM; num++) {
            assertThat(new BonusNumber(num).value()).isEqualTo(num);
        }
    }

    // ==== 예외 처리

    @Test
    @DisplayName("보너스 번호 0이면 예외")
    void numberZero() {
        assertThatThrownBy(() -> new BonusNumber(0)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스 번호 음수면 예외")
    void numberNegative() {
        assertThatThrownBy(() -> new BonusNumber(-1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new BonusNumber(-10)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("값이 45초과면 예외")
    void numberExceed45() {
        assertThatThrownBy(() -> new BonusNumber(46)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new BonusNumber(100)).isInstanceOf(IllegalArgumentException.class);
    }
}
