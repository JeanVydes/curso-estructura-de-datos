/*
Grafo Ponderado
Un grafo ponderado es un grafo donde cada arista tiene un "peso" (número), representando costo, distancia o tiempo.
Por qué es así: Permite modelar escenarios reales con valores cuantitativos, como distancias en mapas (km) o costos en redes (dinero).
Requisitos:

Matriz usa enteros >0 para pesos (en vez de 1/0).
0 significa no arista.
Algoritmos como BFS/DFS se adaptan, pero para caminos más cortos se usa Dijkstra (no incluido aquí, pero se podría agregar).
En código: Cambiamos matriz a pesos, y agregarArista toma un parámetro extra para peso.
 */
public class GrafoPonderado {
    // Definimos una constante para el número de vértices en el grafo.
    // Aquí usamos 4 vértices (numerados de 0 a 3).
    static final int V = 4;
    // Matriz de adyacencia para pesos: valores >0 indican peso de arista, 0 indica
    // no arista.
    // Usamos int para pesos positivos (puedes usar double para fracciones).
    static int[][] matriz = new int[V][V];

    public static void main(String[] args) {
        // PASO 1: Inicializamos la matriz en 0 (no aristas).
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                matriz[i][j] = 0;
            }
        }

        // PASO 2: Agregamos aristas ponderadas.
        // Ahora agregarArista toma un peso (ej. distancia o costo).
        // Como es no dirigido, ponemos peso en ambas direcciones.
        agregarArista(0, 1, 4); // 0 —4— 1
        agregarArista(0, 2, 2); // 0 —2— 2
        agregarArista(1, 3, 1); // 1 —1— 3
        agregarArista(2, 3, 3); // 2 —3— 3

        // PASO 3: Imprimimos la matriz (pesos en lugar de 1/0).
        // La tabla muestra pesos; valores >9 pueden desalinear, pero para ejemplo OK.
        System.out.println("Matriz de Adyacencia (representación del grafo ponderado):");
        imprimirMatrizConBordes();

        // PASO 4: BFS adaptado para ponderado (ignora pesos, solo conectividad).
        System.out.print("BFS desde 0: ");
        bfs(0);

        // PASO 5: DFS adaptado (ignora pesos).
        System.out.print("\nDFS desde 0: ");
        dfs(0);
    }

    // Método para agregar arista ponderada no dirigida.
    // Parámetros: a, b (vértices), peso (valor positivo).
    // Ponemos peso en matriz[a][b] y matriz[b][a].
    static void agregarArista(int a, int b, int peso) {
        matriz[a][b] = peso; // Peso de a a b
        matriz[b][a] = peso; // Peso de b a a (no dirigido)
    }

    // Imprimir matriz: ahora muestra pesos (pueden ser >1).
    static void imprimirMatrizConBordes() {
        System.out.print("+");
        for (int i = 0; i < V + 1; i++) {
            System.out.print("----+"); // Más espacio para pesos >9
        }
        System.out.println();

        System.out.print("|    |");
        for (int i = 0; i < V; i++) {
            System.out.print("  " + i + " |");
        }
        System.out.println();

        System.out.print("+");
        for (int i = 0; i < V + 1; i++) {
            System.out.print("----+");
        }
        System.out.println();

        for (int i = 0; i < V; i++) {
            System.out.print("|  " + i + " |");
            for (int j = 0; j < V; j++) {
                System.out.print("  " + matriz[i][j] + " |");
            }
            System.out.println();

            System.out.print("+");
            for (int k = 0; k < V + 1; k++) {
                System.out.print("----+");
            }
            System.out.println();
        }
    }

    // BFS para ponderado: ignora pesos, trata como si hubiera arista si >0.
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
                if (matriz[actual][vecino] > 0 && !visitado[vecino]) { // Arista si peso >0
                    cola[fondo++] = vecino;
                    visitado[vecino] = true;
                }
            }
        }
    }

    // DFS para ponderado: similar, arista si peso >0.
    static void dfs(int actual) {
        boolean[] visitado = new boolean[V];
        dfsRecursivo(actual, visitado);
        System.out.println();
    }

    static void dfsRecursivo(int actual, boolean[] visitado) {
        visitado[actual] = true;
        System.out.print(actual + " ");
        for (int vecino = 0; vecino < V; vecino++) {
            if (matriz[actual][vecino] > 0 && !visitado[vecino]) { // Arista si peso >0
                dfsRecursivo(vecino, visitado);
            }
        }
    }
}
