package lotto.domain;

import lotto.constants.Rank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class WinningCounter {
    private final Map<Rank, Integer> counter;

    public WinningCounter() {
        this.counter = initialize();
    }

    private static Map<Rank, Integer> initialize() {
        Map<Rank, Integer> counter = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            counter.put(rank, 0);
        }
        return counter;
    }

    public void increment(Rank rank) {
        counter.put(rank, counter.get(rank) + 1);
    }

    private long calculateTotalPrize() {
        long result = 0L;
        for (Rank rank : Rank.values()) {
            result += counter.get(rank) * rank.getPrize();
        }

        return result;
    }

    public Map<Rank, Integer> getMap() {
        return Collections.unmodifiableMap(counter);
    }

    public BigDecimal calculateRateOfProfit(PayAmount payAmount) {
        final long totalPrize = this.calculateTotalPrize();
        final int PERCENTAGE_RATE = 100;
        final int ROUND_SCALE = 1;

        return new BigDecimal(totalPrize)
                .multiply(new BigDecimal(PERCENTAGE_RATE))
                .divide(new BigDecimal(payAmount.amount()), ROUND_SCALE, RoundingMode.HALF_UP);
    }
}
