// USO DE LA LIBRERIA STACK PARA BREVE DEMOSTRACION DE TRANSFORMACION DE EXPRESIONES
// DE NOTACION INFIX A POSTFIX Y PREFIX
// NORMALMENTE SE USA UNA PILA PROPIA, AQUI USAMOS LA LIBRERIA STACK DE C++

#include <iostream>
#include <string>
#include <stack>
#include <algorithm>

using namespace std;

// Define la prioridad de los operadores.
int prioridad(char operador) {
    switch (operador) {
        case '+':
        case '-':
            return 1;
        case '*':
        case '/':
            return 2;
        case '^':
            return 3;
        default:
            return 0;
    }
}

// Convierte una expresión de notación infija a postfija.
string infijoAPostfijo(const string& expr) {
    stack<char> pila;
    string postfijo = "";

    for (char c : expr) {
        if (isalnum(c)) {
            postfijo += c;
        } else if (c == '(') {
            pila.push(c);
        } else if (c == ')') {
            while (!pila.empty() && pila.top() != '(') {
                postfijo += pila.top();
                pila.pop();
            }
            if (!pila.empty() && pila.top() == '(') {
                pila.pop();
            }
        } else { // Es un operador
            while (!pila.empty() && prioridad(c) <= prioridad(pila.top()) && pila.top() != '(') {
                postfijo += pila.top();
                pila.pop();
            }
            pila.push(c);
        }
    }

    while (!pila.empty()) {
        postfijo += pila.top();
        pila.pop();
    }

    return postfijo;
}

// Convierte una expresión infija a prefija.
string infijoAPrefijo(const string& expr) {
    string exprInvertida = "";
    for (int i = expr.length() - 1; i >= 0; --i) {
        char c = expr[i];
        if (c == '(') {
            exprInvertida += ')';
        } else if (c == ')') {
            exprInvertida += '(';
        } else {
            exprInvertida += c;
        }
    }

    string postfijoInvertido = infijoAPostfijo(exprInvertida);

    reverse(postfijoInvertido.begin(), postfijoInvertido.end());
    
    return postfijoInvertido;
}

int main() {
    string expresion1 = "A+B-C";
    cout << "Expresion infija: " << expresion1 << endl;
    cout << "Expresion postfija: " << infijoAPostfijo(expresion1) << endl;
    cout << "Expresion prefija: " << infijoAPrefijo(expresion1) << endl;

    string expresion2 = "(A+B)*C";
    cout << "\nExpresion infija: " << expresion2 << endl;
    cout << "Expresion postfija: " << infijoAPostfijo(expresion2) << endl;
    cout << "Expresion prefija: " << infijoAPrefijo(expresion2) << endl;

    string expresion3 = "(A*B)-(C+D)";
    cout << "\nExpresion infija: " << expresion3 << endl;
    cout << "Expresion postfija: " << infijoAPostfijo(expresion3) << endl;
    cout << "Expresion prefija: " << infijoAPrefijo(expresion3) << endl;

    return 0;
}