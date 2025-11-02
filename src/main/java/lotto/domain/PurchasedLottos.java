package lotto.domain;

import lotto.constants.ErrorMessage;
import lotto.constants.Rank;
import lotto.dto.LottoDto;
import lotto.dto.PurchasedLottosDto;
import lotto.exception.LottoException;

import java.util.List;

public class PurchasedLottos {
    private final List<Lotto> lottos;

    public PurchasedLottos(List<Lotto> lottos, PayAmount payAmount) {
        validate(lottos, payAmount);
        this.lottos = lottos;
    }

    public int size() {
        return lottos.size();
    }

    private void validate(List<Lotto> lottos, PayAmount payAmount) {
        if (lottos.size() != payAmount.countLotto()) {
            throw new LottoException(ErrorMessage.GENERATED_LOTTO_MISMATCH);
        }
    }

    public WinningCounter countMatchedNumbers(WinningNumbers winningNumbers, BonusNumber bonusNumber) {
        WinningCounter winningCounter = new WinningCounter();

        for (Lotto lotto : this.lottos) {
            int countedWinningNumber = winningNumbers.countMatchedNumberFrom(lotto);
            boolean matchesBonus = lotto.hasBonusNumber(bonusNumber);
            Rank rank = Rank.of(countedWinningNumber, matchesBonus);

            winningCounter.increment(rank);
        }
        return winningCounter;
    }

    public PurchasedLottosDto toDto() {
        List<LottoDto> dtos = this.lottos.stream()
                .map(lotto -> new LottoDto(lotto.getNumbers()))
                .toList();
        return new PurchasedLottosDto(dtos);
    }
}
