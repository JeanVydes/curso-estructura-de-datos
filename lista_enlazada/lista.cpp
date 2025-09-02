#include <iostream>

// Definimos la estructura de un nodo
struct Nodo {
    // el dato que queremos guardar
    int dato;
    // apuntador en memoria al siguiente nodo
    Nodo* siguiente;
};

// Funcion para insertar un nuevo nodo al inicio de la lista
// el doble puntero es necesario para modificar la cabeza de la lista
void insertarAlInicio(Nodo** cabeza, int nuevoDato) {
    // 1. Crear el nuevo nodo
    Nodo* nuevoNodo = new Nodo();
    
    // 2. Asignar el dato
    nuevoNodo->dato = nuevoDato;
    
    // 3. Hacer que el siguiente del nuevo nodo apunte a la cabeza actual
    nuevoNodo->siguiente = *cabeza;
    
    // 4. Mover la cabeza para que apunte al nuevo nodo
    // no podemos moverla antes de asignar el siguiente, porque perderiamos la referencia
    *cabeza = nuevoNodo;
}

void insertarAlFinal(Nodo** cabeza, int nuevoDato) {
    // 1. Crear el nuevo nodo
    Nodo* nuevoNodo = new Nodo();
    nuevoNodo->dato = nuevoDato;
    nuevoNodo->siguiente = NULL;

    // 2. Si la lista está vacía, la cabeza es el nuevo nodo
    // el *cabeza se utiliza para desreferenciar el puntero y acceder al nodo original
    if (*cabeza == NULL) {
        *cabeza = nuevoNodo;
    } else {
        // 3. Si no, encontramos el último nodo y lo enlazamos
        Nodo* temp = *cabeza;
        while (temp->siguiente != NULL) {
            temp = temp->siguiente;
        }
        temp->siguiente = nuevoNodo;
    }
}

// Funcion para mostrar la lista
void mostrarLista(Nodo* nodo) {
    while (nodo != NULL) {
        // al final este se mostrara como: 10 -> 20 -> 30 -> NULL
        std::cout << nodo->dato << " -> ";
        // avanzamos al siguiente nodo, reasignando la variable local como el siguiente
        // por esto el ultimo nodo es null, y acabara el ciclo para imprimir el nodo
        nodo = nodo->siguiente;
    }

    // mostramos null para demostracion
    std::cout << "NULL" << std::endl;
}

int main() {
    // Empezamos con una lista vacia (cabeza es NULL)
    Nodo* cabeza = NULL;

    // Insertamos 3 nodos
    // el &cabeza se utiliza para pasar la dirección de la cabeza de la lista
    insertarAlInicio(&cabeza, 30); // La lista es ahora: 30 -> NULL
    insertarAlInicio(&cabeza, 20); // La lista es ahora: 20 -> 30 -> NULL
    insertarAlInicio(&cabeza, 10); // La lista es ahora: 10 -> 20 -> 30 -> NULL

    insertarAlFinal(&cabeza, 40); // La lista es ahora: 10 -> 20 -> 30 -> 40 -> NULL
    insertarAlFinal(&cabeza, 50); // La lista es ahora: 10 -> 20 -> 30 -> 40 -> 50 -> NULL

    std::cout << "La lista enlazada es: " << std::endl;
    mostrarLista(cabeza);

    return 0;
}