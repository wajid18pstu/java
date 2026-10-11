/*
Lab04
Implement the following operations for an undirected graph.
    I.   Insert Vertex: Add a new vertex to the graph.
    II.  Insert Edge: Add an edge between two vertices.
    III. Delete Edge: Remove an existing edge between two vertices.
    IV.  Delete Vertex: Remove a vertex and all edges incident to it.
    V.   Display Graph: Print the current graph representation (adjacency matrix).
    VI.  Breadth-First Traversal (BFS): Traverse the graph starting from a given source vertex.
*/

/*
SAMPLE INPUT (menu choice, then the arguments for that choice; 0 = exit):
1 0
1 1
1 2
1 3
2 0 1
2 0 2
2 1 3
2 2 3
5
6 0
3 0 1
5
4 2
5
6 0
0

Menu choices:
 1 v      -> insert vertex v
 2 u v    -> insert edge u-v
 3 u v    -> delete edge u-v
 4 v      -> delete vertex v
 5        -> display adjacency matrix
 6 s      -> BFS from source s
 0        -> exit

SAMPLE OUTPUT (after first 5):
      0  1  2  3
  0   0  1  1  0
  1   1  0  0  1
  2   1  0  0  1
  3   0  1  1  0
BFS from 0: 0 1 2 3
*/

#include <iostream>
#include <vector>
#include <queue>
#include <iomanip>
#include <algorithm>
using namespace std;

class Graph {
    vector<int> label;              // vertex labels, in insertion order
    vector<vector<int>> adj;        // adjacency matrix

    int indexOf(int v) const {
        auto it = find(label.begin(), label.end(), v);
        return it == label.end() ? -1 : (int)(it - label.begin());
    }

public:
    // I. Insert Vertex
    void insertVertex(int v) {
        if (indexOf(v) != -1) { cout << "Vertex " << v << " already exists\n"; return; }
        label.push_back(v);
        int n = label.size();
        for (auto& row : adj) row.push_back(0);
        adj.push_back(vector<int>(n, 0));
    }

    // II. Insert Edge
    void insertEdge(int u, int v) {
        int i = indexOf(u), j = indexOf(v);
        if (i == -1 || j == -1) { cout << "Vertex not found\n"; return; }
        if (i == j) { cout << "Self-loops not allowed\n"; return; }
        adj[i][j] = adj[j][i] = 1;
    }

    // III. Delete Edge
    void deleteEdge(int u, int v) {
        int i = indexOf(u), j = indexOf(v);
        if (i == -1 || j == -1) { cout << "Vertex not found\n"; return; }
        if (!adj[i][j]) { cout << "Edge does not exist\n"; return; }
        adj[i][j] = adj[j][i] = 0;
    }

    // IV. Delete Vertex (removes its row, column and label)
    void deleteVertex(int v) {
        int i = indexOf(v);
        if (i == -1) { cout << "Vertex not found\n"; return; }
        adj.erase(adj.begin() + i);
        for (auto& row : adj) row.erase(row.begin() + i);
        label.erase(label.begin() + i);
    }

    // V. Display Graph (adjacency matrix)
    void display() const {
        if (label.empty()) { cout << "Graph is empty\n"; return; }
        cout << "    ";
        for (int v : label) cout << setw(3) << v;
        cout << "\n";
        for (size_t i = 0; i < label.size(); i++) {
            cout << setw(3) << label[i] << " ";
            for (size_t j = 0; j < label.size(); j++) cout << setw(3) << adj[i][j];
            cout << "\n";
        }
    }

    // VI. BFS from source vertex
    void bfs(int s) const {
        int src = indexOf(s);
        if (src == -1) { cout << "Source vertex not found\n"; return; }
        vector<bool> visited(label.size(), false);
        queue<int> q;
        visited[src] = true;
        q.push(src);
        cout << "BFS from " << s << ":";
        while (!q.empty()) {
            int u = q.front(); q.pop();
            cout << " " << label[u];
            for (size_t w = 0; w < label.size(); w++)
                if (adj[u][w] && !visited[w]) { visited[w] = true; q.push(w); }
        }
        cout << "\n";
    }
};

int main() {
    Graph g;
    int choice;
    while (cin >> choice && choice != 0) {
        int u, v;
        switch (choice) {
            case 1: cin >> v;      g.insertVertex(v); break;
            case 2: cin >> u >> v; g.insertEdge(u, v); break;
            case 3: cin >> u >> v; g.deleteEdge(u, v); break;
            case 4: cin >> v;      g.deleteVertex(v); break;
            case 5: g.display(); break;
            case 6: cin >> v;      g.bfs(v); break;
            default: cout << "Invalid choice\n";
        }
    }
    return 0;
}
