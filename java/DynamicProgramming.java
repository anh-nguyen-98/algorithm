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

        // return the LIP






        return max;
    }

    private static int findLIP(int[][] matrix, int i, int j, int[][] LIP) {
        if (LIP[i][j] != 0) {
            return LIP[i][j];
        }

        // check if 4 neighbors < cell
        // if (isBiggest(matrix, i, j)) {
        //     LIP[i][j] = 1;
        //     return 1;
        // }
        int ret = 0;

        boolean isBiggest = true;
        for (int step  = 0; step < x.length; step++) {
            int neighbor_x = i + x[step];
            int neighbor_y = j + y[step];
            if (isValid(matrix, neighbor_x, neighbor_y) && matrix[i][j] < matrix[neighbor_x][neighbor_y]) {
                ret = Math.max(ret, 1 + findLIP(matrix, neighbor_x, neighbor_y, LIP));
                isBiggest = false;
            }
        }
        if (isBiggest) {
            ret = 1;
        }

        LIP[i][j] = ret;
        
        return ret;
    }

    private static boolean isBiggest(int[][] matrix, int i, int j) {
        for (int step  = 0; step < x.length; step++) {
            int neighbor_x = i + x[step];
            int neighbor_y = j + y[step];
            if (isValid(matrix, neighbor_x, neighbor_y) && matrix[i][j] < matrix[neighbor_x][neighbor_y]) {
                return false;
            }
        }

        return true;
    }

    private static boolean isValid(int[][] matrix, int i, int j) {
        return i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length;

    }

    public static void main(String args[]) {
        int[][] matrix = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
        // int[][] matrix = {{1}};

        System.out.println(longestIncreasingPath(matrix));
        
    }
  
}