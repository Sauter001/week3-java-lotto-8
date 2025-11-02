package lotto.parser;

import lotto.domain.WinningNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class WinningNumberParserTest {
    private static final InputParser<WinningNumbers> parser = new WinningNumbersParser();

    @Test
    @DisplayName("기본 생성 테스트")
    void createWinningNumberTest() {
        String input = "1,23,35,37";
        assertThat(parser.parse(input)).isInstanceOf(WinningNumbers.class);
    }

    @Test
    @DisplayName("공백 포함된 입력 정상 처리")
    void winningNumbersIncludingBlank() {
        String input = " 1,  2, 3, 4 ";
        assertThat(parser.parse(input)).isInstanceOf(WinningNumbers.class);
    }

    // === 예외 상황 ===
    @Test
    @DisplayName("입력된 번호가 없으면 에러")
    void numbersDoesNotExist() {
        assertThatThrownBy(() -> parser.parse(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("정수 아닌 값 존재시 에러")
    void NotANumberExists() {
        assertThatThrownBy(() -> parser.parse("3,12,a,20,23,35"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복되는 번호 존재시 에러")
    void numbersDuplication() {
        assertThatThrownBy(() -> parser.parse("3,12,20,20,23,35"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("범위 밖의 수 존재시 에러")
    void winningNumberOutOfRange() {
        // 1-45 범위 밖 양수
        assertThatThrownBy(() -> parser.parse("3,12,20,22,31,46"))
                .isInstanceOf(IllegalArgumentException.class);

        // 0 포함
        assertThatThrownBy(() -> parser.parse("0,12,20,22,31,45"))
                .isInstanceOf(IllegalArgumentException.class);

        // 음수 포함
        assertThatThrownBy(() -> parser.parse("-11,12,20,22,31,45"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2,,10", "2, ,10", ",2,10", "2,10,", "2,10, ", ","})
    @DisplayName("쉼표 사이의 빈 문자열")
    void blankBetweenCommas(String input) {
        assertThatThrownBy(() -> parser.parse(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
