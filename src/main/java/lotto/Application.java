package lotto;

import camp.nextstep.edu.missionutils.Console;

public class Application {

    public static void main(String[] args) {
        try {
            new LottoGame().start();
        } finally {
            Console.close();
        }
    }
}
