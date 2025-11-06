/**
 * Clase ArbolExpansionMinimo
 * Esta clase muestra cómo encontrar un Árbol de Expansión Mínimo (MST por sus siglas en inglés).
 * ¿Qué es un Árbol de Expansión Mínimo? Imagina un mapa con ciudades (puntos) conectadas por carreteras con costos (como distancias o precios).
 * El MST es una forma de conectar TODAS las ciudades con el menor costo total posible, sin hacer círculos (ciclos) que desperdicien recursos.
 * Es como elegir las carreteras más baratas que unan todo sin repetir caminos innecesarios.
 * 
 * IMPORTANTE: Funciona en grafos no dirigidos (las conexiones van en ambas direcciones) y con costos positivos.
 * Usamos una matriz para representar el grafo (como en ejemplos anteriores).
 * Implementaremos una versión recursiva del algoritmo de Prim: Empieza desde un punto y va agregando la conexión más barata disponible, recursivamente.
 * 
 * Imagen simple del grafo de ejemplo (en texto):
 * (0) --2-- (1) --4-- (2)
 *  |         ^         |
 *  |         | 3       | 1
 * 5|         |         |
 *  v         |         v
 * (3) --6-- (4)
 * 
 * Costos: El MST debería elegir conexiones como 0-1 (2), 1-2 (4), 0-3 (5), pero calculará el mínimo total.
 * Nota: Para este ejemplo, usamos 5 puntos.
 */
public class ArbolExpansionMinimo {
    // V: Número de puntos (nodos). Usamos 5 para el ejemplo.
    static final int V = 5;
    
    // INF: Valor grande para representar "sin conexión" o infinito.
    static final int INF = Integer.MAX_VALUE;
    
    // Matriz de costos: costos[i][j] = costo de i a j (simétrico, ya que no dirigido).
    // Si INF, no hay conexión directa.
    static int[][] costos = new int[V][V];

    // -----------------------------------------------------------------------------------
    // --- PARTES PRINCIPALES PARA CALCULAR EL MST (VERSIÓN RECURSIVA DE PRIM) ---
    // -----------------------------------------------------------------------------------
    
    /**
     * Función clave: Encontrar el punto con el costo mínimo no incluido aún.
     * Explicación simple: De los puntos que no están en el árbol todavía,
     * elige el que se puede conectar con el menor costo.
     * 
     * @param clave Lista de costos mínimos para cada punto.
     * @param incluido Lista de si el punto ya está en el árbol.
     * @return El número del punto elegido.
     */
    static int encontrarMinClave(int[] clave, boolean[] incluido) {
        // min: El costo más bajo encontrado (empieza en infinito).
        int min = INF;
        // minIndex: El punto con ese costo mínimo.
        int minIndex = -1;
        
        // Revisamos todos los puntos.
        for (int v = 0; v < V; v++) {
            // Si no incluido y su clave es menor que min.
            if (!incluido[v] && clave[v] < min) {
                min = clave[v];
                minIndex = v;
            }
        }
        
        return minIndex;
    }
    
    /**
     * Algoritmo de Prim recursivo para MST.
     * Explicación simple: Empieza desde un punto (inicio=0).
     * Agrega puntos uno por uno: Siempre elige el más barato para conectar al árbol actual.
     * La recursión simula agregar el siguiente punto y repetir hasta que todos estén incluidos.
     * 
     * Imagen simple del proceso (en texto):
     * Empieza en 0 → Agrega conexión más barata (ej. a 1) → Ahora desde 0 o 1, agrega siguiente barata → Repite hasta todos.
     * 
     * @param padre Lista para recordar conexiones (padre[v] = u significa u conectado a v).
     * @param clave Lista de costos mínimos para conectar cada punto.
     * @param incluido Lista de puntos ya en el árbol.
     * @param conteo Cuántos puntos hemos agregado (para parar cuando sea V).
     */
    static void primRecursivo(int[] padre, int[] clave, boolean[] incluido, int conteo) {
        // Caso base: Si ya agregamos todos los puntos, paramos.
        if (conteo == V) {
            return;
        }
        
        // u: El punto con costo mínimo no incluido.
        int u = encontrarMinClave(clave, incluido);
        
        // Si no hay más, paramos (aunque no debería pasar).
        if (u == -1) {
            return;
        }
        
        // Marcamos u como incluido en el árbol.
        incluido[u] = true;
        
        // Actualizamos los vecinos de u: Si podemos conectar más barato a través de u.
        for (int v = 0; v < V; v++) {
            // Si v no incluido, hay conexión u-v, y costo u-v < clave actual de v.
            if (!incluido[v] && costos[u][v] != INF && costos[u][v] < clave[v]) {
                // Actualizamos clave de v y recordamos que viene de u.
                clave[v] = costos[u][v];
                padre[v] = u;
            }
        }
        
        // Llamada recursiva: Agregamos uno más (conteo +1).
        primRecursivo(padre, clave, incluido, conteo + 1);
    }
    
    /**
     * Función para empezar el MST recursivo.
     * Explicación: Prepara las listas y llama a la recursiva.
     * Al final, muestra el resultado.
     * 
     * @return El costo total del MST.
     */
    static int mstRecursivo() {
        // padre: Conexiones del árbol.
        int[] padre = new int[V];
        // clave: Costo mínimo para cada punto (inicia en infinito).
        int[] clave = new int[V];
        // incluido: Si el punto está en el árbol.
        boolean[] incluido = new boolean[V];
        
        // Inicializamos.
        for (int i = 0; i < V; i++) {
            clave[i] = INF;
            incluido[i] = false;
            padre[i] = -1;
        }
        
        // Empezamos desde punto 0, costo 0.
        clave[0] = 0;
        
        // Llamamos recursiva (conteo=0 al inicio).
        primRecursivo(padre, clave, incluido, 0);
        
        // Calculamos costo total sumando claves (excepto 0).
        int costoTotal = 0;
        for (int i = 1; i < V; i++) {
            costoTotal += clave[i];
        }
        
        // Mostramos las conexiones.
        System.out.println("Conexiones del Árbol de Expansión Mínimo:");
        for (int i = 1; i < V; i++) {
            System.out.println(padre[i] + " - " + i + " con costo " + clave[i]);
        }
        
        return costoTotal;
    }
    
    // -----------------------------------------------------------------------------------
    // --- PARTES DE AYUDA (CREAR GRAFO, MOSTRAR, PRINCIPAL) ---
    // -----------------------------------------------------------------------------------
    
    /**
     * Agregar conexión no dirigida con costo.
     * Explicación: Pone el costo en ambas direcciones (i-j y j-i).
     */
    static void agregarConexion(int u, int v, int costo) {
        costos[u][v] = costo;
        costos[v][u] = costo;
    }
    
    /**
     * Mostrar matriz de costos.
     * Explicación: Imprime la tabla de costos.
     */
    static void mostrarMatrizCostos() {
        System.out.print("+");
        for (int i = 0; i < V + 1; i++) {
            System.out.print("-----");
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
                if (costos[i][j] == INF) {
                    System.out.printf(" INF |");
                } else {
                    System.out.printf(" %3d |", costos[i][j]);
                }
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
     * Método principal para correr.
     * Crea el grafo, muestra y calcula MST.
     */
    public static void main(String[] args) {
        // Inicializamos matriz en INF (sin conexiones).
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                costos[i][j] = INF;
            }
        }
        
        // Agregamos conexiones del ejemplo.
        agregarConexion(0, 1, 2);
        agregarConexion(0, 3, 5);
        agregarConexion(1, 2, 4);
        agregarConexion(1, 4, 3);
        agregarConexion(2, 4, 1);
        agregarConexion(3, 4, 6);
        
        System.out.println("Matriz de Costos del Grafo:");
        mostrarMatrizCostos();
        
        System.out.println("\nCalculando el Árbol de Expansión Mínimo Recursivo...");
        int costoMin = mstRecursivo();
        System.out.println("Costo total mínimo: " + costoMin);
    }
}
