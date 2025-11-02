package lotto.constants;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @ParameterizedTest
    @CsvSource({
            "6, false, FIRST",
            "6, true, FIRST",  // 1등은 보너스 여부 무관
            "5, true, SECOND",
            "5, false, THIRD",
            "4, false, FOURTH",
            "4, true, FOURTH", // 4등은 보너스 여부 무관
            "3, false, FIFTH",
            "3, true, FIFTH",  // 5등은 보너스 여부 무관
            "2, false, LOSE",
            "1, true, LOSE",
            "0, false, LOSE"
    })
    @DisplayName("일치 개수와 보너스 여부에 따른 등수 판정")
    void determineRank(int count, boolean hasBonus, Rank expected) {
        assertThat(Rank.of(count, hasBonus)).isEqualTo(expected);
    }

    @Test
    @DisplayName("각 등수의 상금 확인")
    void checkPrize() {
        assertThat(Rank.FIRST.getPrize()).isEqualTo(2_000_000_000L);
        assertThat(Rank.SECOND.getPrize()).isEqualTo(30_000_000L);
        assertThat(Rank.THIRD.getPrize()).isEqualTo(1_500_000L);
        assertThat(Rank.FOURTH.getPrize()).isEqualTo(50_000L);
        assertThat(Rank.FIFTH.getPrize()).isEqualTo(5_000L);
        assertThat(Rank.LOSE.getPrize()).isEqualTo(0L);
    }
}
