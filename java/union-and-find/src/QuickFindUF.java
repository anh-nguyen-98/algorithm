public class QuickFindUF {
    private int[] id;
    public QuickFindUF(int n){
        this.id = new int[n];
        for (int i = 0; i < n; i++){
            id[i] = i;
        }
    }
    public boolean connected(int p, int q){
        return this.id[p] == this.id[q];
    }

    public void union (int p, int q){
        int pid = this.id[p];
        int qid = this.id[q];
        for (int i=0; i < this.id.length; i++){
            if (this.id[i] == qid) this.id[i] = pid;
            
        }
    }
}
