package lista_doblemente_enlazada;

public class lista {

    // Clase interna para el nodo
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

    // La cabeza de la lista
    Nodo cabeza;

    public lista() {
        this.cabeza = null;
    }

    // Metodo para insertar un nuevo nodo al inicio de la lista
    public void insertarAlInicio(int nuevoDato) {
        // 1. Crear el nuevo nodo
        Nodo nuevoNodo = new Nodo(nuevoDato);
        
        // 2. Hacer que el siguiente del nuevo nodo apunte a la cabeza actual
        nuevoNodo.siguiente = cabeza;
        
        // 3. El anterior del nuevo nodo es NULL
        nuevoNodo.anterior = null;
        
        // 4. Si la lista no esta vacia, el anterior de la cabeza actual sera el nuevo nodo
        if (cabeza != null) {
            cabeza.anterior = nuevoNodo;
        }
        
        // 5. Mover la cabeza para que apunte al nuevo nodo
        cabeza = nuevoNodo;
    }
    
    // Metodo para insertar un nuevo nodo al final de la lista
    public void insertarAlFinal(int nuevoDato) {
        // 1. Crear el nuevo nodo
        Nodo nuevoNodo = new Nodo(nuevoDato);
        
        // 2. El siguiente del nuevo nodo es NULL porque será el último
        nuevoNodo.siguiente = null;
        
        // 3. Si la lista está vacía, el nuevo nodo es la cabeza
        if (cabeza == null) {
            nuevoNodo.anterior = null;
            cabeza = nuevoNodo;
            return;
        }

        // 4. Recorremos la lista para encontrar el último nodo
        Nodo ultimo = cabeza;
        while (ultimo.siguiente != null) {
            ultimo = ultimo.siguiente;
        }
        
        // 5. Enlazamos el ultimo nodo al nuevo nodo
        ultimo.siguiente = nuevoNodo;
        nuevoNodo.anterior = ultimo;
    }

    // Metodo para mostrar la lista hacia adelante
    public void mostrarListaAdelante() {
        Nodo nodo = cabeza;
        while (nodo != null) {
            System.out.print(nodo.dato + " <-> ");
            nodo = nodo.siguiente;
        }
        System.out.println("NULL");
    }

    public void mostrarListaAtras() {
        Nodo nodo = cabeza;
        // Primero, llegamos al final de la lista
        while (nodo != null && nodo.siguiente != null) {
            nodo = nodo.siguiente;
        }
        // Luego, recorremos la lista hacia atrás
        while (nodo != null) {
            System.out.print(nodo.dato + " <-> ");
            nodo = nodo.anterior;
        }
        System.out.println("NULL");
    }

    public void insertarEntre(int nuevoDato, int datoAnterior, int datoSiguiente) {
        // 1. Crear el nuevo nodo
        Nodo nuevoNodo = new Nodo(nuevoDato);

        // 2. Verificar que la lista no esté vacía
        if (cabeza == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        // 3. Buscar el nodo con datoAnterior
        Nodo actual = cabeza;
        while (actual != null && actual.dato != datoAnterior) {
            actual = actual.siguiente;
        }

        // 4. Si no se encuentra el nodo con datoAnterior
        if (actual == null) {
            System.out.println("No se encontró el nodo con dato anterior: " + datoAnterior);
            return;
        }

        // 5. Verificar que el siguiente del nodo encontrado sea datoSiguiente
        if (actual.siguiente == null || actual.siguiente.dato != datoSiguiente) {
            System.out.println("El nodo siguiente no coincide con el dato siguiente: " + datoSiguiente);
            return;
        }

        // 6. Insertar el nuevo nodo entre actual y actual.siguiente
        nuevoNodo.siguiente = actual.siguiente;
        nuevoNodo.anterior = actual;
        actual.siguiente.anterior = nuevoNodo;
        actual.siguiente = nuevoNodo;
    }

    public static void main(String[] args) {
        // Creamos una instancia de la lista
        lista lista = new lista();

        // Insertamos 3 nodos al inicio
        lista.insertarAlInicio(30);
        lista.insertarAlInicio(20);
        lista.insertarAlInicio(10);

        System.out.println("La lista despues de insertar al inicio es: ");
        lista.mostrarListaAdelante();

        // Insertamos 2 nodos al final
        lista.insertarAlFinal(40);
        lista.insertarAlFinal(50);

        System.out.println("\nLa lista despues de insertar al final es: ");
        lista.mostrarListaAdelante();

        System.out.println("\nLa lista en orden inverso es: ");
        lista.mostrarListaAtras();
    }
}