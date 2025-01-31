package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import model.BonusNumber;
import model.Lotto;
import model.LottoCount;
import model.LottoRankCollection;
import model.WinningInfo;
import model.WinningNumber;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    private <T> T handleRetryOnError(Supplier<T> method) {
        try {
            return method.get();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return handleRetryOnError(method);
        }
    }

    public void run() {
        int lottoCount = inputPrice().getLottoCount();
        outputView.printLottoCount(lottoCount);
        List<Lotto> lottos = makeLottos(lottoCount);
        printLottos(lottos);
        WinningInfo winningInfo = makeWinningInfo(inputWinningNumber());
        LottoRankCollection lottoRanks = LottoRankCollection.from(lottos, winningInfo);
        outputView.printWinningPaper(lottoRanks.makeWinningPaper(), lottoRanks.calculateRateOfReturn(lottoCount));
    }

    private void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            outputView.println(lotto.makeStringLottoNumber());
        }
    }

    private WinningInfo makeWinningInfo(WinningNumber winningNumber) {
        return handleRetryOnError(() -> WinningInfo.from(winningNumber, inputBonusNumber()));
    }

    private LottoCount inputPrice() {
        return handleRetryOnError(() -> {
            String input = inputView.inputMessage(InputView.LOTTO_PRICE);
            return LottoCount.from(input);
        });
    }

    private WinningNumber inputWinningNumber() {
        return handleRetryOnError(() -> {
            String inputWinningNumbers = inputView.inputMessage(InputView.LOTTO_NUMBER);
            return WinningNumber.from(inputWinningNumbers);
        });
    }

    private BonusNumber inputBonusNumber() {
        return handleRetryOnError(() -> {
            String inputBonusNumbers = inputView.inputMessage(InputView.BONUS_NUMBER);
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
