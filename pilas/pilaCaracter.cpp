#include <iostream>
#include <string>
#include <stack>
#include <algorithm>

bool estaBalanceado(const std::string& expresion) {
    std::stack<char> pila;

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

std::string invertir(const std::string& expresion) {
    std::stack<char> pila;

    for (char c : expresion) {
        pila.push(c);
    }

    std::string invertida = "";
    while (!pila.empty()) {
        invertida += pila.top();
        pila.pop();
    }
    
    return invertida;
}

int main() {
    std::string expresiones[] = {"{[()]}", "{{{([{}])}}}", "{[}]}", "}[{()}]"};
    
    std::cout << "--- Verificacion de balanceo de parentesis ---" << std::endl;
    for (const auto& expr : expresiones) {
        std::cout << "'" << expr << "' esta balanceado? " << (estaBalanceado(expr) ? "true" : "false") << std::endl;
    }

    std::cout << "\n--- Inversion de cadenas ---" << std::endl;
    std::string original = "Hola Mundo!";
    std::cout << "Original: '" << original << "'" << std::endl;
    std::cout << "Invertida: '" << invertir(original) << "'" << std::endl;
    
    return 0;
}