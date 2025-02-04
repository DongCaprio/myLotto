package model;

import static model.LottoCount.ONE_LOTTO_PRICE;

import dto.WinningPaper;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
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

    public List<WinningPaper> makeWinningPaper() {
        LottoRank[] ranks = LottoRank.values();
        List<WinningPaper> winningPapers = new ArrayList<>();

        for (LottoRank rank : ranks) {
            long count = lottoRanks.stream()
                    .filter(lottoRank -> lottoRank == rank)
                    .count();
            if (rank.getPrizeMoney() > 0) {
                boolean bonusCheck = rank == LottoRank.SECOND;
                winningPapers.add(new WinningPaper(
                        rank.getMatchCount(),
                        rank.getPrizeMoney(),
                        (int) count,
                        bonusCheck)
                );
            }
        }
        Collections.reverse(winningPapers);
        return winningPapers;
    }
}
