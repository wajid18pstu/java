// Lab01
// (A) Suppose List is a 10-element linear array with four names: BBBB, DDDD, MMMM, and
// XXXX, in the array. Observe that the names are listed alphabetically, and we want to keep
// the names in array alphabetically at all times. Implement the array in the following two
// situations:
// a) FFFF and ZZZZ are added to the list.
// b) MMMM is deleted from the array.
//
// Menu: 1 <name> = insert, 2 <name> = delete, 3 = display, 0 = exit
//
// Sample Input:
/*1 FFFF
1 ZZZZ
3
2 MMMM
3
0*/

// Sample Output:
// List: BBBB DDDD FFFF MMMM XXXX ZZZZ
// List: BBBB DDDD FFFF XXXX ZZZZ

#include <iostream>
#include <string>
using namespace std;

const int MAX = 10;
string List[MAX] = {"BBBB", "DDDD", "MMMM", "XXXX"};
int n = 4;

void display() {
    cout << "List:";
    for (int i = 0; i < n; i++) cout << " " << List[i];
    cout << endl;
}

bool insertName(const string &x) {
    if (n == MAX) return false;
    int i = n - 1;
    while (i >= 0 && List[i] > x) {   // shift larger names right
        List[i + 1] = List[i];
        i--;
    }
    List[i + 1] = x;
    n++;
    return true;
}

bool deleteName(const string &x) {
    int pos = -1;
    for (int i = 0; i < n; i++)
        if (List[i] == x) { pos = i; break; }
    if (pos == -1) return false;
    for (int i = pos; i < n - 1; i++) List[i] = List[i + 1];
    n--;
    return true;
}

int main() {
    int choice;
    string name;
    while (cin >> choice && choice != 0) {
        if (choice == 1) {
            cin >> name;
            if (!insertName(name)) cout << "Array is full" << endl;
        } else if (choice == 2) {
            cin >> name;
            if (!deleteName(name)) cout << name << " not found" << endl;
        } else if (choice == 3) {
            display();
        }
    }
    return 0;
}
