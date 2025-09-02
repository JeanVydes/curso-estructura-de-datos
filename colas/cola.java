package colas;

public class cola {
    // Clase interna para el nodo
    private static class Nodo {
        int dato;
        Nodo siguiente;

        public Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo frente; // Puntero al frente de la cola
    private Nodo final_de_cola; // Puntero al final de la cola

    public cola() {
        this.frente = null;
        this.final_de_cola = null;
    }

    // Operación enqueue: añadir un elemento al final
    public void enqueue(int nuevoDato) {
        Nodo nuevoNodo = new Nodo(nuevoDato);

        if (final_de_cola == null) {
            frente = nuevoNodo;
            final_de_cola = nuevoNodo;
        } else {
            final_de_cola.siguiente = nuevoNodo;
            final_de_cola = nuevoNodo;
        }
        System.out.println("Enqueue: " + nuevoDato);
    }

    // Operación dequeue: eliminar el elemento del frente
    public int dequeue() {
        if (estaVacia()) {
            System.out.println("Error: La cola está vacia.");
            return -1;
        }

        int dato = frente.dato;
        frente = frente.siguiente;

        if (frente == null) {
            final_de_cola = null;
        }

        System.out.println("Dequeue: " + dato);
        return dato;
    }

    // Operación peek: ver el elemento del frente sin eliminarlo
    public int peek() {
        if (estaVacia()) {
            System.out.println("La cola está vacia.");
            return -1;
        }
        return frente.dato;
    }

    // Verificar si la cola está vacía
    public boolean estaVacia() {
        return frente == null;
    }

    public static void main(String[] args) {
        cola cola = new cola();

        cola.enqueue(10);
        cola.enqueue(20);
        cola.enqueue(30);

        System.out.println("Frente de la cola: " + cola.peek());

        cola.dequeue();
        cola.dequeue();

        System.out.println("Frente de la cola: " + cola.peek());
    }
}
