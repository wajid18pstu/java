// 5. Implement BFS algorithm.
/* Sample Input:
6 5
0 1 
0 2 
1 3
1 4
2 5
0*/
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

void bfs(int start, vector<vector<int>>& adj) {
    vector<bool> visited(adj.size(), false);
    queue<int> q;
    visited[start] = true;
    q.push(start);
    while (!q.empty()) {
        int u = q.front();
        q.pop();
        cout << u << " ";
        for (int v : adj[u]) {
            if (!visited[v]) {
                visited[v] = true;
                q.push(v);
            }
        }
    }
}

int main() {
    int n, e;
    cout << "Enter number of vertices (0..n-1) and edges: ";
    cin >> n >> e;
    vector<vector<int>> adj(n);
    cout << "Enter " << e << " edges (u v), undirected:\n";
    for (int i = 0; i < e; i++) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    int start;
    cout << "Enter start vertex: ";
    cin >> start;
    cout << "BFS traversal: ";
    bfs(start, adj);
    cout << endl;
    return 0;
}
