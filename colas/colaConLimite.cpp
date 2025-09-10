#include <iostream>

// Una estructura para representar un nodo en la cola.
// Un nodo contiene el dato y un puntero al siguiente nodo.
struct Nodo {
    int dato;
    Nodo* siguiente;
};

// Una estructura que actúa como el objeto de la cola.
// Contiene los punteros al frente y al final, el límite y el tamaño actual.
struct ColaConLimite {
    Nodo* frente;
    Nodo* final_de_cola;
    int limite;
    int tamanoActual;
};

// --- Funciones para manipular la cola ---

// Inicializa la cola, configurando sus punteros y el límite de tamaño.
void inicializar(ColaConLimite* cola, int limite) {
    cola->frente = nullptr; // nullptr representa un puntero nulo en C++.
    cola->final_de_cola = nullptr;
    cola->limite = limite;
    cola->tamanoActual = 0;
}

// Verifica si la cola está vacía.
bool estaVacia(ColaConLimite* cola) {
    return cola->frente == nullptr;
}

// Agrega un nuevo dato al final de la cola.
void enqueue(ColaConLimite* cola, int nuevoDato) {
    // Si la cola ya está llena, no se puede agregar nada.
    if (cola->tamanoActual >= cola->limite) {
        std::cerr << "Error: ¡La cola ya alcanzó su límite de " << cola->limite << " elementos!" << std::endl;
        return;
    }

    // Creamos un nuevo nodo en memoria.
    Nodo* nuevoNodo = new Nodo;
    nuevoNodo->dato = nuevoDato;
    nuevoNodo->siguiente = nullptr;

    // Si la cola está vacía, el nuevo nodo es el primero y el último.
    if (cola->final_de_cola == nullptr) {
        cola->frente = nuevoNodo;
        cola->final_de_cola = nuevoNodo;
    } else {
        // Si no está vacía, el nuevo nodo va después del último.
        cola->final_de_cola->siguiente = nuevoNodo;
        cola->final_de_cola = nuevoNodo;
    }

    // Aumentamos el contador de tamaño.
    cola->tamanoActual++;
    std::cout << "Enqueue: " << nuevoDato << std::endl;
}

// Saca y devuelve el elemento del frente de la cola.
int dequeue(ColaConLimite* cola) {
    if (estaVacia(cola)) {
        std::cerr << "Error: La cola está vacía, no hay nada que sacar." << std::endl;
        return -1; // Un valor de error.
    }

    // Guardamos el dato del frente.
    int dato = cola->frente->dato;
    // Guardamos una referencia al nodo que vamos a eliminar.
    Nodo* nodoAEliminar = cola->frente;
    // El nuevo frente de la cola es el siguiente nodo.
    cola->frente = cola->frente->siguiente;

    // Si la cola queda vacía, el final también debe ser nulo.
    if (cola->frente == nullptr) {
        cola->final_de_cola = nullptr;
    }

    // Liberamos la memoria del nodo que sacamos. ¡Muy importante!
    delete nodoAEliminar;
    // Disminuimos el contador.
    cola->tamanoActual--;

    std::cout << "Dequeue: " << dato << std::endl;
    return dato;
}

// Muestra el dato del frente de la cola sin sacarlo.
int peek(ColaConLimite* cola) {
    if (estaVacia(cola)) {
        std::cerr << "Error: La cola está vacía, no hay nada que ver." << std::endl;
        return -1;
    }
    return cola->frente->dato;
}

// --- Ejemplo de uso ---
int main() {
    // Creamos una cola con un límite de 3 elementos.
    ColaConLimite miCola;
    inicializar(&miCola, 3); 

    enqueue(&miCola, 10);
    enqueue(&miCola, 20);
    enqueue(&miCola, 30);
    enqueue(&miCola, 40); // Esto mostrará un error.

    std::cout << "El frente de la cola es: " << peek(&miCola) << std::endl;

    dequeue(&miCola);
    dequeue(&miCola);

    // Podemos volver a agregar elementos.
    enqueue(&miCola, 50);

    return 0;
}