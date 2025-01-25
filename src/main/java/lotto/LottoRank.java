package lotto;

import java.util.List;

public enum LottoRank {

    FIRST(2_000_000_000, 6),
    SECOND(30_000_000, 5),
    THIRD(1_500_000, 5),
    FOURTH(50_000, 4),
    FIFTH(5_000, 3);

    private final int prizeMoney;
    private final int matchCount;

    private static final String SECOND_MSG = ", 보너스 볼 일치";

    LottoRank(int prizeMoney, int matchCount) {
        this.prizeMoney = prizeMoney;
        this.matchCount = matchCount;
    }

    public String makePrintMessage(long count) {
        if (this == SECOND) {
            return String.format("%d개 일치%s (%,d원) - %d개", matchCount, SECOND_MSG, prizeMoney, count);
        }
        return String.format("%d개 일치 (%,d원) - %d개", matchCount, prizeMoney, count);
    }

    public static void printMessage(List<LottoRank> lottoRanks) {
        LottoRank[] ranks = LottoRank.values();
        for (int i = ranks.length - 1; i >= 0; i--) {
            LottoRank lottoRank = ranks[i];
            long count = lottoRanks.stream()
                    .filter(rank -> rank == lottoRank)
                    .count();
            System.out.println(ranks[i].makePrintMessage(count));
        }
    }
}
