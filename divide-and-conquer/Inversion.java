
import java.util.ArrayList;
import java.util.List;

public class Inversion {
    public static long Count(List<Long> ls) {
        if (ls.size() <= 1) return 0;
        int mid = ls.size()/2;
        List<Long> lHlf = subList(ls, 0, mid) ; 
        List<Long> rHlf = subList(ls, mid, ls.size());

        return Count(lHlf) + Count(rHlf) + CountSplit(ls, lHlf, rHlf) ;
    }

    private static long CountSplit(List<Long> ls, List<Long> lHlf, List<Long> rHlf) {
        int i = 0; int j = 0; int k = 0; int count = 0;
        for (i = 0; i < ls.size(); i++) {
            if (j >= lHlf.size()
            || k < rHlf.size() && rHlf.get(k) < lHlf.get(j)) {
                count += lHlf.size() - j;
                ls.set(i, rHlf.get(k++));
            } else {
                ls.set(i, lHlf.get(j++));
            } 
        }
        return count;
    }

    private static List<Long> subList (List<Long> ls, int fromId, int toId) {
        List<Long> sublist = new ArrayList<>();
        if (!(fromId >= 0 && toId >= 0)
            || fromId > toId) throw new IllegalArgumentException
                                ("invalid index input");
        if (ls.size() > 0) {
            for (int i = fromId; i < toId; i++) {
                sublist.add(ls.get(i));
            }
        }
        return sublist;
    }

    public static void main (String[] args) {
        List<Long> test = new ArrayList<>();
        test.add(Long.parseLong("1"));
        test.add(Long.parseLong("3"));
        test.add(Long.parseLong("5"));
        test.add(Long.parseLong("2"));
        test.add(Long.parseLong("4"));
        test.add(Long.parseLong("6"));
        System.out.println(test);
        System.out.println(Count(test));
        System.out.println(test.toString());
    }

 
}