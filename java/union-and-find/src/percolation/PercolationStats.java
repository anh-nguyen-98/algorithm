/* *****************************************************************************
 *  Name:              Nguyen Hoang Nam Anh
 *  Coursera User ID:  123456
 *  Last modified:     03/25/2021
 **************************************************************************** */
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Implementation of estimation of percolation threshold .
 *
 * Simulation model: Monto Carlo
 *
 * @author Nguyen Hoang Nam Anh
 */
public class PercolationStats {
    private double[] thresholds;
    private final double CONFIDENCE_95 = 1.96;

    /**
     * Initializes statistical estimation of a n-by-n grid percolation on
     * a number of trials.
     * @param n grid side
     * @param trials number of trials
     */
    public PercolationStats(int n, int trials){
        if (n <= 0 || trials <= 0){
            throw new IllegalArgumentException
                    ("argument must be positive");
        }
        this.thresholds = new double[trials];
        for (int i = 0; i < trials; i ++){
            thresholds[i] = montoCarlo(n);

        }


    }

    /**
     * Perform an independent experiment on an n-by-n grid using
     * Monto Carlo simulation model
     * @param n grid side
     * @return percolation threshold
     */
    private double montoCarlo (int n){
        Percolation p = new Percolation(n);
        double totalSites = Math.pow(n,2);
        while (!p.percolates()){
            int row = StdRandom.uniform(n) +1;
            int col = StdRandom.uniform(n) +1;
            if (!p.isOpen(row, col)){
                p.open(row, col);
            }
        }
        return p.numberOfOpenSites()/ totalSites;
    }

    /**
     * Retrieves sample mean of percolation thresholds
     * @return mean of percolation thresholds
     */
    public double mean(){
        return StdStats.mean(thresholds);
    }

    /**
     * Retrieves sample standard deviation of percolation threshold
     * @return sample standard deviation of percolation threshold
     */
    public double stddev(){
        return StdStats.stddev(thresholds);
    }


    /**
     * Retrieves low endpoint of 95% confidence interval
     * @return low endpoint of 95% confidence interval
     */
    public double confidenceLo(){
        return StdStats.mean(thresholds) -
                (CONFIDENCE_95 * StdStats.stddev(thresholds))/
                        Math.sqrt(this.thresholds.length);
    }

    /**
     * Retrieves high endpoint of 95% confidence interval
     * @return high endpoint of 95% confidence interval
     */
    public double confidenceHi(){
        return StdStats.mean(thresholds) +
                (CONFIDENCE_95 * StdStats.stddev(thresholds))/
                        Math.sqrt(this.thresholds.length);
    }


    /**
     * test client
     * @param args arguments
     */
    public static void main(String[] args) {

        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0])
                , Integer.parseInt(args[1]));
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                   = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo()
                                   + ", " + stats.confidenceHi() + "]");
    }
}
