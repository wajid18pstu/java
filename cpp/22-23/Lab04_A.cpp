// Lab04
// (A) Suppose, you are given a connected weighted graph with N nodes and M edges. The graph
// is undirected and all weights are non-negative. Your task is to implement Dijkstra's algorithm
// to compute the shortest path from a given source node to all other nodes.
// Write a program that:
// i. Accepts the number of nodes, edges, and the list of edges.
// ii. Computes the shortest distances from the given source node to every other node using
//     Dijkstra's algorithm.
// iii. Outputs the distance of each node from the source in ascending order of node number.
//
// Input format:
// N M
// u v w      (M lines, undirected edge between u and v with weight w; nodes are 1..N)
// source
//
// Sample Input:
// 5 6
// 1 2 4
// 1 3 1
// 3 2 2
// 2 4 1
// 3 4 5
// 4 5 3
// 1
//
// Sample Output:
// Node 1: 0
// Node 2: 3
// Node 3: 1
// Node 4: 4
// Node 5: 7

#include <iostream>
#include <vector>
#include <queue>
#include <climits>
using namespace std;

int main() {
    int N, M;
    cin >> N >> M;
    vector<vector<pair<int, int>>> adj(N + 1);
    for (int i = 0; i < M; i++) {
        int u, v, w;
        cin >> u >> v >> w;
        adj[u].push_back({v, w});
        adj[v].push_back({u, w});
    }
    int src;
    cin >> src;

    vector<long long> dist(N + 1, LLONG_MAX);
    priority_queue<pair<long long, int>, vector<pair<long long, int>>, greater<pair<long long, int>>> pq;
    dist[src] = 0;
    pq.push({0, src});

    while (!pq.empty()) {
        long long d = pq.top().first;
        int u = pq.top().second;
        pq.pop();
        if (d > dist[u]) continue;
        for (auto &e : adj[u]) {
            int v = e.first, w = e.second;
            if (dist[u] + w < dist[v]) {
                dist[v] = dist[u] + w;
                pq.push({dist[v], v});
            }
        }
    }

    for (int i = 1; i <= N; i++) {
        cout << "Node " << i << ": ";
        if (dist[i] == LLONG_MAX) cout << "INF";
        else cout << dist[i];
        cout << endl;
    }
    return 0;
}
