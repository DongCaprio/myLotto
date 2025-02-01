package controller;

import static model.Lotto.LOTTO_END_NUM;
import static model.Lotto.LOTTO_NUM_SIZE;
import static model.Lotto.LOTTO_START_NUM;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import model.BonusNumber;
import model.Lotto;
import model.LottoCount;
import model.LottoRankCollection;
import model.WinningInformation;
import model.WinningNumber;
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
        int lottoCount = inputPrice().getLottoCount();
        outputView.printLottoCount(lottoCount);
        List<Lotto> lottos = makeLottos(lottoCount, LOTTO_START_NUM, LOTTO_END_NUM, LOTTO_NUM_SIZE);
        printLottos(lottos);
        WinningInformation winningInfo = makeWinningInfo(inputWinningNumber());
        LottoRankCollection lottoRanks = LottoRankCollection.from(lottos, winningInfo);
        outputView.printWinningPaper(lottoRanks.makeWinningPaper(), lottoRanks.calculateRateOfReturn(lottoCount));
    }

    private void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            outputView.println(lotto.makeStringLottoNumber());
        }
    }

    private WinningInformation makeWinningInfo(WinningNumber winningNumber) {
        return handleRetryOnError(() -> WinningInformation.from(winningNumber, inputBonusNumber()));
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

    private List<Lotto> makeLottos(int count, int startNumber, int endNumber, int numberSize) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(Lotto.makeRandomLotto(startNumber, endNumber, numberSize));
        }
        return lottos;
    }
}
