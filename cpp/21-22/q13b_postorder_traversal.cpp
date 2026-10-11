// 13. B. Write a program that provides the post-order tree traversing from the followin[g]
//        numbers 44, 30 50, 22, 60, 55, 77, and 57.
#include <iostream>
using namespace std;

struct Node {
    int data;
    Node *left, *right;
    Node(int d) : data(d), left(nullptr), right(nullptr) {}
};

Node* insert(Node* root, int key) {
    if (!root) return new Node(key);
    if (key < root->data) root->left = insert(root->left, key);
    else root->right = insert(root->right, key);
    return root;
}

void postorder(Node* root) {
    if (!root) return;
    postorder(root->left);
    postorder(root->right);
    cout << root->data << " ";
}

int main() {
    int nums[] = {44, 30, 50, 22, 60, 55, 77, 57};
    Node* root = nullptr;
    for (int x : nums) root = insert(root, x);

    cout << "Post-order traversal: ";
    postorder(root);
    cout << endl;
    return 0;
}
