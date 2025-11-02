package lotto.parser;

import lotto.domain.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class WinningNumberParserTest {
    private static final InputParser<WinningNumbers> parser = new WinningNumbersParser();
    // === 예외 상황 ===
    private static final String ERROR_PREFIX = "[ERROR]";

    @Test
    @DisplayName("기본 생성 테스트")
    void createWinningNumberTest() {
        String input = "1,8,23,35,37,45";
        assertThat(parser.parse(input)).isInstanceOf(WinningNumbers.class);
    }

    @Test
    @DisplayName("공백 포함된 입력 정상 처리")
    void winningNumbersIncludingBlank() {
        String input = " 1,  2, 3, 4,5,6 ";
        assertThat(parser.parse(input)).isInstanceOf(WinningNumbers.class);
    }

    @Test
    @DisplayName("입력된 번호가 없으면 에러")
    void numbersDoesNotExist() {
        assertThatThrownBy(() -> parser.parse(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFIX);
    }

    @Test
    @DisplayName("정수 아닌 값 존재시 에러")
    void NotANumberExists() {
        assertThatThrownBy(() -> parser.parse("3,12,a,20,23,35"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFIX);
    }

    @Test
    @DisplayName("당첨 번호가 6개보다 작으면 에러")
    void numbersLessThan6() {
        assertThatThrownBy(() -> parser.parse("3,12,20,23,35"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFIX);
    }

    @Test
    @DisplayName("당첨 번호가 6개보다 많으면 에러")
    void numbersExceed6() {
        assertThatThrownBy(() -> parser.parse("3,12,20,23,35,40,45"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFIX);
    }

    @Test
    @DisplayName("중복되는 번호 존재시 에러")
    void numbersDuplication() {
        assertThatThrownBy(() -> parser.parse("3,12,20,20,23,35"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFIX);
    }

    @Test
    @DisplayName("범위 밖의 수 존재시 에러")
    void winningNumberOutOfRange() {
        // 1-45 범위 밖 양수
        assertThatThrownBy(() -> parser.parse("3,12,20,22,31,46"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFIX);

        // 0 포함
        assertThatThrownBy(() -> parser.parse("0,12,20,22,31,45"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFIX);

        // 음수 포함
        assertThatThrownBy(() -> parser.parse("-11,12,20,22,31,45"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFIX);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2,,10", "2, ,10", ",2,10", "2,10,", "2,10, ", ","})
    @DisplayName("쉼표 사이의 빈 문자열")
    void blankBetweenCommas(String input) {
        assertThatThrownBy(() -> parser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFIX);
    }

    @ParameterizedTest
    @DisplayName("리스트가 아닌 입력 에러")
    @ValueSource(strings = {"abc", "computer science", "1.25", "0xffff", "1 2 3"})
    void invalidInput(String input) {
        assertThatThrownBy(() -> parser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ERROR_PREFIX);
    }
}
