#include <iostream>

using namespace std;

// Definición de la estructura del nodo. Es el equivalente al 'Nodo' de Java.
struct Nodo {
    int dato;
    Nodo* siguiente;
};

// Definición de la estructura de la cola.
// Contiene los punteros al frente y al final.
struct cola {
    Nodo* frente;
    Nodo* final_de_cola;
};

// Inicializa una cola vacía.
void inicializarCola(cola& c) {
    c.frente = nullptr;
    c.final_de_cola = nullptr;
}

// Verifica si la cola está vacía.
bool estaVacia(cola& c) {
    return c.frente == nullptr;
}

// Agrega un elemento al final de la cola.
void enqueue(cola& c, int nuevoDato) {
    Nodo* nuevoNodo = new Nodo;
    nuevoNodo->dato = nuevoDato;
    nuevoNodo->siguiente = nullptr;

    if (c.final_de_cola == nullptr) {
        c.frente = nuevoNodo;
        c.final_de_cola = nuevoNodo;
    } else {
        c.final_de_cola->siguiente = nuevoNodo;
        c.final_de_cola = nuevoNodo;
    }
}

// Elimina y devuelve el elemento del frente de la cola.
int dequeue(cola& c) {
    if (estaVacia(c)) {
        cout << "Error: La cola esta vacia." << endl;
        return -1; // O manejar el error de otra forma, como con excepciones.
    }

    Nodo* temp = c.frente;
    int dato = temp->dato;
    c.frente = temp->siguiente;

    if (c.frente == nullptr) {
        c.final_de_cola = nullptr;
    }

    delete temp; // Liberar la memoria del nodo eliminado.
    return dato;
}

// Muestra el elemento del frente sin eliminarlo.
int peek(cola& c) {
    if (estaVacia(c)) {
        cout << "La cola esta vacia." << endl;
        return -1;
    }
    return c.frente->dato;
}

// Invierte la cola usando recursión.
void invertir(cola& c) {
    if (estaVacia(c)) {
        return;
    }

    int dato = dequeue(c);
    invertir(c);
    enqueue(c, dato);
}

// Muestra todos los elementos de la cola.
void mostrar(cola& c) {
    if (estaVacia(c)) {
        cout << "La cola esta vacia." << endl;
        return;
    }

    Nodo* actual = c.frente;
    cout << "Cola: ";
    while (actual != nullptr) {
        cout << actual->dato << " ";
        actual = actual->siguiente;
    }
    cout << endl;
}

// Algoritmo de ordenación de burbuja para una cola.
void bubbleSortOrdenar(cola& c) {
    if (estaVacia(c)) {
        return;
    }

    bool intercambio;
    do {
        intercambio = false;
        Nodo* actual = c.frente;
        while (actual != nullptr && actual->siguiente != nullptr) {
            if (actual->dato > actual->siguiente->dato) {
                int temp = actual->dato;
                actual->dato = actual->siguiente->dato;
                actual->siguiente->dato = temp;
                intercambio = true;
            }
            actual = actual->siguiente;
        }
    } while (intercambio);
}

// Combina dos colas y las ordena.
void unirYOrdenarDosColas(cola& c1, cola& c2, cola& resultado) {
    while (!estaVacia(c1)) {
        enqueue(resultado, dequeue(c1));
    }

    while (!estaVacia(c2)) {
        enqueue(resultado, dequeue(c2));
    }

    bubbleSortOrdenar(resultado);
}

int main() {
    cola cola1, cola2, cola_unida;
    
    // Inicialización de las colas.
    inicializarCola(cola1);
    inicializarCola(cola2);
    inicializarCola(cola_unida);

    // Enqueue
    enqueue(cola1, 10);
    enqueue(cola1, 20);
    enqueue(cola1, 30);
    mostrar(cola1);

    // Dequeue
    dequeue(cola1);
    dequeue(cola1);
    mostrar(cola1);

    // Enqueue adicionales
    enqueue(cola1, 40);
    enqueue(cola1, 50);
    mostrar(cola1);

    // Invertir
    invertir(cola1);
    mostrar(cola1);

    // Prueba de unirYOrdenarDosColas
    inicializarCola(cola1); // Re-inicializar para la prueba
    inicializarCola(cola2); // Re-inicializar para la prueba
    enqueue(cola1, 5);
    enqueue(cola1, 15);
    enqueue(cola1, 25);
    cout << "Cola 1:" << endl;
    mostrar(cola1);

    enqueue(cola2, 10);
    enqueue(cola2, 20);
    enqueue(cola2, 30);
    cout << "Cola 2:" << endl;
    mostrar(cola2);

    unirYOrdenarDosColas(cola1, cola2, cola_unida);
    cout << "Cola unida y ordenada:" << endl;
    mostrar(cola_unida);

    return 0;
}