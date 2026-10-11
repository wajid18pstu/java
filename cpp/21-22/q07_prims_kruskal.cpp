// 7. Implement Prims and Kruskal algorithm.
/*5 7
0 1 2
0 3 6
1 2 3
1 3 8
1 4 5
2 4 7
3 4 9*/
#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

struct Edge { int u, v, w; };

// ---------- Prim's algorithm ----------
void prim(int n, vector<vector<pair<int,int>>>& adj) {
    vector<bool> inMST(n, false);
    priority_queue<tuple<int,int,int>, vector<tuple<int,int,int>>, greater<>> pq; // (w, v, parent)
    pq.push({0, 0, -1});
    int total = 0;
    cout << "Prim's MST edges:\n";
    while (!pq.empty()) {
        auto [w, u, p] = pq.top();
        pq.pop();
        if (inMST[u]) continue;
        inMST[u] = true;
        total += w;
        if (p != -1) cout << p << " - " << u << " (" << w << ")\n";
        for (auto [v, wt] : adj[u])
            if (!inMST[v]) pq.push({wt, v, u});
    }
    cout << "Total weight (Prim): " << total << "\n";
}

// ---------- Kruskal's algorithm ----------
int findSet(vector<int>& parent, int x) {
    return parent[x] == x ? x : parent[x] = findSet(parent, parent[x]);
}

void kruskal(int n, vector<Edge> edges) {
    sort(edges.begin(), edges.end(), [](const Edge& a, const Edge& b) { return a.w < b.w; });
    vector<int> parent(n);
    for (int i = 0; i < n; i++) parent[i] = i;
    int total = 0;
    cout << "Kruskal's MST edges:\n";
    for (auto& e : edges) {
        int a = findSet(parent, e.u), b = findSet(parent, e.v);
        if (a != b) {
            parent[a] = b;
            total += e.w;
            cout << e.u << " - " << e.v << " (" << e.w << ")\n";
        }
    }
    cout << "Total weight (Kruskal): " << total << "\n";
}

int main() {
    int n, e;
    cout << "Enter number of vertices (0..n-1) and edges (connected graph): ";
    cin >> n >> e;
    vector<Edge> edges(e);
    vector<vector<pair<int,int>>> adj(n);
    cout << "Enter " << e << " edges (u v weight), undirected:\n";
    for (auto& ed : edges) {
        cin >> ed.u >> ed.v >> ed.w;
        adj[ed.u].push_back({ed.v, ed.w});
        adj[ed.v].push_back({ed.u, ed.w});
    }
    prim(n, adj);
    cout << endl;
    kruskal(n, edges);
    return 0;
}
