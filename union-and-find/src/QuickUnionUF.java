public class QuickUnionUF {
    private int[] parent;
    
    public QuickUnionUF(int n){
        this.parent = new int[n];
        for (int i = 0; i < n; i++){
            this.parent[i] = i;
        }

    }

    public boolean connected (int p, int q){
        return root(p) == root(q);
    }

    public void union (int p, int q){
        int qroot = root(q);
        int proot = root(p);
        this.parent[qroot] = proot;
        return;
    }

    private int root (int p){
        // base case 
        if (this.parent[p] == p){
            return p;
        }
        return root (this.parent[p]);
    }
}