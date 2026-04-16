package recursividad.java;

public class cola {
    private static class Nodo {
        int dato;
        Nodo siguiente;

        public Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo frente;
    private Nodo final_de_cola;

    public cola() {
        this.frente = null;
        this.final_de_cola = null;
    }

    public void enqueue(int nuevoDato) {
        Nodo nuevoNodo = new Nodo(nuevoDato);
        if (final_de_cola == null) {
            frente = nuevoNodo;
            final_de_cola = nuevoNodo;
        } else {
            final_de_cola.siguiente = nuevoNodo;
            final_de_cola = nuevoNodo;
        }
    }

    public int dequeue() {
        if (estaVacia()) {
            throw new IllegalStateException("Cola vacía");
        }
        int dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            final_de_cola = null;
        }
        return dato;
    }

    public int peek() {
        if (estaVacia()) {
            throw new IllegalStateException("Cola vacía");
        }
        return frente.dato;
    }

    public boolean estaVacia() {
        return frente == null && final_de_cola == null;
    }

    /**
     * EJERCICIO DE RECURSIVIDAD 1: Invertir la cola usando la propia pila de
     * llamadas.
     * 
     * DIAGRAMA DEL PROCESO (Cola: Frente[10, 20, 30]Fondo):
     * =======================================================
     * invertirRecursivo() // Cola actual: [10, 20, 30]
     * |__ dato = dequeue() -> 10, Cola queda: [20, 30]
     * |__ invertirRecursivo()
     * |__ dato = dequeue() -> 20, Cola queda: [30]
     * |__ invertirRecursivo()
     * |__ dato = dequeue() -> 30, Cola queda: []
     * |__ invertirRecursivo()
     * |__ devuelve: (Caso Base, cola vacía)
     * 
     * EL REGRESO (Desenrollando la pila de llamadas):
     * enqueue(30). Cola queda: [30]
     * enqueue(20). Cola queda: [30, 20]
     * enqueue(10). Cola queda: [30, 20, 10] <-- Resultado Final (Invertida)
     */
    public void invertirRecursivo() {
        // CASO BASE: Si la cola está vacía, la recursión termina.
        // No hay elementos que sacar.
        if (estaVacia()) {
            return;
        }

        // PASO 1 (Antes de la llamada recursiva):
        // Sacamos el elemento al frente. Este elemento se guarda en la memoria
        // local de esta llamada a la función (en la "pila de llamadas").
        int dato = dequeue();

        // PASO 2 (Llamada recursiva):
        // Ordenamos al sistema que repita el proceso para el resto.
        invertirRecursivo();

        // PASO 3 (Después de la llamada recursiva):
        // A la vuelta, insertamos nuestro elemento nuevamente en la cola,
        // cayendo al final en orden invertido.
        enqueue(dato);
    }

    /**
     * EJERCICIO DE RECURSIVIDAD 2: Buscar un elemento en la cola.
     * 
     * DIAGRAMA DEL PROCESO (Buscar 20 en [10, 20, 30]):
     * =======================================================
     * buscar(nodo: 10, valor: 20)
     * |__ 10 == 20? Falso. Devuelve: buscar(nodo: 20, valor: 20)
     * |__ 20 == 20? Verdadero. (Caso Base - Éxito)
     * |__ devuelve: true
     * 
     * EL REGRESO (Desenrollando la pila de llamadas):
     * Devuelve: true
     * Devuelve: true <-- Resultado Final
     * 
     * @param nodo  El nodo actual desde el cual se sigue la búsqueda.
     * @param valor El valor a buscar.
     * @return true si el valor existe en la cola, false en caso contrario.
     */
    public boolean buscarElementoRecursivo(Nodo nodo, int valor) {
        // CASO BASE (Fallo): Llegamos al final de la cola (nodo = null) y no lo
        // encontramos.
        if (nodo == null) {
            return false;
        }

        // CASO BASE (Éxito): El nodo actual tiene el valor. Ocurre una parada temprana.
        if (nodo.dato == valor) {
            return true;
        }

        // PASO RECURSIVO: El nodo actual no era lo que buscábamos,
        // así que delegamos la responsabilidad de seguir buscando en los nodos
        // siguientes.
        return buscarElementoRecursivo(nodo.siguiente, valor);
    }

    /**
     * EJERCICIO DE RECURSIVIDAD 3: Contar todos los elementos de la cola.
     * 
     * DIAGRAMA DEL PROCESO (Contar elementos en cola con [10, 20]):
     * =======================================================
     * contar(nodo: 10)
     * |__ devuelve: 1 + contar(nodo: 20)
     * |__ devuelve: 1 + contar(nodo: null)
     * |__ devuelve: 0 (Caso Base, fin de cola)
     * 
     * EL REGRESO (Desenrollando la pila de llamadas):
     * 1 + 0 = 1
     * 1 + 1 = 2 <-- Resultado Final
     * 
     * @param nodo El nodo actual en evaluación.
     * @return El recuento restante de elementos
     */
    public int contarElementosRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }

        return 1 + contarElementosRecursivo(nodo.siguiente);
    }

    /**
     * EJERCICIO DE RECURSIVIDAD 4: Eliminar una aparición específica de la cola.
     * Si encuentra el valor lo descarta ajustando los enlaces.
     * 
     * DIAGRAMA DEL PROCESO (Eliminar 20 en [10, 20, 30]):
     * =======================================================
     * eliminar(nodo: 10, prev: null)
     * |__ 10 == 20? Falso. Llama: eliminar(nodo: 20, prev: 10)
     * |__ 20 == 20? Verdadero. (Caso Base - Éxito)
     * |__ prev.siguiente = nodo.siguiente -> 10.siguiente = 30
     * |__ termina llamada
     * 
     * EL REGRESO (Desenrollando la pila de llamadas):
     * termina llamada
     * Cola final queda como: [10, 30]
     * 
     * @param nodo    El nodo actual a evaluar.
     * @param prev    El nodo previo (para asegurar el enlace).
     * @param aBorrar Valor del nodo que queremos borrar.
     */
    public void eliminarRecursivo(Nodo nodo, Nodo prev, int aBorrar) {
        if (nodo == null) {
            return;
        }

        if (nodo.dato == aBorrar) {
            if (prev != null) {
                prev.siguiente = nodo.siguiente;
                if (nodo.siguiente == null) {
                    final_de_cola = prev; // si eliminamos el de atrás
                }
            } else {
                dequeue();
                return;
            }
            return;
        }

        eliminarRecursivo(nodo.siguiente, nodo, aBorrar);
    }

    public void mostrar() {
        Nodo actual = frente;
        System.out.print("Cola: ");
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.siguiente;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        cola c = new cola();
        c.enqueue(10);
        c.enqueue(20);
        c.enqueue(30);

        System.out.println("Cola original:");
        c.mostrar();

        c.invertirRecursivo();
        System.out.println("Cola invertida recursivamente:");
        c.mostrar();

        System.out.println("Buscar 20 (recursivo): " + c.buscarElementoRecursivo(c.frente, 20));
        System.out.println("Buscar 50 (recursivo): " + c.buscarElementoRecursivo(c.frente, 50));
    }
}
