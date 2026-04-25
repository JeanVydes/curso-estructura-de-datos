package ejercicios.lubo.taller_arboles;

public class PasoPasoInsertar {
    static class Nodo {
        int id_juego;
        Nodo izquierdo;
        Nodo derecho;

        public Nodo(int id_juego) {
            this.id_juego = id_juego;
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    Nodo raiz;
    int paso = 1;

    public PasoPasoInsertar() {
        this.raiz = null;
    }

    public void insertar(int valor) {
        System.out.println("Paso " + paso + ": Insertando " + valor);
        raiz = insertarRecursivo(raiz, valor);
        printTree();
        System.out.println("--------------------------------------------------");
        paso++;
    }

    private Nodo insertarRecursivo(Nodo nodoActual, int valor) {
        if (nodoActual == null) {
            return new Nodo(valor);
        }

        if (valor < nodoActual.id_juego) {
            nodoActual.izquierdo = insertarRecursivo(nodoActual.izquierdo, valor);
        } else if (valor > nodoActual.id_juego) {
            nodoActual.derecho = insertarRecursivo(nodoActual.derecho, valor);
        }

        return nodoActual;
    }

    public void printTree() {
        System.out.println("Árbol Binario de Búsqueda:");
        printTreeRecursive(raiz, "", false);
    }

    private void printTreeRecursive(Nodo node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "├──> " : "└──< ") + node.id_juego);
            printTreeRecursive(node.derecho, prefix + (isLeft ? "│   " : "    "), true);
            printTreeRecursive(node.izquierdo, prefix + (isLeft ? "│   " : "    "), false);
        }
    }

    public static void main(String[] args) {
        PasoPasoInsertar arbol = new PasoPasoInsertar();
        // 1. Construccion del árbol
        arbol.insertar(60);
        arbol.insertar(30);
        arbol.insertar(90);
        arbol.insertar(20);
        arbol.insertar(50);
        arbol.insertar(80);
        arbol.insertar(100);
        arbol.insertar(10);
        arbol.insertar(25);
        arbol.insertar(35);
        arbol.insertar(55);
    }
}
