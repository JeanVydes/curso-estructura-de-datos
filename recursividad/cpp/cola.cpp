#include <iostream>
#include <stdexcept>

using namespace std;

class Nodo {
public:
    int dato;
    Nodo* siguiente;

    Nodo(int dato) {
        this->dato = dato;
        this->siguiente = nullptr;
    }
};

class Cola {
private:
    Nodo* frente;
    Nodo* finalDeCola;

public:
    Cola() {
        this->frente = nullptr;
        this->finalDeCola = nullptr;
    }

    void enqueue(int nuevoDato) {
        Nodo* nuevoNodo = new Nodo(nuevoDato);
        if (finalDeCola == nullptr) {
            frente = nuevoNodo;
            finalDeCola = nuevoNodo;
        } else {
            finalDeCola->siguiente = nuevoNodo;
            finalDeCola = nuevoNodo;
        }
    }

    int dequeue() {
        if (estaVacia()) {
            throw runtime_error("Cola vacia");
        }
        int dato = frente->dato;
        Nodo* nodoAEliminar = frente;
        frente = frente->siguiente;
        
        if (frente == nullptr) {
            finalDeCola = nullptr;
        }
        delete nodoAEliminar;
        return dato;
    }

    int peek() {
        if (estaVacia()) {
            throw runtime_error("Cola vacia");
        }
        return frente->dato;
    }

    bool estaVacia() {
        return frente == nullptr && finalDeCola == nullptr;
    }

    void invertirRecursivo() {
        if (estaVacia()) {
            return;
        }

        int dato = dequeue();
        invertirRecursivo();
        enqueue(dato);
    }

    bool buscarElementoRecursivo(Nodo* nodo, int valor) {
        if (nodo == nullptr) {
            return false;
        }
        if (nodo->dato == valor) {
            return true;
        }
        return buscarElementoRecursivo(nodo->siguiente, valor);
    }

    Nodo* getFrente() {
        return frente;
    }

    void mostrar() {
        Nodo* actual = frente;
        cout << "Cola: ";
        while (actual != nullptr) {
            cout << actual->dato << " ";
            actual = actual->siguiente;
        }
        cout << endl;
    }
};

int main() {
    Cola c;
    c.enqueue(10);
    c.enqueue(20);
    c.enqueue(30);

    cout << "Cola original:" << endl;
    c.mostrar();

    c.invertirRecursivo();
    cout << "Cola invertida recursivamente:" << endl;
    c.mostrar();

    cout << "Buscar 20 (recursivo): " << (c.buscarElementoRecursivo(c.getFrente(), 20) ? "true" : "false") << endl;
    cout << "Buscar 50 (recursivo): " << (c.buscarElementoRecursivo(c.getFrente(), 50) ? "true" : "false") << endl;

    return 0;
}
