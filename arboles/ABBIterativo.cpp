// arbol_iterativo_procedural_completo.cpp

#include <iostream>
#include <list> // Necesario para el recorrido Post-Orden Morris

/**
 * ESTRUCTURA DEL NODO
 * ¿Qué es? Define la unidad básica de nuestro árbol. Es el "ladrillo"
 * con el que construiremos toda la estructura.
 * ¿Por qué? Necesitamos una forma de almacenar un dato y, al mismo tiempo,
 * tener "caminos" o "enlaces" a otros nodos.
 */
struct Nodo {
    // ¿Qué hace? Almacena el valor numérico del nodo.
    int dato;
    // ¿Qué hacen? Son punteros. Un puntero es como una flecha que apunta a la
    // dirección de memoria de otro nodo. 'izquierdo' apunta a un nodo con un
    // valor menor, y 'derecho' a uno con un valor mayor.
    Nodo* izquierdo;
    Nodo* derecho;

    // ¿Qué es? Un constructor. Es una función especial que se ejecuta al crear un
    // nuevo Nodo.
    // ¿Por qué? Facilita la vida. Con una sola línea (ej: new Nodo(10)), crea el
    // nodo, le asigna el valor y se asegura de que sus hijos apunten a 'nada'
    // (nullptr) inicialmente.
    Nodo(int valor) : dato(valor), izquierdo(nullptr), derecho(nullptr) {}
};

/**
 * INSERCIÓN ITERATIVA
 * @param raiz La raíz del árbol. Se pasa por referencia (Nodo*&) para poder
 * modificarla directamente si el árbol está vacío.
 * @param valor El dato a insertar.
 */
void insertar(Nodo*& raiz, int valor) {
    // --- PASO 1: PREPARACIÓN - CREAR EL NUEVO NODO ---
    // ¿Qué hace? Creamos el nodo que queremos insertar en la memoria.
    // ¿Por qué? Antes de encontrar dónde ponerlo, necesitamos tener el nodo
    // listo. En este momento, es un nodo "flotante", no está conectado a nada.
    Nodo* nuevoNodo = new Nodo(valor);

    // --- PASO 2: EL CASO MÁS SIMPLE - EL ÁRBOL VACÍO ---
    // ¿Qué hace? Comprueba si el puntero de la raíz es nulo.
    // ¿Por qué? Si el árbol no tiene ningún nodo, no hay necesidad de buscar.
    // El nuevo nodo se convierte en la primera y única cosa en el árbol: la raíz.
    if (!raiz) {
        raiz = nuevoNodo;
        return;
    }

    // --- PASO 3: PREPARANDO LA BÚSQUEDA - LOS PUNTEROS GUÍA ---
    // ¿Qué hace? 'actual' se inicializa en la raíz. Será nuestro explorador.
    // ¿Por qué? Lo usaremos para descender por el árbol, moviéndose de nodo en nodo.
    Nodo* actual = raiz;
    // ¿Qué hace? 'padre' se inicializa en nulo. Siempre irá un paso por detrás de 'actual'.
    // ¿Por qué? Es la clave. Cuando 'actual' encuentre un espacio vacío (se vuelva nullptr),
    // 'padre' estará apuntando al nodo al que debemos conectar nuestro 'nuevoNodo'.
    Nodo* padre = nullptr;

    // --- PASO 4: EL DESCENSO POR EL ÁRBOL - EL BUCLE 'while' ---
    // ¿Qué hace? Recorre el árbol hasta que 'actual' se convierta en nullptr.
    // ¿Por qué? Así encontramos el final de una rama, el lugar exacto para insertar.
    while (actual != nullptr) {
        // ¿Qué hace? 'padre' se actualiza para que apunte a 'actual'.
        // ¿Por qué? ANTES de que 'actual' se mueva hacia abajo, hacemos que 'padre'
        // lo alcance. Así, 'padre' siempre recuerda el nodo que acabamos de dejar.
        padre = actual;

        // ¿Qué hace? Compara el valor a insertar con el dato del nodo 'actual'.
        // ¿Por qué? Esta es la regla del ABB. Nos dice si debemos ir a la izquierda o a la derecha.
        if (valor < actual->dato) {
            actual = actual->izquierdo;
        } else if (valor > actual->dato) {
            actual = actual->derecho;
        } else {
            // El valor ya existe. No permitimos duplicados.
            // ¿Qué hace? Elimina el 'nuevoNodo' que creamos y no vamos a usar.
            // ¿Por qué? Para evitar una fuga de memoria.
            delete nuevoNodo;
            return;
        }
    }

    // --- PASO 5: LA CONEXIÓN FINAL - ENGANCHAR EL NODO ---
    // ¿Qué hace? Compara el valor con el del 'padre' para decidir dónde conectar.
    // ¿Por qué? Ya encontramos al padre correcto. Ahora solo necesitamos conectar
    // el 'nuevoNodo' en la rama correcta para mantener el orden del ABB.
    if (valor < padre->dato) {
        padre->izquierdo = nuevoNodo;
    } else {
        padre->derecho = nuevoNodo;
    }
}

/**
 * ELIMINACIÓN ITERATIVA
 * @param raiz La raíz del árbol. Se pasa por referencia para poder modificarla.
 * @param valor El dato del nodo a eliminar.
 */
void eliminar(Nodo*& raiz, int valor) {
    if (!raiz) return;

    // --- PASO 1: BÚSQUEDA DEL NODO A ELIMINAR Y SU PADRE ---
    // ¿Qué hacen? 'padre' y 'actual' funcionan igual que en la inserción.
    // ¿Por qué? Necesitamos encontrar el nodo a borrar ('actual') y también
    // quién es su 'padre' para poder "reconectar" los punteros después.
    Nodo* padre = nullptr;
    Nodo* actual = raiz;
    while (actual != nullptr && actual->dato != valor) {
        padre = actual;
        if (valor < actual->dato) {
            actual = actual->izquierdo;
        } else {
            actual = actual->derecho;
        }
    }
    if (actual == nullptr) return; // Si no se encontró, no hay nada que hacer.

    // --- PASO 2: LÓGICA DE ELIMINACIÓN ---
    // CASO 1 (hoja) Y CASO 2 (un hijo)
    if (actual->izquierdo == nullptr || actual->derecho == nullptr) {
        // ¿Qué hace? 'hijo' apuntará al único hijo de 'actual', o a nullptr si 'actual' es una hoja.
        // ¿Por qué? Simplifica el código. Sea cual sea el caso (0 o 1 hijo), 'hijo'
        // contiene lo que debe reemplazar a 'actual'.
        Nodo* hijo = (actual->izquierdo != nullptr) ? actual->izquierdo : actual->derecho;

        // ¿Qué hace? Comprueba si el nodo a eliminar es la raíz.
        // ¿Por qué? Si no tiene padre, la raíz del árbol debe actualizarse directamente.
        if (padre == nullptr) {
            raiz = hijo;
        }
        // ¿Qué hace? Comprueba si 'actual' es un hijo izquierdo.
        // ¿Por qué? Para saber si debemos modificar el puntero 'izquierdo' o 'derecho' del padre.
        else if (actual == padre->izquierdo) {
            padre->izquierdo = hijo; // El padre "adopta" al nieto.
        } else {
            padre->derecho = hijo; // El padre "adopta" al nieto.
        }
        // ¿Qué hace? Libera la memoria del nodo que ya no está en el árbol.
        // ¿Por qué? Es crucial en C++ para evitar fugas de memoria.
        delete actual;
    }
    // CASO 3: EL NODO TIENE DOS HIJOS
    else {
        // Estrategia: Reemplazar el valor, no el nodo.
        // 1. Encontrar el sucesor (el nodo más pequeño del subárbol derecho).
        // ¿Por qué el sucesor? Porque es el único valor que garantiza ser mayor que todo
        // lo que está a la izquierda y menor que el resto de la derecha.
        Nodo* padreSucesor = actual;
        Nodo* sucesor = actual->derecho;
        while (sucesor->izquierdo != nullptr) {
            padreSucesor = sucesor;
            sucesor = sucesor->izquierdo;
        }

        // 2. Copiamos el valor del sucesor al nodo que queremos "eliminar".
        actual->dato = sucesor->dato;

        // 3. Ahora, el problema se reduce a eliminar el nodo sucesor, que es mucho más fácil
        // (siempre será un caso 1 o 2).
        if (sucesor == padreSucesor->derecho) {
            padreSucesor->derecho = sucesor->derecho;
        } else {
            padreSucesor->izquierdo = sucesor->derecho;
        }
        delete sucesor;
    }
}

/**
 * RECORRIDO IN-ORDEN CON MORRIS
 */
void recorridoInOrdenMorris(Nodo* raiz) {
    std::cout << "In-Orden (Morris):   ";
    Nodo* actual = raiz;
    while (actual != nullptr) {
        if (actual->izquierdo == nullptr) {
            // Si no hay izquierda, visitamos el nodo actual y avanzamos a la derecha.
            std::cout << actual->dato << " ";
            actual = actual->derecho;
        } else {
            // Buscamos el predecesor (el nodo más a la derecha del subárbol izquierdo).
            Nodo* predecesor = actual->izquierdo;
            while (predecesor->derecho != nullptr && predecesor->derecho != actual) {
                predecesor = predecesor->derecho;
            }

            if (predecesor->derecho == nullptr) {
                // Es la primera visita. Creamos el hilo y bajamos por la izquierda.
                predecesor->derecho = actual;
                actual = actual->izquierdo;
            } else {
                // Regresamos por el hilo. Lo rompemos, visitamos el nodo y vamos a la derecha.
                predecesor->derecho = nullptr;
                std::cout << actual->dato << " ";
                actual = actual->derecho;
            }
        }
    }
    std::cout << std::endl;
}

/**
 * RECORRIDO PRE-ORDEN CON MORRIS
 */
void recorridoPreOrdenMorris(Nodo* raiz) {
    std::cout << "Pre-Orden (Morris):  ";
    Nodo* actual = raiz;
    while (actual != nullptr) {
        if (actual->izquierdo == nullptr) {
            std::cout << actual->dato << " ";
            actual = actual->derecho;
        } else {
            Nodo* predecesor = actual->izquierdo;
            while (predecesor->derecho != nullptr && predecesor->derecho != actual) {
                predecesor = predecesor->derecho;
            }

            if (predecesor->derecho == nullptr) {
                // La diferencia clave: Visitamos el nodo al crear el hilo.
                std::cout << actual->dato << " ";
                predecesor->derecho = actual;
                actual = actual->izquierdo;
            } else {
                predecesor->derecho = nullptr;
                actual = actual->derecho;
            }
        }
    }
    std::cout << std::endl;
}

/**
 * RECORRIDO POST-ORDEN CON MORRIS
 */
void recorridoPostOrdenMorris(Nodo* raiz) {
    std::cout << "Post-Orden (Morris): ";
    std::list<int> resultado;
    Nodo* actual = raiz;
    while (actual != nullptr) {
        if (actual->derecho == nullptr) {
            resultado.push_front(actual->dato);
            actual = actual->izquierdo;
        } else {
            Nodo* sucesor = actual->derecho;
            while (sucesor->izquierdo != nullptr && sucesor->izquierdo != actual) {
                sucesor = sucesor->izquierdo;
            }
            if (sucesor->izquierdo == nullptr) {
                resultado.push_front(actual->dato);
                sucesor->izquierdo = actual;
                actual = actual->derecho;
            } else {
                sucesor->izquierdo = nullptr;
                actual = actual->izquierdo;
            }
        }
    }
    for (int dato : resultado) {
        std::cout << dato << " ";
    }
    std::cout << std::endl;
}

/**
 * LIBERACIÓN DE MEMORIA
 * @param nodo La raíz del árbol a destruir.
 */
void destruirArbol(Nodo* nodo) {
    // ¿Qué hace? Usa un recorrido Post-Orden recursivo.
    // ¿Por qué? Es la única forma segura. Debemos eliminar los hijos ANTES de
    // eliminar al padre. Si elimináramos al padre primero, perderíamos los
    // punteros a sus hijos y esa memoria nunca se liberaría (fuga de memoria).
    if (nodo) {
        destruirArbol(nodo->izquierdo);
        destruirArbol(nodo->derecho);
        delete nodo;
    }
}

int main() {
    // 1. CREACIÓN: La raíz del árbol empieza siendo un puntero a nada.
    Nodo* raiz = nullptr;

    std::cout << "--- ARBOL ITERATIVO PROCEDURAL EN C++ ---" << std::endl;
    int valores[] = {10, 5, 15, 3, 7, 12, 18};
    std::cout << "Insertando: ";
    // 2. POBLACIÓN: Se insertan los valores uno por uno en el árbol.
    for (int valor : valores) {
        std::cout << valor << " ";
        insertar(raiz, valor);
    }
    std::cout << "\n" << std::endl;

    // 3. RECORRIDOS: Se muestran los datos del árbol en diferentes órdenes.
    recorridoInOrdenMorris(raiz);
    recorridoPreOrdenMorris(raiz);
    recorridoPostOrdenMorris(raiz);

    // 4. ELIMINACIÓN: Se prueba la función de borrado.
    std::cout << "\nEliminando el 15 (nodo con dos hijos)..." << std::endl;
    eliminar(raiz, 15);
    recorridoInOrdenMorris(raiz);

    // 5. DESTRUCCIÓN: Se libera toda la memoria asignada antes de terminar.
    // ¿Por qué? Para ser un programa limpio y sin fugas de memoria.
    destruirArbol(raiz);

    return 0;
}