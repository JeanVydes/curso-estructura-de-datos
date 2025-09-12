package ejercicios.nallig;

public class Cola1 {
    public int[] cola;
    public int frente;
    public int fin;
    public int max;
    public int numElem;

    public Cola1(int max) {
        this.max = max;
        cola = new int[max];
        frente = 0;
        fin = -1;
        numElem = 0;
    }

    public boolean estaVacia() {
        return numElem == 0;
    }

    public boolean estaLlena() {
        return numElem == max;
    }

    public void insertar(int elem) {
        if (!estaLlena()) {
            fin = (fin + 1) % max; // Movimiento circular: si fin es max-1, vuelve a 0 gracias al módulo max%max=0
            cola[fin] = elem;
            numElem++;
        } else {
            System.out.println("Cola llena, no se puede insertar.");
        }
    }

    public int eliminar() {
        if (!estaVacia()) {
            int elem = cola[frente];
            frente = (frente + 1) % max;
            numElem--;
            return elem;
        }

        System.out.println("Cola vacía, no se puede eliminar.");
        return -1; // Indicador de error
    }

    public int numElem() {
        return numElem;
    }

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("Cola vacía.");
            return;
        }

        System.out.print("Elementos en la cola: ");

        for (int i = 0; i < numElem; i++) {
            System.out.print(cola[(frente + i) % max] + " ");
        }

        System.out.println();
    }

    public void vaciar() {
        frente = 0;
        fin = -1;
        numElem = 0;
    }
}
