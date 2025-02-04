package model;

public enum LottoRank {

    FIRST(2_000_000_000, 6),
    SECOND(30_000_000, 5),
    THIRD(1_500_000, 5),
    FOURTH(50_000, 4),
    FIFTH(5_000, 3),
    FAIL(0, 0);

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

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
