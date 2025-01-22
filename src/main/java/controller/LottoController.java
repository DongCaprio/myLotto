package controller;

import java.util.function.Supplier;
import verify.LottoCount;
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
        LottoCount lottoCount = inputPrice();
    }

    private LottoCount inputPrice() {
        return handleRetryOnError(() -> {
            String input = inputView.input(InputView.LOTTO_PRICE);
            return LottoCount.from(input);
        });
    }

}
