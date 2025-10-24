/*
Grafo Dirigido
Un grafo dirigido (o dígrafo) es un grafo donde las aristas tienen dirección, como flechas. Esto significa que una conexión de A a B no implica automáticamente B a A.
Por qué es así: Modela relaciones unidireccionales, como enlaces web (una página apunta a otra, pero no viceversa), dependencias de tareas (tarea A precede a B) o flujo de tráfico en calles de un solo sentido.
Requisitos:

Aristas no simétricas: En la matriz, matriz[a][b] = 1 pero NO necesariamente matriz[b][a] = 1.
Puede tener ciclos o ser acíclico (DAG).
En código: Modificamos agregarArista para no agregar la arista reversa.
BFS y DFS se adaptan naturalmente, pero DFS puede usarse para topological sort en DAGs.
 */
public class GrafoDirigido {
    // Definimos una constante para el número de vértices en el grafo.
    // Aquí usamos 4 vértices (numerados de 0 a 3), pero puedes cambiar este valor
    // si necesitas más.
    static final int V = 4;
    // Creamos una matriz de adyacencia: es un array 2D (tabla) de enteros.
    // Cada fila representa un vértice origen, cada columna un vértice destino.
    // Si matriz[i][j] == 1, hay una arista dirigida de i a j.
    // Inicialmente, todo estará en 0 (sin conexiones).
    static int[][] matriz = new int[V][V];

    public static void main(String[] args) {
        // PASO 1: Inicializamos la matriz de adyacencia.
        // Recorremos todas las filas y columnas para poner todo en 0.
        // Esto asegura que no haya conexiones por defecto.
        for (int i = 0; i < V; i++) { // i es el índice de la fila (vértice origen)
            for (int j = 0; j < V; j++) { // j es el índice de la columna (vértice destino)
                matriz[i][j] = 0; // Ponemos 0: no hay arista entre i y j
            }
        }

        // PASO 2: Agregamos aristas dirigidas al grafo.
        // Cada llamada a agregarArista crea una conexión unidireccional de origen a
        // destino.
        // Ejemplo: agregarArista(0, 1) crea 0 → 1, pero NO 1 → 0.
        agregarArista(0, 1); // 0 → 1
        agregarArista(0, 2); // 0 → 2
        agregarArista(1, 3); // 1 → 3
        agregarArista(2, 3); // 2 → 3

        // PASO 3: Imprimimos la matriz de adyacencia como una tabla con bordes.
        // Nota: En un grafo dirigido, la matriz NO es simétrica (fila i/col j puede
        // diferir de fila j/col i).
        System.out.println("Matriz de Adyacencia (representación del grafo dirigido):");
        imprimirMatrizConBordes();

        // PASO 4: Ejecutamos BFS desde el vértice 0.
        // BFS explora nivel por nivel, respetando la dirección de las aristas.
        System.out.print("BFS desde 0: ");
        bfs(0);

        // PASO 5: Ejecutamos DFS desde el vértice 0.
        // DFS explora en profundidad, siguiendo las direcciones.
        System.out.print("\nDFS desde 0: ");
        dfs(0);
    }

    // Método para agregar una arista dirigida.
    // Parámetros: origen (vértice de salida), destino (vértice de llegada).
    // Solo ponemos 1 en matriz[origen][destino], NO en la reversa (porque es
    // dirigido).
    static void agregarArista(int origen, int destino) {
        matriz[origen][destino] = 1; // Hay conexión dirigida de origen a destino
        // NO agregamos matriz[destino][origen] = 1; esto lo hace dirigido
    }

    // Método para imprimir la matriz como una tabla con bordes.
    // Igual que en no dirigido, pero la matriz ahora refleja direcciones (no
    // simétrica).
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

    // Método para BFS en grafo dirigido.
    // Igual que en no dirigido, pero solo sigue aristas de actual a vecino
    // (matriz[actual][vecino]).
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
                if (matriz[actual][vecino] == 1 && !visitado[vecino]) { // Solo direcciones salientes
                    cola[fondo++] = vecino;
                    visitado[vecino] = true;
                }
            }
        }
    }

    // Método para DFS en grafo dirigido.
    // Igual, pero recursión sigue solo direcciones salientes.
    static void dfs(int actual) {
        boolean[] visitado = new boolean[V];
        dfsRecursivo(actual, visitado);
        System.out.println();
    }

    static void dfsRecursivo(int actual, boolean[] visitado) {
        visitado[actual] = true;
        System.out.print(actual + " ");
        for (int vecino = 0; vecino < V; vecino++) {
            if (matriz[actual][vecino] == 1 && !visitado[vecino]) { // Solo direcciones salientes
                dfsRecursivo(vecino, visitado);
            }
        }
    }
}