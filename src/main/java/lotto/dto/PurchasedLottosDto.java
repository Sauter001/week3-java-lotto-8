package lotto.dto;

import java.util.List;

public record PurchasedLottosDto(List<LottoDto> lottos) {
    public int size() {
        return lottos.size();
    }
}
