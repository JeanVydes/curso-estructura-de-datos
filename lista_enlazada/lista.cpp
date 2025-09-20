#include <iostream>

// Un nodo es como una caja en una cadena.
// Cada caja tiene un dato y una flecha que apunta a la siguiente caja.
struct Nodo {
    int dato;      // El valor que guardamos en la caja.
    Nodo* siguiente; // La "flecha" que apunta a la siguiente caja.
};

// 'cabeza' es el puntero al inicio de la cadena de cajas.
// Si 'cabeza' es nulo, significa que la cadena está vacía.
Nodo* cabeza = nullptr;

//---
void insertarAlInicio(int nuevoDato) {
    // Creamos una nueva caja.
    Nodo* nuevoNodo = new Nodo;
    nuevoNodo->dato = nuevoDato;
    
    // Hacemos que la flecha de la nueva caja apunte a la caja que antes era la primera.
    nuevoNodo->siguiente = cabeza;
    
    // Ahora, la nueva caja es el inicio de la cadena.
    cabeza = nuevoNodo;
}

//---
void insertarAlFinal(int nuevoDato) {
    // Creamos una nueva caja.
    Nodo* nuevoNodo = new Nodo;
    nuevoNodo->dato = nuevoDato;
    nuevoNodo->siguiente = nullptr; // Su flecha apunta a la nada, porque es la última.

    // Si la cadena está vacía, la nueva caja se convierte en la primera.
    if (cabeza == nullptr) {
        cabeza = nuevoNodo;
        return;
    }
    
    // Recorremos la cadena desde el inicio hasta encontrar la última caja.
    Nodo* ultimo = cabeza;
    while (ultimo->siguiente != nullptr) {
        ultimo = ultimo->siguiente;
    }
    
    // Cuando encontramos la última caja, hacemos que su flecha apunte a la nueva.
    ultimo->siguiente = nuevoNodo;
}

//---
void insertarDespuesDeCualquierNodoQueSeaMayorQue(int nuevoDato, int condicion) {
    if (cabeza == nullptr) {
        std::cout << "La lista está vacía." << std::endl;
        return;
    }

    // Buscamos una caja que cumpla la condición.
    Nodo* nodoActual = cabeza;
    while (nodoActual != nullptr) {
        if (nodoActual->dato >= condicion) {
            // Cuando la encontramos, creamos una nueva caja...
            Nodo* nuevoNodo = new Nodo;
            nuevoNodo->dato = nuevoDato;
            // ...hacemos que su flecha apunte a la que apuntaba la caja actual...
            nuevoNodo->siguiente = nodoActual->siguiente;
            // ...y hacemos que la flecha de la caja actual apunte a la nueva.
            nodoActual->siguiente = nuevoNodo;
            return;
        }
        nodoActual = nodoActual->siguiente;
    }

    std::cout << "No se encontró ningún nodo que cumpla con la condición." << std::endl;
}

//---
void insertarDespuesDeLosTresPrimerosNodos(int nuevoDato) {
    // Nos aseguramos de que haya al menos tres cajas en la cadena.
    if (cabeza == nullptr || cabeza->siguiente == nullptr || cabeza->siguiente->siguiente == nullptr) {
        std::cout << "La lista no tiene suficientes nodos." << std::endl;
        return;
    }

    // Buscamos la tercera caja.
    Nodo* tercerNodo = cabeza->siguiente->siguiente;

    // Creamos la nueva caja y la insertamos entre la tercera y la cuarta.
    Nodo* nuevoNodo = new Nodo;
    nuevoNodo->dato = nuevoDato;
    nuevoNodo->siguiente = tercerNodo->siguiente;
    tercerNodo->siguiente = nuevoNodo;
}

//---
void mostrarLista() {
    // Comenzamos desde la primera caja.
    Nodo* nodo = cabeza;
    while (nodo != nullptr) {
        // Mostramos el dato de la caja actual y una flecha.
        std::cout << nodo->dato << " -> ";
        // Pasamos a la siguiente caja usando su flecha.
        nodo = nodo->siguiente;
    }
    std::cout << "NULL" << std::endl;
}

//---
int main() {
    insertarAlInicio(30);
    insertarAlInicio(20);
    insertarAlInicio(10);
    
    std::cout << "La lista después de insertar al inicio es:" << std::endl;
    mostrarLista();

    insertarAlFinal(40);
    insertarAlFinal(50);

    std::cout << "\nLa lista después de insertar al final es:" << std::endl;
    mostrarLista();

    insertarDespuesDeCualquierNodoQueSeaMayorQue(35, 30); 
    std::cout << "\nLa lista después de insertar 35 después del primer nodo >= 30 es:" << std::endl;
    mostrarLista();
    
    insertarDespuesDeLosTresPrimerosNodos(25);
    std::cout << "\nLa lista después de insertar 25 después del tercer nodo es:" << std::endl;
    mostrarLista();

    return 0;
}