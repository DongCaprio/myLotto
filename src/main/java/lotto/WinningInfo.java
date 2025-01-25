package lotto;

import custom.CustomException;
import java.util.HashSet;
import java.util.Set;

public class WinningInfo {

    private final WinningNumber winningNumber;
    private final BonusNumber bonusNumber;

    private WinningInfo(WinningNumber winningNumber, BonusNumber bonusNumber) {
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
    }

    public static WinningInfo from(WinningNumber winningNumber, BonusNumber bonusNumber) {
        checkDuplicate(winningNumber, bonusNumber);
        return new WinningInfo(winningNumber, bonusNumber);
    }

    private static void checkDuplicate(WinningNumber winningNumber, BonusNumber bonusNumber) {
        if (winningNumber.getWinningNumber().contains(bonusNumber.getBonusNumber())) {
            throw new CustomException("로또 당첨 번호와 보너스 번호는 중복될 수 없습니다");
        }
    }

    public LottoRank specifyLottoRank(Lotto lotto) {
        int winningCount = checkWinningCount(lotto);
        if (winningCount == 6) {
            return LottoRank.FIRST;
        }
        if (winningCount == 5) {
            return checkBonusNumber(lotto);
        }
        if (winningCount == 4) {
            return LottoRank.FOURTH;
        }
        if (winningCount == 3) {
            return LottoRank.FIFTH;
        }
        return null;
    }

    private int checkWinningCount(Lotto lotto) {
        Set<Integer> winningSet = new HashSet<>(winningNumber.getWinningNumber());
        Set<Integer> lottoSet = new HashSet<>(lotto.getNumbers());
        winningSet.retainAll(lottoSet);
        return winningSet.size();
    }

    private LottoRank checkBonusNumber(Lotto lotto) {
        boolean matchBonus = lotto.getNumbers().contains(bonusNumber.getBonusNumber());
        if (matchBonus) {
            return LottoRank.SECOND;
        }
        return LottoRank.THIRD;
    }
}
