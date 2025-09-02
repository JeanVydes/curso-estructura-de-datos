#include <iostream>

// Definimos la estructura de un nodo
struct Nodo {
    int dato;
    Nodo* siguiente;
};

class Cola {
private:
    Nodo* frente; // Puntero al frente de la cola
    Nodo* final;  // Puntero al final de la cola

public:
    Cola() {
        frente = NULL;
        final = NULL;
    }

    // Operación enqueue: añadir un elemento al final
    void enqueue(int nuevoDato) {
        // 1. Crear el nuevo nodo
        Nodo* nuevoNodo = new Nodo();
        nuevoNodo->dato = nuevoDato;
        nuevoNodo->siguiente = NULL; // El nuevo nodo siempre será el último, así que su 'siguiente' es NULL
        
        // 2. Si la cola está vacía, el frente y el final son el nuevo nodo
        if (final == NULL) {
            frente = nuevoNodo;
            final = nuevoNodo;
        } else {
            // 3. El nodo actual al final ahora apunta al nuevo nodo
            final->siguiente = nuevoNodo;
            // 4. El nuevo nodo es ahora el final de la cola
            final = nuevoNodo;
        }
        std::cout << "Enqueue: " << nuevoDato << std::endl;
    }

    // Operación dequeue: eliminar el elemento del frente
    int dequeue() {
        // Si la cola está vacía, no hay nada que eliminar
        if (frente == NULL) {
            std::cout << "Error: La cola está vacia." << std::endl;
            return -1; // Valor de error
        }
        
        // 1. Guardar el nodo del frente para su eliminación
        Nodo* temp = frente;
        int dato = temp->dato;
        
        // 2. Mover el puntero 'frente' al siguiente nodo
        frente = frente->siguiente;
        
        // 3. Si el frente se vuelve NULL, la cola está vacía,
        //    así que el 'final' también debe ser NULL
        if (frente == NULL) {
            final = NULL;
        }
        
        // 4. Liberar la memoria del nodo que se eliminó
        delete temp;
        std::cout << "Dequeue: " << dato << std::endl;
        return dato;
    }

    // Operación peek: ver el elemento del frente sin eliminarlo
    int peek() {
        if (frente == NULL) {
            std::cout << "La cola está vacia." << std::endl;
            return -1;
        }
        return frente->dato;
    }
    
    // Verificar si la cola está vacía
    bool estaVacia() {
        return frente == NULL;
    }
};

int main() {
    Cola cola;
    
    cola.enqueue(10);
    cola.enqueue(20);
    cola.enqueue(30);

    std::cout << "Frente de la cola: " << cola.peek() << std::endl; // Debería ser 10

    cola.dequeue();
    cola.dequeue();

    std::cout << "Frente de la cola: " << cola.peek() << std::endl; // Debería ser 30

    return 0;
}
