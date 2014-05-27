package ColtPerformanceExample;

/**
 *
 * @author Deni <TheHalcyonSavant@gmail.com>
 */
public class Stopwatch {

    protected Stopwatch() {
    }
    private static long startNanos;
    public static double milliSeconds = 0;

    public static void start() {
        startNanos = System.nanoTime();
    }

    public static void stop() {
        milliSeconds = (double) (System.nanoTime() - startNanos) / 1000000;
    }

    public static String getTimeStr() {
        String ret = "[Time: %.3f ";
        if (milliSeconds > 1000) {
            return String.format(ret + "seconds]", milliSeconds / 1000);
        } else {
            return String.format(ret + "milliseconds]", milliSeconds);
        }
    }
}
