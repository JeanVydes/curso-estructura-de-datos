
/**
 * Arbol.java (Implementación Recursiva)
 *
 * Esta clase implementa un Árbol Binario de Búsqueda (ABB) utilizando
 * exclusivamente métodos recursivos. La recursión es un enfoque muy natural
 * y elegante para las estructuras de datos jerárquicas como los árboles,
 * ya que las operaciones en un árbol completo a menudo se pueden definir
 * en términos de la misma operación en sus subárboles (izquierdo y derecho).
 */
public class Arbol {

    static class Nodo {

        int dato;
        Nodo izquierdo;
        Nodo derecho;

        public Nodo(int dato) {
            this.dato = dato;
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    Nodo raiz;

    public Arbol() {
        this.raiz = null;
    }

    // --- MÉTODOS PÚBLICOS DE INTERFAZ ---
    // Estos métodos son los que el usuario llama. Ocultan la complejidad
    // de la recursión iniciando el proceso desde la raíz del árbol.
    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

    public void eliminar(int valor) {
        raiz = eliminarRecursivo(raiz, valor);
    }

    // --- MÉTODOS PRIVADOS RECURSIVOS ---
    // Aquí reside la lógica principal. Cada método se llama a sí mismo
    // para "descender" por el árbol hasta encontrar un punto de parada.
    /**
     * Inserta un valor en el árbol de forma recursiva.
     *
     * ¿Qué se busca? Encontrar el lugar vacío (null) donde el nuevo nodo debe
     * ser insertado para mantener la propiedad del ABB (menores a la izquierda,
     * mayores a la derecha).
     *
     * ¿Cómo funciona la recursión aquí? La función se llama a sí misma para
     * bajar por el árbol. El "retorno" de cada llamada es crucial: devuelve el
     * subárbol (modificado o no) para que la llamada anterior pueda
     * "reconectar" su puntero de hijo izquierdo o derecho.
     */
    private Nodo insertarRecursivo(Nodo nodoActual, int valor) {
        // --- CASO BASE: El punto de parada de la recursión ---
        // Si el nodo actual es null, hemos encontrado el lugar perfecto para
        // el nuevo nodo. Creamos el nodo y lo retornamos. La llamada anterior
        // en la pila (el padre) asignará este nuevo nodo a su puntero
        // izquierdo o derecho.
        if (nodoActual == null) {
            return new Nodo(valor);
        }

        // --- PASO RECURSIVO: La decisión de hacia dónde ir ---
        // Si no estamos en un lugar vacío, debemos decidir si ir a la
        // izquierda o a la derecha.
        if (valor < nodoActual.dato) {
            // El valor es menor, así que pertenece al subárbol izquierdo.
            // Le decimos al nodo actual que su hijo izquierdo será el resultado
            // de intentar insertar el valor en su actual hijo izquierdo.
            nodoActual.izquierdo = insertarRecursivo(nodoActual.izquierdo, valor);
        } else if (valor > nodoActual.dato) {
            // Hacemos lo mismo para el lado derecho si el valor es mayor.
            nodoActual.derecho = insertarRecursivo(nodoActual.derecho, valor);
        }

        // Si valor == nodoActual.dato, no hacemos nada para evitar duplicados.
        // Finalmente, retornamos el 'nodoActual' (sin cambios si el valor ya existía,
        // o con un hijo actualizado si se realizó una inserción más abajo). Esto
        // asegura que los enlaces del árbol por encima de la inserción permanezcan intactos.
        return nodoActual;
    }

    /**
     * Elimina un nodo del árbol de forma recursiva.
     *
     * ¿Qué se busca? Encontrar el nodo con el valor dado y eliminarlo, pero lo
     * más importante es reestructurar el árbol para que no se rompa la
     * propiedad del ABB.
     *
     * ¿Cómo funciona la recursión aquí? Primero, la recursión nos ayuda a
     * encontrar el nodo. Una vez encontrado, la lógica se divide en 3 casos
     * según cuántos hijos tenga el nodo. El valor de retorno de la función le
     * dice al nodo padre qué nodo debe ocupar el lugar del que se eliminó.
     */
    private Nodo eliminarRecursivo(Nodo nodoActual, int valor) {
        // --- CASO BASE DE BÚSQUEDA ---
        // Si llegamos a null, el valor no está en el árbol, así que no hay nada que hacer.
        if (nodoActual == null) {
            return null;
        }

        // --- PASO RECURSIVO DE BÚSQUEDA ---
        // Navegamos por el árbol para encontrar el nodo a eliminar.
        if (valor < nodoActual.dato) {
            nodoActual.izquierdo = eliminarRecursivo(nodoActual.izquierdo, valor);
        } else if (valor > nodoActual.dato) {
            nodoActual.derecho = eliminarRecursivo(nodoActual.derecho, valor);
        } else {
            // --- NODO ENCONTRADO: APLICAMOS LA LÓGICA DE ELIMINACIÓN ---
            // A partir de aquí, 'nodoActual' es el nodo que queremos eliminar.

            // CASO 1: El nodo es una hoja (0 hijos).
            // Es el caso más simple. Simplemente lo eliminamos devolviendo null.
            // La llamada recursiva padre asignará null a su puntero de hijo.
            if (nodoActual.izquierdo == null && nodoActual.derecho == null) {
                return null;
            }

            // CASO 2: El nodo tiene un solo hijo.
            // Lo "saltamos" devolviendo su único hijo. El padre del nodo eliminado
            // "adoptará" directamente a su nieto.
            if (nodoActual.derecho == null) {
                return nodoActual.izquierdo;
            }
            if (nodoActual.izquierdo == null) {
                return nodoActual.derecho;
            }

            // CASO 3: El nodo tiene dos hijos.
            // Este es el caso complejo. No podemos simplemente eliminarlo.
            // Estrategia:
            // 1. No eliminamos el nodo, sino que sobrescribimos su valor.
            // 2. Buscamos un sustituto adecuado: el "sucesor in-orden" (el
            //    valor más pequeño en el subárbol derecho). Este valor es el
            //    único que garantiza ser mayor que todo lo de la izquierda
            //    y menor que el resto de la derecha.
            // 3. Copiamos el valor del sucesor al nodo actual.
            // 4. Ahora tenemos un duplicado, por lo que llamamos a eliminar de
            //    nuevo, pero esta vez para borrar el sucesor de su posición
            //    original en el subárbol derecho (que será un caso más fácil, 1 o 2).
            int sucesor = encontrarMinimo(nodoActual.derecho);
            nodoActual.dato = sucesor;
            nodoActual.derecho = eliminarRecursivo(nodoActual.derecho, sucesor);
        }

        return nodoActual;
    }

    /**
     * Encuentra el valor mínimo en un subárbol. ¿Por qué es necesario? Es una
     * función auxiliar clave para el Caso 3 de la eliminación. ¿Cómo funciona?
     * Por la propiedad del ABB, el valor más pequeño siempre estará en el nodo
     * más a la izquierda posible.
     */
    private int encontrarMinimo(Nodo nodo) {
        // Simplemente seguimos yendo a la izquierda hasta que no podamos más.
        return nodo.izquierdo == null ? nodo.dato : encontrarMinimo(nodo.izquierdo);
    }

    // --- MÉTODOS DE RECORRIDO ---
    // Los recorridos determinan el orden en que se visitan los nodos.
    public void recorridoInOrden() {
        System.out.print("In-Orden (Recursivo):  ");
        inOrdenRecursivo(raiz);
        System.out.println();
    }

    private void inOrdenRecursivo(Nodo nodo) {
        // Secuencia: Izquierda -> Raíz -> Derecha.
        // ¿Qué logra? Visita los nodos en orden ascendente. Es la propiedad
        // más útil de un ABB.
        if (nodo != null) {
            // 1. Primero, resuelve todo el subárbol izquierdo.
            inOrdenRecursivo(nodo.izquierdo);
            // 2. Una vez que la izquierda está completa, visita el nodo actual (raíz del subárbol).
            System.out.print(nodo.dato + " ");
            // 3. Finalmente, resuelve todo el subárbol derecho.
            inOrdenRecursivo(nodo.derecho);
        }
    }

    public void recorridoPreOrden() {
        System.out.print("Pre-Orden (Recursivo): ");
        preOrdenRecursivo(raiz);
        System.out.println();
    }

    private void preOrdenRecursivo(Nodo nodo) {
        // Secuencia: Raíz -> Izquierda -> Derecha.
        // ¿Qué logra? Es útil para copiar un árbol, ya que al procesar el nodo
        // raíz primero, puedes crearlo y luego, recursivamente, añadirle sus hijos.
        if (nodo != null) {
            // 1. Visita la raíz del subárbol primero.
            System.out.print(nodo.dato + " ");
            // 2. Luego, resuelve todo el subárbol izquierdo.
            preOrdenRecursivo(nodo.izquierdo);
            // 3. Finalmente, resuelve todo el subárbol derecho.
            preOrdenRecursivo(nodo.derecho);
        }
    }

    public void recorridoPostOrden() {
        System.out.print("Post-Orden (Recursivo):");
        postOrdenRecursivo(raiz);
        System.out.println();
    }

    private void postOrdenRecursivo(Nodo nodo) {
        // Secuencia: Izquierda -> Derecha -> Raíz.
        // ¿Qué logra? Es útil para eliminar un árbol. Se asegura de que todos
        // los hijos de un nodo sean eliminados antes de eliminar el propio nodo.
        if (nodo != null) {
            // 1. Primero, resuelve todo el subárbol izquierdo.
            postOrdenRecursivo(nodo.izquierdo);
            // 2. Luego, resuelve todo el subárbol derecho.
            postOrdenRecursivo(nodo.derecho);
            // 3. Solo al final, después de que todos sus descendientes han sido procesados, visita la raíz.
            System.out.print(nodo.dato + " ");
        }
    }

    /**
     *
     * Inicia el proceso para calcular la altura del árbol. La altura se define
     * como el número de aristas en el camino más largo desde la raíz hasta un
     * nodo hoja.
     *
     * @return La altura del árbol. Un árbol vacío tiene altura -1.
     */
    public int calcularAltura() {
        return calcularAlturaRecursivo(raiz);
    }

    private int calcularAlturaRecursivo(Nodo nodo) {
        // --- CASO BASE: El punto de parada de la recursión ---
        // Si el nodo es nulo, significa que hemos llegado al final de una rama.
        // Se considera que un nodo nulo tiene una altura de -1. Esto hace que
        // la matemática funcione correctamente: un nodo hoja (cuyos hijos son nulos)
        // tendrá una altura de 1 + max(-1, -1) = 0.
        if (nodo == null) {
            return -1;
        }

        // --- PASO RECURSIVO: La división del problema ---
        // La lógica es: la altura de un árbol es 1 (para contar el nivel actual)
        // más la altura del subárbol más alto de sus hijos.
        // Para saberlo, debemos primero calcular la altura de cada subárbol.
        // 1. Llama recursivamente para obtener la altura de todo el subárbol izquierdo.
        int alturaIzquierda = calcularAlturaRecursivo(nodo.izquierdo);

        // 2. Llama recursivamente para obtener la altura de todo el subárbol derecho.
        int alturaDerecha = calcularAlturaRecursivo(nodo.derecho);

        // 3. Compara las dos alturas, elige la mayor, y le suma 1 (el nivel actual).
        // Este valor se retorna a la llamada anterior en la pila.
        return 1 + Math.max(alturaIzquierda, alturaDerecha);
    }

    /**
     *
     * Inicia la validación para comprobar si el árbol completo cumple con la
     * propiedad fundamental de un Árbol Binario de Búsqueda en TODOS sus nodos.
     *
     * @return true si el árbol es un ABB válido, false en caso contrario.
     */
    public boolean esArbolValido() {
        // La llamada inicial no tiene restricciones, por lo que pasamos null para
        // min y max, que representarán -infinito y +infinito. Usamos Long para
        // evitar problemas si el árbol contiene los valores Integer.MIN_VALUE o MAX_VALUE.
        return esArbolValidoRecursivo(raiz, null, null);
    }

    private boolean esArbolValidoRecursivo(Nodo nodo, Long min, Long max) {
        // --- CASO BASE: Un subárbol vacío no viola la regla ---
        // Si llegamos a un nodo nulo, significa que esa rama del árbol es válida.
        if (nodo == null) {
            return true;
        }

        // --- LÓGICA DE VALIDACIÓN ---
        // Un simple 'nodo.izquierdo.dato < nodo.dato' no es suficiente, porque
        // un nodo debe ser menor/mayor que TODOS sus ancestros, no solo su padre.
        // Por eso, pasamos un rango [min, max] que se va estrechando a medida
        // que descendemos por el árbol.
        // 1. Comprueba si el valor del nodo actual viola el rango heredado de sus ancestros.
        //    - Si hay un límite inferior (min) y el dato es menor o igual, es inválido.
        //    - Si hay un límite superior (max) y el dato es mayor o igual, es inválido.
        if ((min != null && nodo.dato <= min) || (max != null && nodo.dato >= max)) {
            return false;
        }

        // --- PASO RECURSIVO: Propagar las restricciones a los hijos ---
        // Si el nodo actual es válido, ahora debemos verificar que sus subárboles también lo sean.
        // La clave está en cómo actualizamos los límites para las llamadas recursivas:
        // - Al ir a la IZQUIERDA: Todos los nodos de ese subárbol deben ser menores que
        //   el nodo actual. Por lo tanto, el valor del nodo actual se convierte en el
        //   nuevo límite SUPERIOR (max) para esa rama.
        // - Al ir a la DERECHA: Todos los nodos deben ser mayores. El valor del nodo
        //   actual se convierte en el nuevo límite INFERIOR (min).
        //
        // El árbol completo es válido solo si AMBAS llamadas recursivas retornan true.
        return esArbolValidoRecursivo(nodo.izquierdo, min, (long) nodo.dato)
                && esArbolValidoRecursivo(nodo.derecho, (long) nodo.dato, max);
    }

    public static void main(String[] args) {
        // El método main demuestra el funcionamiento de las operaciones implementadas.
        Arbol arbol = new Arbol();
        System.out.println("--- ÁRBOL RECURSIVO ---");
        int[] valores = {10, 5, 15, 3, 7, 12, 18};
        System.out.print("Insertando: ");
        for (int valor : valores) {
            System.out.print(valor + " ");
            arbol.insertar(valor);
        }
        System.out.println("\n");

        // Muestra los diferentes órdenes de visita de los nodos.
        arbol.recorridoInOrden();
        arbol.recorridoPreOrden();
        arbol.recorridoPostOrden();

        // Demuestra que la eliminación (incluso el caso complejo) funciona correctamente.
        System.out.println("\nEliminando el 15 (nodo con dos hijos)...");
        arbol.eliminar(15);
        arbol.recorridoInOrden(); // Se imprime de nuevo para ver el resultado.
    }
}
