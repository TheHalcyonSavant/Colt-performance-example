package ColtPerformanceExample;

import ColtPerformanceExample.DescriptiveTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Deni <TheHalcyonSavant@gmail.com>
 */
public class Main {

    public static void main(String[] args) throws Exception {
        List<Integer> mybigarray =
                generateSomeHugeArray(10000000, 100);
        //generateSomeHugeArray(20, 100);
        //new ArrayList<Integer>(java.util.Arrays.asList(19,22,25,28,49,52,53,54,56,56,59,65,66,69,78,84,89,92,92,95));

        PrimitiveMath arrayManipulator = new PrimitiveMath();
        arrayManipulator.getStats(mybigarray);

        DescriptiveTest arrayManipulator2 = new DescriptiveTest();
        arrayManipulator2.getStats(mybigarray);
    }

    private static List<Integer> generateSomeHugeArray(int size, int maxVal) {
        List<Integer> result = new ArrayList<Integer>();
        Random rnd = new Random();
        Integer tmp = rnd.nextInt(maxVal) + 1;

        System.out.print("Integer [" + (size > 20 ? 1 : tmp));
        result.add(tmp);
        for (Integer i = 1; i < size; i++) {
            tmp = rnd.nextInt(maxVal) + 1;
            if (size <= 20) {
                System.out.print("," + tmp);
            }
            result.add(tmp);
        }
        System.out.print((size > 20 ? " .. " + String.format("%,d", size) : "") + "]");
        System.out.println(" 1 < X < " + maxVal);

        if (result.size() <= 20) {
            List<Integer> result_tmp = new ArrayList<Integer>(result);
            java.util.Collections.sort(result_tmp);
            System.out.print("Sorted [" + result_tmp.get(0));
            for (int i = 1; i < result_tmp.size(); i++) {
                System.out.print(",");
                int middle = (result_tmp.size() - 1) / 2;
                if (i == middle) {
                    System.out.print(">");
                }
                System.out.print(result_tmp.get(i));
                if (result_tmp.size() % 2 == 0) {
                    if (i == middle + 1) {
                        System.out.print("<");
                    }
                } else if (i == middle) {
                    System.out.print("<");
                }
            }
            System.out.println("]");
        }

        System.out.println("---------------------------------------------------------------------------------");

        return result;
    }
}
