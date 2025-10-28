public class GrafoDirigidoFloydWarshall {

    static final int INF = Integer.MAX_VALUE; // Representa "Infinito" (carretera sin conexión)
    static final int N = 3; // N: Número Total de Nodos o "Ciudades" (0, 1, 2)

    public static void main(String[] args) {

        // --- 1. Matriz de Costos (para Floyd-Warshall) ---
        // Nuestro mapa de 3 ciudades con costos de viaje:
        // 0 -> 1 (3)
        // 1 -> 2 (2)
        // 2 -> 0 (1)
        // 2 -> 1 (4)
        int[][] matrizFloyd = {
                { 0, 3, INF },
                { INF, 0, 2 },
                { 1, 4, 0 }
        };

        {
            System.out.println("==================================================================");
            System.out.println("--- ALGORITMO DE FLOYD-WARSHALL RECURSIVO (Caminos Mínimos) ---");
            System.out.println("==================================================================");
            System.out.println("\nMATRIZ INICIAL (Costos del Dígrafo de 3 Nodos):");
            imprimirMatriz(matrizFloyd);
        }

        // Llamada al método que inicia el proceso recursivo.
        // Se encuentra el camino más barato de cada ciudad (i) a cada ciudad (j).
        FloydWarshallExplicado.calcular(matrizFloyd, N, INF);

        {
            System.out.println("\nMATRIZ FINAL (Distancias Mínimas después de probar todas las escalas):");
            imprimirMatriz(matrizFloyd);
            System.out.println("------------------------------------------------------------------");
        }

        // --- 2. Matriz Booleana (para Warshall) ---
        // Mapa booleano: 'T' si hay conexión directa, 'F' si no.
        boolean[][] matrizWarshall = {
                { false, true, false },
                { false, false, true },
                { true, false, false }
        };

        {
            System.out.println("\n==================================================================");
            System.out.println("--- ALGORITMO DE WARSHALL RECURSIVO (Cierre Transitivo) ---");
            System.out.println("==================================================================");
            System.out.println("\nMATRIZ INICIAL (Conexiones Directas del Dígrafo de 3 Nodos):");
            imprimirMatriz(matrizWarshall);
        }

        // Llamada al método que inicia el proceso recursivo.
        // Se determina si hay un camino existente (T/F) de cada i a cada j.
        WarshallExplicado.calcular(matrizWarshall, N);

        {
            System.out.println("\nMATRIZ FINAL (Cierre Transitivo: ¿Hay un camino de i a j?):");
            imprimirMatriz(matrizWarshall);
            System.out.println("------------------------------------------------------------------");
        }
    }

    private static void imprimirMatriz(int[][] matriz) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matriz[i][j] == INF) {
                    System.out.printf("%5s", "INF");
                } else {
                    System.out.printf("%5d", matriz[i][j]);
                }
            }
            System.out.println();
        }
    }

    private static void imprimirMatriz(boolean[][] matriz) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%5s", matriz[i][j] ? "T" : "F");
            }

            System.out.println();
        }
    }
}

// ------------------------------------------------------------------
// FLOYD-WARSHALL
// ------------------------------------------------------------------

class FloydWarshallExplicado {
    // La matriz de distancias se mantiene como variable estática para que la
    // función
    // recursiva pueda acceder a ella fácilmente.
    private static int[][] dist;
    private static int N_NODES;
    private static int INF_VALUE;

    public static void calcular(int[][] matrizInicial, int n, int inf) {
        N_NODES = n;
        INF_VALUE = inf;
        // Copiamos la matriz inicial, para que los cambios se reflejen en la matriz del
        // main, para no modificar la original.
        dist = matrizInicial;

        // INICIO DE LA RECURSIVIDAD: k = 0 (Empezamos probando la Ciudad 0 como escala)
        floydRecursivo(0);
    }

    /**
     * Función recursiva: Controla el nodo intermedio o de ESCALA (k).
     * 
     * @param k El vértice intermedio actual (0 a N-1).
     */
    private static void floydRecursivo(int k) {
        // CONDICIÓN DE PARADA (Caso Base)
        // ¿Por qué k tiene que ser igual al número de nodos para parar?
        // Porque si N_NODES es 3 (ciudades 0, 1, 2), la última iteración útil es k=2.
        // Cuando k llega a 3, ya probamos TODAS las ciudades como escalas.
        if (k == N_NODES) {
            return;
        }

        {
            System.out.println("\n--- Procesando k = " + k + " (La Ciudad " + k + " es la escala obligatoria) ---");
        }

        // Los ciclos 'i' y 'j' se mantienen iterativos:
        // i (fila) = Ciudad de Origen | j (columna) = Ciudad de Destino
        for (int i = 0; i < N_NODES; i++) { // i: La Ciudad de Origen
            for (int j = 0; j < N_NODES; j++) { // j: La Ciudad de Destino

                // Explicación de la Pregunta Algorítmica:
                // Preguntamos: ¿Es más barato el camino de i a k + k a j?

                // 1. Comprobamos si existe una conexión válida entre i -> k y k -> j,
                // comprobando que no sean INF:
                if (dist[i][k] != INF_VALUE && dist[k][j] != INF_VALUE) {

                    int caminoActual = dist[i][j]; // Costo conocido de i a j
                    int caminoNuevo = dist[i][k] + dist[k][j]; // Costo de i -> k -> j

                    // 2. Aplicamos la fórmula de Programación Dinámica:
                    // dist[i][j] = min(caminoActual, caminoNuevo)
                    if (caminoNuevo < caminoActual) {
                        dist[i][j] = caminoNuevo;
                        {
                            System.out.println("  Mejora: de " + i + " a " + j + ". Antes: " + caminoActual
                                    + ", Ahora (vía " + k + "): " + caminoNuevo);
                        }
                    }
                }
            }
        }

        // PASO RECURSIVO
        // Le dice al algoritmo: "Ya terminaste de probar el nodo k, ahora llama
        // a la función para procesar el nodo k+1 como la próxima escala obligatoria."
        floydRecursivo(k + 1);
    }
}

// ------------------------------------------------------------------
// WARSHALL
// ------------------------------------------------------------------

class WarshallExplicado {
    // La matriz booleana se mantiene como variable estática para que la función
    // recursiva pueda acceder a ella fácilmente.
    // se llama cierre porque representa el cierre transitivo del grafo.
    // un cierre transitivo indica si existe un camino (directo o indirecto) entre dos nodos.
    private static boolean[][] cierre;
    private static int N_NODES;

    public static void calcular(boolean[][] matrizInicial, int n) {
        N_NODES = n;
        // La matriz 'cierre' se modifica directamente.
        cierre = matrizInicial;

        // Inicialización: un nodo siempre está conectado a sí mismo.
        for (int i = 0; i < N_NODES; i++) {
            cierre[i][i] = true;
        }

        // INICIO DE LA RECURSIVIDAD: k = 0 (Empezamos a buscar nuevos enlaces usando la
        // Ciudad 0)
        warshallRecursivo(0);
    }

    /**
     * Función recursiva: Controla el nodo intermedio o PUENTE (k).
     * 
     * @param k El vértice intermedio actual (0 a N-1).
     */
    private static void warshallRecursivo(int k) {

        // 🛑 CONDICIÓN DE PARADA: k == N (Ya probamos todas las ciudades como puentes)
        if (k == N_NODES) {
            return;
        }

        {
            System.out.println("\n--- Procesando k = " + k + " (La Ciudad " + k + " es el puente de conexión) ---");
        }

        // i (fila) = Ciudad de Origen | j (columna) = Ciudad de Destino
        for (int i = 0; i < N_NODES; i++) {
            for (int j = 0; j < N_NODES; j++) {

                // Explicación de la Pregunta Algorítmica:
                // ¿Se puede realizar el viaje de i a j A TRAVÉS de k?

                // 1. Verificamos si existe el nuevo enlace:
                // ¿Hay camino de i a k (cierre[i][k])? AND ¿Hay camino de k a j (cierre[k][j])?
                boolean hayNuevoEnlaceViaK = cierre[i][k] && cierre[k][j];

                // 2. Aplicamos la fórmula (O Lógico):
                // cierre[i][j] = cierre[i][j] OR hayNuevoEnlaceViaK
                if (hayNuevoEnlaceViaK && !cierre[i][j]) {
                    cierre[i][j] = true;
                    {
                        System.out.println("  Nuevo Enlace: de " + i + " a " + j + " (Vía Puente " + k + ")");
                    }
                } else if (hayNuevoEnlaceViaK && cierre[i][j]) {
                    // Si ya existía, no es una mejora, pero se mantiene la lógica.
                    // cierre[i][j] = true || true --> cierre[i][j] = true
                }
            }
        }

        // PASO RECURSIVO
        // Llamamos a la función para procesar el nodo k+1 como el próximo puente.
        warshallRecursivo(k + 1);
    }
}