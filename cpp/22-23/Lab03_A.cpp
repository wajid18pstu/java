// Lab03
// (A) Infix expressions are the common arithmetic notations we use every day, such as A + B *
// C. In postfix notation (also known as Reverse Polish Notation), operators follow their operands,
// e.g., A B C * +. Your task is to convert a valid infix expression into a postfix expression
//
// Sample Input 1:
// A + B * C
// Sample Output 1:
// A B C * +
//
// Sample Input 2:
// ( A + B ) * C - D / E
// Sample Output 2:
// A B + C * D E / -

#include <iostream>
#include <string>
#include <stack>
#include <cctype>
using namespace std;

int prec(char c) {
    if (c == '^') return 3;
    if (c == '*' || c == '/') return 2;
    if (c == '+' || c == '-') return 1;
    return 0;
}

string infixToPostfix(const string &s) {
    stack<char> st;
    string out;
    for (char c : s) {
        if (isspace((unsigned char)c)) continue;
        if (isalnum((unsigned char)c)) {
            out += c;
            out += ' ';
        } else if (c == '(') {
            st.push(c);
        } else if (c == ')') {
            while (!st.empty() && st.top() != '(') {
                out += st.top(); out += ' ';
                st.pop();
            }
            if (!st.empty()) st.pop();   // remove '('
        } else {                         // operator
            while (!st.empty() && st.top() != '(' &&
                   (prec(st.top()) > prec(c) ||
                    (prec(st.top()) == prec(c) && c != '^'))) {
                out += st.top(); out += ' ';
                st.pop();
            }
            st.push(c);
        }
    }
    while (!st.empty()) {
        out += st.top(); out += ' ';
        st.pop();
    }
    return out;
}

int main() {
    string infix;
    getline(cin, infix);
    cout << infixToPostfix(infix) << endl;
    return 0;
}
