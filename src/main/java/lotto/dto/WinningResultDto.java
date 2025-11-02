package lotto.dto;

import lotto.constants.Rank;

import java.math.BigDecimal;
import java.util.Map;

public record WinningResultDto(Map<Rank, Integer> counter, BigDecimal rateOfProfit) {
}
