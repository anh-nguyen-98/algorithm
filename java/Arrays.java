public class Arrays {
    public static void rotate(int[][] matrix) {
        int n = matrix.length -1;
        int start = 0;
        while (start <= n-start) {
            
            for (int j = start; j < n-start; j++) {
                int top = matrix[start][j];
                int temp = top;

                // save left edge to top edge
                int left = matrix[n-j][start];
                matrix[start][j] = left;

                int bottom = matrix[n-start][n-j];
                matrix[n-j][start] = bottom;

                int right = matrix[j][n-start];
                matrix[n-start][n-j] = right;

                matrix[j][n-start] = temp;

            }
            // next layer
            start +=1;
        }
    }
    
}
