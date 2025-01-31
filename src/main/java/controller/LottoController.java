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
        List<LottoRank> lottoRanks = getLottoRanks(lottos, winningInfo);
        LottoRank.printMessage(lottoRanks);
        calculateRateOfReturn(lottoRanks, lottoCount);
    }

    private void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            outputView.println(lotto.toString());
        }
    }

    private List<LottoRank> getLottoRanks(List<Lotto> lottos, WinningInfo winningInfo) {
        return lottos.stream()
                .map(winningInfo::specifyLottoRank)
                .filter(Objects::nonNull)
                .toList();
    }

    private void calculateRateOfReturn(List<LottoRank> lottoRanks, int lottoCount) {
        double totalMoney = lottoRanks.stream()
                .mapToDouble(LottoRank::getPrizeMoney)
                .sum();
        double rateReturn = totalMoney / (ONE_LOTTO_PRICE * lottoCount) * 100;
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.printf("총 수익률은 %s%%입니다.%n", df.format(rateReturn));
    }

    private WinningInfo makeWinningInfo(WinningNumber winningNumber) {
        return handleRetryOnError(() -> WinningInfo.from(winningNumber, inputBonusNumber()));
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
