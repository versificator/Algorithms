import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] percolationStepStat;
    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        validatePositive(n);
        validatePositive(trials);
        percolationStepStat = new double[trials];
        for (int observation = 0; observation < trials; observation++) {
            Percolation pr = new Percolation(n);
            while (!pr.percolates()) {
                int x = StdRandom.uniform(1, n+1);
                int y = StdRandom.uniform(1, n+1);
                pr.open(x, y);
            }
            percolationStepStat[observation] = (double) pr.numberOfOpenSites() / (double) (n * n);
        }
    }    
    
    
    private void validatePositive(int number) {
        if (number < 1) throw new IllegalArgumentException("Invalid number");
    }
        
    
  // sample mean of percolation threshold  
    public double mean() {
        return StdStats.mean(percolationStepStat);
    }
               
    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(percolationStepStat);
    }                     
    
    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96*Math.sqrt(stddev())/Math.sqrt(percolationStepStat.length));
    
    }                  
    
    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() - (1.96*Math.sqrt(stddev())/Math.sqrt(percolationStepStat.length));
    }                  

    public static void main(String[] args) {
        PercolationStats PS = new PercolationStats(2, 100000);
        System.out.println(PS.mean());
        System.out.println(PS.stddev());
        System.out.println(PS.confidenceLo());
        System.out.println(PS.confidenceHi());
    }
}