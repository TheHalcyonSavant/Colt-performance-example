package ColtPerformanceExample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * _myBigArray can be unsorted
 *
 * @author Deni <TheHalcyonSavant@gmail.com>
 */
class PrimitiveMath {

    private List<Integer> _myBigArray;

    // for further optimization the two medianOfMedians calls can be concurrent
    private void median() throws Exception {
        Stopwatch.start();

        int n = _myBigArray.size();
        Integer m1 = medianOfMedians(_myBigArray, n / 2);
        if (n % 2 == 0) {
            Integer m2 = medianOfMedians(_myBigArray, (n / 2) - 1);

            Stopwatch.stop();
            System.out.format("\tMedian: %.2f\t%s\n", (double) (m1 + m2) / 2, Stopwatch.getTimeStr());
        } else {

            Stopwatch.stop();
            System.out.format("\tMedian: %d\t%s\n", m1, Stopwatch.getTimeStr());
        }
    }

    // O(n) -> http://en.wikipedia.org/wiki/Median_of_medians#Proof_of_O.28n.29_running_time
    private static Integer medianOfMedians(List<Integer> arr, int k) {
        List<Integer> tmp;

        if (arr.size() <= 10) {
            tmp = new ArrayList<Integer>(arr);
            Collections.sort(tmp);
            return tmp.get(k);
        }

        List<Integer> l_x = new ArrayList<Integer>();

        for (int i = 0; i < arr.size(); i += 5) {
            tmp = new ArrayList<Integer>();

            for (int q = i; q < i + 5 && q < arr.size(); q++) {
                tmp.add(arr.get(q));
            }

            if (i + 5 < arr.size()) {
                l_x.add(medianOfMedians(tmp, 3));
            } else {
                l_x.add(medianOfMedians(tmp, (arr.size() - i) / 2));
            }
        }

        Integer l_middle = medianOfMedians(l_x, l_x.size() / 2);
        List<Integer> l_lower, l_higher, l_exact;
        l_lower = new ArrayList<Integer>();
        l_higher = new ArrayList<Integer>();
        l_exact = new ArrayList<Integer>();

        for (Integer v : arr) {
            if (v < l_middle) {
                l_lower.add(v);
            } else if (v > l_middle) {
                l_higher.add(v);
            } else {
                l_exact.add(v);
            }
        }

        if (k < l_lower.size()) {
            return medianOfMedians(l_lower, k);
        } else if (k >= l_lower.size() + l_exact.size()) {
            return medianOfMedians(l_higher, k - l_lower.size() - l_exact.size());
        } else {
            return l_exact.get(0);
        }
    }

    // O(n)
    private void mean() {
        Stopwatch.start();

        int sum = 0;

        for (Integer val : _myBigArray) {
            sum += val;
        }

        Stopwatch.stop();
        System.out.format("\tMean: %.2f\t%s\n",
                (double) sum / _myBigArray.size(), Stopwatch.getTimeStr());
    }

    // O(n)
    private void mode() {
        Stopwatch.start();

        Boolean moreThen0 = false;
        Map<Integer, Integer> freqs = new HashMap<Integer, Integer>();

        for (Integer val : _myBigArray) {
            Integer freq = freqs.get(val);
            if (freq == null) {
                freq = 1;
            } else {
                freq++;
                moreThen0 = true;
            }
            freqs.put(val, freq);
        }

        int mode = 0, maxFreq = 1;

        if (moreThen0) {
            for (Map.Entry<Integer, Integer> entry : freqs.entrySet()) {
                int freq = entry.getValue();
                int v = entry.getKey();
                if (freq > maxFreq) {
                    maxFreq = freq;
                    mode = v;
                } else if (freq == maxFreq && mode > v) {
                    // get the minimum of all the maximum frequencies
                    mode = v;
                }
            }
        }

        Stopwatch.stop();
        System.out.format("\tMode: %.2f\t%s\n", (double) mode, Stopwatch.getTimeStr());
    }

    // O(n)
    private void range() {
        Stopwatch.start();
        Integer initMin = _myBigArray.get(0), initMax = initMin;
        for (Integer v : _myBigArray) {
            if (v < initMin) {
                initMin = v;
            }
            if (v > initMax) {
                initMax = v;
            }
        }
        Stopwatch.stop();
        System.out.format("\tRange: %.2f\t%s\n", (double) initMax - initMin, Stopwatch.getTimeStr());
    }

    public void getStats(List<Integer> myBigArray) throws Exception {
        _myBigArray = myBigArray;
        System.out.println("Primitive Math, everything in O(n):");
        median();
        mean();
        mode();
        range();
    }
}
