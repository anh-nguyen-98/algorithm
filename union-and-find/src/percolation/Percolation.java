/* *****************************************************************************
 *  Name:              Nguyen Hoang Nam Anh
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;



/**
 * A representation of a percolation object.
 *
 * By convention, the row and column indices are integers between 1 and n,
 * where (1, 1) is the upper-left site
 *
 * @author NGUYEN HOANG NAM ANH
 */
public class Percolation {

    private boolean[][] grid;
    private int n;
    private int numOpen;
    private WeightedQuickUnionUF dSF; // disjoint set forest of sites
    private int topRoot; // virtual top root
    private int botRoot; // virtual bottom root


    /**
     * Initializes percolation n-by-n grid, with all sites initially blocked
     * Site code: Open = 1; Blocked = 0;
     * 4 site neighbors: UDLR
     * @param n grid side
     * @throws IllegalArgumentException if n <= 0
     */
    public Percolation(int n){
        if (n <= 0){
            throw new IllegalArgumentException("size of grid side can't be "
                                                       + "less than 1");
        }
        this.n = n;
        this.grid = new boolean[n][n];
        this.numOpen = 0;

        // 0: virtual top root, 1 - n^2: n sites, n^2 +1: virtual bottom root
        this.dSF = new WeightedQuickUnionUF(n*n +2);

        this.topRoot = 0;
        this.botRoot = n*n +1;

    }


    /**
     * Opens the site (row, col) if it is not open already
     * @param row one-base row index
     * @param col one-base column index
     * @throws IllegalArgumentException if row, col < 1 or > n
     */
    public void open(int row, int col){
        if (row < 1 || row > n|| col < 1 || col > n){
            throw new IllegalArgumentException("index is out of bound");
        }

        if (!grid[gridId(row)][gridId(col)]){
            // set grid[row][col] --> 1
            grid[gridId(row)][gridId(col)] = true;

            if (row == 1){
                this.dSF.union(topRoot, dSFId(row, col));
            }

            // connects site to neighbors open sites
            neighbor(row, col);

            if (row == n){
                if (!this.percolates() || this.percolates() && isFull(row, col)){
                    this.dSF.union(botRoot, dSFId(row, col));
                }

            }

            // this.numOpen += 1;
            this.numOpen += 1;
        }

    }

    /**
     * Connects new open site with neighbors
     * @param row one-base row index
     * @param col one-base column index
     */
    private void neighbor (int row, int col){
        for (int i = -1; i <= 1; i += 2){
            int r = row + i;
            if ( r > 0 && r <= n
                    && isOpen(r, col)){
                this.dSF.union(dSFId(row, col), dSFId(r, col));
            }
            int c = col + i;
            if (c > 0 && c <= n
                    && isOpen(row, c)){
                this.dSF.union(dSFId(row, col), dSFId(row, c));
            }
        }
    }


    /**
     * Checks whether the site (row, col) is open
     * @param row one-base row index
     * @param col one-base column index
     * @return true iff the site (row, col) is open
     */
    public boolean isOpen(int row, int col){
        if (row < 1 || row > n|| col < 1 || col > n){
            throw new IllegalArgumentException("index is out of bound");
        }
        return grid[gridId(row)][gridId(col)];
    }


    /**
     * Checks whether the site (row, col) is full
     * @param row one-base row index
     * @param col one-base column index
     * @return true iff the site (row, col) is full
     */
    public boolean isFull(int row, int col){
        if (row < 1 || row > n|| col < 1 || col > n){
            throw new IllegalArgumentException("index is out of bound");
        }
        return isOpen(row, col)
                && dSF.find(dSFId(row, col)) == dSF.find(topRoot);}


    /**
     * Retrieves the number of open sites
     * @return the number of open sites
     */
    public int numberOfOpenSites(){return numOpen;}


    /**
     * Checks whether the system percolates
     * @return true iff the system percolates
     */
    public boolean percolates(){
        return dSF.find(topRoot) == dSF.find(botRoot);}

    /**
     * Retrieves index of site (row, col) in DSF
     * @param row one-base row index
     * @param col one-base column index
     * @return index of site in DSF
     */
    private int dSFId (int row, int col){
        if (row < 1 || row > n|| col < 1 || col > n){
            throw new IllegalArgumentException("index is out of bound");
        }
        return (row-1)*n+ col;
    }

    /**
     * Retrieves zero-based id of one-based id
     * @param id one-based index
     * @return zero-based id
     */
    private int gridId (int id){
        return id - 1;
    }

    // public static void main(String[] args) throws FileNotFoundException {
    //     Scanner scanner = new Scanner(new File("input10.txt"));
    //     int n = scanner.nextInt();
    //     System.out.println(n);
    //     Percolation p = new Percolation(n);
    //
    //     for (int i = 0; i < 56; i++){
    //         int r = scanner.nextInt();
    //         int c = scanner.nextInt();
    //         p.open(r, c);
    //     }
    //     System.out.println(p.isFull(9, 1));
    //
    // }


}
