public class DFS {
    public static boolean dfs(int a[][], int si, int sj, int di, int dj, int[][] path) {
        if (si < 0 || sj < 0 || si >= a.length || sj >= a[0].length || a[si][sj] == 1 || path[si][sj] == 0) {
            return false;
        }
        if (si == di && sj == dj) {
            path[si][sj] = 0;
            return true;
        }

        path[si][sj] = 0;

        if (dfs(a, si, sj + 1, di, dj, path) ||
                dfs(a, si + 1, sj, di, dj, path) ||
                dfs(a, si, sj - 1, di, dj, path) ||
                dfs(a, si - 1, sj, di, dj, path)) {
            return true;
        }
        path[si][sj] = 1;
        return false;
    }

    public static void main(String[] args) {
        int[][] a = {
                {0, 0, 1, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 1, 1, 1},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0}
        };

        int[][] path = new int[a.length][a[0].length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                path[i][j] = 1;
            }
        }

        if (dfs(a, 0, 0, a.length - 1, a[0].length - 1, path)) {
            System.out.println("Path found:");
        } else {
            System.out.println("No path exists.");
        }
        for (int[] x : path) {
            for (int v : x) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
}
