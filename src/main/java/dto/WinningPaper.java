package dto;

public class WinningPaper {

    private final int matchedNumber;
    private final int prizeMoney;
    private final int winnerCount;
    private final boolean bonusCheck;

    public WinningPaper(int matchedNumber, int prizeMoney, int winnerCount, boolean bonusCheck) {
        this.matchedNumber = matchedNumber;
        this.prizeMoney = prizeMoney;
        this.winnerCount = winnerCount;
        this.bonusCheck = bonusCheck;
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
