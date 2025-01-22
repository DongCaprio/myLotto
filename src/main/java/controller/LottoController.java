package controller;

import verify.LottoPrice;
import view.InputView;

public class LottoController {

    private final InputView inputView = new InputView();

    public void run() {
        LottoPrice lottoPrice = inputPrice();
    }

    private LottoPrice inputPrice() {
        String input = inputView.input(InputView.LOTTO_PRICE);
        return LottoPrice.from(input);
    }

}
