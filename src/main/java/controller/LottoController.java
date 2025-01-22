package controller;

import java.util.function.Supplier;
import verify.LottoPrice;
import view.InputView;

public class LottoController {

    private final InputView inputView = new InputView();

    private <T> T handleRetryOnError(Supplier<T> method) {
        try {
            return method.get();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return handleRetryOnError(method);
        }
    }

    public void run() {
        LottoPrice lottoPrice = inputPrice();
    }

    private LottoPrice inputPrice() {
        return handleRetryOnError(() -> {
            String input = inputView.input(InputView.LOTTO_PRICE);
            return LottoPrice.from(input);
        });
    }

}
