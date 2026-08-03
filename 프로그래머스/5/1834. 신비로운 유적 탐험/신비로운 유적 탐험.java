import java.util.*;

class Solution {
    private List<Integer>[] tree1;
    private List<Integer>[] tree2;
    private int[][] dp;

    public int solution(int n1, int[][] g1, int n2, int[][] g2) {
        tree1 = buildTree(n1, g1);
        tree2 = buildTree(n2, g2);

        dp = new int[n1 + 1][n2 + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        // 1번 노드가 루트로 고정되어 있음
        return getMaxCommon(1, 1);
    }

    // 무방향 간선을 부모->자식 방향 트리로 변환
    private List<Integer>[] buildTree(int n, int[][] edges) {
        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        List<Integer>[] tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) tree[i] = new ArrayList<>();

        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int neighbor : adj[curr]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    tree[curr].add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return tree;
    }

    // 트리 DP: u와 v를 매칭했을 때 최대 공통 노드 수 반환
    private int getMaxCommon(int u, int v) {
        if (dp[u][v] != -1) return dp[u][v];

        List<Integer> children1 = tree1[u];
        List<Integer> children2 = tree2[v];

        if (children1.isEmpty() || children2.isEmpty()) {
            return dp[u][v] = 1;
        }

        int sz1 = children1.size();
        int sz2 = children2.size();
        int[][] cost = new int[sz1][sz2];

        // 자식 노드들 간의 매칭 가중치(최대 공통 노드 수)를 구함
        for (int i = 0; i < sz1; i++) {
            for (int j = 0; j < sz2; j++) {
                cost[i][j] = getMaxCommon(children1.get(i), children2.get(j));
            }
        }

        // 헝가리안 알고리즘을 통한 자식 노드 최대 가중치 이분 매칭
        return dp[u][v] = 1 + getHungarianMatching(sz1, sz2, cost);
    }

    // 헝가리안 알고리즘 (Maximum Weight Bipartite Matching)
    private int getHungarianMatching(int n, int m, int[][] cost) {
        int maxDim = Math.max(n, m);
        int[][] grid = new int[maxDim + 1][maxDim + 1];

        // 최댓값을 찾아 최소 비용 문제로 변환하기 위함
        int maxVal = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maxVal = Math.max(maxVal, cost[i][j]);
            }
        }

        // Dummy 노드를 포함해 maxDim 크기의 정방행렬 생성 (Dummy의 비용은 0)
        for (int i = 1; i <= maxDim; i++) {
            for (int j = 1; j <= maxDim; j++) {
                int c = (i <= n && j <= m) ? cost[i - 1][j - 1] : 0;
                grid[i][j] = maxVal - c; 
            }
        }

        int[] u = new int[maxDim + 1];
        int[] v = new int[maxDim + 1];
        int[] p = new int[maxDim + 1];
        int[] way = new int[maxDim + 1];

        for (int i = 1; i <= maxDim; i++) {
            p[0] = i;
            int j0 = 0;
            int[] minv = new int[maxDim + 1];
            Arrays.fill(minv, Integer.MAX_VALUE);
            boolean[] used = new boolean[maxDim + 1];

            do {
                used[j0] = true;
                int i0 = p[j0], delta = Integer.MAX_VALUE, j1 = 0;
                for (int j = 1; j <= maxDim; j++) {
                    if (!used[j]) {
                        int cur = grid[i0][j] - u[i0] - v[j];
                        if (cur < minv[j]) {
                            minv[j] = cur;
                            way[j] = j0;
                        }
                        if (minv[j] < delta) {
                            delta = minv[j];
                            j1 = j;
                        }
                    }
                }
                for (int j = 0; j <= maxDim; j++) {
                    if (used[j]) {
                        u[p[j]] += delta;
                        v[j] -= delta;
                    } else {
                        minv[j] -= delta;
                    }
                }
                j0 = j1;
            } while (p[j0] != 0);

            do {
                int j1 = way[j0];
                p[j0] = p[j1];
                j0 = j1;
            } while (j0 != 0);
        }

        // 실제 매칭된 짝들의 최소 비용 합산
        int totalMinCost = 0;
        for (int j = 1; j <= maxDim; j++) {
            if (p[j] != 0) {
                totalMinCost += grid[p[j]][j];
            }
        }

        // 변환했던 식을 원래의 최대 가중치 합으로 복원
        return (maxDim * maxVal) - totalMinCost;
    }
}