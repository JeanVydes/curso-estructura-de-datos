package recursividad.java;

public class lista_doblemente_enlazada {
    private static class Nodo {
        char dato;
        Nodo anterior;
        Nodo siguiente;

        public Nodo(char dato) {
            this.dato = dato;
            this.anterior = null;
            this.siguiente = null;
        }
    }

    Nodo cabeza = null;

    public void insertarAlInicio(char nuevoDato) {
        Nodo nuevoNodo = new Nodo(nuevoDato);
        nuevoNodo.siguiente = cabeza;

        nuevoNodo.anterior = null;
        if (cabeza != null) {
            cabeza.anterior = nuevoNodo;
        }

        cabeza = nuevoNodo;
    }

    public void insertarAlFinal(char nuevoDato) {
        Nodo nuevoNodo = new Nodo(nuevoDato);
        nuevoNodo.siguiente = null;

        if (cabeza == null) {
            nuevoNodo.anterior = null;
            cabeza = nuevoNodo;
            return;
        }

        Nodo ultimo = cabeza;
        while (ultimo.siguiente != null) {
            ultimo = ultimo.siguiente;
        }

        ultimo.siguiente = nuevoNodo;
        nuevoNodo.anterior = ultimo;
    }

    public void mostrarListaAdelante() {
        Nodo nodo = cabeza;
        System.out.print("Lista Hacia Adelante: ");
        while (nodo != null) {
            System.out.print(nodo.dato + " <-> ");
            nodo = nodo.siguiente; // Avanzamos con el puntero 'siguiente'.
        }
        System.out.println("NULL");
    }

    /**
     * Cuenta el número de nodos en la lista de forma recursiva.
     * IMPORTANTE: Esta es una lista NO CIRCULAR, por lo que asume
     * que su final es null. Si fuera circular la forma de detenerse
     * sería distinta (ej. iterar comparando contra cabeza para no ciclar).
     *
     * @param nodo El nodo desde el cual se inicia el conteo.
     * @return El número de nodos en la lista.
     */
    public int contarRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarRecursivo(nodo.siguiente);
    }

    /**
     * Busca un nodo con un dato específico de forma recursiva.
     * IMPORTANTE: Esta es una lista NO CIRCULAR, por lo que asume
     * que su final es null, si no lo fuera causaría un stack overflow
     * o NullPointerException al no manejar adecuadamente el fin del ciclo.
     *
     * @param nodo  El nodo desde el cual comenzar la búsqueda.
     * @param valor El valor (dato) que se está buscando en la lista.
     * @return El nodo que contiene el valor, o 'null' si no se encuentra.
     */
    public static Nodo buscarRecursivo(Nodo nodo, char valor) {
        if (nodo == null) {
            return null;
        }
        if (nodo.dato == valor) {
            return nodo;
        }
        return buscarRecursivo(nodo.siguiente, valor);
    }

    /**
     * EJERCICIO DE RECURSIVIDAD 3: Imprimir la lista hacia adelante.
     * En lugar de usar un bucle while, usamos recursividad para iterar.
     * 
     * @param nodo El nodo actual a imprimir.
     */
    public void imprimirAdelanteRecursivo(Nodo nodo) {
        // CASO BASE: Si el nodo es nulo, hemos llegado al final de la lista.
        if (nodo == null) {
            System.out.println("NULL");
            return;
        }

        // PASO RECURSIVO:
        // 1. Imprimimos el dato del nodo actual.
        System.out.print(nodo.dato + " <-> ");

        // 2. Llamamos recursivamente a la función con el siguiente nodo.
        // Esto forma una cadena de llamadas hasta llegar al caso base.
        imprimirAdelanteRecursivo(nodo.siguiente);
    }

    /**
     * EJERCICIO DE RECURSIVIDAD 4: Imprimir la lista hacia atrás (en reversa).
     * Este es el ejemplo perfecto del poder de la recursión sobre la iteración.
     * A diferencia de iterar con un puntero "anterior", aquí usamos la pila de
     * llamadas
     * del sistema para "recordar" los nodos visitados y luego imprimir al volver
     * atrás.
     * 
     * @param nodo El nodo actual desde el que iniciar.
     */
    public void imprimirAtrasRecursivo(Nodo nodo) {
        // CASO BASE: Llegamos al final de la lista, terminamos.
        // Imprimimos el inicio visual de la reversa.
        if (nodo == null) {
            System.out.print("NULL <-> ");
            return;
        }

        // PASO RECURSIVO (Antes de imprimir):
        // 1. Nos adentramos "profundamente" en la lista llamando recursivamente al
        // siguiente nodo.
        // Todavía NO imprimimos este nodo. Esperaremos a que la recursión llegue al
        // final.
        imprimirAtrasRecursivo(nodo.siguiente);

        // 2. Aquí es donde ocurre la magia. Una vez que las llamadas recursivas
        // empiezan a resolverse (porque alcanzaron el final y el caso base),
        // imprimirán los nodos desde el último visitado hasta el primero de forma
        // inversa.
        System.out.print(nodo.dato + " ");
    }

    /**
     * EJERCICIO DE RECURSIVIDAD 5: Comprobar si un dato existe en la lista desde
     * atrás (partiendo del último nodo)
     * usando sólo las referencias en el puntero 'anterior'.
     * 
     * Explicación:
     * Empezamos desde el último nodo y, en lugar de avanzar al 'siguiente',
     * retrocedemos hacia el 'anterior' en cada llamada recursiva. Es como
     * leer un libro desde la última página hasta la primera.
     * 
     * DIAGRAMA DEL PROCESO (Buscar 'A' leyendo hacia atrás: ... <-> A <-> B <-> C):
     * =======================================================
     * buscarHaciaAtrasRecursivo(nodo: C, 'A')
     * |__ 'C' == 'A'? Falso. Devuelve: buscarHaciaAtrasRecursivo(nodo: B, 'A')
     * |__ 'B' == 'A'? Falso. Devuelve: buscarHaciaAtrasRecursivo(nodo: A, 'A')
     * |__ 'A' == 'A'? Verdadero (Caso Base).
     * |__ devuelve: true
     * 
     * EL REGRESO (Desenrollando la pila de llamadas):
     * Devuelve: true
     * Devuelve: true
     * Devuelve: true <-- Resultado Final
     * 
     * @param ultimoNodo   El nodo a evaluar de una búsqueda inversa (en reversa).
     * @param datoBusqueda El valor del dato a contrastar.
     * @return true si es encontrado, false caso contrario.
     */
    public boolean buscarHaciaAtrasRecursivo(Nodo ultimoNodo, char datoBusqueda) {
        if (ultimoNodo == null) {
            return false;
        }

        if (ultimoNodo.dato == datoBusqueda) {
            return true;
        }

        return buscarHaciaAtrasRecursivo(ultimoNodo.anterior, datoBusqueda);
    }

    /**
     * EJERCICIO DE RECURSIVIDAD 6: Eliminar el primer nodo que contenga cierto
     * valor
     * 
     * @param nodo  actual evaluado.
     * @param valor a borrar.
     */
    public void eliminarNodoRecursivo(Nodo nodo, char valor) {
        if (nodo == null) {
            return; // no se encontró el valor
        }

        if (nodo.dato == valor) {
            // Desenlazar el nodo
            if (nodo.anterior != null) {
                Nodo nodoAnterior = nodo.anterior;
                nodoAnterior.siguiente = nodo.siguiente;
            } else {
                // si es la cabeza, actualizamos
                cabeza = nodo.siguiente;
            }

            if (nodo.siguiente != null) {
                Nodo nodoSiguiente = nodo.siguiente;
                nodoSiguiente.anterior = nodo.anterior;
            }
            return; // Borrado exitoso, termina
        }

        eliminarNodoRecursivo(nodo.siguiente, valor);
    }

    public static void main(String[] args) {
        lista_doblemente_enlazada lista = new lista_doblemente_enlazada();

        lista.insertarAlInicio('A');
        lista.insertarAlInicio('B');
        lista.insertarAlInicio('D');
        lista.insertarAlInicio('E');

        Nodo nodoC = buscarRecursivo(lista.cabeza, 'C');
        if (nodoC == null) {
            System.out.println("Nodo 'C' no encontrado. Insertando 'C' al final.");
            lista.insertarAlFinal('C');
        } else {
            System.out.println("Nodo 'C' ya existe en la lista.");
        }

        System.out.println("\nImprimiendo hacia adelante usando forma recursiva:");
        lista.imprimirAdelanteRecursivo(lista.cabeza);

        System.out.println("\nImprimiendo hacia atrás (en reversa) usando forma recursiva:");
        lista.imprimirAtrasRecursivo(lista.cabeza);
        System.out.println();
    }
}