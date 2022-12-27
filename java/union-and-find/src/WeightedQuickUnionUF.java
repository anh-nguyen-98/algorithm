/**
 * Implementation of Quick Find-Union with weighted type plus path
 * compression.
 *
 * @author NGUYEN HOANG NAM ANH
 */
public class WeightedQuickUnionUF{
	private int[] parent;
	private int[] sz;

	public WeightedQuickUnionUF(int n){
		this.parent = new int[n];
		this.sz = new int[n];

		for (int i=0; i < n; i++){
			this.parent[i] = i;
			this.sz[i] = 1;
		}
	}

	public boolean connected(int p, int q){
		return root(p) == root(q);

	}

	public void union(int p, int q){
		int pRoot = root(p);
		int qRoot = root(q);
		if (pRoot == qRoot) return;
		if (this.sz[pRoot] >= this.sz[qRoot]){
			this.parent[qRoot] = pRoot;
			this.sz[pRoot] += this.sz[qRoot]; 
		} else{
			this.parent[pRoot] = qRoot;
			this.sz[qRoot] += this.sz[pRoot];
		}



	}

	/**
	 * find p's root combines pointing p & its accendents 
	 * to root (path compression) 
	 * @param p
	 * @return
	 */
	private int root(int p){
		
		if (this.parent[p] == p){
			return p;
		}
		this.parent[p] = root(this.parent[p]);
		return this.parent[p];
	}

	
	
}