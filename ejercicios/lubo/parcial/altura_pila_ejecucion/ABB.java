public class ABB {
    static class Nodo {
        char valor;
        Nodo izquierdo;
        Nodo derecho;

        public Nodo(char valor) {
            this.valor = valor;
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    Nodo raiz;
    int nivelPila = 0; // Para controlar la indentación visual en la consola

    public ABB() {
        this.raiz = null;
    }

    public int operacion() {
        System.out.println("Llamada inicial: operacion(raiz)");
        return operacion(raiz);
    }

    private int operacion(Nodo nodo) {
        // Generar espacios según el nivel de la pila para visualizar la profundidad
        String sangria = "  ".repeat(nivelPila);
        String nombreNodo = (nodo == null) ? "null" : String.valueOf(nodo.valor);

        System.out.println(sangria + "-> [ENTRA] operacion(" + nombreNodo + ")");
        nivelPila++;

        if (nodo == null) {
            nivelPila--;
            System.out.println(sangria + "<- [RETORNA] 0 (nodo == null)");
            return 0;
        }

        System.out.println(sangria + "   Llamando a hijo izquierdo de " + nombreNodo + "...");
        int resultadoIzquierdo = operacion(nodo.izquierdo);

        System.out.println(sangria + "   Llamando a hijo derecho de " + nombreNodo + "...");
        int resultadoDerecho = operacion(nodo.derecho);

        int resultado = 1 + Math.max(resultadoIzquierdo, resultadoDerecho);
        nivelPila--;

        System.out.println(sangria + "<- [RETORNA] 1 + max(" + resultadoIzquierdo + ", " + resultadoDerecho + ") = "
                + resultado + " para el nodo " + nombreNodo);

        return resultado;
    }

    public static void main(String[] args) {
        ABB arbol = new ABB();

        // Construimos manualmente el árbol de la imagen para garantizar
        // exactamente la misma estructura que muestra el ejercicio.
        arbol.raiz = new Nodo('M');

        arbol.raiz.izquierdo = new Nodo('F');
        arbol.raiz.derecho = new Nodo('T');

        arbol.raiz.izquierdo.izquierdo = new Nodo('B');
        arbol.raiz.izquierdo.derecho = new Nodo('H');

        arbol.raiz.izquierdo.derecho.derecho = new Nodo('L');

        arbol.raiz.derecho.derecho = new Nodo('W');

        System.out.println("==========================================");
        System.out.println(" EJECUCIÓN PASO A PASO (PILA RECURSIVA)");
        System.out.println("==========================================");
        int resultadoFinal = arbol.operacion();
        System.out.println("==========================================");
        System.out.println("Resultado Final del árbol: " + resultadoFinal);
    }
}
