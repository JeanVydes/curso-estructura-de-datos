package pilas;

public class pila {
    // Definición de la clase interna para representar un nodo en la pila. 
    // Un nodo es la unidad fundamental de la pila.
    private static class Nodo {
        int dato;      // Almacena el valor del nodo.
        Nodo siguiente; // Puntero al siguiente nodo en la secuencia, o sea, al nodo de "abajo".

        public Nodo(int dato) {
            this.dato = dato;
            // Cuando se crea un nuevo nodo, 'siguiente' es inicialmente 'null'.
            this.siguiente = null;
        }
    }

    // Puntero principal de la pila. Siempre apunta al elemento más alto.
    private Nodo cima;

    // Constructor de la pila.
    // Inicializa la pila como vacía, sin ningún elemento.
    public pila() {
        this.cima = null;
    }

    // Operación **push**: añade un elemento en la parte superior de la pila.
    // Es una operación muy eficiente con un tiempo de ejecución constante, O(1).
    public void push(int nuevoDato) {
        // 1. Crea un nuevo nodo con el dato que se va a agregar.
        Nodo nuevoNodo = new Nodo(nuevoDato);
        
        // 2. Hace que el nuevo nodo apunte al nodo que actualmente está en la cima.
        // Esto lo enlaza con el resto de la pila.
        nuevoNodo.siguiente = cima;
        
        // 3. Actualiza la cima para que ahora apunte al nuevo nodo.
        // Esto lo convierte en el nuevo elemento superior.
        this.cima = nuevoNodo;
        
        System.out.println("Push: " + nuevoDato);
    }

    // Operación **pop**: elimina y devuelve el elemento superior de la pila.
    // También es una operación de tiempo constante, O(1).
    public int pop() {
        // 1. Verifica si la pila está vacía antes de intentar eliminar un elemento.
        if (estaVacia()) {
            System.out.println("Error: La pila esta vacia.");
            return -1; // Devuelve un valor de error.
        }
        
        // 2. Guarda el dato del nodo de la cima para poder devolverlo más tarde.
        int dato = cima.dato;
        
        // 3. Mueve la cima al siguiente nodo.
        // El recolector de basura de Java se encargará del nodo que ya no tiene referencias.
        this.cima = cima.siguiente;
        
        System.out.println("Pop: " + dato);
        
        // 4. Devuelve el dato del elemento que fue eliminado.
        return dato;
    }

    // Operación **peek**: permite ver el elemento superior sin eliminarlo.
    public int peek() {
        if (estaVacia()) {
            System.out.println("La pila esta vacia.");
            return -1; // Devuelve un valor de error.
        }
        // Devuelve el dato del nodo que está en la cima.
        return cima.dato;
    }

    // Verifica si la pila está vacía.
    // La pila está vacía si el puntero 'cima' no apunta a nada.
    public boolean estaVacia() {
        return cima == null;
    }

    // Método principal para probar la funcionalidad de la pila.
    public static void main(String[] args) {
        // 1. Crea una nueva instancia de la clase 'pila'.
        pila pila = new pila();

        // 2. Agrega elementos a la pila.
        pila.push(10); // 10 está en el fondo
        pila.push(20); // 20 está sobre el 10
        pila.push(30); // 30 está en la cima

        // 3. Muestra el elemento de la cima.
        System.out.println("Cima de la pila: " + pila.peek()); // Salida: 30

        // 4. Elimina los dos elementos superiores.
        pila.pop(); // Elimina 30
        pila.pop(); // Elimina 20

        // 5. Muestra el nuevo elemento de la cima.
        System.out.println("Cima de la pila: " + pila.peek()); // Salida: 10
    }
}