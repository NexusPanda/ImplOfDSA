package Graph;

public class DisjointSet {
    int[] parent;
    int[] rank;

    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return;
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }

    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    public static void main(String[] args) {
        DisjointSet ds = new DisjointSet(7);

        ds.union(0, 1);
        ds.union(1, 2);
        ds.union(3, 4);
        ds.union(5, 6);
        ds.union(4, 5);

        System.out.println(ds.isConnected(0, 2));
        System.out.println(ds.isConnected(0, 4));
        System.out.println(ds.isConnected(3, 6));

        ds.union(2,4);

        System.out.println(ds.isConnected(0, 6));
    }
}
