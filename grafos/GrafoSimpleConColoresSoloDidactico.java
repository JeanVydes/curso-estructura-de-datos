public class GrafoSimpleConColoresSoloDidactico {

    // Número de vértices
    static final int V = 4;

    // Matriz de adyacencia
    static int[][] matriz = new int[V][V];

    // CÓDIGOS DE COLOR ANSI
    static final String AZUL = "\u001B[34m";     // Bordes
    static final String VERDE = "\u001B[32m";     // 1's (conexiones)
    static final String AMARILLO = "\u001B[33m";  // Encabezados
    static final String RESET = "\u001B[0m";      // Resetear color

    public static void main(String[] args) {

        // PASO 1: Inicializar matriz en 0
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                matriz[i][j] = 0;
            }
        }

        // PASO 2: Agregar aristas
        agregarArista(0, 1);
        agregarArista(0, 2);
        agregarArista(1, 3);
        agregarArista(2, 3);

        // PASO 3: Imprimir tabla COLORIDA
        System.out.println(AMARILLO + "=== MATRIZ DE ADYACENCIA (Grafo Visual) ===" + RESET);
        imprimirMatrizConColores();

        // PASO 4: BFS y DFS
        System.out.print(AMARILLO + "\nBFS desde 0: " + RESET);
        bfs(0);

        System.out.print(AMARILLO + "\nDFS desde 0: " + RESET);
        dfs(0);
        System.out.println();
    }

    static void agregarArista(int a, int b) {
        matriz[a][b] = 1;
        matriz[b][a] = 1;
    }

    // IMPRIMIR TABLA CON COLORES Y BORDES
    static void imprimirMatrizConColores() {
        // Línea superior
        System.out.print(AZUL + "+");  
        for (int i = 0; i < V + 1; i++) {
            System.out.print("---+");
        }
        System.out.println(RESET);

        // Encabezados de columnas
        System.out.print(AZUL + "|   |" + RESET);
        for (int i = 0; i < V; i++) {
            System.out.print(AMARILLO + " " + i + " |" + RESET);
        }
        System.out.println();

        // Separador
        System.out.print(AZUL + "+");
        for (int i = 0; i < V + 1; i++) {
            System.out.print("---+");
        }
        System.out.println(RESET);

        // Filas de la matriz
        for (int i = 0; i < V; i++) {
            System.out.print(AZUL + "| " + AMARILLO + i + RESET + AZUL + " |" + RESET);  // Etiqueta de fila
            for (int j = 0; j < V; j++) {
                if (matriz[i][j] == 1) {
                    System.out.print(VERDE + " " + matriz[i][j] + " " + RESET + AZUL + "|" + RESET);
                } else {
                    System.out.print(" " + matriz[i][j] + " " + AZUL + "|" + RESET);
                }
            }
            System.out.println();

            // Separador entre filas
            System.out.print(AZUL + "+");
            for (int k = 0; k < V + 1; k++) {
                System.out.print("---+");
            }
            System.out.println(RESET);
        }
    }

    // BFS (sin cambios, solo para que funcione)
    static void bfs(int inicio) {
        boolean[] visitado = new boolean[V];
        int[] cola = new int[V];
        int frente = 0, fondo = 0;

        cola[fondo++] = inicio;
        visitado[inicio] = true;

        while (frente < fondo) {
            int actual = cola[frente++];
            System.out.print(actual + " ");

            for (int vecino = 0; vecino < V; vecino++) {
                if (matriz[actual][vecino] == 1 && !visitado[vecino]) {
                    cola[fondo++] = vecino;
                    visitado[vecino] = true;
                }
            }
        }
    }

    // DFS (recursivo)
    static void dfs(int actual) {
        boolean[] visitado = new boolean[V];
        dfsRecursivo(actual, visitado);
        System.out.println();
    }

    static void dfsRecursivo(int actual, boolean[] visitado) {
        visitado[actual] = true;
        System.out.print(actual + " ");

        for (int vecino = 0; vecino < V; vecino++) {
            if (matriz[actual][vecino] == 1 && !visitado[vecino]) {
                dfsRecursivo(vecino, visitado);
            }
        }
    }
}
