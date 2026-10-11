/*
Lab02 (b)
Consider the following list of 15 numbers: 14, 10, 17, 12, 11, 20, 12, 18, 25, 20, 8,
22, 11, 23. Build a tree using the binary search tree operation on the above data.
*/

/*
SAMPLE INPUT (count of numbers, then the numbers):
14
14 10 17 12 11 20 12 18 25 20 8 22 11 23

(Note: the question says "15 numbers" but lists 14 values; the program reads
whatever count is given. Duplicates are inserted into the right subtree.)

SAMPLE OUTPUT:
Inorder   : 8 10 11 11 12 12 14 17 18 20 20 22 23 25
Preorder  : 14 10 8 12 11 11 12 17 20 18 25 20 22 23
Postorder : 8 11 11 12 12 10 18 23 22 20 25 20 17 14
*/

#include <iostream>
using namespace std;

struct TNode {
    int data;
    TNode *left, *right;
    TNode(int d) : data(d), left(nullptr), right(nullptr) {}
};

TNode* bstInsert(TNode* root, int v) {
    if (!root) return new TNode(v);
    if (v < root->data) root->left = bstInsert(root->left, v);
    else root->right = bstInsert(root->right, v);   // duplicates go right
    return root;
}

void inorder(TNode* r)   { if (r) { inorder(r->left); cout << r->data << " "; inorder(r->right); } }
void preorder(TNode* r)  { if (r) { cout << r->data << " "; preorder(r->left); preorder(r->right); } }
void postorder(TNode* r) { if (r) { postorder(r->left); postorder(r->right); cout << r->data << " "; } }

// Prints tree sideways (root at left, right subtree on top)
void printTree(TNode* r, int space = 0) {
    if (!r) return;
    printTree(r->right, space + 6);
    for (int i = 0; i < space; i++) cout << ' ';
    cout << r->data << "\n";
    printTree(r->left, space + 6);
}

int main() {
    int n;
    cin >> n;
    TNode* root = nullptr;
    for (int i = 0; i < n; i++) {
        int x; cin >> x;
        root = bstInsert(root, x);
    }
    cout << "Inorder   : "; inorder(root);   cout << "\n";
    cout << "Preorder  : "; preorder(root);  cout << "\n";
    cout << "Postorder : "; postorder(root); cout << "\n";
    cout << "Tree (rotated 90 degrees, root on the left):\n";
    printTree(root);
    return 0;
}
