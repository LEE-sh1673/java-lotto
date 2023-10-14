package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public int readInt() {
        return Integer.parseInt(readLine());
    }

    public String readLine() {
        return Console.readLine();
    }

    public List<Integer> readNumbers() {
        return Arrays.stream(readLine().split(","))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }
}
