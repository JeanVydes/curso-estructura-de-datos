#include <iostream>

// Definimos la estructura de un nodo
struct Nodo {
    // el dato que queremos guardar
    int dato;
    // apuntador en memoria al nodo anterior
    Nodo* anterior;
    // apuntador en memoria al nodo siguiente
    Nodo* siguiente;
};

// Funcion para insertar un nuevo nodo al inicio de la lista
void insertarAlInicio(Nodo** cabeza, int nuevoDato) {
    // 1. Crear el nuevo nodo
    Nodo* nuevoNodo = new Nodo();
    
    // 2. Asignar el dato
    nuevoNodo->dato = nuevoDato;
    
    // 3. El nuevo nodo sera la nueva cabeza, entonces su anterior es NULL
    nuevoNodo->anterior = NULL;
    
    // 4. Hacer que el siguiente del nuevo nodo apunte a la cabeza actual
    nuevoNodo->siguiente = *cabeza;
    
    // 5. Si la lista no estaba vacia, el anterior de la cabeza actual sera el nuevo nodo
    if ((*cabeza) != NULL) {
        (*cabeza)->anterior = nuevoNodo;
    }
    
    // 6. Mover la cabeza para que apunte al nuevo nodo
    *cabeza = nuevoNodo;
}

// Funcion para insertar un nuevo nodo al final de la lista
void insertarAlFinal(Nodo** cabeza, int nuevoDato) {
    // 1. Crear el nuevo nodo
    Nodo* nuevoNodo = new Nodo();
    
    // 2. Asignar el dato
    nuevoNodo->dato = nuevoDato;
    
    // 3. El siguiente del nuevo nodo es NULL porque será el último
    nuevoNodo->siguiente = NULL;
    
    // 4. Si la lista está vacía, el nuevo nodo es la cabeza
    if (*cabeza == NULL) {
        nuevoNodo->anterior = NULL;
        *cabeza = nuevoNodo;
        return;
    }

    // 5. Recorremos la lista para encontrar el último nodo
    Nodo* ultimo = *cabeza;
    while (ultimo->siguiente != NULL) {
        ultimo = ultimo->siguiente;
    }

    // 6. Enlazamos el último nodo al nuevo nodo
    ultimo->siguiente = nuevoNodo;
    nuevoNodo->anterior = ultimo;
}

// Funcion para mostrar la lista hacia adelante
void mostrarListaAdelante(Nodo* nodo) {
    while (nodo != NULL) {
        std::cout << nodo->dato << " <-> ";
        nodo = nodo->siguiente;
    }
    std::cout << "NULL" << std::endl;
}

int main() {
    // Empezamos con una lista vacia (cabeza es NULL)
    Nodo* cabeza = NULL;

    // Insertamos 3 nodos al inicio
    insertarAlInicio(&cabeza, 30);
    insertarAlInicio(&cabeza, 20);
    insertarAlInicio(&cabeza, 10);

    std::cout << "La lista despues de insertar al inicio es: " << std::endl;
    mostrarListaAdelante(cabeza);

    // Insertamos 2 nodos al final
    insertarAlFinal(&cabeza, 40);
    insertarAlFinal(&cabeza, 50);

    std::cout << "\nLa lista despues de insertar al final es: " << std::endl;
    mostrarListaAdelante(cabeza);

    return 0;
}