package ejercicios.nallig;

public class Cola2 {
    public Nodo frente;
    public Nodo fin;

    public Cola2() {
        this.frente = null;
        this.fin = null;
    }

    public boolean esVacia() {
        return frente == null;
    }

    public void insertar(int elem) {
        Nodo nuevoNodo = new Nodo(elem);
        if (esVacia()) {
            frente = nuevoNodo;
            fin = nuevoNodo;
            fin.siguiente = frente; // Hacer que el último nodo apunte al primero
        } else {
            fin.siguiente = nuevoNodo;
            fin = nuevoNodo;
            fin.siguiente = frente; // Mantener la circularidad
        }
    }

    public int eliminar() {
        if (!esVacia()) {
            int elem = frente.dato;
            frente = frente.siguiente;
            if (frente == null) {
                fin = null;
            } else {
                fin.siguiente = frente; // Mantener la circularidad
            }
            return elem;
        } else {
            System.out.println("Cola vacía, no se puede eliminar.");
            return -1; // Indicador de error
        }
    }

    public void mostrar() {
        if (esVacia()) {
            System.out.println("Cola vacía.");
            return;
        }
        System.out.print("Elementos en la cola: ");
        Nodo actual = frente;
        do {
            System.out.print(actual.dato + " ");
            actual = actual.siguiente;
        } while (actual != frente);
        System.out.println();
    }

    public int numElem() {
        int contador = 0;
        Nodo actual = frente;
        do {
            contador++;
            actual = actual.siguiente;
        } while (actual != frente);
        return contador;
    }
}
