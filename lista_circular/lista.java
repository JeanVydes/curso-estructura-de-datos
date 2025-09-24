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
    }
}