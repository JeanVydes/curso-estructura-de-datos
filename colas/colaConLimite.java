package colas;

public class colaConLimite {
    private class Nodo {
        int dato;
        Nodo siguiente;

        public Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }
    
    private Nodo frente;
    private Nodo final_de_cola;
    private int limite;
    private int tamañoActual;

    public colaConLimite(int limite) {
        this.frente = null;
        this.final_de_cola = null;
        this.limite = limite;
        this.tamañoActual = 0;
    }

    public void enqueue(int nuevoDato) {
        // Verifica si la cola ha alcanzado su límite
        if (tamañoActual >= limite) {
            throw new IllegalStateException("La cola ha alcanzado su límite máximo.");
        }

        Nodo nuevoNodo = new Nodo(nuevoDato);

        if (final_de_cola == null) {
            frente = nuevoNodo;
            final_de_cola = nuevoNodo;
        } else {
            final_de_cola.siguiente = nuevoNodo;
            final_de_cola = nuevoNodo;
        }

        // Incrementa el tamaño actual de la cola
        tamañoActual++;
    }

    public int dequeue() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía, no se puede realizar 'dequeue'.");
        }

        int dato = frente.dato;
        frente = frente.siguiente;

        if (frente == null) {
            final_de_cola = null;
        }

        // Decrementa el tamaño actual de la cola
        tamañoActual--;

        return dato;
    }

    public int peek() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía, no se puede realizar 'peek'.");
        }
        return frente.dato;
    }

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("La cola está vacía.");
            return;
        }
        Nodo actual = frente;
        System.out.print("Cola: ");
        while (actual != null) {
            System.out.print(actual.dato + " ");
            actual = actual.siguiente;
        }
        System.out.println();
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public static void main(String[] args) {
        colaConLimite cola = new colaConLimite(3);

        cola.enqueue(10);
        cola.enqueue(20);
        cola.enqueue(30);

        cola.mostrar();

        // Intentar agregar un cuarto elemento debería lanzar una excepción
        try {
            cola.enqueue(40);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        // deberia seguir mostrando los 3 elementos
        cola.mostrar();
    }
}
