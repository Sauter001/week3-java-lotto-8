package lotto.domain;

import lotto.constants.Rank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class WinningCounter {
    private final Map<Rank, Integer> counter;

    private WinningCounter(List<Rank> ranks) {
        this.counter = initialize(ranks);
    }

    private static Map<Rank, Integer> initialize(List<Rank> ranks) {
        Map<Rank, Integer> counter = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            counter.put(rank, 0);
        }

        for (Rank rank : ranks) {
            counter.put(rank, counter.get(rank) + 1);
        }
        return counter;
    }

    public static WinningCounter from(List<Rank> ranks) {
        return new WinningCounter(ranks);
    }

    private long calculateTotalPrize() {
        long result = 0L;
        for (Rank rank : Rank.values()) {
            result += counter.get(rank) * rank.getPrize();
        }

        return result;
    }

    public Map<Rank, Integer> getMap() {
        // 불변 Map 객체 생성
        return Map.copyOf(counter);
    }

    public BigDecimal calculateRateOfProfit(PayAmount payAmount) {
        final long totalPrize = this.calculateTotalPrize();
        final int PERCENTAGE_RATE = 100;
        final int ROUND_SCALE = 2;

        return new BigDecimal(totalPrize)
                .multiply(BigDecimal.valueOf(PERCENTAGE_RATE))
                .divide(BigDecimal.valueOf(payAmount.amount()), ROUND_SCALE, RoundingMode.HALF_UP);
    }
}
