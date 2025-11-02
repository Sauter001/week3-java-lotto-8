package lotto.parser;

import lotto.domain.PayAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class PayAmountParserTest {
    InputParser<PayAmount> payAmountParser = new PayAmountParser();

    @Test
    @DisplayName("정상 값 테스트")
    void parseNormal() {
        assertThatNoException().isThrownBy(() -> {
            for (int i = 1000; i <= 1_000_000; i += 1000) {
                PayAmount payAmount = payAmountParser.parse(String.valueOf(i));
                assertThat(payAmount.amount()).isEqualTo(i);
            }
        });
    }

    // === 예외 상황 ===
    @ParameterizedTest
    @DisplayName("1000원 단위 아니면 에러")
    @ValueSource(strings = {"1", "10", "100", "1200", "1001"})
    void notDivIn1000() {
        assertThatException().isThrownBy(() -> payAmountParser.parse("100"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("0은 에러")
    void zeroParse() {
        assertThatException().isThrownBy(() -> payAmountParser.parse("0"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("음수 에러")
    @ValueSource(strings = {"-1000", "-1", "-2000"})
    void negativeParse() {
        assertThatException().isThrownBy(() -> payAmountParser.parse("-100"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("정수가 아닌 입력 에러")
    @ValueSource(strings = {"abc", "computer science", "1.25", "0xffff"})
    void invalidInputParse(String input) {
        assertThatException().isThrownBy(() -> payAmountParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("INT 범위 밖 입력 에러")
    void intOutOfRange() {
        List<String> longValues = List.of(String.valueOf(0x80000000L), "2147484000");
        for  (String value : longValues) {
            assertThatException().isThrownBy(() -> payAmountParser.parse(value))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
