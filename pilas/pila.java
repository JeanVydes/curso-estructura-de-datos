package pilas;

public class pila {
    // Clase interna para el nodo
    private static class Nodo {
        int dato;
        Nodo siguiente;

        public Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    // El puntero a la parte superior de la pila
    private Nodo cima;

    public pila() {
        this.cima = null;
    }

    // Operacion push: anadir un elemento
    public void push(int nuevoDato) {
        Nodo nuevoNodo = new Nodo(nuevoDato);
        nuevoNodo.siguiente = cima;
        cima = nuevoNodo;
        System.out.println("Push: " + nuevoDato);
    }

    // Operacion pop: eliminar el elemento superior
    public int pop() {
        if (estaVacia()) {
            System.out.println("Error: La pila esta vacia.");
            return -1; // Valor de error
        }
        int dato = cima.dato;
        cima = cima.siguiente;
        System.out.println("Pop: " + dato);
        return dato;
    }

    // Operacion peek: ver el elemento superior sin eliminarlo
    public int peek() {
        if (estaVacia()) {
            System.out.println("La pila esta vacia.");
            return -1; // Valor de error
        }
        return cima.dato;
    }

    // Verificar si la pila esta vacia
    public boolean estaVacia() {
        return cima == null;
    }

    public static void main(String[] args) {
        pila pila = new pila();

        pila.push(10);
        pila.push(20);
        pila.push(30);

        System.out.println("Cima de la pila: " + pila.peek());

        pila.pop();
        pila.pop();

        System.out.println("Cima de la pila: " + pila.peek());
    }
}
