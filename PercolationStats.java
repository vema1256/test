public class PercolationStats {
	private double [] numopensitesarr; 
	private int N, T;

	public PercolationStats(int N_, int T_){
		numopensitesarr = new double [T_];
		N = N_;
		T = T_;
	}    // perform T independent computational experiments on an N-by-N grid

	public double mean() {
		return StdStats.mean( numopensitesarr );
	}

	public double stddev(){
		return StdStats.stddev( numopensitesarr );	
	}                   // sample standard deviation of percolation threshold

	public double confidenceLo(){ // returns lower bound of the 95% confidence interval
		return mean() - (1.96 * stddev()/Math.sqrt( T));
	}

	public double confidenceHi() {             // returns upper bound of the 95% confidence interval
		return mean() + (1.96 * stddev()/Math.sqrt( T));
	}

 
	public void collectstats() {
		int rowidx, colidx;
  		for( int i =0; i < T; i++){
			
			Percolation pf = new Percolation(N);
			while(!pf.percolates()){
				rowidx = StdRandom.uniform( N) + 1;
				colidx = StdRandom.uniform( N) + 1;
				pf.open( rowidx, colidx);
			}
			numopensitesarr[i]  = pf.opensites/( double) (N*N);
			//StdOut.printf("Running Stats for i %d OS %d\n", i, pf.opensites);
 		}
 			//StdOut.printf("Done \n");
    }

   public static void main(String[] args) {
   		//StdOut.printf("Args %s %s", args[0], args[1]);
   		 
 		int N = Integer.parseInt(args[0]); 
		int T = Integer.parseInt(args[1]); 
		PercolationStats ps = new PercolationStats(N, T);
		//StdOut.printf("Will colect Stats for %d %d", N, T);
		ps.collectstats();
		StdOut.printf("mean                    = %f\n", ps.mean() );
		StdOut.printf("stddev                  = %f\n", ps.stddev() );
		StdOut.printf("95%% confidence interval = %f %f \n", ps.confidenceLo(), ps.confidenceHi() );

  	}



}