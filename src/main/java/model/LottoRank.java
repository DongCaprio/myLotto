package model;

public enum LottoRank {

    FIRST(2_000_000_000, 6),
    SECOND(30_000_000, 5),
    THIRD(1_500_000, 5),
    FOURTH(50_000, 4),
    FIFTH(5_000, 3),
    FAIL(0, 0);

    private static final String SECOND_MSG = ", 보너스 볼 일치";

    private final int prizeMoney;
    private final int matchCount;

    LottoRank(int prizeMoney, int matchCount) {
        this.prizeMoney = prizeMoney;
        this.matchCount = matchCount;
    }

    public static LottoRank specifyLottoRank(int winningCount, boolean matchBonus) {
        if (winningCount == 6) {
            return LottoRank.FIRST;
        }
        if (winningCount == 5) {
            if (matchBonus) {
                return LottoRank.SECOND;
            }
            return LottoRank.THIRD;
        }
        if (winningCount == 4) {
            return LottoRank.FOURTH;
        }
        if (winningCount == 3) {
            return LottoRank.FIFTH;
        }
        return LottoRank.FAIL;
    }

    public String makePrintMessage(long count) {
        if (this == SECOND) {
            return String.format("%d개 일치%s (%,d원) - %d개%n", matchCount, SECOND_MSG, prizeMoney, count);
        }
        return String.format("%d개 일치 (%,d원) - %d개%n", matchCount, prizeMoney, count);
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }
}
