#include <iostream>

// Definimos la estructura de un nodo
struct Nodo {
    int dato;
    Nodo* siguiente;
};

// Funcion para insertar un nuevo nodo al inicio de la lista circular
void insertarAlInicio(Nodo** ultimo, int nuevoDato) {
    // 1. Crear el nuevo nodo
    Nodo* nuevoNodo = new Nodo();
    nuevoNodo->dato = nuevoDato;
    
    // 2. Si la lista esta vacia
    if (*ultimo == NULL) {
        // el nuevo nodo se apunta a si mismo
        *ultimo = nuevoNodo;
        (*ultimo)->siguiente = *ultimo;
    } else {
        // 3. El nuevo nodo apunta a la cabeza actual
        nuevoNodo->siguiente = (*ultimo)->siguiente;
        // 4. El ultimo nodo apunta al nuevo nodo
        (*ultimo)->siguiente = nuevoNodo;
    }
}

// Funcion para insertar un nuevo nodo al final de la lista circular
void insertarAlFinal(Nodo** ultimo, int nuevoDato) {
    // 1. Crear el nuevo nodo
    Nodo* nuevoNodo = new Nodo();
    nuevoNodo->dato = nuevoDato;
    
    // 2. Si la lista esta vacia
    if (*ultimo == NULL) {
        // el nuevo nodo se apunta a si mismo
        *ultimo = nuevoNodo;
        (*ultimo)->siguiente = *ultimo;
    } else {
        // 3. El nuevo nodo apunta a la cabeza actual
        nuevoNodo->siguiente = (*ultimo)->siguiente;
        // 4. El ultimo nodo apunta al nuevo nodo
        (*ultimo)->siguiente = nuevoNodo;
        // 5. El ultimo ahora es el nuevo nodo
        *ultimo = nuevoNodo;
    }
}

// Funcion para mostrar la lista circular
void mostrarLista(Nodo* ultimo) {
    if (ultimo == NULL) {
        std::cout << "La lista esta vacia." << std::endl;
        return;
    }
    Nodo* temp = ultimo->siguiente;
    
    do {
        std::cout << temp->dato << " -> ";
        temp = temp->siguiente;
    } while (temp != ultimo->siguiente);
    
    std::cout << "..." << std::endl;
}

int main() {
    // Empezamos con una lista vacia
    Nodo* ultimo = NULL;

    // Insertamos 2 nodos al inicio
    insertarAlInicio(&ultimo, 20);
    insertarAlInicio(&ultimo, 10);

    std::cout << "La lista despues de insertar al inicio es: " << std::endl;
    mostrarLista(ultimo);
    
    // Insertamos 2 nodos al final
    insertarAlFinal(&ultimo, 30);
    insertarAlFinal(&ultimo, 40);

    std::cout << "\nLa lista despues de insertar al final es: " << std::endl;
    mostrarLista(ultimo);

    return 0;
}
