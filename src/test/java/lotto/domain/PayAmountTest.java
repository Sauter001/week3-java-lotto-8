package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PayAmountTest {
    @Test
    @DisplayName("정상 생성")
    void createPayAmount() {
        for (int amount = 1000; amount <= 1000000; amount += 1000) {
            assertThat(new PayAmount(amount).amount()).isEqualTo(amount);
        }
    }

    // ==== 예외 처리

    @Test
    @DisplayName("구입 금액 0이면 예외")
    void amountZero() {
        assertThatThrownBy(() -> new PayAmount(0)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구입 금액 음수면 예외")
    void amountNegative() {
        assertThatThrownBy(() -> new PayAmount(-1)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new PayAmount(-10)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("구입 금액이 1000 단위가 아니면 예외")
    @ValueSource(ints = {1024, 100, 12, 10, 1})
    void amountNotDividedIn1000(int amount) {
        assertThatThrownBy(() -> new PayAmount(amount)).isInstanceOf(IllegalArgumentException.class);
    }
}
