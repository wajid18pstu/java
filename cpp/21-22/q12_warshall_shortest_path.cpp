// 12. Implement Warshall's algorithms to find shortest path.
/*4 5
0 1 5
0 3 10
1 2 3
2 3 1
3 0 2*/
#include <iostream>
#include <vector>
#include <climits>
using namespace std;

const int INF = INT_MAX / 2;

int main() {
    int n, e;
    cout << "Enter number of vertices (0..n-1) and edges: ";
    cin >> n >> e;
    vector<vector<int>> d(n, vector<int>(n, INF));
    for (int i = 0; i < n; i++) d[i][i] = 0;

    cout << "Enter " << e << " directed edges (u v weight):\n";
    for (int i = 0; i < e; i++) {
        int u, v, w;
        cin >> u >> v >> w;
        d[u][v] = min(d[u][v], w);
    }

    // Warshall (Floyd-Warshall) shortest path between all pairs
    for (int k = 0; k < n; k++)
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (d[i][k] < INF && d[k][j] < INF && d[i][k] + d[k][j] < d[i][j])
                    d[i][j] = d[i][k] + d[k][j];

    cout << "All-pairs shortest path matrix (INF = no path):\n";
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            if (d[i][j] >= INF) cout << "INF\t";
            else cout << d[i][j] << "\t";
        }
        cout << "\n";
    }
    return 0;
}
