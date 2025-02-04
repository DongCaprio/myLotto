package model;

import static model.LottoCount.ONE_LOTTO_PRICE;

import java.text.DecimalFormat;
import java.util.List;

public class LottoRankCollection {

    private final List<LottoRank> lottoRanks;

    private LottoRankCollection(List<LottoRank> lottoRanks) {
        this.lottoRanks = lottoRanks;
    }

    public static LottoRankCollection from(List<Lotto> lottos, WinningInformation winningInformation) {
        return new LottoRankCollection(
                lottos.stream()
                        .map(lotto -> LottoRank.specifyLottoRank(
                                winningInformation.checkWinningCount(lotto),
                                winningInformation.matchBonusNumber(lotto)
                        ))
                        .toList()
        );

    }

    public String calculateRateOfReturn(int lottoCount) {
        double totalMoney = lottoRanks.stream()
                .mapToDouble(LottoRank::getPrizeMoney)
                .sum();
        double rateReturn = totalMoney / (ONE_LOTTO_PRICE * lottoCount) * 100;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(rateReturn);
    }

    public String makeWinningPaper() {
        LottoRank[] ranks = LottoRank.values();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = ranks.length - 1; i >= 0; i--) {
            LottoRank lottoRank = ranks[i];
            long count = lottoRanks.stream()
                    .filter(rank -> rank == lottoRank)
                    .count();
            stringBuilder.append(lottoRank.makePrintMessage(count));
        }
        return stringBuilder.toString();
    }
}
