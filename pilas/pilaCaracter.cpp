#include <iostream>
#include <string>
#include <stack>
#include <algorithm>

using namespace std;

bool estaBalanceado(const string& expresion) {
    stack<char> pila;

    for (char c : expresion) {
        if (c == '(' || c == '[' || c == '{') {
            pila.push(c);
        } else if (c == ')' || c == ']' || c == '}') {
            if (pila.empty()) {
                return false;
            }
            char ultimoAbierto = pila.top();
            pila.pop();

            if ((c == ')' && ultimoAbierto != '(') ||
                (c == ']' && ultimoAbierto != '[') ||
                (c == '}' && ultimoAbierto != '{')) {
                return false;
            }
        }
    }

    return pila.empty();
}

string invertir(const string& expresion) {
    stack<char> pila;

    for (char c : expresion) {
        pila.push(c);
    }

    string invertida = "";
    while (!pila.empty()) {
        invertida += pila.top();
        pila.pop();
    }
    
    return invertida;
}

bool esPalindromo(const string& expresion) {
    stack<char> pila;
    string filtrada = "";

    // Filtrar solo caracteres alfanuméricos y convertir a minúsculas.
    // Y agregar a la pila.
    for (char c : expresion) {
        if (isalnum(c)) {
            filtrada += tolower(c);
            pila.push(tolower(c));
        }
    }

    // Comparar la cadena filtrada con la pila.
    for (char c : filtrada) {
        // Si el carácter no coincide, no es un palíndromo.
        if (c != pila.top()) {
            return false;
        }
        pila.pop();
    }

    return true;
}

int main() {
    string expresiones[] = {"{[()]}", "{{{([{}])}}}", "{[}]}", "}[{()}]"};
    
    cout << "--- Verificacion de balanceo de parentesis ---" << endl;
    for (const auto& expr : expresiones) {
        cout << "'" << expr << "' esta balanceado? " << (estaBalanceado(expr) ? "true" : "false") << endl;
    }

    cout << "\n--- Inversion de cadenas ---" << endl;
    string original = "Hola Mundo!";
    cout << "Original: '" << original << "'" << endl;
    cout << "Invertida: '" << invertir(original) << "'" << endl;
    
    return 0;
}