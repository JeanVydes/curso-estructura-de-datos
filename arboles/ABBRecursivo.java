/**
 * ============================================================================
 * 🌲 ÁRBOL BINARIO DE BÚSQUEDA (ABB) - IMPLEMENTACIÓN RECURSIVA 🌲
 * ============================================================================
 *
 * ¿QUÉ ES ESTE ARCHIVO?
 * Este archivo contiene la implementación "elegante" y matemática de un ABB.
 * Utiliza la RECURSIVIDAD para todas sus operaciones principales (insertar,
 * eliminar, buscar, recorrer).
 *
 * LA MAGIA DE LA RECURSIÓN EN ÁRBOLES
 * Piensa en un árbol no como una gran estructura monolítica, sino como un
 * nodo unido a dos "mini-árboles" (sus subárboles).
 * 
 * (10) <-- Nodo actual
 * / \
 * (5) (15) <-- Cada hijo es, a su vez, la raíz de su propio mini-árbol.
 * 
 * La recursividad nos permite pensar así: "Para insertar un número en el árbol
 * gigante, solo lo comparo con la raíz actual. Si es menor, le digo al subárbol
 * izquierdo que se encargue él del problema repitiendo este mismo proceso".
 * 
 * ----------------------------------------------------------------------------
 * 🚀 EJEMPLO VISUAL PASO A PASO: INSERTAR EL NÚMERO 7
 * ----------------------------------------------------------------------------
 * Queremos insertar el 7 en este árbol inicial:
 * 
 * [10]
 * / \
 * [5] [15]
 * \
 * [8]
 * 
 * PASO 1: insertarRecursivo(nodo=10, valor=7)
 * ¿7 < 10? SÍ. Bajamos por la izquierda y "pausamos" este paso.
 * 
 * PASO 2: insertarRecursivo(nodo=5, valor=7)
 * ¿7 < 5? NO. ¿7 > 5? SÍ. Bajamos por la derecha y "pausamos".
 * 
 * PASO 3: insertarRecursivo(nodo=8, valor=7)
 * ¿7 < 8? SÍ. Bajamos por la izquierda y "pausamos".
 * 
 * PASO 4: insertarRecursivo(nodo=null, valor=7) <-- ¡BINGO! CASO BASE.
 * Hemos llegado a un espacio vacío. La recursión toca fondo.
 * Creamos el nuevo nodo [7] y se lo "devolvemos" (return) al paso 3.
 * 
 * EL DESENLACE (Desenrollando las pausas):
 * - El nodo [8] recibe el [7] y lo engancha a su izquierda.
 * - El nodo [5] actualiza su derecha (sigue siendo el [8], todo bien).
 * - El nodo [10] actualiza su izquierda (sigue siendo el [5]).
 * 
 * ÁRBOL RESULTANTE:
 * [10]
 * / \
 * [5] [15]
 * \
 * [8]
 * /
 * [7] <-- ¡Nuevo nodo conectado mágicamente por el retorno recursivo!
 * ============================================================================
 */
public class ABBRecursivo {

    /**
     * ESTRUCTURA DEL NODO
     * ¿Qué es? La plantilla para cada elemento del árbol. Es el "ladrillo"
     * con el que construiremos toda la estructura.
     * ¿Por qué? Necesitamos una forma de almacenar un dato y, al mismo tiempo,
     * tener "caminos" o "enlaces" a otros nodos.
     */
    static class Nodo {
        // ¿Qué hace? Almacena el valor numérico del nodo.
        int dato;
        // ¿Qué hacen? Son referencias. Una referencia es como una flecha que apunta a
        // otro objeto Nodo en la memoria. 'izquierdo' apunta a un nodo con un
        // valor menor, y 'derecho' a uno con un valor mayor.
        Nodo izquierdo;
        Nodo derecho;

        // ¿Qué es? Un constructor. Es una función especial que se ejecuta al crear un
        // nuevo Nodo.
        // ¿Por qué? Facilita la vida. Con una sola línea (ej: new Nodo(10)), crea el
        // nodo, le asigna el valor y se asegura de que sus hijos apunten a 'nada'
        // (null) inicialmente.
        public Nodo(int dato) {
            this.dato = dato;
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    Nodo raiz;

    public ABBRecursivo() {
        this.raiz = null;
    }

    // --- MÉTODOS PÚBLICOS DE INTERFAZ ---
    // ¿Qué hacen? Son el punto de entrada para el usuario.
    // ¿Por qué? Ocultan la complejidad de la recursión. El usuario no necesita
    // saber sobre 'nodoActual', solo quiere insertar un valor en "el árbol".
    // Estas funciones inician el proceso recursivo desde la raíz.
    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

    public void eliminar(int valor) {
        raiz = eliminarRecursivo(raiz, valor);
    }

    /**
     * INSERCIÓN RECURSIVA (Método auxiliar)
     */
    private Nodo insertarRecursivo(Nodo nodoActual, int valor) {
        // --- PASO 1: CASO BASE - EL PUNTO DE PARADA ---
        // ¿Qué hace? Comprueba si hemos llegado a un espacio vacío (una referencia
        // nula).
        // ¿Por qué? La recursión debe terminar. Este es el final del camino: hemos
        // encontrado el lugar exacto donde debe ir el nuevo nodo. Creamos el nodo
        // y lo retornamos para que la llamada anterior (su padre) lo "enganche".
        if (nodoActual == null) {
            return new Nodo(valor);
        }

        // --- PASO 2: PASO RECURSIVO - LA DECISIÓN Y LA LLAMADA ---
        // ¿Qué hace? Compara el valor con el dato del nodo actual para decidir a dónde
        // ir.
        // ¿Por qué? Para mantener la propiedad del ABB.
        if (valor < nodoActual.dato) {
            // ¿Qué hace? Llama a la misma función, pero para el subárbol izquierdo.
            // ¿Por qué? Delega la tarea a un problema más pequeño. La clave es la
            // asignación `nodoActual.izquierdo = ...`. El padre (nodoActual) actualiza
            // su puntero izquierdo con el resultado de la inserción en su subárbol.
            nodoActual.izquierdo = insertarRecursivo(nodoActual.izquierdo, valor);
        } else if (valor > nodoActual.dato) {
            // La misma lógica, pero para el lado derecho.
            nodoActual.derecho = insertarRecursivo(nodoActual.derecho, valor);
        }

        // --- PASO 3: EL RETORNO - MANTENIENDO LA CADENA ---
        // ¿Qué hace? Devuelve el puntero al nodo actual.
        // ¿Por qué? Si el valor ya existía, o si la inserción ocurrió más abajo,
        // este nodo no cambia. Devolverlo asegura que los enlaces por encima de él
        // en la cadena de recursión permanezcan intactos.
        return nodoActual;
    }

    /**
     * ELIMINACIÓN RECURSIVA (Método auxiliar)
     */
    private Nodo eliminarRecursivo(Nodo nodoActual, int valor) {
        // --- PASO 1: BÚSQUEDA - ENCONTRAR EL NODO ---
        // ¿Qué hace? Si llegamos a un nulo, el valor no existe. Es el caso base de la
        // búsqueda.
        if (nodoActual == null) {
            return null;
        }
        // ¿Qué hace? Llama recursivamente para bajar por el árbol hasta encontrar el
        // nodo.
        if (valor < nodoActual.dato) {
            nodoActual.izquierdo = eliminarRecursivo(nodoActual.izquierdo, valor);
        } else if (valor > nodoActual.dato) {
            nodoActual.derecho = eliminarRecursivo(nodoActual.derecho, valor);
        } else {
            // --- PASO 2: NODO ENCONTRADO - LÓGICA DE ELIMINACIÓN ---

            // CASO 1: Nodo con 0 hijos (hoja).
            // ¿Qué hace? Devuelve null.
            // ¿Por qué? El padre que llamó a esta función recibirá null y lo asignará
            // a su puntero de hijo, efectivamente "cortando" la hoja del árbol.
            if (nodoActual.izquierdo == null && nodoActual.derecho == null) {
                return null;
            }

            // CASO 2: Nodo con 1 hijo.
            // ¿Qué hace? Devuelve la referencia a su único hijo.
            // ¿Por qué? El padre del nodo eliminado "adoptará" directamente a su nieto,
            // "puenteando" el nodo eliminado y manteniendo la estructura.
            if (nodoActual.derecho == null) {
                return nodoActual.izquierdo;
            }
            if (nodoActual.izquierdo == null) {
                return nodoActual.derecho;
            }

            // CASO 3: Nodo con 2 hijos.
            // ¿Qué hace? Encuentra el sucesor, copia su valor al nodo actual, y luego
            // elimina el sucesor de su posición original.
            // ¿Por qué? Es la única forma de eliminar el valor sin romper la estructura.
            // No eliminamos el nodo, solo "robamos" el valor del sucesor. El problema
            // se convierte en el más fácil de eliminar el sucesor, que a lo sumo
            // tendrá un hijo (Caso 1 o 2).
            int sucesor = encontrarMinimo(nodoActual.derecho);
            nodoActual.dato = sucesor;
            nodoActual.derecho = eliminarRecursivo(nodoActual.derecho, sucesor);
        }
        return nodoActual;
    }

    /**
     * ENCONTRAR MÍNIMO (Función auxiliar para eliminar)
     */
    private int encontrarMinimo(Nodo nodo) {
        // ¿Qué hace? Sigue el camino de referencias izquierdas hasta el final.
        // ¿Por qué? Por definición, en un ABB, el valor más pequeño siempre está en
        // el nodo más a la izquierda posible.
        return nodo.izquierdo == null ? nodo.dato : encontrarMinimo(nodo.izquierdo);
    }

    // --- MÉTODOS DE RECORRIDO ---

    public void recorridoInOrden() {
        System.out.print("In-Orden (Recursivo):  ");
        inOrdenRecursivo(raiz);
        System.out.println();
    }

    private void inOrdenRecursivo(Nodo nodo) {
        // Secuencia: Izquierda -> Raíz -> Derecha. Visita los nodos en orden
        // ascendente.
        if (nodo != null) {
            inOrdenRecursivo(nodo.izquierdo); // 1. Resuelve todo lo de la izquierda primero.
            System.out.print(nodo.dato + " "); // 2. Luego visita la raíz actual.
            inOrdenRecursivo(nodo.derecho); // 3. Finalmente, resuelve todo lo de la derecha.
        }
    }

    // ... (Los otros recorridos siguen una lógica similar de ordenar las 3
    // acciones)
    public void recorridoPreOrden() {
        System.out.print("Pre-Orden (Recursivo): ");
        preOrdenRecursivo(raiz);
        System.out.println();
    }

    private void preOrdenRecursivo(Nodo nodo) {
        // Secuencia: Raíz -> Izquierda -> Derecha. Usa la raíz antes de explorar los
        // hijos.
        if (nodo != null) {
            System.out.print(nodo.dato + " "); // 1. Visita la raíz primero.
            preOrdenRecursivo(nodo.izquierdo); // 2. Luego resuelve la izquierda.
            preOrdenRecursivo(nodo.derecho); // 3. Finalmente resuelve la derecha.
        }
    }

    public void recorridoPostOrden() {
        System.out.print("Post-Orden (Recursivo):");
        postOrdenRecursivo(raiz);
        System.out.println();
    }

    private void postOrdenRecursivo(Nodo nodo) {
        // Secuencia: Izquierda -> Derecha -> Raíz. Deja la raíz para el final.
        if (nodo != null) {
            postOrdenRecursivo(nodo.izquierdo); // 1. Izquierda primero.
            postOrdenRecursivo(nodo.derecho); // 2. Luego derecha.
            System.out.print(nodo.dato + " "); // 3. Finalmente la raíz.
        }
    }

    /**
     * CALCULAR ALTURA (Interfaz pública)
     */
    public int calcularAltura() {
        return calcularAlturaRecursivo(raiz);
    }

    private int calcularAlturaRecursivo(Nodo nodo) {
        // --- CASO BASE ---
        // ¿Qué hace? Si el nodo es nulo, devuelve -1.
        // ¿Por qué? Un espacio vacío no tiene altura. El -1 hace que la matemática
        // para un nodo hoja (un nodo sin hijos) sea correcta: 1 + max(-1, -1) = 0.
        if (nodo == null) {
            return -1;
        }
        // --- PASO RECURSIVO ---
        // ¿Qué hace? Calcula la altura de cada subárbol por separado.
        // ¿Por qué? La altura es el camino más largo. Debemos explorar ambos
        // caminos (izquierdo y derecho) para saber cuál es más largo.
        int alturaIzquierda = calcularAlturaRecursivo(nodo.izquierdo);
        int alturaDerecha = calcularAlturaRecursivo(nodo.derecho);
        // ¿Qué hace? Devuelve 1 más la altura del subárbol más alto.
        // ¿Por qué? El '1' cuenta el nivel del nodo actual, y el 'max' elige el
        // camino más largo que encontró entre sus hijos.
        return 1 + Math.max(alturaIzquierda, alturaDerecha);
    }

    /**
     * VALIDAR ABB (Interfaz pública)
     */
    public boolean esArbolValido() {
        // ¿Qué hace? Llama a la función auxiliar con 'null' para los límites.
        // ¿Por qué? 'null' representa "sin límite" o infinito. La raíz inicial no
        // tiene restricciones de sus ancestros (porque no tiene).
        return esArbolValidoRecursivo(raiz, null, null);
    }

    private boolean esArbolValidoRecursivo(Nodo nodo, Integer min, Integer max) {
        // --- CASO BASE ---
        // ¿Qué hace? Si el nodo es nulo, la rama es válida.
        // ¿Por qué? Un subárbol vacío no puede violar ninguna regla.
        if (nodo == null) {
            return true;
        }
        // --- LÓGICA DE VALIDACIÓN ---
        // ¿Qué hace? Comprueba si el valor del nodo actual está fuera del rango [min,
        // max]
        // permitido por sus ancestros.
        // ¿Por qué? No basta con comparar con el padre directo. Un nodo debe respetar a
        // TODOS sus ancestros. Este rango que se va estrechando garantiza esta
        // propiedad global.
        if ((min != null && nodo.dato <= min) || (max != null && nodo.dato >= max)) {
            return false;
        }
        // --- PASO RECURSIVO ---
        // ¿Qué hace? Llama a la función para sus hijos con los rangos actualizados.
        // ¿Por qué? Al ir a la izquierda, el valor del nodo actual se convierte en el
        // nuevo MÁXIMO
        // permitido. Al ir a la derecha, se convierte en el nuevo MÍNIMO.
        return esArbolValidoRecursivo(nodo.izquierdo, min, nodo.dato) &&
                esArbolValidoRecursivo(nodo.derecho, nodo.dato, max);
    }

    public static void main(String[] args) {
        ABBRecursivo arbol = new ABBRecursivo();
        System.out.println("--- ÁRBOL RECURSIVO ---");
        int[] valores = { 10, 5, 15, 3, 7, 12, 18 };
        System.out.print("Insertando: ");
        for (int valor : valores) {
            System.out.print(valor + " ");
            arbol.insertar(valor);
        }
        System.out.println("\n");

        arbol.recorridoInOrden();
        arbol.recorridoPreOrden();
        arbol.recorridoPostOrden();

        System.out.println("\nLa altura del arbol es: " + arbol.calcularAltura());
        System.out.println("El arbol es un ABB valido? " + (arbol.esArbolValido() ? "Si" : "No"));

        System.out.println("\nEliminando el 15 (nodo con dos hijos)...");
        arbol.eliminar(15);
        arbol.recorridoInOrden();
    }
}