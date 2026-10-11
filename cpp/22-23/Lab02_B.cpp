// Lab02
// (B) Implement pre-order, in-order, and post-order traversal on the following tree.
//
//            1
//          /   \
//        12      9
//       /  \
//      5    6
//
// Sample Input:
// (none - the tree shown above is built inside the program)
//
// Sample Output:
// Pre-order : 1 12 5 6 9
// In-order  : 5 12 6 1 9
// Post-order: 5 6 12 9 1

#include <iostream>
using namespace std;

struct Node {
    int data;
    Node *left, *right;
    Node(int d) : data(d), left(nullptr), right(nullptr) {}
};

void preorder(Node *r) {
    if (!r) return;
    cout << r->data << " ";
    preorder(r->left);
    preorder(r->right);
}

void inorder(Node *r) {
    if (!r) return;
    inorder(r->left);
    cout << r->data << " ";
    inorder(r->right);
}

void postorder(Node *r) {
    if (!r) return;
    postorder(r->left);
    postorder(r->right);
    cout << r->data << " ";
}

int main() {
    Node *root = new Node(1);
    root->left = new Node(12);
    root->right = new Node(9);
    root->left->left = new Node(5);
    root->left->right = new Node(6);

    cout << "Pre-order : "; preorder(root);  cout << endl;
    cout << "In-order  : "; inorder(root);   cout << endl;
    cout << "Post-order: "; postorder(root); cout << endl;
    return 0;
}
