package lotto.parser;

import lotto.domain.BonusNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class BonusNumberParserTest {
    private static final String ERROR_PREFIX = "[ERROR]";

    private static final BonusNumberParser parser = new BonusNumberParser();

    @Test
    @DisplayName("기본 테스트")
    void parseBonusNumber() {
        BonusNumber bonusNumber = parser.parse("42");
        assertThat(bonusNumber.value()).isEqualTo(42);
    }

    @Test
    @DisplayName("경계값 테스트")
    void parseBoundaryNumber() {
        BonusNumber bonusNumber = parser.parse("1");
        assertThat(bonusNumber.value()).isEqualTo(1);

        bonusNumber = parser.parse("45");
        assertThat(bonusNumber.value()).isEqualTo(45);
    }

    @ParameterizedTest
    @ValueSource(strings = {"42  ", " 42", " 42 ", "    42"})
    @DisplayName("공백 포함 입력 테스트")
    void parseBonusNumberIncludingBlank(String value) {
        BonusNumber bonusNumber = parser.parse(value);
        assertThat(bonusNumber.value()).isEqualTo(42);
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
                    .hasMessageContaining(ERROR_PREFIX);;
        }
    }
}
