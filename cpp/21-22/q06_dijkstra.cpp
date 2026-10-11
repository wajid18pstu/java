// 6. Implement dijkstra algorithm.
/* Sample Input:
5 6
0 1 4
0 2 1
2 1 2
1 3 1
2 3 5
3 4 3
0*/
#include <iostream>
#include <vector>
#include <queue>
#include <climits>
using namespace std;

typedef pair<int, int> pii; // (distance, vertex)

int main() {
    int n, e;
    cout << "Enter number of vertices (0..n-1) and edges: ";
    cin >> n >> e;
    vector<vector<pii>> adj(n);
    cout << "Enter " << e << " edges (u v weight), undirected:\n";
    for (int i = 0; i < e; i++) {
        int u, v, w;
        cin >> u >> v >> w;
        adj[u].push_back({v, w});
        adj[v].push_back({u, w});
    }
    int src;
    cout << "Enter source vertex: ";
    cin >> src;

    vector<int> dist(n, INT_MAX);
    priority_queue<pii, vector<pii>, greater<pii>> pq;
    dist[src] = 0;
    pq.push({0, src});

    while (!pq.empty()) {
        auto [d, u] = pq.top();
        pq.pop();
        if (d > dist[u]) continue;
        for (auto [v, w] : adj[u]) {
            if (dist[u] + w < dist[v]) {
                dist[v] = dist[u] + w;
                pq.push({dist[v], v});
            }
        }
    }

    cout << "Shortest distances from " << src << ":\n";
    for (int i = 0; i < n; i++) {
        cout << i << " : ";
        if (dist[i] == INT_MAX) cout << "unreachable\n";
        else cout << dist[i] << "\n";
    }
    return 0;
}
