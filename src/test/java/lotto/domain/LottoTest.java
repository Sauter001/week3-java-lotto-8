package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class LottoTest {
    @Test
    @DisplayName("로또 번호 경계값(45) 테스트")
    void lottoNumberThreshold() {
        assertThatNoException().isThrownBy(() -> new Lotto(List.of(1, 2, 3, 5, 7, 45)));
    }

    @Test
    @DisplayName("정렬 되지 않은 로또 번호 자동 정렬")
    void lottoSorted() {
        Lotto lotto = new  Lotto(List.of(1, 7, 3, 9, 24, 10));
        List<Integer> numbers = lotto.getNumbers();

        for (int i = 0; i < numbers.size() - 1; i++) {
            assertThat(numbers.get(i) < numbers.get(i + 1)).isTrue();
        }
    }

    // === 예외 상황 ===

    @Test
    void lottoNumbersExceed6() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void lottoNumbersLessThan6() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void lottoNumberOutOfRange() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 50)))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Lotto(List.of(0, 2, 3, 4, 5, 40)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void lottoNumberDuplication() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
