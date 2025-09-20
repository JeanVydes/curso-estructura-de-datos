package lista_doblemente_enlazada;

/**
 * 🔗 Implementación de una Lista Doblemente Enlazada.
 * A diferencia de las listas circulares, esta tiene un inicio ('cabeza') y un
 * final bien definidos que apuntan a 'null'.
 *
 * Su gran ventaja es que cada nodo conoce a su vecino de adelante ('siguiente')
 * y al de atrás ('anterior'), lo que nos permite recorrer la lista en AMBAS
 * direcciones con facilidad.
 */
public class lista {

    /**
     * Define la estructura de cada "eslabón" de nuestra cadena.
     * Contiene el dato y los dos punteros que lo enlazan.
     */
    private static class Nodo {
        // el dato que queremos guardar
        int dato;
        // el apuntador en memoria al nodo anterior
        Nodo anterior;
        // el apuntador en memoria al nodo siguiente
        Nodo siguiente;

        public Nodo(int dato) {
            this.dato = dato;
            this.anterior = null;
            this.siguiente = null;
        }
    }

    // La 'cabeza' es el punto de inicio de nuestra lista. Si es null, la lista está vacía.
    Nodo cabeza;

    public lista() {
        this.cabeza = null;
    }

    /**
     * Inserta un nuevo nodo al inicio de la lista.
     * @param nuevoDato El dato del nodo a insertar.
     */
    public void insertarAlInicio(int nuevoDato) {
        // 1. Creamos el nuevo nodo.
        Nodo nuevoNodo = new Nodo(nuevoDato);
        
        // ANTES: [cabeza] <=> [nodo2] <=> ...
        //
        // 2. El 'siguiente' del nuevo nodo debe apuntar a la que era la cabeza.
        //    [nuevoNodo] --> [cabeza]
        nuevoNodo.siguiente = cabeza;
        
        // 3. El 'anterior' del nuevo nodo siempre será NULL porque es el nuevo inicio.
        nuevoNodo.anterior = null;
        
        // 4. Si la lista no estaba vacía, el 'anterior' de la antigua cabeza
        //    ahora debe apuntar al nuevo nodo.
        //    [nuevoNodo] <--> [cabeza_antigua]
        if (cabeza != null) {
            cabeza.anterior = nuevoNodo;
        }
        
        // 5. Finalmente, movemos el puntero 'cabeza' para que apunte al nuevo nodo.
        // DESPUÉS: [cabeza es nuevoNodo] <=> [nodo2] <=> ...
        cabeza = nuevoNodo;
    }
    
    /**
     * Inserta un nuevo nodo al final de la lista.
     * @param nuevoDato El dato del nodo a insertar.
     */
    public void insertarAlFinal(int nuevoDato) {
        Nodo nuevoNodo = new Nodo(nuevoDato);
        
        // El 'siguiente' del nuevo nodo siempre es NULL porque será el último.
        nuevoNodo.siguiente = null;
        
        // Si la lista está vacía, el nuevo nodo se convierte en la cabeza.
        if (cabeza == null) {
            nuevoNodo.anterior = null;
            cabeza = nuevoNodo;
            return;
        }

        // 🧠 Para insertar al final, primero debemos llegar hasta allí.
        // Recorremos la lista hasta que encontremos el nodo cuyo 'siguiente' es NULL.
        Nodo ultimo = cabeza;
        while (ultimo.siguiente != null) {
            ultimo = ultimo.siguiente;
        }
        
        // Ahora que 'ultimo' es el último nodo, lo enlazamos con el nuevo.
        // ANTES: ... <=> [ultimo] -> NULL
        //
        // 1. El 'siguiente' del último nodo ahora es el nuevo nodo.
        ultimo.siguiente = nuevoNodo;
        // 2. El 'anterior' del nuevo nodo es el que era el último.
        nuevoNodo.anterior = ultimo;
        //
        // DESPUÉS: ... <=> [ultimo] <=> [nuevoNodo] -> NULL
    }

    /**
     * Muestra la lista desde la cabeza hasta el final.
     */
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
     * Muestra la lista desde el final hasta la cabeza.
     * ¡Aquí se ve el poder de la lista doblemente enlazada!
     */
    public void mostrarListaAtras() {
        Nodo nodo = cabeza;
        System.out.print("Lista Hacia Atras: ");
        
        // 1. Primero, debemos llegar hasta el final de la lista.
        if (nodo == null) {
            System.out.println("NULL (lista vacía)");
            return;
        }
        while (nodo.siguiente != null) {
            nodo = nodo.siguiente;
        }
        
        // 2. Ahora que estamos en el último nodo, recorremos hacia atrás
        //    usando el puntero 'anterior'.
        while (nodo != null) {
            System.out.print(nodo.dato + " <-> ");
            nodo = nodo.anterior; // Retrocedemos con el puntero 'anterior'.
        }
        System.out.println("NULL");
    }

    /**
     * Inserta un nodo entre otros dos nodos existentes.
     * @param nuevoDato El dato del nodo a insertar.
     * @param datoAnterior El dato del nodo que estará ANTES del nuevo.
     * @param datoSiguiente El dato del nodo que estará DESPUÉS del nuevo.
     */
    public void insertarEntre(int nuevoDato, int datoAnterior, int datoSiguiente) {
        Nodo nuevoNodo = new Nodo(nuevoDato);

        if (cabeza == null) {
            System.out.println("No se puede insertar, la lista está vacía.");
            return;
        }

        // 1. Buscamos el nodo que contendrá el 'datoAnterior'.
        Nodo actual = cabeza;
        while (actual != null && actual.dato != datoAnterior) {
            actual = actual.siguiente;
        }

        // 2. Validaciones de seguridad.
        if (actual == null) {
            System.out.println("Error: No se encontró el nodo con el dato anterior: " + datoAnterior);
            return;
        }
        if (actual.siguiente == null || actual.siguiente.dato != datoSiguiente) {
            System.out.println("Error: El nodo siguiente a [" + datoAnterior + "] no es [" + datoSiguiente + "].");
            return;
        }
        
        // 3. Re-enlazamos los 4 punteros necesarios.
        //
        // ANTES: ... <=> [actual] <=> [siguienteDeActual] <=> ...
        //
        // PASO A: El 'siguiente' del nuevo nodo apunta al siguiente de 'actual'.
        nuevoNodo.siguiente = actual.siguiente;
        // PASO B: El 'anterior' del nuevo nodo apunta a 'actual'.
        nuevoNodo.anterior = actual;
        // PASO C: El 'anterior' del nodo que era el siguiente ahora apunta al nuevo nodo.
        actual.siguiente.anterior = nuevoNodo;
        // PASO D: El 'siguiente' de 'actual' ahora apunta al nuevo nodo.
        actual.siguiente = nuevoNodo;
        //
        // DESPUÉS: ... <=> [actual] <=> [nuevoNodo] <=> [siguienteDeActual] <=> ...
        System.out.println("\nInsertando [" + nuevoDato + "] entre [" + datoAnterior + "] y [" + datoSiguiente + "]...");
    }

    public static void main(String[] args) {
        lista lista = new lista();

        // Construimos la lista inicial.
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(40);
        lista.insertarAlFinal(50);
        
        System.out.println("La lista inicial es: ");
        lista.mostrarListaAdelante(); // 10 <-> 20 <-> 40 <-> 50 <-> NULL

        // Probamos la inserción "entre".
        lista.insertarEntre(30, 20, 40);

        System.out.println("\nLa lista después de insertar [30] es: ");
        lista.mostrarListaAdelante(); // 10 <-> 20 <-> 30 <-> 40 <-> 50 <-> NULL
        
        System.out.println("\nLa lista final en orden inverso es: ");
        lista.mostrarListaAtras(); // 50 <-> 40 <-> 30 <-> 20 <-> 10 <-> NULL
    }
}