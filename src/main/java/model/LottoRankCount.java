package model;

public class LottoRankCount {

    private final LottoRank rank;
    private final int matchCount;

    public LottoRankCount(LottoRank rank, int matchCount) {
        this.rank = rank;
        this.matchCount = matchCount;
    }

    public LottoRank getRank() {
        return rank;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
