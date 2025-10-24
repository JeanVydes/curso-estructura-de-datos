/*
Grafo Conectado
Un grafo conectado es uno donde hay un camino entre cualquier par de vértices (todo unido).
Por qué es así: Representa sistemas integrados, como redes completas sin islas aisladas.
Requisitos:

Al menos un camino entre todos los vértices.
En código: El original ya es conectado (nuestro ejemplo lo es), así que usamos el mismo, pero agregamos comentarios enfatizando conectividad.
Si no es conectado, BFS/DFS no visitan todo desde un inicio.
 */
public class GrafoConectado {
    // Definimos una constante para el número de vértices en el grafo.
    // Aquí usamos 4 vértices, y el grafo es conectado: hay caminos entre todos.
    static final int V = 4;
    // Matriz de adyacencia: 1 indica arista, 0 no.
    // Para conectividad, aseguramos aristas que unan todo (ej. sin componentes
    // aisladas).
    static int[][] matriz = new int[V][V];

    public static void main(String[] args) {
        // PASO 1: Inicializamos la matriz en 0.
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                matriz[i][j] = 0;
            }
        }

        // PASO 2: Agregamos aristas para asegurar conectividad.
        // Con estas aristas, todo está conectado: desde 0 se alcanza 1,2,3.
        agregarArista(0, 1); // Conecta 0-1
        agregarArista(0, 2); // Conecta 0-2
        agregarArista(1, 3); // Conecta 1-3
        agregarArista(2, 3); // Conecta 2-3
        // Nota: Si quitamos una, podría desconectarse (ej. sin 1-3 y 2-3, 3 aislado).

        // PASO 3: Imprimimos la matriz.
        // En un conectado, BFS/DFS desde cualquier vértice visita todos.
        System.out.println("Matriz de Adyacencia (representación del grafo conectado):");
        imprimirMatrizConBordes();

        // PASO 4: BFS: en conectado, visita todos los vértices.
        System.out.print("BFS desde 0: ");
        bfs(0);

        // PASO 5: DFS: igual, cubre todo.
        System.out.print("\nDFS desde 0: ");
        dfs(0);
    }

    // Agregar arista no dirigida (simétrica para conectividad bidireccional).
    static void agregarArista(int a, int b) {
        matriz[a][b] = 1;
        matriz[b][a] = 1;
    }

    // Imprimir matriz: en conectado, no hay filas/columnas todo 0 (excepto
    // aislados, pero aquí no).
    static void imprimirMatrizConBordes() {
        System.out.print("+");
        for (int i = 0; i < V + 1; i++) {
            System.out.print("---+");
        }
        System.out.println();

        System.out.print("|   |");
        for (int i = 0; i < V; i++) {
            System.out.print(" " + i + " |");
        }
        System.out.println();

        System.out.print("+");
        for (int i = 0; i < V + 1; i++) {
            System.out.print("---+");
        }
        System.out.println();

        for (int i = 0; i < V; i++) {
            System.out.print("| " + i + " |");
            for (int j = 0; j < V; j++) {
                System.out.print(" " + matriz[i][j] + " |");
            }
            System.out.println();

            System.out.print("+");
            for (int k = 0; k < V + 1; k++) {
                System.out.print("---+");
            }
            System.out.println();
        }
    }

    // BFS: en conectado, la cola eventualmente incluye todos.
    static void bfs(int inicio) {
        boolean[] visitado = new boolean[V];
        int[] cola = new int[V];
        int frente = 0;
        int fondo = 0;
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

    // DFS: recursión alcanza todos en conectado.
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
