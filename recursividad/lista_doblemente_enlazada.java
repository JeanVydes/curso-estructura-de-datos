package recursividad;

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

        Nodo ultimo = cabeza.anterior;
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
     * IMPORTANTE: La lógica de este método asume que la lista es CIRCULAR,
     * ya que su condición para detenerse es `nodo.siguiente == cabeza`.
     * Si se usa en una lista que termina en 'null', causará un error de
     * NullPointerException.
     *
     * @param nodo El nodo desde el cual se inicia el conteo. Para contar toda la
     *             lista, se debe llamar inicialmente con la cabeza.
     * @return El número de nodos en la lista.
     */
    public int contarRecursivo(Nodo nodo) {
        // Caso base: si el siguiente nodo es la cabeza, significa que hemos recorrido
        // toda la lista circular
        // y este es el último nodo. Devolvemos 1 para contarlo.
        if (nodo.siguiente == cabeza) {
            return 1;
        }
        // Paso recursivo: contamos el nodo actual (sumando 1) y le añadimos el
        // resultado
        // de llamar a la función para el resto de la lista (a partir del siguiente
        // nodo).
        return 1 + contarRecursivo(nodo.siguiente);
    }

    /**
     * Busca un nodo con un dato específico de forma recursiva.
     * IMPORTANTE: Al igual que el método de contar, la lógica de este método asume
     * que la lista es CIRCULAR.
     * Su caso base para cuando no se encuentra el elemento es `nodo.siguiente ==
     * cabeza`.
     * Esto fallará en una lista no circular que termina en 'null'.
     *
     * @param nodo  El nodo desde el cual comenzar la búsqueda.
     * @param valor El valor (dato) que se está buscando en la lista.
     * @return El nodo que contiene el valor, o 'null' si no se encuentra.
     */
    public static Nodo buscarRecursivo(Nodo nodo, char valor) {
        // Caso base (fallido): si el siguiente es la cabeza, hemos recorrido la lista
        // sin encontrar el valor. Devolvemos null. (Nota: esta lógica no evalúa el
        // último nodo).
        if (nodo.siguiente == nodo) {
            return null;
        }

        // Caso base (éxito): si el dato del nodo actual es el que buscamos,
        // hemos encontrado el nodo y lo devolvemos.
        if (nodo.dato == valor) {
            return nodo;
        }

        if (nodo.siguiente == null) {
            return null; // Evita NullPointerException en listas no circulares.
        }

        // Paso recursivo: si no hemos encontrado el valor en el nodo actual,
        // continuamos la búsqueda llamando a la función con el siguiente nodo.
        return buscarRecursivo(nodo.siguiente, valor);
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
    }
}