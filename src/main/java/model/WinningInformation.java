package model;

import exception.CustomException;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningInformation {

    private final WinningNumber winningNumber;
    private final LottoNumber bonusNumber;

    private WinningInformation(WinningNumber winningNumber, LottoNumber bonusNumber) {
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
    }

    public static WinningInformation from(WinningNumber winningNumber, LottoNumber bonusNumber) {
        checkDuplicate(winningNumber, bonusNumber);
        return new WinningInformation(winningNumber, bonusNumber);
    }

    private static void checkDuplicate(WinningNumber winningNumber, LottoNumber bonusNumber) {
        if (winningNumber.getWinningNumber()
                .stream()
                .anyMatch(lottoNumber -> lottoNumber.getLottoNumber() == bonusNumber.getLottoNumber())) {
            throw new CustomException("로또 당첨 번호와 보너스 번호는 중복될 수 없습니다");
        }
    }

    public int checkWinningCount(Lotto lotto) {
        Set<Integer> winningSet = winningNumber.getWinningNumber().stream()
                .map(LottoNumber::getLottoNumber)
                .collect(Collectors.toSet());

        Set<Integer> lottoSet = lotto.getNumbers().stream()
                .map(LottoNumber::getLottoNumber)
                .collect(Collectors.toSet());

        winningSet.retainAll(lottoSet);
        return winningSet.size();
    }

    public boolean matchBonusNumber(Lotto lotto) {
        return lotto.getNumbers().stream()
                .map(LottoNumber::getLottoNumber)
                .anyMatch(bonusNumber::isSameNumber);
    }

}
