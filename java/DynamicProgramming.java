public class DynamicProgramming {
    public static int[] x = {-1, 0, 0, 1};
    public static  int[] y = {0, -1, 1, 0};
    public static int longestIncreasingPath(int[][] matrix) {
        // create lip table
        int[][] LIP = new int[matrix.length][matrix[0].length];
        // find lip for each cell
        int max= 0;
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                max = Math.max(findLIP(matrix, r, c, LIP), max);
            }
            
        }

        return max;
    }

    private static int findLIP(int[][] matrix, int i, int j, int[][] LIP) {
        if (LIP[i][j] != 0) {
            return LIP[i][j];
        }

        int ret = 1;

        for (int step  = 0; step < x.length; step++) {
            int neighbor_x = i + x[step];
            int neighbor_y = j + y[step];
            if (isValid(matrix, neighbor_x, neighbor_y) && matrix[i][j] < matrix[neighbor_x][neighbor_y]) {
                ret = Math.max(ret, 1 + findLIP(matrix, neighbor_x, neighbor_y, LIP)); 
            }
        }
     

        LIP[i][j] = ret;
        
        return ret;
    }


    private static boolean isValid(int[][] matrix, int i, int j) {
        return i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length;

    }


    /**
     * The maximum profit can be made
     * @param prices stock prices in n days.
     * @param k the number of maximum transactions.
     * @return The maximum profit can be made with at most k transactions
     */
    public static int maxProfit(int[] prices, int k) {
        // O(n * k)
        int[][] profit = new int[k+1][prices.length];
        int ret = 0;
        for (int t = 1; t <= k; t++) {
            int pMax = Integer.MIN_VALUE;
            for (int i = 1; i < prices.length; i++) {
                pMax = Math.max(pMax, - prices[i-1] + profit[t-1][i-1]);
                profit[t][i] = Math.max(profit[t][i-1], prices[i] + pMax);
                ret = Math.max(ret, profit[t][i]);
            }
        }
        return ret;
    }



    public static void main(String args[]) {
        int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        // int[][] matrix = {{1}};

        System.out.println(longestIncreasingPath(matrix));
        // int[] prices = {2, 4, 1};
        int[] prices = {3,2,6,5,0,3};
        System.out.println(maxProfit(prices, 2));
        
    }
  
}