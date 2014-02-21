/**
 * Auto Generated Java Class.
 */
public class Percolation {
    private blocklen ;
    private boolean [] percmat;
    private int [] rootmat;
    private int [] sz;
    private topid, bottomid;
    private boolean pcolate;
    

    public Percolation(int N){
        // Convention begin with i=1 and j=1
        // Row i=1 is the top
        // Row i=N is the botton
        // create N-by-N grid, with all sites blocked
        blocklen = N;
        percmat = new int[N*N];
        szmat =  new int[N*N];
        rootmat =  new int[N*N + 2];
        topid = 0;
        bottomid = N*N + 1;
        int idx;
        rootmat[topid] = topid;
        rootmat[bottomid] = bottomid;
        pcolate = false;

        for (int i=1; i <= N; i++){
            for (int j=1; j <= N; j++){
                idx = (i-1)*N + (j-1);
                percmat[ idx ] = false;
                sz[idx] = 1;
                if( i==1){
                    rootmat[idx] = topid;
                }
                else if ( i== N){
                    rootmat[idx] = bottomid;
                }
                else{
                    rootmat[idx] = idx;   
                }

            }
        }
    }   

    public int find(int p) {
            while (p != rootmat[p])
                p = rootmat[p];
            return p;
    }


    public void open(int i, int j){

        if (!isOpen(i, j)){
            idx = (i-1)*blocklen + (j-1);
            for (int li=max(0,i-1); li<= min(i+1, blocklen); li++){
                for (int li=max(0,i-1); li<= min(i+1, blocklen); li++){
                    if( isOpen(li, lj))
                        union(i,j, li, lj);      
                }
            percmat[ idx ] = true;
            }
        }
    }



    public void union(int i, int j, int li, int lj) {
        int p = (i-1)*blocklen + (j-1);
        int q = (li-1)*blocklen + (lj-1);
        int rootP = find(p);
        int rootQ = find(q);
        if( ( ( rootP == topid) &&(rootQ =bottomid))  || 
            ( ( rootP == topid) &&(rootQ =bottomid)) && !pcolate )
            pcolate = true;

        if (rootP == rootQ) return;

        // make smaller root point to larger one
        if   (sz[rootP] < sz[rootQ]) { rootmat[rootP] = rootQ; sz[rootQ] += sz[rootP]; }
        else                         { rootmat[rootQ] = rootP; sz[rootP] += sz[rootQ]; }
        
    }


    public int find(int p) {
        while (p != rootmat[p])
            p = rootmat[p];
        return p;
    }

    public boolean isOpen(int i, int j){
        int idx = (i-1)*N + (j-1);
        return percmat[idx];
    }


    
    public boolean isFull(int i, int j) {   // is site (row i, column j) full?

    }

    public boolean percolates(){
        return
    }   
    
}
