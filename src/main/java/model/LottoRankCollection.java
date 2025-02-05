package model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.dto.WinningPaperDto;

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
        double rateReturn = totalMoney / (LottoPrice.PRICE.getOnePrice() * lottoCount) * 100;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(rateReturn);
    }

    public List<WinningPaperDto> makeWinningPaper() {
        LottoRank[] ranks = LottoRank.values();
        List<WinningPaperDto> winningPapers = new ArrayList<>();

        for (LottoRank rank : ranks) {
            long matchCount = lottoRanks.stream()
                    .filter(lottoRank -> lottoRank == rank)
                    .count();
            if (rank.getPrizeMoney() > LottoRank.FAIL.getPrizeMoney()) {
                boolean bonusCheck = rank == LottoRank.SECOND;
                winningPapers.add(new WinningPaperDto(
                        rank.getMatchCount(),
                        rank.getPrizeMoney(),
                        (int) matchCount,
                        bonusCheck)
                );
            }
        }
        Collections.reverse(winningPapers);
        return winningPapers;
    }
}
