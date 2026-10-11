// 9. Write a program to evaluate any given postfix expression.
//    P: 3, 1, +, 2, ↑, 7, 4, -, 2, *, +, 5, -
/*Press Enter to use the given P (3, 1, +, 2, ↑, 7, 4, -, 2, *, +, 5, -). The output is Result = 17.

Or type your own, with tokens separated by commas:

5, 3, +, 2, **/
#include <iostream>
#include <sstream>
#include <stack>
#include <string>
#include <cmath>
using namespace std;

bool isOperator(const string& t) {
    return t == "+" || t == "-" || t == "*" || t == "/" || t == "^" || t == "↑";
}

double apply(const string& op, double a, double b) {
    if (op == "+") return a + b;
    if (op == "-") return a - b;
    if (op == "*") return a * b;
    if (op == "/") return a / b;
    return pow(a, b); // "^" or "↑" (exponentiation)
}

int main() {
    // Tokens separated by commas (use ^ or ↑ for exponent). Press Enter to use the given P.
    string expr;
    cout << "Enter postfix expression (comma separated), or press Enter for default:\n";
    getline(cin, expr);
    if (expr.empty()) expr = "3, 1, +, 2, ↑, 7, 4, -, 2, *, +, 5, -";

    for (char& c : expr) if (c == ',') c = ' ';
    stringstream ss(expr);
    string tok;
    stack<double> st;

    while (ss >> tok) {
        if (isOperator(tok)) {
            if (st.size() < 2) { cout << "Invalid expression!\n"; return 1; }
            double b = st.top(); st.pop();
            double a = st.top(); st.pop();
            st.push(apply(tok, a, b));
        } else {
            st.push(stod(tok));
        }
    }
    if (st.size() != 1) { cout << "Invalid expression!\n"; return 1; }
    cout << "Result = " << st.top() << endl;
    return 0;
}
