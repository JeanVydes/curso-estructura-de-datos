#include <iostream>

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

class Pila {
private:
    Nodo* cima;

    /**
     * FUNCIÓN AUXILIAR RECURSIVA: Insertar en el fondo.
     * 
     * ¿Por qué necesitamos esto? En una pila tradicional, `push` sólo pone cosas
     * en la cima (arriba). Si queremos poner algo hasta abajo (fondo), tenemos
     * que sacar todo lo que está estorbando, poner nuestro elemento, y regresar
     * lo que sacamos en su mismo orden.
     * 
     * ILUSTRACIÓN ASCII (Insertar 'X' en el fondo de [1, 2]):
     * ==============================================================
     * LLAMADA 1: insertarEnElFondo(X) en Pila: Cima[1, 2]Fondo
     * - ¿Vacía? NO.
     * - datoTemporal = pop() -> ¡Sacamos el 1! Pila queda [2]
     * - LLAMADA RECURSIVA a sí misma: insertarEnElFondo(X)
     * 
     * LLAMADA 2: insertarEnElFondo(X) en Pila: Cima[2]Fondo
     * - ¿Vacía? NO.
     * - datoTemporal = pop() -> ¡Sacamos el 2! Pila queda []
     * - LLAMADA RECURSIVA a sí misma: insertarEnElFondo(X)
     * 
     * LLAMADA 3 (Caso Base): insertarEnElFondo(X) en Pila: Cima[]Fondo
     * - ¿Vacía? SÍ. -> push(X). Pila queda [X].
     * - Terminamos y vamos de regreso arriba...
     * 
     * <- VOLVEMOS A LLAMADA 2: push(datoTemporal) -> push(2). Pila queda [2, X]
     * 
     * <- VOLVEMOS A LLAMADA 1: push(datoTemporal) -> push(1). Pila queda [1, 2, X]
     * ==============================================================
     */
    void insertarEnElFondo(int dato) {
        if (estaVacia()) {
            this->push(dato);
            return;
        }

        int datoTemporal = this->pop();
        insertarEnElFondo(dato);
        this->push(datoTemporal);
    }

public:
    Pila() {
        this->cima = nullptr;
    }

    void push(int nuevoDato) {
        Nodo* nuevoNodo = new Nodo(nuevoDato);
        nuevoNodo->siguiente = cima;
        this->cima = nuevoNodo;
    }

    int pop() {
        if (estaVacia()) {
            return -1;
        }
        int dato = cima->dato;
        Nodo* nodoAEliminar = cima;
        this->cima = cima->siguiente;
        delete nodoAEliminar;
        return dato;
    }

    int peek() {
        if (estaVacia()) {
            return -1;
        }
        return cima->dato;
    }

    bool estaVacia() {
        return cima == nullptr;
    }

    /**
     * EJERCICIO DE RECURSIVIDAD 1: Contar elementos de la pila.
     * 
     * ¿Cómo funciona?
     * En lugar de usar un bucle 'while', avanzamos por la estructura y decimos:
     * "La cantidad de elementos desde este nodo es: 1 (yo mismo) + (la cantidad del
     * resto)".
     * 
     * EJEMPLO: Pila [10, 20, 30] -> Cima = 30
     * contar(30) -> devuelve 1 + 2
     * contar(20) -> devuelve 1 + 1
     * contar(10) -> devuelve 1 + 0
     * contar(null) -> devuelve 0 (CASO BASE)
     * 
     * RESULTADO FINAL: 1 + (1 + (1 + 0)) = 3.
     */
    int contarRecursivo(Nodo* nodo) {
        if (nodo == nullptr) {
            return 0;
        }
        return 1 + contarRecursivo(nodo->siguiente);
    }

    /**
     * EJERCICIO DE RECURSIVIDAD 2: Sumar todos los elementos de la pila.
     * 
     * Comparte el mismo principio de "conteo", pero en vez de sumar '1',
     * sumamos el valor del dato del nodo actual.
     * 
     * EJEMPLO ASCII:
     * [30] -> cima
     * [20]
     * [10]
     * 
     * La operación será: 30 + sumar([20, 10]) => 30 + 20 + sumar([10]) => 30 + 20 +
     * 10 + 0 = 60.
     */
    int sumarElementosRecursivo(Nodo* nodo) {
        if (nodo == nullptr) {
            return 0;
        }
        return nodo->dato + sumarElementosRecursivo(nodo->siguiente);
    }

    /**
     * EJERCICIO DE RECURSIVIDAD 3: Invertir la pila.
     * 
     * MÁGIA DE LA PILA DE LLAMADAS:
     * Si nosotros extraemos (pop) todos los elementos de la pila y guardamos
     * cada uno en una llamada recursiva, al regresar de la recursión los habremos
     * sacado desde el primero hasta el último. Al meterlos "al fondo", el orden
     * queda completamente invertido.
     * 
     * PROCESO EJEMPLIFICADO (Pila original: Cima[A, B, C]Fondo):
     * 
     * -> invertirPila([A, B, C])
     * Saca 'A', llama invertirPila([B, C])
     * 
     * -> invertirPila([B, C])
     * Saca 'B', llama invertirPila([C])
     * 
     * -> invertirPila([C])
     * Saca 'C', llama invertirPila([])
     * 
     * -> invertirPila([]) -> Caso Base (retorna)
     * 
     * <- Inserta 'C' en el fondo de [] => Pila: [C]
     * 
     * <- Inserta 'B' en el fondo de [C] => Pila: Cima[C, B]Fondo
     * 
     * <- Inserta 'A' en el fondo de [C, B] => Pila: Cima[C, B, A]Fondo
     * (¡Invertida!)
     */
    void invertirPila() {
        if (estaVacia()) {
            return;
        }

        int dato = this->pop();
        invertirPila();
        insertarEnElFondo(dato);
    }

    Nodo* getCima() {
        return cima;
    }
};

int main() {
    Pila p;
    p.push(1);
    p.push(2);
    p.push(3);
    p.push(4);

    cout << "Cantidad de elementos (Recursivo): " << p.contarRecursivo(p.getCima()) << endl;
    cout << "Suma de elementos (Recursivo): " << p.sumarElementosRecursivo(p.getCima()) << endl;
    
    cout << "Cima antes de invertir: " << p.peek() << endl;
    p.invertirPila();
    cout << "Cima despues de invertir: " << p.peek() << endl;

    return 0;
}
