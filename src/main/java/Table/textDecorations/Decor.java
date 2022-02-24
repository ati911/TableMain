package Table.textDecorations;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public interface Decor {
    String RESET = "\u001B[0m";
    String BLACK = "\u001B[30m";
    String RED = "\u001B[31m";
    String GREEN = "\u001B[32m";
    String YELLOW = "\u001B[33m";
    String BLUE = "\u001B[34m";
    String PURPLE = "\u001B[35m";
    String CYAN = "\u001B[36m";
    String WHITE = "\u001B[37m";

    String ITALIC = "\033[3m";
    String BOLD = "\033[1m";
    String INVERSE = "\033[7m";
    String UNDERLINE = "\033[4m";
    String DOUBLE_UNDERLINE = "\033[21m";
    String CROSSED_OUT = "\033[9m";
    String FRAMED = "\033[51m";

    String BACK_BLACK = "\033[40m";
    String BACK_RED = "\033[41m";
    String BACK_GREEN = "\033[42m";
    String BACK_YELLOW = "\033[43m";
    String BACK_BLUE = "\033[44m";
    String BACK_PURPLE = "\033[45m";
    String BACK_CYAN = "\033[46m";
    String BACK_WHITE = "\033[47m";

    static final int DEFAULT_DELAY_1S = 1000;
    static final int DEFAULT_DELAY_3S = 3000;
    static final int DEFAULT_DELAY_2S = 2000;
    static final int DEFAULT_SHORT_DELAY = 10;



    static void print(String text, String... decor) {
        System.out.print(appendDecors(decor) + text + RESET + " ");
    }

    static void println(String text, String... decor) {
        System.out.println(appendDecors(decor) + text + RESET);
    }

    static void print(int delay, String text, String... decor) {
        System.out.print(appendDecors(decor) + text + RESET + " ");
        sleep(delay);
    }

    static void println(int delay, String text, String... decor) {
        System.out.println(appendDecors(decor) + text + RESET);
        sleep(delay);
    }

    private static String appendDecors(String... decor) {
        StringBuilder decorFinal = new StringBuilder();
        Arrays.stream(decor).forEach(decorFinal::append);
        return decorFinal.toString();
    }

    private static void sleep(int delay) {
        try {
            TimeUnit.MILLISECONDS.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
