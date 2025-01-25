package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import lotto.BonusNumber;
import lotto.Lotto;
import lotto.WinningInfo;
import lotto.WinningNumber;
import verify.LottoCount;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

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
        outputView.printLottoCount(lottoCount.getLottoCount());
        List<Lotto> lottos = makeLottos(lottoCount.getLottoCount());
        for (Lotto lotto : lottos) {
            outputView.println(lotto.toString());
        }
        WinningInfo winningInfo = WinningInfo.from(inputWinningNumber(), inputBonusNumber());
    }

    private LottoCount inputPrice() {
        return handleRetryOnError(() -> {
            String input = inputView.input(InputView.LOTTO_PRICE);
            return LottoCount.from(input);
        });
    }

    private WinningNumber inputWinningNumber() {
        return handleRetryOnError(() -> {
            String inputWinningNumbers = inputView.input(InputView.LOTTO_NUMBER);
            return WinningNumber.from(inputWinningNumbers);
        });
    }

    private BonusNumber inputBonusNumber() {
        return handleRetryOnError(() -> {
            String inputBonusNumbers = inputView.input(InputView.BONUS_NUMBER);
            return BonusNumber.from(inputBonusNumbers);
        });
    }

    private List<Lotto> makeLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(Lotto.makeRandomLotto());
        }
        return lottos;
    }
}
