#include <iostream>

using namespace std;

class Nodo {
public:
    char dato;
    Nodo* anterior;
    Nodo* siguiente;

    Nodo(char dato) {
        this->dato = dato;
        this->anterior = nullptr;
        this->siguiente = nullptr;
    }
};

class ListaDoblementeEnlazada {
public:
    Nodo* cabeza;

    ListaDoblementeEnlazada() {
        this->cabeza = nullptr;
    }

    void insertarAlInicio(char nuevoDato) {
        Nodo* nuevoNodo = new Nodo(nuevoDato);
        nuevoNodo->siguiente = cabeza;
        nuevoNodo->anterior = nullptr;
        
        if (cabeza != nullptr) {
            cabeza->anterior = nuevoNodo;
        }
        cabeza = nuevoNodo;
    }

    void insertarAlFinal(char nuevoDato) {
        Nodo* nuevoNodo = new Nodo(nuevoDato);
        nuevoNodo->siguiente = nullptr;
        
        if (cabeza == nullptr) {
            nuevoNodo->anterior = nullptr;
            cabeza = nuevoNodo;
            return;
        }

        Nodo* ultimo = cabeza;
        while (ultimo->siguiente != nullptr) {
            ultimo = ultimo->siguiente;
        }
        
        ultimo->siguiente = nuevoNodo;
        nuevoNodo->anterior = ultimo;
    }

    void mostrarListaAdelante() {
        Nodo* nodo = cabeza;
        cout << "Lista Hacia Adelante: ";
        while (nodo != nullptr) {
            cout << nodo->dato << " <-> ";
            nodo = nodo->siguiente;
        }
        cout << "NULL" << endl;
    }

    int contarRecursivo(Nodo* nodo) {
        if (nodo == nullptr) {
            return 0;
        }
        return 1 + contarRecursivo(nodo->siguiente);
    }

    static Nodo* buscarRecursivo(Nodo* nodo, char valor) {
        if (nodo == nullptr) {
            return nullptr;
        }
        if (nodo->dato == valor) {
            return nodo;
        }
        return buscarRecursivo(nodo->siguiente, valor);
    }

    void imprimirAdelanteRecursivo(Nodo* nodo) {
        if (nodo == nullptr) {
            cout << "NULL" << endl;
            return;
        }
        cout << nodo->dato << " <-> ";
        imprimirAdelanteRecursivo(nodo->siguiente);
    }

    void imprimirAtrasRecursivo(Nodo* nodo) {
        if (nodo == nullptr) {
            cout << "NULL <-> ";
            return;
        }
        imprimirAtrasRecursivo(nodo->siguiente);
        cout << nodo->dato << " ";
    }
};

int main() {
    ListaDoblementeEnlazada lista;
    lista.insertarAlInicio('A');
    lista.insertarAlInicio('B');
    lista.insertarAlInicio('D');
    lista.insertarAlInicio('E');
    
    Nodo* nodoC = ListaDoblementeEnlazada::buscarRecursivo(lista.cabeza, 'C');
    if (nodoC == nullptr) {
        cout << "Nodo 'C' no encontrado. Insertando 'C' al final." << endl;
        lista.insertarAlFinal('C');
    } else {
        cout << "Nodo 'C' ya existe en la lista." << endl;
    }
    
    cout << "\nImprimiendo hacia adelante usando forma recursiva:" << endl;
    lista.imprimirAdelanteRecursivo(lista.cabeza);
    
    cout << "\nImprimiendo hacia atras (en reversa) usando forma recursiva:" << endl;
    lista.imprimirAtrasRecursivo(lista.cabeza);
    cout << endl;
    
    return 0;
}
