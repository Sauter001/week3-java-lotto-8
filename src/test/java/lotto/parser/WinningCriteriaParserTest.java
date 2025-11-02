package lotto.parser;

import lotto.domain.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningCriteriaParserTest {
    private static final String ERROR_PREFIX = "[ERROR]";

    private static final WinningNumbers winningNumbers = new WinningNumbers(List.of(2, 3, 4, 5, 6, 7));
    private static final WinningCriteriaParser parser = new WinningCriteriaParser(winningNumbers);

    @Test
    @DisplayName("기본 테스트")
    void parseBonusNumber() {
        assertThatNoException().isThrownBy(() -> parser.parse("42"));
    }

    @Test
    @DisplayName("경계값 테스트")
    void parseBoundaryNumber() {
        assertThatNoException().isThrownBy(() -> parser.parse("1"));
        assertThatNoException().isThrownBy(() -> parser.parse("45"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"42  ", " 42", " 42 ", "    42"})
    @DisplayName("공백 포함 입력 테스트")
    void parseBonusNumberIncludingBlank(String value) {
        assertThatNoException().isThrownBy(() -> parser.parse(value));
    }

    // === 예외 처리 ===
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    @DisplayName("공백 포함 입력 시 예외")
    void parseBlank(String value) {
        assertThatThrownBy(() -> parser.parse(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFIX);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "46", "48"})
    @DisplayName("공백 포함 입력 시 예외")
    void parseNumberOutOfRange(String value) {
        assertThatThrownBy(() -> parser.parse(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFIX);
    }

    @Test
    @DisplayName("INT 범위 밖 입력 에러")
    void intOutOfRange() {
        List<String> longValues = List.of(String.valueOf(0x80000000L), "2147484000");
        for (String value : longValues) {
            assertThatThrownBy(() -> parser.parse(value))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining(ERROR_PREFIX);
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"2", "3", "4", "5", "6", "7"})
    @DisplayName("보너스 번호가 당첨 번호와 겹치면 에러")
    void bonusNumberDuplicateExistsInWinningNumbers(String value) {
        assertThatThrownBy(() -> parser.parse(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFIX);
    }
}
