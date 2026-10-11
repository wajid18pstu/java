// 8. Implement Bellman-Ford algorithm.
/*5 8
0 1 -1
0 2 4
1 2 3
1 3 2
1 4 2
3 2 5
3 1 1
4 3 -3
0*/
#include <iostream>
#include <vector>
#include <climits>
using namespace std;

struct Edge { int u, v, w; };

int main() {
    int n, e;
    cout << "Enter number of vertices (0..n-1) and edges: ";
    cin >> n >> e;
    vector<Edge> edges(e);
    cout << "Enter " << e << " directed edges (u v weight):\n";
    for (auto& ed : edges) cin >> ed.u >> ed.v >> ed.w;
    int src;
    cout << "Enter source vertex: ";
    cin >> src;

    vector<long long> dist(n, LLONG_MAX);
    dist[src] = 0;

    for (int i = 1; i <= n - 1; i++) {
        for (auto& ed : edges) {
            if (dist[ed.u] != LLONG_MAX && dist[ed.u] + ed.w < dist[ed.v])
                dist[ed.v] = dist[ed.u] + ed.w;
        }
    }

    // Check for negative-weight cycle
    for (auto& ed : edges) {
        if (dist[ed.u] != LLONG_MAX && dist[ed.u] + ed.w < dist[ed.v]) {
            cout << "Graph contains a negative weight cycle!\n";
            return 0;
        }
    }

    cout << "Shortest distances from " << src << ":\n";
    for (int i = 0; i < n; i++) {
        cout << i << " : ";
        if (dist[i] == LLONG_MAX) cout << "unreachable\n";
        else cout << dist[i] << "\n";
    }
    return 0;
}
