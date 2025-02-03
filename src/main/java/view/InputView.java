package view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String LOTTO_PRICE = "구입금액을 입력해 주세요.";
    private static final String LOTTO_NUMBER = "\n당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER = "\n보너스 번호를 입력해 주세요.";

    public String inputLottoPrice() {
        System.out.println(LOTTO_PRICE);
        return Console.readLine();
    }

    public String inputLottoNumber() {
        System.out.println(LOTTO_NUMBER);
        return Console.readLine();
    }

    public String inputBonusNumber() {
        System.out.println(BONUS_NUMBER);
        return Console.readLine();
    }
}
