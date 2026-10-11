// 4. Implement DFS algorithm.
#include <iostream>
#include <vector>
using namespace std;

void dfs(int u, vector<vector<int>>& adj, vector<bool>& visited) {
    visited[u] = true;
    cout << u << " ";
    for (int v : adj[u])
        if (!visited[v]) dfs(v, adj, visited);
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
    vector<bool> visited(n, false);
    cout << "DFS traversal: ";
    dfs(start, adj, visited);
    cout << endl;
    return 0;
}
