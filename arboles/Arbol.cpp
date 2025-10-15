// arbol_recursivo_procedural_simple.cpp

#include <iostream>
#include <algorithm> // Solo para std::max, como permitiste.

/**
 * ESTRUCTURA DEL NODO
 * ¿Qué es? La plantilla para cada elemento del árbol. Contiene el dato y los
 * punteros (flechas) hacia sus posibles hijos.
 */
struct Nodo {
    int dato;
    Nodo* izquierdo;
    Nodo* derecho;

    // Constructor para crear un nodo de forma sencilla.
    Nodo(int valor) : dato(valor), izquierdo(nullptr), derecho(nullptr) {}
};

/**
 * INSERCIÓN RECURSIVA (Función auxiliar)
 */
Nodo* insertarRecursivo(Nodo* nodoActual, int valor) {
    // --- PASO 1: CASO BASE - EL PUNTO DE PARADA ---
    // ¿Qué hace? Comprueba si hemos llegado a un espacio vacío (un puntero nulo).
    // ¿Por qué? La recursión debe terminar. Este es el final del camino: hemos
    // encontrado el lugar exacto donde debe ir el nuevo nodo. Creamos el nodo
    // y lo devolvemos para que la llamada anterior (su padre) lo "enganche".
    if (nodoActual == nullptr) {
        return new Nodo(valor);
    }

    // --- PASO 2: PASO RECURSIVO - LA DECISIÓN Y LA LLAMADA ---
    // ¿Qué hace? Compara el valor con el dato del nodo actual.
    // ¿Por qué? Para decidir si el nuevo valor pertenece al subárbol izquierdo o
    // al derecho, manteniendo así la propiedad del ABB.
    if (valor < nodoActual->dato) {
        // ¿Qué hace? Llama a la misma función, pero para el subárbol izquierdo.
        // ¿Por qué? Delega la tarea a un problema más pequeño. La clave es la
        // asignación: `nodoActual->izquierdo = ...`. El padre actualiza su
        // puntero izquierdo con el resultado de la inserción en su subárbol.
        nodoActual->izquierdo = insertarRecursivo(nodoActual->izquierdo, valor);
    } else if (valor > nodoActual->dato) {
        // La misma lógica, pero para el lado derecho.
        nodoActual->derecho = insertarRecursivo(nodoActual->derecho, valor);
    }

    // --- PASO 3: EL RETORNO - MANTENIENDO LA CADENA ---
    // ¿Qué hace? Devuelve el puntero al nodo actual.
    // ¿Por qué? Si el valor ya existía, o si la inserción ocurrió más abajo,
    // este nodo no cambia. Devolverlo asegura que los enlaces por encima de él
    // en la cadena de recursión permanezcan intactos.
    return nodoActual;
}

/**
 * ENCONTRAR MÍNIMO (Función auxiliar para eliminar)
 */
int encontrarMinimo(Nodo* nodo) {
    // ¿Qué hace? Sigue el camino de punteros izquierdos hasta el final.
    // ¿Por qué? Por definición, en un ABB, el valor más pequeño siempre está en
    // el nodo más a la izquierda posible.
    return nodo->izquierdo == nullptr ? nodo->dato : encontrarMinimo(nodo->izquierdo);
}


/**
 * ELIMINACIÓN RECURSIVA (Función auxiliar)
 */
Nodo* eliminarRecursivo(Nodo* nodoActual, int valor) {
    // --- PASO 1: BÚSQUEDA - ENCONTRAR EL NODO ---
    if (nodoActual == nullptr) return nullptr;

    if (valor < nodoActual->dato) {
        nodoActual->izquierdo = eliminarRecursivo(nodoActual->izquierdo, valor);
    } else if (valor > nodoActual->dato) {
        nodoActual->derecho = eliminarRecursivo(nodoActual->derecho, valor);
    } else {
        // --- PASO 2: NODO ENCONTRADO - LÓGICA DE ELIMINACIÓN ---

        // CASO 1: Nodo con 0 hijos (hoja).
        // ¿Qué hace? Elimina el nodo de la memoria y devuelve nullptr.
        // ¿Por qué? El padre que llamó recibirá nullptr y lo asignará a su
        // puntero de hijo, efectivamente "cortando" la hoja.
        if (nodoActual->izquierdo == nullptr && nodoActual->derecho == nullptr) {
            delete nodoActual;
            return nullptr;
        }

        // CASO 2: Nodo con 1 hijo.
        // ¿Qué hace? Guarda una referencia al único hijo, elimina el nodo actual y devuelve el hijo.
        // ¿Por qué? El padre del nodo eliminado "adoptará" directamente a su nieto.
        if (nodoActual->derecho == nullptr) {
            Nodo* temp = nodoActual->izquierdo;
            delete nodoActual;
            return temp;
        }
        if (nodoActual->izquierdo == nullptr) {
            Nodo* temp = nodoActual->derecho;
            delete nodoActual;
            return temp;
        }

        // CASO 3: Nodo con 2 hijos.
        // ¿Qué hace? Encuentra el sucesor, copia su valor y luego elimina el sucesor de su posición.
        // ¿Por qué? Es la única forma de eliminar el valor sin romper la estructura.
        // No eliminamos el nodo, solo "robamos" el valor del sucesor. El problema se
        // convierte en eliminar el sucesor, que es un caso más fácil (1 o 2).
        int sucesor = encontrarMinimo(nodoActual->derecho);
        nodoActual->dato = sucesor;
        nodoActual->derecho = eliminarRecursivo(nodoActual->derecho, sucesor);
    }
    return nodoActual;
}

// --- FUNCIONES DE RECORRIDO
void inOrdenRecursivo(Nodo* nodo) { 
    if (nodo) { 
        inOrdenRecursivo(nodo->izquierdo);
        std::cout << nodo->dato << " ";
        inOrdenRecursivo(nodo->derecho);
    }
}
void preOrdenRecursivo(Nodo* nodo) { 
    if (nodo) { 
        std::cout << nodo->dato << " "; 
        preOrdenRecursivo(nodo->izquierdo); 
        preOrdenRecursivo(nodo->derecho); 
    } 
}
void postOrdenRecursivo(Nodo* nodo) { 
    if (nodo) { 
        postOrdenRecursivo(nodo->izquierdo); 
        postOrdenRecursivo(nodo->derecho); 
        std::cout << nodo->dato << " "; 
    } 
}

/**
 * CALCULAR ALTURA RECURSIVA
 */
int calcularAlturaRecursivo(Nodo* nodo) {
    // --- CASO BASE ---
    // ¿Qué hace? Si el nodo es nulo, devuelve -1.
    // ¿Por qué? Un espacio vacío no tiene altura. El -1 hace que la matemática
    // para un nodo hoja (un nodo sin hijos) sea correcta: 1 + max(-1, -1) = 0.
    if (nodo == nullptr) {
        return -1;
    }
    // --- PASO RECURSIVO ---
    // ¿Qué hace? Calcula la altura de cada subárbol por separado.
    // ¿Por qué? La altura es el camino más largo. Debemos explorar ambos
    // caminos (izquierdo y derecho) para saber cuál es más largo.
    int alturaIzquierda = calcularAlturaRecursivo(nodo->izquierdo);
    int alturaDerecha = calcularAlturaRecursivo(nodo->derecho);
    // ¿Qué hace? Devuelve 1 más la altura del subárbol más alto.
    // ¿Por qué? El '1' cuenta el nivel del nodo actual, y el 'max' elige el
    // camino más largo que encontró entre sus hijos.
    return 1 + std::max(alturaIzquierda, alturaDerecha);
}

/**
 * VALIDAR ABB RECURSIVO (Función auxiliar con punteros para los límites)
 * @param nodo El nodo actual que se está validando.
 * @param min Puntero al límite inferior. Si es nullptr, no hay límite inferior.
 * @param max Puntero al límite superior. Si es nullptr, no hay límite superior.
 * @return true si el subárbol es un ABB válido.
 */
bool esArbolValidoRecursivo(Nodo* nodo, const int* min, const int* max) {
    // --- CASO BASE ---
    // ¿Qué hace? Si el nodo es nulo, la rama es válida.
    // ¿Por qué? Un subárbol vacío no puede violar ninguna regla.
    if (nodo == nullptr) {
        return true;
    }

    // --- LÓGICA DE VALIDACIÓN ---
    // ¿Qué hace? Comprueba si el valor del nodo actual está fuera del rango permitido.
    // ¿Por qué? Primero verifica si el límite existe (si el puntero no es nulo) y,
    // si existe, compara el valor del nodo (*min o *max) con el dato actual.
    if ((min != nullptr && nodo->dato <= *min) || (max != nullptr && nodo->dato >= *max)) {
        return false;
    }

    // --- PASO RECURSIVO ---
    // ¿Qué hace? Llama a la función para sus hijos, pasando los nuevos límites.
    // ¿Por qué? Al ir a la izquierda, pasamos la dirección de memoria del dato
    // actual (`&(nodo->dato)`) como el nuevo límite MÁXIMO. Al ir a la derecha,
    // lo pasamos como el nuevo MÍNIMO. Así se propaga la restricción.
    return esArbolValidoRecursivo(nodo->izquierdo, min, &(nodo->dato)) &&
           esArbolValidoRecursivo(nodo->derecho, &(nodo->dato), max);
}


// --- FUNCIÓN DE LIMPIEZA ---
void destruirArbol(Nodo* nodo) {
    if (nodo) {
        destruirArbol(nodo->izquierdo);
        destruirArbol(nodo->derecho);
        delete nodo;
    }
}


int main() {
    Nodo* raiz = nullptr;

    std::cout << "--- ARBOL RECURSIVO PROCEDURAL EN C++ ---" << std::endl;
    int valores[] = {10, 5, 15, 3, 7, 12, 18};
    std::cout << "Insertando: ";
    for (int valor : valores) {
        std::cout << valor << " ";
        raiz = insertarRecursivo(raiz, valor);
    }
    std::cout << "\n" << std::endl;

    std::cout << "In-Orden (Recursivo):  "; inOrdenRecursivo(raiz); std::cout << std::endl;
    std::cout << "Pre-Orden (Recursivo): "; preOrdenRecursivo(raiz); std::cout << std::endl;
    std::cout << "Post-Orden (Recursivo):"; postOrdenRecursivo(raiz); std::cout << std::endl;

    std::cout << "\nLa altura del arbol es: " << calcularAlturaRecursivo(raiz) << std::endl;
    // La llamada inicial usa 'nullptr' para indicar que no hay límites.
    std::cout << "El arbol es un ABB valido? " << (esArbolValidoRecursivo(raiz, nullptr, nullptr) ? "Si" : "No") << std::endl;

    std::cout << "\nEliminando el 15 (nodo con dos hijos)..." << std::endl;
    raiz = eliminarRecursivo(raiz, 15);
    std::cout << "In-Orden (Recursivo):  "; inOrdenRecursivo(raiz); std::cout << std::endl;

    destruirArbol(raiz);

    return 0;
}