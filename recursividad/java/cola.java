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
     * Las colas son estructuras FIFO (First In, First Out - Primero en entrar,
     * primero en salir).
     * Si extraemos elementos (dequeue) y los volvemos a insertar (enqueue)
     * inmediatamente,
     * el orden seguirá siendo exactamente el mismo.
     * 
     * Sin embargo, con ayuda de la recursión, podemos extraer un elemento, esperar
     * a que
     * se extraigan e inserten todos los demás, y al final insertar este elemento,
     * haciendo
     * que mágicamente su posición se invierta.
     * 
     * DIAGRAMA DEL PROCESO (Cola: [10, 20, 30]):
     * =======================================================
     * LLAMADA 1: invertirRecursivo() en Cola: Frente[10, 20, 30]Fondo
     * dato = dequeue() -> Sacamos 10. Cola queda [20, 30].
     * LLAMADA 2: invertirRecursivo()
     * dato = dequeue() -> Sacamos 20. Cola queda [30].
     * LLAMADA 3: invertirRecursivo()
     * dato = dequeue() -> Sacamos 30. Cola queda [].
     * LLAMADA 4 (Caso base): Cola vacía. Termina.
     * <- FIN LLAMADA 3: enqueue(dato) -> enqueue(30). Cola: Frente[30]Fondo
     * <- FIN LLAMADA 2: enqueue(dato) -> enqueue(20). Cola: Frente[30, 20]Fondo
     * <- FIN LLAMADA 1: enqueue(dato) -> enqueue(10). Cola: Frente[30, 20, 10]Fondo
     * =======================================================
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
     * La idea es preguntarle directamente al nodo actual si es el elemento que
     * buscamos.
     * - Si lo es, ¡bingo, ya lo encontramos!
     * - Si no lo es, pasamos la búsqueda a nuestro vecino ("¿Eres el dato?").
     * - Si llegamos al final (null), entonces no existía.
     * 
     * EJEMPLO DE BÚSQUEDA DEL "20" EN LA COLA [10, 20, 30]:
     * buscar(nodo=10, 20) -> (10 == 20? No) -> retorna buscar(nodo=20, 20)
     * buscar(nodo=20, 20) -> (20 == 20? SÍ) -> retorna true!
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
