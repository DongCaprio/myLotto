package view.dto;

import java.util.List;
import model.LottoRank;
import model.LottoRankCount;

public class WinningPaperDto {

    private final int matchedNumber;
    private final int prizeMoney;
    private final int winnerCount;
    private final boolean bonusCheck;

    private WinningPaperDto(int matchedNumber, int prizeMoney, int winnerCount, boolean bonusCheck) {
        this.matchedNumber = matchedNumber;
        this.prizeMoney = prizeMoney;
        this.winnerCount = winnerCount;
        this.bonusCheck = bonusCheck;
    }

    public static List<WinningPaperDto> ofList(List<LottoRankCount> lottoRankCounts) {
        return lottoRankCounts.stream()
                .map(rankCount -> new WinningPaperDto(
                        rankCount.getRank().getMatchCount(),
                        rankCount.getRank().getPrizeMoney(),
                        rankCount.getMatchCount(),
                        rankCount.getRank() == LottoRank.SECOND
                )).toList();
    }

    public int getMatchedNumber() {
        return matchedNumber;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getWinnerCount() {
        return winnerCount;
    }

    public boolean isBonusCheck() {
        return bonusCheck;
    }
}
