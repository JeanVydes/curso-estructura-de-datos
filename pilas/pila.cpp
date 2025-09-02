#include <iostream>

// Definimos la estructura de un nodo
struct Nodo {
    int dato;
    Nodo* siguiente;
};

class Pila {
private:
    Nodo* cima; // El puntero a la parte superior de la pila

public:
    Pila() {
        cima = NULL;
    }

    // Operacion push: anadir un elemento
    void push(int nuevoDato) {
        Nodo* nuevoNodo = new Nodo();
        nuevoNodo->dato = nuevoDato;
        nuevoNodo->siguiente = cima;
        cima = nuevoNodo;
        std::cout << "Push: " << nuevoDato << std::endl;
    }

    // Operacion pop: eliminar el elemento superior
    int pop() {
        if (cima == NULL) {
            std::cout << "Error: La pila esta vacia." << std::endl;
            return -1; // Valor de error
        }
        // guardamos la cima en una variable temporal para no perderlo y poderlo eliminarlo de memoria 
        Nodo* temp = cima;
        int dato = temp->dato;
        cima = cima->siguiente;
        delete temp;
        std::cout << "Pop: " << dato << std::endl;
        return dato;
    }

    // Operacion peek: ver el elemento superior sin eliminarlo
    int peek() {
        if (cima == NULL) {
            std::cout << "La pila esta vacia." << std::endl;
            return -1; // Valor de error
        }
        return cima->dato;
    }
    
    // Verificar si la pila esta vacia
    bool estaVacia() {
        return cima == NULL;
    }
};

int main() {
    Pila pila;
    
    pila.push(10);
    pila.push(20);
    pila.push(30);

    std::cout << "Cima de la pila: " << pila.peek() << std::endl;

    pila.pop();
    pila.pop();

    std::cout << "Cima de la pila: " << pila.peek() << std::endl;

    return 0;
}
