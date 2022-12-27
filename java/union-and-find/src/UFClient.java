public class UFClient {
    public static void main(String[] args){
        int n = StdIn.readInt();
        UF uf = new UF(n);
        while (!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q)){
                uf.union(p, q);
                StdOut.println (p + " " + q);
            }
        }

    }
}
