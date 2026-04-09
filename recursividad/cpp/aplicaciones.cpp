#include <iostream>

using namespace std;

int factorial(int n) {
    if (n <= 1) {
        return 1;
    }
    return n * factorial(n - 1);
}

int fibonacci(int n) {
    if (n == 0) return 0;
    if (n == 1) return 1;
    return fibonacci(n - 1) + fibonacci(n - 2);
}

int sumaDigitos(int n) {
    if (n < 10) {
        return n;
    }
    return (n % 10) + sumaDigitos(n / 10);
}

int potencia(int base, int exp) {
    if (exp == 0) {
        return 1;
    }
    return base * potencia(base, exp - 1);
}

int main() {
    cout << "=== APLICACION 1: FACTORIAL ===" << endl;
    cout << "Factorial de 4 es: " << factorial(4) << endl;
    cout << "Factorial de 5 es: " << factorial(5) << endl;

    cout << "\n=== APLICACION 2: FIBONACCI ===" << endl;
    cout << "Fibonacci (posicion 4) es: " << fibonacci(4) << endl;
    cout << "Fibonacci (posicion 6) es: " << fibonacci(6) << endl;
    cout << "Secuencia de Finobacci hasta la posicion 6: ";
    for (int i = 0; i <= 6; i++) {
        cout << fibonacci(i) << " ";
    }
    cout << endl;

    cout << "\n=== APLICACION 3: SUMA DE DIGITOS ===" << endl;
    int num = 12345;
    cout << "Suma de los digitos de " << num << " es: " << sumaDigitos(num) << endl;

    cout << "\n=== APLICACION 4: POTENCIA ===" << endl;
    cout << "2 elevado a 3 es: " << potencia(2, 3) << endl;
    cout << "5 elevado a 3 es: " << potencia(5, 3) << endl;

    return 0;
}
