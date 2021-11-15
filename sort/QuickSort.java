import java.util.Arrays;
import java.util.List;

public class QuickSort {
    public static <T extends Comparable<T>> List<T> sort (List<T> ls, String rule) {
        if (ls.size() == 0) return ls;
        return sortHelper(ls, 0, ls.size() -1, rule);
    } 

    private static <T extends Comparable<T>> List<T> sortHelper (List<T> ls, int lo, int hi, String rule) {
        // base case: small enough not to divide
        if (!(lo >= 0 && hi < ls.size() && lo < hi)) return ls;
        // Choose randomized pivot 
        int pId = ChoosePivot(lo, hi, ls, rule);

        // swap ls[p] & ls[lo]
        pId = swap(ls, pId, lo);
        // partition into 2 buckets < p & >= p
        int i = pId + 1; int j = i; 
        T p = ls.get(pId);
        while (j <= hi) {
            if (ls.get(j).compareTo(p) < 0) {
                swap(ls, i, j);
                i++;
            }
            j++;
        }
        // put pivot to right pos
        pId = swap (ls, pId, i -1);
        // recursive sort bucket < p
        sortHelper(ls, lo, pId -1, rule);    
        // recursive sort bucket >= p
        sortHelper(ls, pId +1, hi, rule);
        return ls;
    }


    /**
     * Returns randomized index within [lo, hi]
     * @param lo
     * @param hi
     * @return
     */
    private static <T extends Comparable<T>> int ChoosePivot (int lo, int hi, List<T> ls, String rule) {
        switch (rule) {
            case "F" :
                return lo;
            case "L" :
                return hi;
            
            case "M": // median of (lo, hi, mid) (3 distinct numbers)
                int mid = (lo + hi)/2;
                T loVal = ls.get(lo);
                T hiVal = ls.get(hi);
                T midVal = ls.get(mid);
                // med = loVal
                if (loVal.compareTo(hiVal) * loVal.compareTo(midVal) < 0) { 
                    return lo;
                }
                if (loVal.compareTo(hiVal) < 0) {
                    return hiVal.compareTo(midVal) < 0 ? hi : mid;
                }
                return hiVal.compareTo(midVal) < 0 ? mid : hi;
            default :
                return lo;
        }
        
    }

    
    /**
     * Swaps element ls[i] & ls[j], return new pId. 
     * @param ls
     * @param i
     * @param j
     */
    private static <T extends Comparable<T>> int swap (List<T> ls, int curId, int newId) {
        if (curId < 0 || curId >= ls.size() || newId < 0 || newId >= ls.size()) {
            throw new IllegalArgumentException("index out of bound");
        }
        T temp = ls.get(curId);
        ls.set(curId, ls.get(newId));
        ls.set(newId, temp);
        return newId;
    }

    public static <T extends Comparable<T>> int count (List<T> ls, String rule) {
        if (ls.size() == 0) return 0;
        return countHelper(ls, 0, ls.size() -1, rule);

    }

    private static <T extends Comparable<T>> int countHelper (List<T> ls, int lo, int hi, String rule) {
        if (lo < 0 || hi >= ls.size() || lo >= hi) return 0;
        // Choose randomized pivot 
        int pId = ChoosePivot(lo, hi, ls, rule);

        // swap ls[p] & ls[lo]
        pId = swap(ls, pId, lo);
        // partition into 2 buckets < p & >= p
        int i = pId + 1; int j = i; 
        T p = ls.get(pId);
        while (j <= hi) {
            if (ls.get(j).compareTo(p) < 0) {
                swap(ls, i, j);
                i++;
            }
            j++;
        }
        // put pivot to right pos
        pId = swap (ls, pId, i -1);
        // recursive sort bucket < p
        int lHlfCount = countHelper(ls, lo, pId -1, rule);    
        // recursive sort bucket >= p
        int rHlfCount = countHelper(ls, pId +1, hi, rule);
        return (hi - lo) + lHlfCount + rHlfCount;
    }

    public static void main (String[] args) {
        List<Integer> test = Arrays.asList(4, 2, 1, 7, 4, 4,3);
        // System.out.println(sort(test).toString());
        List<Integer> testCount = Arrays.asList(4, 2, 1, 7, 3);
        System.out.println(count(testCount, "first"));
    }
}
