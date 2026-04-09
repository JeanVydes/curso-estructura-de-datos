package recursividad.java;

public class lista_doblemente_enlazada {
    private static class Nodo {
        char dato;
        Nodo anterior;
        Nodo siguiente;

        public Nodo(char dato) {
            this.dato = dato;
            this.anterior = null;
            this.siguiente = null;
        }
    }

    Nodo cabeza = null;

    public void insertarAlInicio(char nuevoDato) {
        Nodo nuevoNodo = new Nodo(nuevoDato);
        nuevoNodo.siguiente = cabeza;

        nuevoNodo.anterior = null;
        if (cabeza != null) {
            cabeza.anterior = nuevoNodo;
        }

        cabeza = nuevoNodo;
    }

    public void insertarAlFinal(char nuevoDato) {
        Nodo nuevoNodo = new Nodo(nuevoDato);
        nuevoNodo.siguiente = null;

        if (cabeza == null) {
            nuevoNodo.anterior = null;
            cabeza = nuevoNodo;
            return;
        }

        Nodo ultimo = cabeza;
        while (ultimo.siguiente != null) {
            ultimo = ultimo.siguiente;
        }

        ultimo.siguiente = nuevoNodo;
        nuevoNodo.anterior = ultimo;
    }

    public void mostrarListaAdelante() {
        Nodo nodo = cabeza;
        System.out.print("Lista Hacia Adelante: ");
        while (nodo != null) {
            System.out.print(nodo.dato + " <-> ");
            nodo = nodo.siguiente; // Avanzamos con el puntero 'siguiente'.
        }
        System.out.println("NULL");
    }

    /**
     * Cuenta el número de nodos en la lista de forma recursiva.
     * IMPORTANTE: Esta es una lista NO CIRCULAR, por lo que asume
     * que su final es null. Si fuera circular la forma de detenerse
     * sería distinta (ej. iterar comparando contra cabeza para no ciclar).
     *
     * @param nodo El nodo desde el cual se inicia el conteo.
     * @return El número de nodos en la lista.
     */
    public int contarRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarRecursivo(nodo.siguiente);
    }

    /**
     * Busca un nodo con un dato específico de forma recursiva.
     * IMPORTANTE: Esta es una lista NO CIRCULAR, por lo que asume
     * que su final es null, si no lo fuera causaría un stack overflow
     * o NullPointerException al no manejar adecuadamente el fin del ciclo.
     *
     * @param nodo  El nodo desde el cual comenzar la búsqueda.
     * @param valor El valor (dato) que se está buscando en la lista.
     * @return El nodo que contiene el valor, o 'null' si no se encuentra.
     */
    public static Nodo buscarRecursivo(Nodo nodo, char valor) {
        if (nodo == null) {
            return null;
        }
        if (nodo.dato == valor) {
            return nodo;
        }
        return buscarRecursivo(nodo.siguiente, valor);
    }

    /**
     * EJERCICIO DE RECURSIVIDAD 3: Imprimir la lista hacia adelante.
     * En lugar de usar un bucle while, usamos recursividad para iterar.
     * 
     * @param nodo El nodo actual a imprimir.
     */
    public void imprimirAdelanteRecursivo(Nodo nodo) {
        // CASO BASE: Si el nodo es nulo, hemos llegado al final de la lista.
        if (nodo == null) {
            System.out.println("NULL");
            return;
        }

        // PASO RECURSIVO:
        // 1. Imprimimos el dato del nodo actual.
        System.out.print(nodo.dato + " <-> ");

        // 2. Llamamos recursivamente a la función con el siguiente nodo.
        // Esto forma una cadena de llamadas hasta llegar al caso base.
        imprimirAdelanteRecursivo(nodo.siguiente);
    }

    /**
     * EJERCICIO DE RECURSIVIDAD 4: Imprimir la lista hacia atrás (en reversa).
     * Este es el ejemplo perfecto del poder de la recursión sobre la iteración.
     * A diferencia de iterar con un puntero "anterior", aquí usamos la pila de
     * llamadas
     * del sistema para "recordar" los nodos visitados y luego imprimir al volver
     * atrás.
     * 
     * @param nodo El nodo actual desde el que iniciar.
     */
    public void imprimirAtrasRecursivo(Nodo nodo) {
        // CASO BASE: Llegamos al final de la lista, terminamos.
        // Imprimimos el inicio visual de la reversa.
        if (nodo == null) {
            System.out.print("NULL <-> ");
            return;
        }

        // PASO RECURSIVO (Antes de imprimir):
        // 1. Nos adentramos "profundamente" en la lista llamando recursivamente al
        // siguiente nodo.
        // Todavía NO imprimimos este nodo. Esperaremos a que la recursión llegue al
        // final.
        imprimirAtrasRecursivo(nodo.siguiente);

        // 2. Aquí es donde ocurre la magia. Una vez que las llamadas recursivas
        // empiezan a resolverse (porque alcanzaron el final y el caso base),
        // imprimirán los nodos desde el último visitado hasta el primero de forma
        // inversa.
        System.out.print(nodo.dato + " ");
    }

    public static void main(String[] args) {
        lista_doblemente_enlazada lista = new lista_doblemente_enlazada();

        lista.insertarAlInicio('A');
        lista.insertarAlInicio('B');
        lista.insertarAlInicio('D');
        lista.insertarAlInicio('E');

        Nodo nodoC = buscarRecursivo(lista.cabeza, 'C');
        if (nodoC == null) {
            System.out.println("Nodo 'C' no encontrado. Insertando 'C' al final.");
            lista.insertarAlFinal('C');
        } else {
            System.out.println("Nodo 'C' ya existe en la lista.");
        }

        System.out.println("\nImprimiendo hacia adelante usando forma recursiva:");
        lista.imprimirAdelanteRecursivo(lista.cabeza);

        System.out.println("\nImprimiendo hacia atrás (en reversa) usando forma recursiva:");
        lista.imprimirAtrasRecursivo(lista.cabeza);
        System.out.println();
    }
}