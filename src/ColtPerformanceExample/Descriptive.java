package ColtPerformanceExample;

import cern.colt.list.DoubleArrayList;

/**
 * Probably Colt Java library is the best open source for optimizing
 * mathematical operations: http://acs.lbl.gov/software/colt/
 *
 * @author Deni <TheHalcyonSavant@gmail.com>
 */
public class Descriptive extends cern.jet.stat.Descriptive {

    public static double median(DoubleArrayList sortedData) {
        Stopwatch.start();

        double result = cern.jet.stat.Descriptive.quantile(sortedData, 0.5);

        Stopwatch.stop();

        return result;
    }

    public static double mean(DoubleArrayList dal) {
        Stopwatch.start();

        double result = cern.jet.stat.Descriptive.mean(dal);

        Stopwatch.stop();

        return result;
    }

    @SuppressWarnings("empty-statement")
    public static double mode(DoubleArrayList sortedData) {
        Stopwatch.start();

        int size = sortedData.size();
        int i = 0, maxFreq = 1;
        double result = 0;

        while (i < size) {
            double element = sortedData.get(i);
            int cursor = i;
            // determine run length (number of equal elements)
            while (++i < size && sortedData.get(i) == element);
            int freq = i - cursor;
            if (freq > maxFreq) {
                maxFreq = freq;
                result = element;
            }
        }

        Stopwatch.stop();

        return result;
    }

    public static double range(DoubleArrayList sortedData) {
        Stopwatch.start();

        double result = sortedData.get(sortedData.size() - 1) - sortedData.get(0);

        Stopwatch.stop();

        return result;
    }
}
