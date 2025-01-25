package view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static final String LOTTO_PRICE = "구입금액을 입력해 주세요.";
    public static final String LOTTO_NUMBER = "\n당첨 번호를 입력해 주세요.";
    public static final String BONUS_NUMBER = "\n보너스 번호를 입력해 주세요.";

    public String input(String msg) {
        System.out.println(msg);
        return Console.readLine();
    }
}
