package lotto.constants;

import java.util.stream.Stream;

public enum Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    LOSE(-1, false, 0);

    private final int numberToMatch;
    private final boolean requireBonus;
    private final long prize;

    Rank(int numberToMatch, boolean requireBonus, long prize) {
        this.numberToMatch = numberToMatch;
        this.requireBonus = requireBonus;
        this.prize = prize;
    }

    public static Rank of(int count, boolean matchesBonus) {
        return Stream.of(values())
                .filter(rank -> rank.matches(count, matchesBonus))
                .findFirst()
                .orElse(LOSE);
    }

    private boolean matches(int count, boolean matchesBonus) {
        boolean countMatches = (this.numberToMatch == count);
        boolean bonusMatches = (!this.requireBonus || matchesBonus);

        return countMatches && bonusMatches;
    }

    public int getNumberToMatch() {
        return numberToMatch;
    }

    public boolean isRequireBonus() {
        return requireBonus;
    }

    public long getPrize() {
        return prize;
    }
}
