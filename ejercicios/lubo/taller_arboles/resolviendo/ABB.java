package ejercicios.lubo.taller_arboles.resolviendo;

public class ABB {
    static class Nodo {
        String dato;
        String prioridad; // N = normal | P = prioridad
        Nodo izquierdo;
        Nodo derecho;

        public Nodo(String dato, String prioridad) {
            this.dato = dato;
            this.prioridad = prioridad;
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    Nodo raiz;

    public ABB() {
        this.raiz = null;
    }

    private void inOrdenPrioritarioRecursivo(Nodo nodo) {
        if (nodo == null) {
            return;
        }

        inOrdenPrioritarioRecursivo(nodo.izquierdo);

        if (nodo.prioridad.equals("N")) {
            System.out.println(nodo.dato);
        }

        inOrdenPrioritarioRecursivo(nodo.derecho);
    }

    private int contarNodosNormales(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }

        int valorDeEsteNodo = 0;
        if (nodo.prioridad.equals("N")) {
            valorDeEsteNodo = 1;
        }

        int count = valorDeEsteNodo;
        count += contarNodosNormales(nodo.izquierdo);
        count += contarNodosNormales(nodo.derecho);

        return count;
    }

    private Nodo buscarRecursivo(Nodo nodoActual, String valor) {
        if (nodoActual == null || nodoActual.dato.equals(valor)) {
            return nodoActual;
        }

        if (valor.compareTo(nodoActual.dato) < 0) {
            return buscarRecursivo(nodoActual.izquierdo, valor);
        } else {
            return buscarRecursivo(nodoActual.derecho, valor);
        }
    }

    public void insertar(String dato, String prioridad) {
        raiz = insertarRecursivo(raiz, dato, prioridad);
    }

    private Nodo insertarRecursivo(Nodo nodoActual, String dato, String prioridad) {
        if (nodoActual == null) {
            return new Nodo(dato, prioridad);
        }

        // Comparamos toda la cadena de texto
        // No solo el primer digito
        // Se trata en conjunto, como una sola unidad
        // Como una suma de todas sus partes
        if (dato.compareTo(nodoActual.dato) < 0) {
            nodoActual.izquierdo = insertarRecursivo(nodoActual.izquierdo, dato, prioridad);
        } else if (dato.compareTo(nodoActual.dato) > 0) {
            nodoActual.derecho = insertarRecursivo(nodoActual.derecho, dato, prioridad);
        }

        return nodoActual; // No se permiten valores duplicados

    }

    public static void main(String[] args) {
        ABB arbol = new ABB();
        // 1. Construccion del árbol (dato, prioridad)
        arbol.insertar("A1", "N");
        // ESTE NODO NO SE VA A INSERTAR, PORQUE YA EXISTE UN NODO CON EL DATO "A1"
        arbol.insertar("A1", "N");

        // Algunos pensarian que este nodo NO SE va a insertar,
        // Pero si lo hara, no es correcto pensar que solamente porque comienza con
        // A ya existe, porque el dato completo es "A10" y no "A1"
        // Esto se llama lexiografia
        // comparamos A con A, 1 con 1, y luego 0 con nada, y como 0 es mayor que nada,
        // entonces "A10" es mayor que "A1"
        arbol.insertar("A10", "N");

        // Caso del taller
        arbol.insertar("C150", "N");
        // Este se convertira en el hijo izquierdo de C150, porque "C15" es menor que
        // "C150"
        // La solucion para el caso del taller, era primero eliminar el nodo "C150" y
        // luego insertar "C15" (solo queriamos insertar el C15)
        arbol.insertar("C15", "N");

        // y para los que se pregunta, para que esa logica funcione
        // se deberia comparar unicamente el primer digito,
        // pero en las funciones que les dieron, se compara toda la cadena
        // primero tenian que leer como funcionaba la funcion de insercion para poder
        // entender como funcionaba especificamente

        arbol.inOrdenPrioritarioRecursivo(arbol.raiz);

        Nodo nodo = arbol.buscarRecursivo(arbol.raiz, "A10");
        if (nodo != null) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }
}