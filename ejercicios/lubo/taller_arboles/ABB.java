package ejercicios.lubo.taller_arboles;

public class ABB {
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

    public ABB() {
        this.raiz = null;
    }

    /*
     * Expansión recursiva (InOrden) con ESTE árbol:
     *
     * 60
     * / \
     *  30  90
     * / \ / \
     *  20 50 80 100
     *  / \    / \
     * 10 25 35 55
     *
     * Regla InOrden:
     * inOrden(nodo) = inOrden(izquierdo) -> visitar nodo -> inOrden(derecho)
     * Caso base: inOrden(null) -> retorna
     *
     * Llamada inicial: inOrden(60)
     * Expansión principal:
     * inOrden(60)
     * ├── inOrden(30)
     * │ ├── inOrden(20)
     * │ │  ├── inOrden(10) -> print(10)
     * │ │  ├── print(20)
     * │ │  └── inOrden(25) -> print(25)
     * │ ├── print(30)
     * │ └── inOrden(50)
     * │    ├── inOrden(35) -> print(35)
     * │    ├── print(50)
     * │    └── inOrden(55) -> print(55)
     * ├── print(60)
     * └── inOrden(90)
     *    ├── inOrden(80) -> print(80)
     *    ├── print(90)
     *    └── inOrden(100) -> print(100)
     *
     * Salida final InOrden:
     * 10 20 25 30 35 50 55 60 80 90 100
     */
    private void inOrdenRecursivo(Nodo nodo) {
        if (nodo != null) {
            inOrdenRecursivo(nodo.izquierdo);
            System.out.print(nodo.id_juego + " ");
            inOrdenRecursivo(nodo.derecho);
        }
    }

    private void preOrdenRecursivo(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.id_juego + " ");
            preOrdenRecursivo(nodo.izquierdo);
            preOrdenRecursivo(nodo.derecho);
        }
    }

    private void postOrdenRecursivo(Nodo nodo) {
        if (nodo != null) {
            postOrdenRecursivo(nodo.izquierdo);
            postOrdenRecursivo(nodo.derecho);
            System.out.print(nodo.id_juego + " ");
        }
    }

    private int encontrarMinimo(Nodo nodo) {
        return nodo.izquierdo == null ? nodo.id_juego : encontrarMinimo(nodo.izquierdo);
    }

    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

    public void eliminar(int valor) {
        raiz = eliminarRecursivo(raiz, valor);
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

    private Nodo eliminarRecursivo(Nodo nodoActual, int valor) {
        if (nodoActual == null) {
            return null;
        }

        if (valor < nodoActual.id_juego) {
            nodoActual.izquierdo = eliminarRecursivo(nodoActual.izquierdo, valor);
        } else if (valor > nodoActual.id_juego) {
            nodoActual.derecho = eliminarRecursivo(nodoActual.derecho, valor);
        } else {
            if (nodoActual.izquierdo == null && nodoActual.derecho == null) {
                return null;
            }

            if (nodoActual.derecho == null) {
                return nodoActual.izquierdo;
            }
            if (nodoActual.izquierdo == null) {
                return nodoActual.derecho;
            }

            int sucesor = encontrarMinimo(nodoActual.derecho);
            nodoActual.id_juego = sucesor;
            nodoActual.derecho = eliminarRecursivo(nodoActual.derecho, sucesor);
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

    // Resolviendo el taller

    public Nodo buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private Nodo buscarRecursivo(Nodo nodoActual, int valor) {
        if (nodoActual == null || nodoActual.id_juego == valor) {
            System.out.println("Valor " + valor + " encontrado en el nodo con id_juego: "
                    + (nodoActual != null ? nodoActual.id_juego : "null") + ".");
            return nodoActual;
        }

        if (valor < nodoActual.id_juego) {
            System.out.println(
                    "Valor " + valor + " es menor que " + nodoActual.id_juego + ", buscando en el subárbol izquierdo.");
            return buscarRecursivo(nodoActual.izquierdo, valor);
        } else {
            System.out.println(
                    "Valor " + valor + " es mayor que " + nodoActual.id_juego + ", buscando en el subárbol derecho.");
            return buscarRecursivo(nodoActual.derecho, valor);
        }
    }

    /*
     * Basada en aristas vs basada en nodos
     * 
     * Si es basada en aristas, la raiz cuenta como 0 y un arbol vacio tiene altura
     * -1, si es basada en nodos, la raiz cuenta como 1 y un arbol vacio tiene
     * altura 0
     */

    private int altura(Nodo nodo) {
        if (nodo == null) {
            return -1; // Altura de un nodo nulo es -1
        }
        int alturaIzquierda = altura(nodo.izquierdo);
        int alturaDerecha = altura(nodo.derecho);
        return 1 + Math.max(alturaIzquierda, alturaDerecha);
    }

    private int contarNodos(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodos(nodo.izquierdo) + contarNodos(nodo.derecho);
    }

    private int contarHojas(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        if (nodo.izquierdo == null && nodo.derecho == null) {
            return 1; // Es una hoja
        }
        return contarHojas(nodo.izquierdo) + contarHojas(nodo.derecho);
    }

    public static void main(String[] args) {
        ABB arbol = new ABB();
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

        // 2. Dibujar Arbol

        arbol.printTree();
        System.out.println("\n\n");

        // 3. Recorridos
        System.out.println("Recorrido InOrden:");
        arbol.inOrdenRecursivo(arbol.raiz);
        System.out.println("\nRecorrido PreOrden:");
        arbol.preOrdenRecursivo(arbol.raiz);
        System.out.println("\nRecorrido PostOrden:");
        arbol.postOrdenRecursivo(arbol.raiz);

        System.out.println("\n\n");

        // 4. Eliminar nodo con un hijo
        // Como ya dibujamos el arbol,
        // Sabemos que nodo ees hoja y que nodo tiene un hijo
        // y que nodo tiene dos hijos

        System.out.println("\nEliminando nodos:");

        // Hojas son: 10, 25, 55, 80, 100
        arbol.eliminar(10); // Nodo hoja
        System.out.println("Después de eliminar el nodo 10 (nodo hoja):");
        arbol.inOrdenRecursivo(arbol.raiz);
        System.out.println("\n\n");
        arbol.printTree();

        // No hay nodos con un solo hijo
        /*
         * arbol.eliminar(??); // Nodo con un hijo
         * System.out.println("\nDespués de eliminar el nodo ?? (nodo con un hijo):");
         * arbol.inOrdenRecursivo(arbol.raiz);
         * System.out.println("\n\n");
         * arbol.printTree();
         */

        // Nodos con dos hijos: 60, 90, 30, 50, 20
        arbol.eliminar(30); // Nodo con dos hijos
        System.out.println("\nDespués de eliminar el nodo 30 (nodo con dos hijos):");
        arbol.inOrdenRecursivo(arbol.raiz);
        System.out.println("\n\n");
        arbol.printTree();

        // Busqueda de un nodo y su ruta
        arbol.buscar(55); // Nodo existente
        arbol.buscar(999); // Nodo no existente

        // 5. Metricas del arbol
        int altura = arbol.altura(arbol.raiz);
        int totalNodos = arbol.contarNodos(arbol.raiz);
        int totalHojas = arbol.contarHojas(arbol.raiz);

        System.out.println("\nAltura del árbol: " + altura);
        System.out.println("Total de nodos en el árbol: " + totalNodos);
        System.out.println("Total de hojas en el árbol: " + totalHojas);

        // 6. Anallisis
        /*
         * ¿Que ventaja tiene usar un Arbol Binario de busqueda sobre estos datos?
         * 
         * La ventaja de usar un Árbol Binario de Búsqueda (ABB) para estos datos es que
         * permite realizar operaciones de búsqueda, inserción y eliminación de manera
         * eficiente. En un ABB, los nodos están organizados de tal manera que el valor
         * de cada nodo es mayor que los valores en su subárbol izquierdo y menor que
         * los valores en su subárbol derecho. Esto facilita la búsqueda de elementos,
         * ya que se puede descartar la mitad del árbol en cada paso, lo que resulta en
         * una complejidad promedio de O(log n) para estas operaciones. Además, el ABB
         * mantiene un ordenamiento natural de los datos, lo que puede ser útil para
         * realizar recorridos ordenados y otras operaciones relacionadas con el orden
         * de los elementos.
         */

        /*
         * ¿Que pasa si los datos llegan en orden decresciente o creciente?
         * Si los datos llegan en orden creciente o decreciente, el Árbol Binario de
         * Búsqueda (ABB) se volverá desequilibrado o degradado, lo que significa que se
         * parecerá
         * más a una lista enlazada que a un árbol. En este caso, la altura del árbol
         * será igual al número de nodos, lo que resultará en una complejidad de O(n)
         * para las operaciones de búsqueda, inserción y eliminación. Esto se debe a que
         * cada nodo tendrá solo un hijo (ya sea izquierdo o derecho), lo que hace que
         * el árbol pierda su eficiencia característica.
         */

        /*
         * ¿Que se puede hacer para evitar esto?
         * Para evitar que el Árbol Binario de Búsqueda (ABB) se vuelva desequilibrado
         * cuando los datos llegan en orden creciente o decreciente, se pueden utilizar
         * técnicas de balanceo, como los árboles AVL o los árboles Red-Black. Estas
         * estructuras de datos mantienen el equilibrio del árbol después de cada
         * operación de inserción o eliminación, asegurando que la altura del árbol se
         * mantenga logarítmica en relación al número de nodos. Esto se logra mediante
         * rotaciones y ajustes en la estructura del árbol para garantizar que no se
         * formen ramas demasiado largas, lo que mejora la eficiencia de las operaciones
         * de búsqueda, inserción y eliminación incluso en casos de datos ordenados.
         */
    }
}