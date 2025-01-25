package lotto;

import custom.CustomException;

public class WinningInfo {

    private final WinningNumber winningNumber;
    private final BonusNumber bonusNumber;

    public WinningInfo(WinningNumber winningNumber, BonusNumber bonusNumber) {
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
}
