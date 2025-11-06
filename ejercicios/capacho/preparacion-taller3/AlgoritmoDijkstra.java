/**
 * Clase AlgoritmoDijkstra
 * Finalidad: Demostrar la implementación del algoritmo de Dijkstra,
 * que sirve para encontrar el camino más corto desde UN nodo de origen
 * hacia TODOS los demás nodos en un grafo ponderado (con pesos).
 *
 * IMPORTANTE: Dijkstra solo funciona si TODOS los pesos de las aristas
 * son NO NEGATIVOS (0 o más). Si hay pesos negativos, se debe usar otro
 * algoritmo como Bellman-Ford.
 *
 * Usaremos una matriz de adyacencia ponderada para representar el grafo.
 * - 0 = No hay arista entre los nodos.
 * - > 0 = El "peso" o "costo" de traversing de un nodo a otro.
 *
 * Descripción Visual del Grafo de Ejemplo (ASCII):
 * Este es el grafo que usaremos para demostrar Dijkstra. Es un grafo dirigido
 * con pesos positivos:
 *
 * (0) --4--> (1) --1--> (3) --3--> (4)
 * | ^
 * | | 2
 * 1| |
 * v |
 * (2) --5--> (3)
 *
 * Ejemplos de caminos desde el nodo 0 (con costos):
 * - A 1: Directo (4) vs. Vía 2 (1+2=3) → Dijkstra elegirá el más corto: 3 (vía
 * 2).
 * - A 3: Vía 1 (3+1=4) vs. Vía 2 directo (1+5=6) → Elegirá 4 (vía 2 y 1).
 * - A 4: Vía 3 (4+3=7) → Total 7.
 * - A 2: Directo (1).
 * - A 0: 0 (a sí mismo).
 *
 * Nota: En este grafo, no todos los nodos son alcanzables desde otros, pero
 * Dijkstra maneja eso asignando INF a los inalcanzables.
 */
public class AlgoritmoDijkstra {
    // V: Número de nodos (Vértices). Usaremos 5 para este ejemplo simple,
    // pero se puede cambiar para grafos más grandes.
    static final int V = 5;

    // INF: Constante para representar "Infinito" (nodo inalcanzable).
    // Usamos Integer.MAX_VALUE para asegurar que cualquier suma de distancias
    // reales
    // sea menor, evitando overflow en comparaciones como 'dist[u] + peso <
    // dist[v]'.
    // En la práctica, si los pesos son muy grandes, podría haber overflow, pero
    // para
    // ejemplos educativos como este, es suficiente.
    static final int INF = Integer.MAX_VALUE;

    // Matriz de adyacencia para un grafo PONDERADO y DIRIGIDO.
    // grafoPesado[i][j] = peso de la arista de i a j (0 si no hay arista).
    // Nota: Para grafos no dirigidos, se asignaría simétricamente en [i][j] y
    // [j][i].
    static int[][] grafoPesado = new int[V][V];

    // -----------------------------------------------------------------------------------
    // --- MÉTODOS NÚCLEO DE DIJKSTRA (COMUNES A AMBAS VERSIONES) ---
    // -----------------------------------------------------------------------------------

    /**
     * Función CLAVE de Dijkstra: Encontrar el próximo nodo a visitar.
     * Descripción: De todos los nodos que AÚN NO HEMOS FINALIZADO,
     * esta función encuentra el que tiene la distancia MÍNIMA acumulada
     * desde el origen. Este nodo es el que se "finaliza" en la iteración actual.
     *
     * ¿Qué significa "finalizado"? En Dijkstra, un nodo se considera "finalizado"
     * (o "visitado" en algunas descripciones) cuando hemos確定 su distancia más corta
     * desde el origen y ya no se actualizará más. Esto se basa en el principio de
     * que, con pesos no negativos, una vez seleccionado el nodo más cercano, su
     * distancia es óptima y no puede mejorarse.
     *
     * Visualización Simple (ASCII) del Proceso:
     * Conjunto de nodos no finalizados:
     * [0: dist=0, finalizado] [1: dist=3] [2: dist=1] [3: dist=INF] ← Elegir 2
     * (mínimo).
     *
     * Nota de optimización: Esta implementación usa una búsqueda lineal O(V) para
     * encontrar el mínimo, lo que hace que el algoritmo total sea O(V^2). Para
     * grafos
     * grandes, se optimiza con una cola de prioridad (heap) para O((V+E) log V),
     * pero para enseñanza, esta versión simple es ideal. No se ha optimizado aquí
     * porque el foco es en la claridad y comprensión del algoritmo básico.
     *
     * @param dist     Array de distancias actuales desde el origen (se lee, no se
     *                 modifica).
     * @param visitado Array booleano que marca los nodos ya finalizados.
     * @return El ÍNDICE (int) del nodo más cercano no finalizado, o -1 si no hay
     *         más.
     */
    static int encontrarMinDistancia(int[] dist, boolean[] visitado) {
        // min: Almacena la distancia mínima encontrada hasta ahora (inicia en INF).
        int min = INF;
        // min_index: Almacena el índice del nodo con la distancia mínima (inicia en -1,
        // significando "no encontrado").
        int min_index = -1;

        // Recorremos todos los nodos para buscar el mínimo.
        for (int v = 0; v < V; v++) {
            // v: Índice del nodo actual que estamos evaluando.
            // Comprobamos si v no está finalizado y su dist[v] es menor que min actual.
            if (!visitado[v] && dist[v] < min) {
                // Actualizamos min con dist[v].
                min = dist[v];
                // Guardamos v como min_index.
                min_index = v;
            }
        }

        // Devolvemos min_index (el nodo elegido) o -1 si no hay más.
        return min_index;
    }

    // -----------------------------------------------------------------------------------
    // --- VERSIÓN ITERATIVA (Estándar y Eficiente para Grafos Pequeños) ---
    // -----------------------------------------------------------------------------------

    /**
     * Algoritmo de Dijkstra - Implementación Iterativa (Clásica).
     * Descripción: Esta es la forma estándar de Dijkstra para grafos con matriz de
     * adyacencia.
     * Funciona iterando V veces (o menos si no hay más nodos alcanzables): en cada
     * iteración,
     * selecciona el nodo u más cercano no finalizado, lo marca como finalizado (su
     * distancia es óptima),
     * y actualiza (relaja) las distancias de sus vecinos si se encuentra un camino
     * más corto a través de u.
     *
     * Visualización del Flujo General (ASCII):
     * Inicio → Inicializar: dist[origen]=0, otros=INF; visitado=todos false
     * ↓ (Bucle: para cada nodo, hasta V veces)
     * Encontrar u (min dist no finalizado) → Si ninguno, parar
     * ↓
     * Finalizar u (visitado[u]=true) → Relajar vecinos: para cada v adyacente,
     * | si dist[u] + peso < dist[v], actualizar dist[v]
     * ↓
     * Repetir hasta no más nodos → Fin: dist[] tiene las distancias mínimas
     *
     * Explicación de "Relajación": Es el paso clave donde verificamos si pasar por
     * u
     * mejora la distancia a v. Si sí, actualizamos dist[v]. Con pesos no negativos,
     * esto garantiza que las distancias se mejoren progresivamente hasta ser
     * óptimas.
     *
     * Nota: Esta versión es O(V^2) debido a la búsqueda lineal del mínimo. No se ha
     * optimizado con heap porque el objetivo es enseñar el concepto base de manera
     * clara.
     * Para optimización, se usaría PriorityQueue en Java.
     *
     * @param inicio El nodo (vértice) desde el que empezamos a medir distancias.
     */
    static void dijkstraIterativo(int inicio) {
        // dist: Array para almacenar distancias mínimas desde inicio (inicia en INF).
        int[] dist = new int[V];
        // visitado: Array booleano para marcar nodos finalizados.
        boolean[] visitado = new boolean[V];

        // Inicializamos dist a INF y visitado a false para todos los nodos.
        for (int i = 0; i < V; i++) {
            dist[i] = INF;
            visitado[i] = false;
        }

        // Distancia de inicio a sí mismo es 0.
        dist[inicio] = 0;

        // Bucle principal: Hasta V iteraciones (una por nodo potencial).
        for (int i = 0; i < V; i++) {
            // u: Nodo seleccionado con distancia mínima no finalizado.
            int u = encontrarMinDistancia(dist, visitado);

            // Si u es -1 o dist[u] es INF, no hay más nodos, salimos.
            if (u == -1 || dist[u] == INF) {
                break;
            }

            // Marcamos u como finalizado.
            visitado[u] = true;

            {
                System.out.println(" -> Finalizando Nodo " + u + " (Distancia: " + dist[u] + ")");
            }

            // Recorremos posibles vecinos v de u.
            for (int v = 0; v < V; v++) {
                // peso_uv: Peso de arista u -> v (0 si no existe).
                int peso_uv = grafoPesado[u][v];

                // Condiciones para relajar: v no finalizado, arista existe, u alcanzable, nuevo
                // camino más corto.
                if (!visitado[v] && peso_uv != 0 && dist[u] != INF && (dist[u] + peso_uv < dist[v])) {
                    // Actualizamos dist[v] con el nuevo mínimo.
                    dist[v] = dist[u] + peso_uv;

                    {
                        System.out.println(" ... Actualizando vecino " + v + ". Dist anterior: "
                                + (dist[v] == INF ? "INF" : dist[v])
                                + ". Nueva: " + (dist[u] + peso_uv));
                    }
                }
            }
        }
        {
            System.out.println("Cálculo iterativo completo.");
            imprimirSolucion(dist, inicio);
        }
    }

    // -----------------------------------------------------------------------------------
    // --- VERSIÓN RECURSIVA
    // -----------------------------------------------------------------------------------

    /**
     * Función "lanzadora" (starter) para la versión recursiva de Dijkstra.
     * Descripción: Inicializa las estructuras de datos (dist y visitado) y comienza
     * la recursión llamando al helper. La recursión es una forma alternativa de
     * implementar el bucle iterativo, pero no es más eficiente (puede causar stack
     * overflow
     * en grafos grandes). Se incluye para mostrar cómo el algoritmo se puede pensar
     * recursivamente: procesar un nodo y recursar para el siguiente.
     *
     * Nota: No se ha optimizado porque la recursión en Dijkstra no es común ni
     * eficiente;
     * se usa aquí solo para fines educativos, para contrastar con la iterativa.
     *
     * @param inicio El nodo de origen desde el que medimos distancias.
     */
    static void dijkstraRecursivo_Inicio(int inicio) {
        // dist: Array para distancias mínimas.
        int[] dist = new int[V];
        // visitado: Array para nodos finalizados.
        boolean[] visitado = new boolean[V];

        // Inicializamos dist a INF y visitado a false.
        for (int i = 0; i < V; i++) {
            dist[i] = INF;
            visitado[i] = false;
        }

        dist[inicio] = 0;
        dijkstraRecursivo_Helper(dist, visitado);
        imprimirSolucion(dist, inicio);
    }

    /**
     * Función "Helper" (ayudante) recursiva para Dijkstra.
     * Descripción: Esta función procesa UN nodo por llamada recursiva: encuentra el
     * u más cercano
     * no finalizado, lo finaliza, relaja (relajar significa actualizar la distancia entre
     * u y sus vecinos) y luego se llama a sí misma para procesar
     * el siguiente nodo. Esto simula el bucle 'for' de la versión iterativa.
     *
     * Visualización del Flujo Recursivo (ASCII):
     * Helper → Encontrar u (min dist) → Si ninguno, caso base: return
     * ↓
     * Finalizar u → Relajar vecinos (actualizar dist si mejor)
     * ↓
     * Recursar: Helper() → ... (hasta que no queden nodos)
     *
     * Explicación: La recursión continúa hasta que no hay más nodos por finalizar
     * (caso base). Cada llamada maneja una "iteración" lógica.
     *
     * Nota: En Java, la profundidad de recursión está limitada (stack size), por lo
     * que
     * para V grande, fallaría. No optimizado intencionalmente para mantener
     * simplicidad.
     *
     * @param dist     El array de distancias (se modifica durante la recursión).
     * @param visitado El array de finalizados (se modifica durante la recursión).
     */
    private static void dijkstraRecursivo_Helper(int[] dist, boolean[] visitado) {
        // u: Nodo con distancia mínima no finalizado.
        int u = encontrarMinDistancia(dist, visitado);

        // Caso base: Si no hay u válido, terminamos.
        if (u == -1 || dist[u] == INF) return;

        // Finalizamos u.
        visitado[u] = true;

        {
            System.out.println(" -> Finalizando Nodo " + u + " (Distancia: " + dist[u] + ")");
        }

        // Recorremos vecinos v.
        for (int v = 0; v < V; v++) {
            // peso_uv: Peso u -> v.
            int peso_uv = grafoPesado[u][v];

            // Condiciones para relajar.
            if (!visitado[v] && peso_uv != 0 && dist[u] != INF && (dist[u] + peso_uv < dist[v])) {
                // Actualizamos dist[v].
                dist[v] = dist[u] + peso_uv;

                {
                    System.out.println(" ... Actualizando vecino " + v + ". Dist anterior: "
                            + (dist[v] == INF ? "INF" : dist[v])
                            + ". Nueva: " + (dist[u] + peso_uv));
                }
            }
        }

        // Llamada recursiva para siguiente nodo.
        dijkstraRecursivo_Helper(dist, visitado);
    }

    // -----------------------------------------------------------------------------------
    // --- MÉTODOS DE APOYO (NO CORE DE DIJKSTRA: CONSTRUCCIÓN, IMPRESIÓN, MAIN) ---
    // -----------------------------------------------------------------------------------

    /**
     * Método para agregar un arco ponderado y dirigido al grafo.
     * Descripción: Asigna el peso en la matriz grafoPesado[u][v].
     * Nota: Para grafos no dirigidos, llamar dos veces (u->v y v->u con mismo
     * peso).
     * Asegúrate de que peso >= 0 para que Dijkstra funcione correctamente.
     *
     * @param u    Nodo origen.
     * @param v    Nodo destino.
     * @param peso Costo de la arista (debe ser >=0).
     */
    static void agregarArcoPonderado(int u, int v, int peso) {
        grafoPesado[u][v] = peso; // Asignamos en una dirección (grafo dirigido).
    }

    /**
     * Método para imprimir la matriz ponderada (costos del grafo).
     * Descripción: Muestra la estructura del grafo inicial en formato de tabla
     * ASCII.
     * Útil para verificar la entrada antes de ejecutar Dijkstra. Las filas son
     * orígenes,
     * columnas destinos, valores son pesos (0 = sin arista).
     */
    static void imprimirMatrizPesada() {
        System.out.print("+");
        for (int i = 0; i < V + 1; i++) {
            System.out.print("---- ");
        }
        System.out.println("+");

        System.out.print("|   |");
        for (int i = 0; i < V; i++) {
            System.out.printf(" %3d |", i);
        }
        System.out.println();

        System.out.print("+");
        for (int i = 0; i < V + 1; i++) {
            System.out.print("-----+");
        }
        System.out.println();

        for (int i = 0; i < V; i++) {
            System.out.printf("| %3d |", i);

            for (int j = 0; j < V; j++) {
                System.out.printf(" %3d |", grafoPesado[i][j]);
            }
            System.out.println();

            System.out.print("+");
            for (int k = 0; k < V + 1; k++) {
                System.out.print("-----+");
            }
            System.out.println();
        }
    }

    /**
     * Método para imprimir la solución final de Dijkstra.
     * Descripción: Muestra la distancia más corta desde el 'inicio' a cada nodo
     * en formato de tabla simple. Si un nodo es inalcanzable, muestra "INF".
     *
     * @param dist   Array final de distancias mínimas.
     * @param inicio El nodo de origen usado.
     */
    static void imprimirSolucion(int[] dist, int inicio) {
        System.out.println("Distancias más cortas desde el nodo " + inicio + ":");
        System.out.print("+--------+----------+\n");
        System.out.print("| Nodo   | Distancia|\n");
        System.out.print("+--------+----------+\n");

        for (int i = 0; i < V; i++) {
            if (dist[i] == INF) {
                System.out.printf("| %d      | INF      |\n", i);
            } else {
                System.out.printf("| %d      | %d        |\n", i, dist[i]);
            }
        }

        System.out.print("+--------+----------+\n");
    }

    /**
     * Método principal (main) para la ejecución de la clase.
     * Define el grafo de ejemplo, imprime la matriz,
     * y ejecuta ambas versiones de Dijkstra (iterativa y recursiva).
     */
    public static void main(String[] args) {
        agregarArcoPonderado(0, 1, 4);
        agregarArcoPonderado(0, 2, 1);
        agregarArcoPonderado(1, 3, 1);
        agregarArcoPonderado(2, 1, 2);
        agregarArcoPonderado(2, 3, 5);
        agregarArcoPonderado(3, 4, 3);

        System.out.println("Matriz de Adyacencia Ponderada (Costos):");
        imprimirMatrizPesada();

        System.out.println("\n--- Ejecución de Dijkstra (Iterativo) ---");
        dijkstraIterativo(0);

        System.out.println("\n--- Ejecución de Dijkstra (Recursivo) ---");
        dijkstraRecursivo_Inicio(0);
    }
}