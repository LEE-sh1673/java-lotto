package lotto.view;

public class OutputView {

    public void printPublishedLotto(final String lotto) {
        System.out.println();
        System.out.println(lotto);
    }

    public void printWinningStatistic(final String playResults, final String profitRate) {
        System.out.println();
        System.out.println(playResults);
        System.out.println(profitRate);
    }
}
