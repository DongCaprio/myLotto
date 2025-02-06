package controller;

import static model.Lotto.LOTTO_END_NUM;
import static model.Lotto.LOTTO_NUM_SIZE;
import static model.Lotto.LOTTO_START_NUM;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import model.Lotto;
import model.LottoCount;
import model.LottoNumber;
import model.LottoRankCollection;
import model.LottoRankCount;
import model.WinningInformation;
import model.WinningNumber;
import model.dto.WinningPaperDto;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        int lottoCount = inputPrice().getLottoCount();
        outputView.printLottoCount(lottoCount);
        List<Lotto> lottos = makeLottos(lottoCount, LOTTO_START_NUM, LOTTO_END_NUM, LOTTO_NUM_SIZE);
        printLottos(lottos);
        WinningInformation winningInfo = makeWinningInfo(inputWinningNumber());
        LottoRankCollection lottoRanks = LottoRankCollection.from(lottos, winningInfo);
        List<LottoRankCount> lottoRankCounts = lottoRanks.makeWinningPaper();
        outputView.printWinningPaper(WinningPaperDto.ofList(lottoRankCounts));
        outputView.printRateOfReturn(lottoRanks.calculateRateOfReturn(lottoCount));
    }

    private List<Lotto> makeLottos(int count, int startNumber, int endNumber, int numberSize) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(Lotto.makeRandomLotto(startNumber, endNumber, numberSize));
        }
        return lottos;
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
            String input = inputView.inputLottoPrice();
            return LottoCount.from(input);
        });
    }

    private WinningNumber inputWinningNumber() {
        return handleRetryOnError(() -> {
            String inputWinningNumbers = inputView.inputLottoNumber();
            return WinningNumber.from(inputWinningNumbers);
        });
    }

    private LottoNumber inputBonusNumber() {
        return handleRetryOnError(() -> {
            String inputBonusNumbers = inputView.inputBonusNumber();
            return LottoNumber.from(inputBonusNumbers);
        });
    }

    private <T> T handleRetryOnError(Supplier<T> method) {
        try {
            return method.get();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return handleRetryOnError(method);
        }
    }
}
