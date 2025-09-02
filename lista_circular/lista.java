package lista_circular;

public class lista {

    // Clase interna para el nodo
    private static class Nodo {
        int dato;
        Nodo siguiente;

        public Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    // El ultimo nodo de la lista
    Nodo ultimo;

    public lista() {
        this.ultimo = null;
    }

    // Metodo para insertar un nuevo nodo al inicio de la lista circular
    public void insertarAlInicio(int nuevoDato) {
        // 1. Crear el nuevo nodo
        Nodo nuevoNodo = new Nodo(nuevoDato);
        
        // 2. Si la lista esta vacia
        if (ultimo == null) {
            // el nuevo nodo se apunta a si mismo
            ultimo = nuevoNodo;
            ultimo.siguiente = ultimo;
        } else {
            // 3. El nuevo nodo apunta a la cabeza actual
            nuevoNodo.siguiente = ultimo.siguiente;
            // 4. El ultimo nodo apunta al nuevo nodo
            ultimo.siguiente = nuevoNodo;
        }
    }

    // Metodo para insertar un nuevo nodo al final de la lista circular
    public void insertarAlFinal(int nuevoDato) {
        // 1. Crear el nuevo nodo
        Nodo nuevoNodo = new Nodo(nuevoDato);
        
        // 2. Si la lista esta vacia
        if (ultimo == null) {
            // el nuevo nodo se apunta a si mismo
            ultimo = nuevoNodo;
            ultimo.siguiente = ultimo;
        } else {
            // 3. El nuevo nodo apunta a la cabeza actual
            nuevoNodo.siguiente = ultimo.siguiente;
            // 4. El ultimo nodo apunta al nuevo nodo
            ultimo.siguiente = nuevoNodo;
            // 5. El ultimo ahora es el nuevo nodo
            ultimo = nuevoNodo;
        }
    }

    // Metodo para mostrar la lista circular
    public void mostrarLista() {
        if (ultimo == null) {
            System.out.println("La lista esta vacia.");
            return;
        }
        
        Nodo temp = ultimo.siguiente;
        do {
            System.out.print(temp.dato + " -> ");
            temp = temp.siguiente;
        } while (temp != ultimo.siguiente);
        
        System.out.println("...");
    }

    public static void main(String[] args) {
        // Creamos una instancia de la lista
        lista lista = new lista();

        // Insertamos 2 nodos al inicio
        lista.insertarAlInicio(20);
        lista.insertarAlInicio(10);

        System.out.println("La lista despues de insertar al inicio es: ");
        lista.mostrarLista();
        
        // Insertamos 2 nodos al final
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);

        System.out.println("\nLa lista despues de insertar al final es: ");
        lista.mostrarLista();
    }
}