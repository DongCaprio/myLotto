package view;

import java.util.List;
import model.dto.WinningPaperDto;

public class OutputView {

    private static final String HEADER_FORMAT = "%n당첨 통계%n---%n";
    private static final String SECOND_PRIZE_FORMAT = "%d개 일치, 보너스 볼 일치 (%,d원) - %d개%n";
    private static final String NORMAL_PRIZE_FORMAT = "%d개 일치 (%,d원) - %d개%n";

    public void println(String message) {
        System.out.println(message);
    }

    public void printLottoCount(int count) {
        println("\n" + count + "개를 구매했습니다.");
    }

    public void printWinningPaper(List<WinningPaperDto> winningPapers) {
        System.out.printf(HEADER_FORMAT);
        winningPapers.forEach(this::printPaper);
    }

    private void printPaper(WinningPaperDto paper) {
        String format = NORMAL_PRIZE_FORMAT;
        if (paper.isBonusCheck()) {
            format = SECOND_PRIZE_FORMAT;
        }
        System.out.printf(format,
                paper.getMatchedNumber(),
                paper.getPrizeMoney(),
                paper.getWinnerCount());
    }

    public void printRateOfReturn(String rateOfReturn) {
        System.out.printf("총 수익률은 %s%%입니다.%n", rateOfReturn);
    }
}
