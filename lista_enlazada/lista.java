package lista_enlazada;

public class lista {

    // Clase interna para el nodo
    // Un nodo es el bloque de construccion de la lista enlazada
    private static class Nodo {
        // el dato que queremos guardar
        int dato;
        // el apuntador en memoria al siguiente nodo
        Nodo siguiente;

        // Constructor del nodo
        public Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    // La cabeza de la lista, el punto de partida
    Nodo cabeza;

    // Constructor de la clase ListaEnlazada
    public lista() {
        // al inicio no hay nodos, entonces la cabeza es nula
        this.cabeza = null;
    }

    // Metodo para insertar un nuevo nodo al inicio de la lista
    public void insertarAlInicio(int nuevoDato) {
        // 1. Crear el nuevo nodo
        Nodo nuevoNodo = new Nodo(nuevoDato);
        
        // 2. Hacer que el siguiente del nuevo nodo apunte a la cabeza actual
        nuevoNodo.siguiente = cabeza;
        
        // 3. Mover la cabeza para que apunte al nuevo nodo
        cabeza = nuevoNodo;
    }
    
    // Metodo para insertar un nuevo nodo al final de la lista
    public void insertarAlFinal(int nuevoDato) {
        // 1. Crear el nuevo nodo
        Nodo nuevoNodo = new Nodo(nuevoDato);

        // 2. Si la lista está vacía, el nuevo nodo es la cabeza
        if (cabeza == null) {
            cabeza = nuevoNodo;
            return;
        }
        
        // 3. Si no, recorremos la lista para encontrar el último nodo
        Nodo ultimo = cabeza;
        while (ultimo.siguiente != null) {
            ultimo = ultimo.siguiente;
        }
        
        // 4. Enlazamos el ultimo nodo al nuevo nodo
        ultimo.siguiente = nuevoNodo;
    }

    // Insertar un nodo despues de cualquier nodo que sea menor que una condicion
    // ejemplo, insertar el 40 despues del primer nodo que sea mayor que 30
    public void insertarDespuesDeCualquierNodoQueSeaMayorQue(int nuevoDato, int condicion) {
        // 1. Verificar que el nodo anterior no sea nulo
        if (cabeza == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        // 2. Buscar el nodo que cumple con la condición
        Nodo nodoActual = cabeza;
        while (nodoActual != null) {
            if (nodoActual.dato >= condicion) {
                // 3. Si lo encontramos, insertamos el nuevo nodo después de él
                Nodo nuevoNodo = new Nodo(nuevoDato);
                nuevoNodo.siguiente = nodoActual.siguiente;
                nodoActual.siguiente = nuevoNodo;
                return;
            }
            nodoActual = nodoActual.siguiente;
        }

        System.out.println("No se encontró ningún nodo que cumpla con la condición.");
    }

    // Vamos a insertar un nodo despues de los tres primeros nodos, si existen
    public void insertarDespuesDeLosTresPrimerosNodos(int nuevoDato) {
        // 1. Verificar que la lista tenga al menos tres nodos
        if (cabeza == null || cabeza.siguiente == null || cabeza.siguiente.siguiente == null) {
            System.out.println("La lista no tiene suficientes nodos.");
            return;
        }

        // 2. Encontrar el tercer nodo
        Nodo tercerNodo = cabeza.siguiente.siguiente;

        // 3. Insertar el nuevo nodo después del tercer nodo
        Nodo nuevoNodo = new Nodo(nuevoDato);
        nuevoNodo.siguiente = tercerNodo.siguiente;
        tercerNodo.siguiente = nuevoNodo;
    }

    // Metodo para mostrar la lista
    public void mostrarLista() {
        // creamos una variable temporal para recorrer la lista, que comience en la cabeza
        Nodo nodo = cabeza;
        while (nodo != null) {
            // mostramos el dato del nodo actual
            System.out.print(nodo.dato + " -> ");
            // avanzamos al siguiente nodo, reasignando la variable local como el siguiente
            // cuando el nodo es 'null', el bucle acabara
            nodo = nodo.siguiente;
        }
        // mostramos null para demostracion
        System.out.println("NULL");
    }

    public static void main(String[] args) {
        // Creamos una instancia de la lista
        lista lista = new lista();

        // Insertamos 3 nodos al inicio
        lista.insertarAlInicio(30); // La lista es ahora: 30 -> NULL
        lista.insertarAlInicio(20); // La lista es ahora: 20 -> 30 -> NULL
        lista.insertarAlInicio(10); // La lista es ahora: 10 -> 20 -> 30 -> NULL
        
        System.out.println("La lista enlazada despues de insertar al inicio es: ");
        lista.mostrarLista(); // Deberia mostrar: 10 -> 20 -> 30 -> NULL

        // Insertamos nodos al final
        lista.insertarAlFinal(40); // La lista es ahora: 10 -> 20 -> 30 -> 40 -> NULL
        lista.insertarAlFinal(50); // La lista es ahora: 10 -> 20 -> 30 -> 40 -> 50 -> NULL

        System.out.println("\nLa lista despues de insertar al final es:");
        lista.mostrarLista(); // Deberia mostrar: 10 -> 20 -> 30 -> 40 -> 50 -> NULL

        // ahora insertamos un nodo despues del primer nodo que sea mayor que 30
        lista.insertarDespuesDeCualquierNodoQueSeaMayorQue(35, 30); // La lista es ahora: 10 -> 20 -> 30 -> 35 ->
        System.out.println("\nLa lista despues de insertar despues del primer nodo que sea mayor que 30 es:");
        lista.mostrarLista();
    

        // ahora insertamos un nodo despues de los tres primeros nodos
        lista.insertarDespuesDeLosTresPrimerosNodos(32); // La lista
        System.out.println("\nLa lista despues de insertar despues de los tres primeros nodos es:");
        lista.mostrarLista(); // Deberia mostrar: 10 -> 20 -> 30 -> 32 -> 35 -> 40 -> 50 -> NULL
    }
}
