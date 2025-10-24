public class GrafoSimple {
    // Definimos una constante para el número de vértices en el grafo.
    // Aquí usamos 4 vértices (numerados de 0 a 3), pero puedes cambiar este valor
    // si necesitas más.
    static final int V = 4;
    // Creamos una matriz de adyacencia: es un array 2D (tabla) de enteros.
    // Cada fila representa un vértice origen, cada columna un vértice destino.
    // Si matriz[i][j] == 1, hay una arista (conexión) de i a j.
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

        // PASO 2: Agregamos aristas al grafo.
        // Cada llamada a agregarArista conecta dos vértices.
        // Por ejemplo, agregarArista(0, 1) crea una conexión entre 0 y 1.
        // Recuerda: en un grafo no dirigido, la conexión es bidireccional.
        agregarArista(0, 1); // Conecta 0 con 1
        agregarArista(0, 2); // Conecta 0 con 2
        agregarArista(1, 3); // Conecta 1 con 3
        agregarArista(2, 3); // Conecta 2 con 3

        // PASO 3: Imprimimos la matriz de adyacencia como una tabla con bordes.
        // Esto hace que se vea más claro, como una tabla real con líneas.
        // Usamos caracteres como '+' , '-' y '|' para dibujar los bordes.
        System.out.println("Matriz de Adyacencia (representación del grafo):");
        imprimirMatrizConBordes();

        // PASO 4: Ejecutamos BFS (Búsqueda en Anchura) desde el vértice 0.
        // BFS explora el grafo nivel por nivel, empezando desde el vértice inicial.
        System.out.print("BFS desde 0: ");
        bfs(0);

        // PASO 5: Ejecutamos DFS (Búsqueda en Profundidad) desde el vértice 0.
        // DFS explora lo más profundo posible por un camino antes de retroceder.
        System.out.print("\nDFS desde 0: ");
        dfs(0);

        // Ejercicios agregados (5 al 10)
        System.out.println("\n--- Ejercicios Agregados ---");
        System.out.println("Ejercicio 5: ¿Hay ciclo? " + tieneCiclo());
        System.out.println("Ejercicio 6: Número de componentes conectadas: " + contarComponentesConectadas());
        System.out.println("Ejercicio 7: Distancia más corta de 0 a 3: " + distanciaMasCorta(0, 3));
        System.out.println("Ejercicio 8: Grado del vértice 0: " + gradoVertice(0));
        System.out.println("Ejercicio 9: ¿Es bipartito? " + esBipartito());
        System.out.println("Ejercicio 10: ¿Es un árbol? " + esArbol());
    }

    // Método para agregar una arista entre dos vértices.
    // Parámetros: a (vértice origen), b (vértice destino).
    // Como es un grafo no dirigido, ponemos 1 en matriz[a][b] y en matriz[b][a].
    static void agregarArista(int a, int b) {
        matriz[a][b] = 1; // Hay conexión de a a b
        matriz[b][a] = 1; // Hay conexión de b a a (bidireccional)
    }

    // Método para imprimir la matriz como una tabla con bordes.
    // Esto hace que sea más visual y claro para entender el grafo.
    // Dibujamos líneas horizontales con '+' y '-', y verticales con '|'.
    static void imprimirMatrizConBordes() {
        // Primero, imprimimos la línea superior de la tabla.
        // Creamos una línea con '+' en las esquinas y '-' en medio.
        System.out.print("+"); // Esquina superior izquierda
        for (int i = 0; i < V + 1; i++) { // +1 para la columna de etiquetas
            System.out.print("---+"); // Línea horizontal para cada celda
        }
        System.out.println(); // Salto de línea

        // Imprimimos la fila de encabezados (etiquetas de columnas: vértices).
        System.out.print("|   |"); // Espacio para la esquina superior izquierda (vacía)
        for (int i = 0; i < V; i++) { // Recorremos los vértices (columnas)
            System.out.print(" " + i + " |"); // Imprimimos el número del vértice
        }
        System.out.println(); // Salto de línea

        // Imprimimos una línea horizontal separadora después de los encabezados.
        System.out.print("+"); // Esquina
        for (int i = 0; i < V + 1; i++) {
            System.out.print("---+"); // Línea horizontal
        }
        System.out.println(); // Salto de línea

        // Ahora imprimimos cada fila de la matriz.
        for (int i = 0; i < V; i++) { // i es la fila (vértice origen)
            System.out.print("| " + i + " |"); // Etiqueta de la fila (número del vértice)
            for (int j = 0; j < V; j++) { // j es la columna (vértice destino)
                System.out.print(" " + matriz[i][j] + " |"); // Imprimimos el valor (0 o 1)
            }
            System.out.println(); // Salto de línea después de la fila

            // Imprimimos una línea horizontal separadora después de cada fila.
            System.out.print("+"); // Esquina
            for (int k = 0; k < V + 1; k++) {
                System.out.print("---+"); // Línea horizontal
            }
            System.out.println(); // Salto de línea
        }
    }

    // Método para BFS (Búsqueda en Anchura).
    // Usamos una cola implementada con un array simple (sin Queue de Java).
    // Parámetro: inicio (vértice desde donde empezamos la búsqueda).
    static void bfs(int inicio) {
        // Creamos un array de booleanos para marcar qué vértices ya visitamos.
        // Inicialmente, todos son false (no visitados).
        boolean[] visitado = new boolean[V];
        // Creamos un array para la cola (fila de vértices pendientes).
        // Usamos un array de enteros de tamaño V (máximo posible).
        int[] cola = new int[V];
        // Variables para manejar la cola: frente (principio), fondo (final).
        // Inicialmente, la cola está vacía (frente = 0, fondo = 0).
        int frente = 0;
        int fondo = 0;
        // Agregamos el vértice inicial a la cola y lo marcamos como visitado.
        cola[fondo] = inicio; // Ponemos en la cola
        fondo++; // Avanzamos el fondo
        visitado[inicio] = true; // Marcamos como visitado
        // Mientras la cola no esté vacía (frente < fondo).
        while (frente < fondo) {
            // Sacamos el vértice actual del frente de la cola.
            int actual = cola[frente];
            frente++; // Avanzamos el frente
            // Imprimimos el vértice actual (lo visitamos).
            System.out.print(actual + " ");
            // Recorremos todos los posibles vecinos (de 0 a V-1).
            for (int vecino = 0; vecino < V; vecino++) {
                // Si hay arista (matriz[actual][vecino] == 1) y no está visitado.
                if (matriz[actual][vecino] == 1 && !visitado[vecino]) {
                    // Agregamos el vecino a la cola.
                    cola[fondo] = vecino;
                    fondo++; // Avanzamos el fondo
                    // Marcamos como visitado para no repetirlo.
                    visitado[vecino] = true;
                }
            }
        }
    }

    // Método para DFS (Búsqueda en Profundidad).
    // Usamos recursión (el método se llama a sí mismo).
    // Parámetro: actual (vértice inicial).
    static void dfs(int actual) {
        // Creamos un array de booleanos para marcar visitados.
        boolean[] visitado = new boolean[V];
        // Llamamos al método recursivo para empezar la búsqueda.
        dfsRecursivo(actual, visitado);
        // Imprimimos un salto de línea al final.
        System.out.println();
    }

    // Método recursivo para DFS.
    // Parámetros: actual (vértice que estamos visitando), visitado (array de
    // marcas).
    static void dfsRecursivo(int actual, boolean[] visitado) {
        // Marcamos el vértice actual como visitado.
        visitado[actual] = true;
        // Imprimimos el vértice actual.
        System.out.print(actual + " ");
        // Recorremos todos los posibles vecinos (de 0 a V-1).
        for (int vecino = 0; vecino < V; vecino++) {
            // Si hay arista (matriz[actual][vecino] == 1) y no está visitado.
            if (matriz[actual][vecino] == 1 && !visitado[vecino]) {
                // Llamamos recursivamente para visitar el vecino.
                dfsRecursivo(vecino, visitado);
            }
        }
    }

    /**
     * Ejercicio 5: Detectar si hay ciclo en el grafo.
     * 
     * Finalidad: Verificar si el grafo contiene al menos un ciclo (un camino que
     * vuelve al punto de partida).
     * Para qué: Útil para detectar redundancias en redes (ej. en rutas de
     * transporte) o para validar si un grafo es acíclico (como en dependencias de
     * tareas).
     * Cómo: Usa DFS modificado. Durante la exploración, si encontramos un vecino ya
     * visitado que NO es el padre del vértice actual, hay ciclo.
     * 
     * @return true si hay ciclo, false si no.
     */
    static boolean tieneCiclo() {
        boolean[] visitado = new boolean[V];
        // Recorremos todos los vértices por si hay componentes desconectadas.
        for (int i = 0; i < V; i++) {
            if (!visitado[i]) {
                if (tieneCicloDFS(i, visitado, -1)) { // -1 es "sin padre" inicial
                    return true;
                }
            }
        }
        return false;
    }

    // Helper recursivo para tieneCiclo.
    private static boolean tieneCicloDFS(int actual, boolean[] visitado, int padre) {
        visitado[actual] = true;
        for (int vecino = 0; vecino < V; vecino++) {
            if (matriz[actual][vecino] == 1) {
                if (!visitado[vecino]) {
                    if (tieneCicloDFS(vecino, visitado, actual)) {
                        return true;
                    }
                } else if (vecino != padre) { // Vecino visitado y no es padre → ciclo
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Ejercicio 6: Contar componentes conectadas.
     * 
     * Finalidad: Determinar cuántos "grupos aislados" hay en el grafo (subgrafos
     * donde todos están conectados internamente, pero no con otros).
     * Para qué: Útil para analizar redes desconectadas (ej. islas en un mapa o
     * clústers en redes sociales).
     * Cómo: Usa DFS o BFS para explorar cada componente. Cada vez que inicias una
     * búsqueda desde un vértice no visitado, incrementas el contador.
     * 
     * @return Número de componentes conectadas.
     */
    static int contarComponentesConectadas() {
        boolean[] visitado = new boolean[V];
        int count = 0;
        for (int i = 0; i < V; i++) {
            if (!visitado[i]) {
                dfsRecursivo(i, visitado); // Explora el componente
                count++; // Nuevo componente encontrado
            }
        }
        return count;
    }

    /**
     * Ejercicio 7: Camino más corto (sin pesos).
     * 
     * Finalidad: Encontrar la distancia mínima en número de aristas entre dos
     * vértices.
     * Para qué: Útil en navegación (ej. número de paradas en metro) o redes
     * sociales (grados de separación).
     * Cómo: Usa BFS, ya que explora por niveles. Cada nivel representa +1 en
     * distancia. Retorna -1 si no hay camino.
     * 
     * @param inicio Vértice de partida.
     * @param fin    Vértice de llegada.
     * @return Distancia (número de aristas) o -1 si no conectado.
     */
    static int distanciaMasCorta(int inicio, int fin) {
        if (inicio == fin)
            return 0;
        boolean[] visitado = new boolean[V];
        int[] distancia = new int[V];
        int[] cola = new int[V];
        int frente = 0, fondo = 0;

        cola[fondo++] = inicio;
        visitado[inicio] = true;
        distancia[inicio] = 0;

        while (frente < fondo) {
            int actual = cola[frente++];
            for (int vecino = 0; vecino < V; vecino++) {
                if (matriz[actual][vecino] == 1 && !visitado[vecino]) {
                    cola[fondo++] = vecino;
                    visitado[vecino] = true;
                    distancia[vecino] = distancia[actual] + 1;
                    if (vecino == fin)
                        return distancia[vecino];
                }
            }
        }
        return -1; // No hay camino
    }

    /**
     * Ejercicio 8: Grado de un vértice.
     * 
     * Finalidad: Contar cuántas aristas salen de un vértice (número de conexiones).
     * Para qué: Mide la "importancia" de un nodo (ej. en redes sociales, amigos de
     * una persona).
     * Cómo: Suma los 1's en la fila de la matriz para ese vértice.
     * 
     * @param vertice El vértice a consultar.
     * @return Grado (número de aristas conectadas).
     */
    static int gradoVertice(int vertice) {
        int grado = 0;
        for (int j = 0; j < V; j++) {
            grado += matriz[vertice][j]; // Suma 1 por cada conexión
        }
        return grado;
    }

    /**
     * Ejercicio 9: Verificar si es bipartito.
     * 
     * Finalidad: Comprobar si el grafo se puede dividir en dos grupos donde no hay
     * conexiones internas.
     * Para qué: Útil en scheduling (ej. asignar colores a mapas) o modelar
     * conflictos (ej. bipartición en redes).
     * Cómo: Usa BFS para colorear vértices alternadamente (color 0 y 1). Si un
     * vecino tiene el mismo color, no es bipartito.
     * 
     * @return true si es bipartito, false si no.
     */
    static boolean esBipartito() {
        int[] color = new int[V]; // -1: sin color, 0 o 1: colores
        for (int i = 0; i < V; i++)
            color[i] = -1;

        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                if (!esBipartitoBFS(i, color))
                    return false;
            }
        }
        return true;
    }

    // Helper para esBipartito.
    private static boolean esBipartitoBFS(int inicio, int[] color) {
        int[] cola = new int[V];
        int frente = 0, fondo = 0;
        cola[fondo++] = inicio;
        color[inicio] = 0;

        while (frente < fondo) {
            int actual = cola[frente++];
            for (int vecino = 0; vecino < V; vecino++) {
                if (matriz[actual][vecino] == 1) {
                    if (color[vecino] == -1) {
                        color[vecino] = 1 - color[actual]; // Color opuesto
                        cola[fondo++] = vecino;
                    } else if (color[vecino] == color[actual]) {
                        return false; // Mismo color → no bipartito
                    }
                }
            }
        }
        return true;
    }

    /**
     * Ejercicio 10: Verificar si es un árbol.
     * 
     * Finalidad: Comprobar si el grafo es conectado y sin ciclos (un árbol:
     * exactamente V-1 aristas).
     * Para qué: Modela estructuras jerárquicas (ej. árboles genealógicos o redes
     * sin redundancias).
     * Cómo: Verifica que haya exactamente 1 componente conectada y no haya ciclos.
     * 
     * @return true si es árbol, false si no.
     */
    static boolean esArbol() {
        if (contarComponentesConectadas() != 1)
            return false; // Debe ser conectado
        if (tieneCiclo())
            return false; // No debe tener ciclos
        return true;
    }
}