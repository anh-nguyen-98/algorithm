public class Strings {
    public static void main(String args[]) {
        Node n = new Node(4);
        n.appendToTail(7);
        n.appendToTail( 8);
        n.appendToTail(7);
        // n = n.deleteNode(4);
        // n.removeDupSlow();
        n = n.removeNthFromEnd(n, 4);

        // System.out.println(n.toString());
        int[] nums = {1};
        int target = -7;
        // System.out.println(searchInsert(nums, target));

        String text1 = "abc";
        String text2 = "def";
        // System.out.println(longestCommonSubsequence(text1, text2));

        // System.out.println( isUnique(""));

        String s = "Mr John";
        // System.out.println(URLify(s));
        String s1 = "TactCoa";
        System.out.println(palindromePerm(s1));

    }
    public static int searchInsert(int[] nums, int target) {
        int ret = searchHelper(nums, 0, nums.length -1, target);
        if (nums[ret] == target) {
            return ret;
        }
        if (nums[ret] < target) {
            return ret + 1;
        }
        return ret;
    

    }

    public static int searchHelper(int[] nums, int start, int end, int target) {
        if (start > end) {
            if (start>= 0 && start < nums.length) {
                return start;
            }
            return end;
        }
        int mid = (start + end) /2;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[mid] > target) {
            return searchHelper(nums, start, mid-1, target);
        }
        return searchHelper(nums, mid+1, end, target);
    }

    public static int longestCommonSubsequence(String text1 , String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] maxLength = new int[m][n];
        
        // base case: compare text1[0...i] & text2[0]
        if (text1.charAt(0) == text2.charAt(0)) {
            maxLength[0][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            if (maxLength[i-1][0] == 1 || text1.charAt(i) == text2.charAt(0)) {
                maxLength[i][0] = 1;
            }
        }

        for (int j = 1; j < n; j++) {
            if (maxLength[0][j-1] == 1 || text1.charAt(0) == text2.charAt(j)) {
                maxLength[0][j] = 1;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    maxLength[i][j] = maxLength[i-1][j-1] + 1;
                }
                else if (maxLength[i][j-1] >= maxLength[i-1][j]) {
                    maxLength[i][j] =  maxLength[i][j-1];
                }
                else {
                    maxLength[i][j] = maxLength[i-1][j];
                }
            }
        }
        
        return maxLength[m-1][n-1];
    }

    public static boolean isUnique(String s) {
        
        boolean[] occurrence = new boolean[128];

        for (int i = 0; i < s.length(); i++) {
            int id = (int) s.charAt(i);
            if ( occurrence[id]  ) {
                return false;
            }
            
            occurrence[id] = true;
        }
        
        return true;
    }

    public static boolean isPermutation(String s1, String s2){

        if (s1.length() != s2.length()) {
            return false;
        }

        // s1 frequency

        int[] frequencyS1 = new int[128];
        // s2 frequency
        int[] frequencyS2 = new int[128];

        for (int i = 0; i < s1.length(); i++) {
            frequencyS1[(int) s1.charAt(i)] += 1;
            frequencyS2[(int) s2.charAt(i)] += 1;
        }

        // check if 2 frequency are the same
        for (int i = 0; i < 128; i++) {
            if (frequencyS1[i] != frequencyS2[i]) {
                return false;
            }
        }

        return false;
    }

    public static String URLify(String s) {

        // calculate the new array length
        int spaceCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                spaceCount += 1;
            }
        }
        int newLength = s.length() + 2 * spaceCount;
        char[] newS = new char[newLength];
        int next = 0;
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == ' ') {
                newS[next] = '%';
                newS[next+1] = '2';
                newS[next+2] = '0';
                next += 3;
            } else {
                newS[next] = s.charAt(i);
                next++;
            }
        }
        return String.valueOf(newS);
    }


    public static boolean palindromePerm(String s) {
        boolean[] isOddFrequency = new boolean[128];

        for (int i = 0; i < s.length(); i++) {
            int charId =  (int) Character.toLowerCase( s.charAt(i));
            isOddFrequency[charId] = !isOddFrequency[charId];
        }

        // check how many odd fr characters
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += isOddFrequency[i]? 1: 0;
        }
        return count <= 1;
    }

}