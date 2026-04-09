package lista_circular;

/**
 * ⚙️ Implementación de una Lista Circular Simple.
 * * A diferencia de las listas doblemente enlazadas, esta solo tiene un puntero
 * 'siguiente', por lo que solo podemos recorrerla en una dirección.
 */
public class lista {

    /**
     * Define la estructura de cada elemento en la lista.
     * Es una clase 'interna' porque solo tiene sentido dentro del contexto de la
     * lista.
     */
    private static class Nodo {
        int dato;
        Nodo siguiente; // Solo un puntero, por eso es una lista "simple".

        public Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    // Nuestro ÚNICO punto de acceso a la lista. Si es null, la lista está vacía.
    Nodo ultimo = null;

    /**
     * Inserta un nuevo nodo justo al inicio de la lista (se convierte en la nueva
     * cabeza).
     * 
     * @param nuevoDato El dato del nodo a insertar.
     */
    public void insertarAlInicio(int nuevoDato) {
        // 1. Creamos el nuevo nodo.
        Nodo nuevoNodo = new Nodo(nuevoDato);

        // 2. Si la lista está vacía, este es el primer y único nodo.
        if (ultimo == null) {
            ultimo = nuevoNodo;
            // Para cerrar el círculo, se apunta a sí mismo.
            // .----------.
            // | [ultimo] |
            // '----------'
            ultimo.siguiente = ultimo;
        } else {
            // 3. Si ya hay nodos, lo insertamos justo después del último.

            // ANTES: (ultimo) ---> (cabeza) ---> ...
            //
            // PASO A: El 'siguiente' del nuevo nodo debe apuntar a la cabeza actual.
            // La cabeza actual es 'ultimo.siguiente'.
            //
            // (nuevoNodo) ---> (cabeza)
            nuevoNodo.siguiente = ultimo.siguiente;

            // PASO B: El 'siguiente' del último nodo ahora debe apuntar al nuevo nodo.
            // Esto convierte al nuevo nodo en la nueva cabeza.
            //
            // (ultimo) ---> (nuevoNodo) ---> (cabeza_antigua)
            ultimo.siguiente = nuevoNodo;
        }
    }

    /**
     * Inserta un nuevo nodo al final de la lista (se convierte en el nuevo
     * 'ultimo').
     * 
     * @param nuevoDato El dato del nodo a insertar.
     */
    public void insertarAlFinal(int nuevoDato) {
        Nodo nuevoNodo = new Nodo(nuevoDato);

        if (ultimo == null) {
            ultimo = nuevoNodo;
            ultimo.siguiente = ultimo;
        } else {
            // Los primeros dos pasos son idénticos a 'insertarAlInicio'
            // para colocar el nuevo nodo en el lugar correcto.
            nuevoNodo.siguiente = ultimo.siguiente;
            ultimo.siguiente = nuevoNodo;

            // PASO CLAVE: Movemos el puntero 'ultimo' para que apunte
            // a nuestro nuevo nodo. ¡Esto es lo que lo convierte en el nuevo final!
            //
            // DESPUÉS DE LOS 2 PRIMEROS PASOS: ... (ultimo_antiguo) -> (nuevoNodo) ->
            // (cabeza)
            //
            // AHORA CON EL PASO FINAL: ... (nodo) -> (nuevoNodo <- se convierte en el
            // ultimo) -> (cabeza)
            ultimo = nuevoNodo;
        }
    }

    /**
     * Elimina el primer nodo de la lista (la cabeza).
     * 
     * Pasos:
     * 1. Si la lista está vacía, no hacemos nada.
     * 2. Si solo hay un nodo, lo eliminamos (ultimo = null).
     * 3. Si hay varios, movemos la cabeza (ultimo.siguiente) al siguiente.
     */
    public void eliminarPrimero() {
        if (ultimo == null) {
            System.out.println("Error: La lista está vacía. No hay nada que eliminar.");
            return;
        }

        // Caso especial: solo hay un nodo
        if (ultimo.siguiente == ultimo) {
            System.out.println("Eliminando primer nodo (único): " + ultimo.dato);
            ultimo = null;
            return;
        }

        // Caso general: hay varios nodos
        // La cabeza es siempre ultimo.siguiente
        // ANTES: (cabeza=ultimo.siguiente) -> (nodo2) -> ... -> (ultimo)
        //
        Nodo cabezaAntigua = ultimo.siguiente;
        System.out.println("Eliminando primer nodo: " + cabezaAntigua.dato);

        // Hacemos que ultimo apunte al nodo siguiente a la cabeza antigua
        // Esto hace que la nueva cabeza sea lo que era nodo2
        ultimo.siguiente = cabezaAntigua.siguiente;
        //
        // DESPUÉS: (cabeza=nodo2) -> (nodo3) -> ... -> (ultimo)
    }

    /**
     * Elimina el último nodo de la lista.
     * 
     * Pasos:
     * 1. Si la lista está vacía, no hacemos nada.
     * 2. Si solo hay un nodo, lo eliminamos (ultimo = null).
     * 3. Si hay varios, buscamos el penúltimo y lo enlazamos a la cabeza.
     */
    public void eliminarUltimo() {
        if (ultimo == null) {
            System.out.println("Error: La lista está vacía. No hay nada que eliminar.");
            return;
        }

        // Caso especial: solo hay un nodo
        if (ultimo.siguiente == ultimo) {
            System.out.println("Eliminando último nodo (único): " + ultimo.dato);
            ultimo = null;
            return;
        }

        // Caso general: hay varios nodos
        // Buscamos el penúltimo nodo (el que apunta a ultimo)
        // ANTES: (cabeza) -> ... -> (penúltimo) -> (último) -> (cabeza)
        //
        Nodo penultimo = ultimo;
        while (penultimo.siguiente != ultimo) {
            penultimo = penultimo.siguiente;
        }

        System.out.println("Eliminando último nodo: " + ultimo.dato);

        // Hacemos que el penúltimo apunte a la cabeza (cerrando el círculo sin el
        // último)
        penultimo.siguiente = ultimo.siguiente; // o penultimo.siguiente = cabeza
        // Movemos ultimo para que sea el penúltimo
        ultimo = penultimo;
        //
        // DESPUÉS: (cabeza) -> ... -> (penúltimo/nuevo_último) -> (cabeza)
    }

    /**
     * Elimina el nodo en la posición n (1-indexed).
     * 
     * Ejemplo: Si posicion=1, elimina el primer nodo (la cabeza).
     * Si posicion=2, elimina el segundo nodo.
     * 
     * Pasos:
     * 1. Validar que la posición sea válida.
     * 2. Buscar el nodo anterior al que queremos eliminar.
     * 3. Re-enlazar para saltarse el nodo a eliminar.
     * 
     * @param posicion La posición del nodo a eliminar (1-indexed).
     */
    public void eliminarPosicion(int posicion) {
        if (ultimo == null) {
            System.out.println("Error: La lista está vacía. No hay nada que eliminar.");
            return;
        }

        // Validar que la posición sea positiva
        if (posicion < 1) {
            System.out.println("Error: La posición debe ser >= 1.");
            return;
        }

        // Caso especial: eliminar el primer nodo
        if (posicion == 1) {
            eliminarPrimero();
            return;
        }

        // Caso general: contar hasta la posición anterior a la que queremos eliminar
        Nodo anterior = ultimo.siguiente; // Comenzamos en la cabeza
        int contador = 1;

        // Buscamos el nodo anterior
        while (contador < posicion - 1 && anterior.siguiente != ultimo.siguiente) {
            anterior = anterior.siguiente;
            contador++;
        }

        Nodo nodoAEliminar = anterior.siguiente;

        // Validar que la posición existe
        // Si hemos llegado al final de la lista sin encontrar la posición
        if (contador + 1 > getLongitud()) {
            System.out.println("Error: La posición " + posicion + " está fuera de rango.");
            return;
        }

        // ANTES: ... -> (anterior) -> (nodoAEliminar) -> (siguiente) -> ...
        //
        System.out.println("Eliminando nodo en posición " + posicion + ": " + nodoAEliminar.dato);

        // Re-enlazamos saltándonos el nodo a eliminar
        anterior.siguiente = nodoAEliminar.siguiente;

        // Si el nodo a eliminar era el último, actualizar ultimo
        if (nodoAEliminar == ultimo) {
            ultimo = anterior;
        }
        //
        // DESPUÉS: ... -> (anterior) -> (siguiente) -> ...
    }

    /**
     * Método auxiliar: retorna la longitud de la lista.
     */
    private int getLongitud() {
        if (ultimo == null) {
            return 0;
        }
        Nodo temp = ultimo.siguiente;
        int contador = 1;
        while (temp != ultimo) {
            contador++;
            temp = temp.siguiente;
        }
        return contador;
    }

    /**
     * Recorre y muestra todos los elementos de la lista.
     */
    public void mostrarLista() {
        if (ultimo == null) {
            System.out.println("La lista esta vacia.");
            return;
        }

        // Para empezar a recorrer desde el inicio, comenzamos en el nodo
        // que está DESPUÉS del último, es decir, la cabeza.
        Nodo temp = ultimo.siguiente;

        // Usamos un bucle do-while para asegurar que se imprima al menos
        // una vez, incluso si solo hay un nodo en la lista.
        do {
            System.out.print(temp.dato + " -> ");
            temp = temp.siguiente;
        } while (temp != ultimo.siguiente); // El bucle se detiene cuando damos la vuelta completa.

        System.out.println("...(vuelve al inicio)");
    }

    /**
     * Método principal para probar nuestra lista.
     */
    public static void main(String[] args) {
        // 1. Creamos una instancia de la lista.
        lista lista = new lista();

        // 2. Insertamos 2 nodos al inicio.
        // Lista: (vacía)
        lista.insertarAlInicio(20); // Lista: 20
        lista.insertarAlInicio(10); // Lista: 10 -> 20

        System.out.println("La lista despues de insertar al inicio es: ");
        lista.mostrarLista(); // Debería mostrar 10 -> 20

        // 3. Insertamos 2 nodos al final.
        // Lista actual: 10 -> 20
        lista.insertarAlFinal(30); // Lista: 10 -> 20 -> 30
        lista.insertarAlFinal(40); // Lista: 10 -> 20 -> 30 -> 40

        System.out.println("\nLa lista despues de insertar al final es: ");
        lista.mostrarLista(); // Debería mostrar 10 -> 20 -> 30 -> 40

        System.out.println("\n=== PRUEBAS DE ELIMINACIÓN ===");

        // EJEMPLO 1: Eliminar el primer nodo
        System.out.println("\n1. Eliminando el primer nodo:");
        lista.eliminarPrimero();
        lista.mostrarLista();

        // EJEMPLO 2: Eliminar el último nodo
        System.out.println("\n2. Eliminando el último nodo:");
        lista.eliminarUltimo();
        lista.mostrarLista();

        // EJEMPLO 3: Eliminar en posición específica
        System.out.println("\n3. Eliminando el nodo en posición 1:");
        lista.eliminarPosicion(1);
        lista.mostrarLista();

        // EJEMPLO 4: Aclarar que la lista es circular
        System.out.println("\n4. Estado final de la lista circular:");
        lista.mostrarLista();
    }
}