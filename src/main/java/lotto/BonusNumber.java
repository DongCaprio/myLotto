package lotto;

import custom.CustomException;

public class BonusNumber {

    private final int bonusNumber;

    private BonusNumber(int bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    public static BonusNumber from(String input) {
        return new BonusNumber(verifyBonusNumber(input));
    }

    private static int verifyBonusNumber(String inputBonus) {
        try {
            int bonus = Integer.parseInt(inputBonus);
            if (bonus < 1 || bonus > 45) {
                throw new CustomException("1 ~ 45 사이값을 입력해주세요");
            }
            return bonus;
        } catch (NumberFormatException e) {
            throw new CustomException("알맞은 숫자를 입력해주세요");
        } catch (CustomException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
