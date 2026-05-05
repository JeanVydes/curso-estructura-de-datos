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

        String primerCaracter = nodoActual.dato.charAt(0) + "";
        if (dato.charAt(0) + "".compareTo(primerCaracter) < 0) {
            nodoActual.izquierdo = insertarRecursivo(nodoActual.izquierdo, dato, prioridad);
        } else if (dato.charAt(0) + "".compareTo(primerCaracter) > 0) {
            nodoActual.derecho = insertarRecursivo(nodoActual.derecho, dato, prioridad);
        }

        return nodoActual; // No se permiten valores duplicados

    }
    
    public static void main(String[] args) {
        ABB arbol = new ABB();
        // 1. Construccion del árbol (dato, prioridad)
        arbol.insertar("A1", "N");
        arbol.insertar("A1", "N");

        arbol.inOrdenPrioritarioRecursivo(arbol.raiz);

        Nodo nodo = arbol.buscarRecursivo(arbol.raiz, "C10");
        if (nodo != null) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }
}