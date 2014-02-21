 public class Percolation {
  
  private WeightedQuickUnionUF2 uf;
  private boolean [] percmat;
  public int opensites;
  private int blocklen;
  private int topsiteid , bottomsiteid;
  private int getidx(int i, int j){
    return (i-1)*blocklen + j;
  }
  
  public Percolation(int N) {
    topsiteid = 0;
    bottomsiteid = N*N + 1;
    percmat  = new boolean [N*N + 2];
    uf = new WeightedQuickUnionUF2( N*N + 2); 
    opensites =0;
    blocklen = N;
  
    for (int i=0; i < N*N + 2; i++){
            percmat[i] = false;
    }
    percmat[0] = true;
    percmat[N*N+1] = true;
  }

  public void showmat(){
    for( int i=1; i<=blocklen; i++){
      for( int j=1; j<=blocklen; j++){
        int idx = getidx(i, j);
        StdOut.printf("%d,", uf.id[idx]);
      }
      StdOut.printf("\n");
    }

  }

   public void open(int i, int j){         // open site (row i, column j) if it is not already
        int idx = getidx( i, j);
        StdOut.printf( "Opening %d %d %d\n",i, j, idx);
        //StdOut.println("Idx");
        if (!isOpen(i, j)){
              
              if( i == 1){
                uf.union(idx, topsiteid);
              }
              else if ( i == blocklen )
              {
                uf.union(idx, bottomsiteid);
              }

              for (int li = Math.max(1, i-1); li<= Math.min(i+1, blocklen); li++){
                  for (int lj = Math.max(1, j-1); lj<= Math.min(j+1, blocklen); lj++){
                      int nidx = getidx( li, lj);
                      int deltax = Math.abs( li-i) + Math.abs(lj-j);
                      //StdOut.printf( "Neigh %d %d %d %d %s\n", li, lj, nidx, deltax, isOpen(li, lj) );
                      if( isOpen(li, lj) && deltax < 2){
                          //StdOut.printf( "Parsing Neighbours %d %d %d\n", li, lj, nidx);
                          uf.union(idx, nidx);      
                        }
                  }
            percmat[ idx] = true;
            
            }
            opensites++;
        }
   }
   
   public boolean isOpen(int i, int j){    // is site (row i, column j) open?
    int idx = getidx(i, j);
    //StdOut.printf( "Is Open %d %d %d", i, j, idx);
    return percmat[ idx ];
   }

   public boolean isFull(int i, int j){
    int idx = getidx(i, j);
    return uf.connected(idx, topsiteid) && uf.connected(idx, bottomsiteid);
   }    // is site (row i, column j) full?

   public boolean percolates(){
    return uf.connected( topsiteid, bottomsiteid);
   }

   
    public static void main(String[] args) {
            StdOut.println("Hello World");
            int N = 5;
            Percolation pf = new Percolation(N);
            pf.open(1, 1);
            //pf.showmat();
            //StdOut.println("Hello World");
            pf.open(1, 2);
            pf.open(1, 4);
            pf.showmat();
            pf.open(2, 4);
            //StdOut.println(pf.percolates() );
            pf.open(3, 2);
            pf.open(3, 4);
            pf.showmat();
            pf.open(3, 5);
            pf.open(4, 1);
            pf.showmat();
            pf.open(4, 3);
            pf.open(5, 1);
            pf.open(5, 2);
            pf.open(5, 4);
            pf.open(5, 5);
            StdOut.println(pf.percolates() );
            pf.showmat();

            StdOut.println(pf.percolates() );
            pf.open(3, 1);
            pf.open(4, 4);
            StdOut.println(pf.percolates() );
            pf.showmat();

            StdOut.printf("OpenSite %d", pf.opensites);
            StdOut.printf("IsFull %s", pf.isFull(4, 4));
            

        }
}