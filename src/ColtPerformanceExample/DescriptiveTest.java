package ColtPerformanceExample;

import cern.colt.list.DoubleArrayList;
import java.util.List;

/**
 *
 * @author Deni <TheHalcyonSavant@gmail.com>
 */
class DescriptiveTest {

    private DoubleArrayList _sortedDal;

    public void getStats(List<Integer> myBigArray) {
        System.out.println("Colt Library:");

        Stopwatch.start();

        _sortedDal = new DoubleArrayList();
        _sortedDal.addAllOf(myBigArray);
        _sortedDal.sort();

        Stopwatch.stop();

        System.out.format("\tSorting array\t%s\n", Stopwatch.getTimeStr());

        output("Median");
        output("Mean");
        output("Mode");
        output("Range");
    }

    private void output(String func) {
        try {
            System.out.format("\t%s: %.2f\t%s\n", func,
                    Class.forName("ColtPerformanceExample.Descriptive")
                    .getMethod(func.toLowerCase(), DoubleArrayList.class)
                    .invoke(null, _sortedDal), Stopwatch.getTimeStr());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
