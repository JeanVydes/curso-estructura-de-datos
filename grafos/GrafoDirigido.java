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

    /**
     * Ejercicio 5: Detectar si hay ciclo en el grafo.
     * * Finalidad: Verificar si el grafo contiene al menos un ciclo (un camino que
     * vuelve al punto de partida).
     * Para qué: Útil para detectar redundancias en redes (ej. en rutas de
     * transporte) o
     * para validar si un grafo es acíclico (como en dependencias de tareas).
     * Cómo: Usa DFS modificado. Durante la exploración, si encontramos un vecino ya
     * visitado que NO es el padre del vértice actual, hay ciclo.
     * * @return true si hay ciclo, false si no.
     */
    static boolean tieneCiclo() {
        // 1. Inicializa un arreglo para marcar los vértices visitados.
        boolean[] visitado = new boolean[V];

        // 2. Recorremos todos los vértices por si hay componentes desconectadas.
        for (int i = 0; i < V; i++) {
            // 3. Si un vértice 'i' no ha sido visitado, iniciamos una nueva búsqueda DFS
            // desde él.
            if (!visitado[i]) {
                // 4. Llamamos a la función DFS auxiliar.
                // Pasamos -1 como 'padre' inicial, ya que la raíz de DFS no tiene padre.
                if (tieneCicloDFS(i, visitado, -1)) {
                    return true; // Si se detecta un ciclo en esta componente, retornamos true inmediatamente.
                }
            }
        }
        // 5. Si terminamos de recorrer todas las componentes sin encontrar ciclos,
        // retornamos false.
        return false;
    }

    private static boolean tieneCicloDFS(int actual, boolean[] visitado, int padre) {
        // 1. Marca el vértice actual como visitado.
        visitado[actual] = true;

        // 2. Itera sobre todos los posibles vecinos (columnas de la matriz).
        for (int vecino = 0; vecino < V; vecino++) {
            // 3. Verifica si hay una arista (conexión) del 'actual' al 'vecino'.
            if (matriz[actual][vecino] == 1) {

                // 4. Caso A: Vecino no visitado.
                if (!visitado[vecino]) {
                    // Realiza la llamada recursiva (DFS) para explorar el camino.
                    // El 'actual' se convierte en el 'padre' para la próxima llamada.
                    if (tieneCicloDFS(vecino, visitado, actual)) {
                        return true; // Propaga el resultado: si se detectó ciclo más adelante, retornamos true.
                    }
                }
                // 5. Caso B: Vecino ya visitado.
                // Esto solo indica un ciclo si el vecino visitado NO es el nodo del que
                // acabamos de venir.
                else if (vecino != padre) {
                    // Si el 'vecino' ya fue visitado Y no es el 'padre' (el nodo anterior), hemos
                    // encontrado un camino
                    // que regresa a un nodo ya explorado, cerrando un ciclo.
                    return true;
                }
            }
        }
        // 6. Si el DFS termina sin encontrar un ciclo partiendo de este camino, retorna
        // false.
        return false;
    }

    // -------------------------------------------------------------------------------------------------

    /**
     * Ejercicio 6: Contar componentes conectadas.
     * * Finalidad: Determinar cuántos "grupos aislados" hay en el grafo (subgrafos
     * donde todos están conectados internamente, pero no con otros).
     * Para qué: Útil para analizar redes desconectadas (ej. islas en un mapa o
     * clústers en redes sociales).
     * Cómo: Usa DFS o BFS para explorar cada componente. Cada vez que inicias una
     * búsqueda desde un vértice no visitado, incrementas el contador.
     * * @return Número de componentes conectadas.
     */
    static int contarComponentesConectadas() {
        // 1. Inicializa el arreglo de visitados para rastrear qué vértices ya se
        // exploraron.
        boolean[] visitado = new boolean[V];
        // 2. Inicializa el contador de componentes a cero.
        int count = 0;

        // 3. Recorre todos los posibles vértices iniciales.
        for (int i = 0; i < V; i++) {
            // 4. Si el vértice 'i' no ha sido visitado, significa que pertenece a una NUEVA
            // componente.
            if (!visitado[i]) {
                // 5. Llama a DFS para marcar todos los vértices de esta componente como
                // visitados.
                // La función dfsRecursivo solo se encarga de explorar y marcar, no retorna
                // nada.
                dfsRecursivo(i, visitado);
                // 6. Incrementa el contador, ya que hemos terminado de explorar una nueva
                // componente conectada.
                count++;
            }
        }
        // 7. Retorna el número total de veces que tuvimos que iniciar un nuevo DFS.
        return count;
    }

    // Nota: dfsRecursivo (no incluido aquí) es la función estándar de DFS que marca
    // como visitados a todos los vecinos
    // y sus descendientes, explorando un componente completo.

    // -------------------------------------------------------------------------------------------------

    /**
     * Ejercicio 7: Camino más corto (sin pesos).
     * * Finalidad: Encontrar la distancia mínima en número de aristas entre dos
     * vértices.
     * Para qué: Útil en navegación (ej. número de paradas en metro) o redes
     * sociales (grados de separación).
     * Cómo: Usa BFS (Búsqueda en Amplitud), ya que explora por niveles. Cada nivel
     * representa +1 en distancia. Retorna -1 si no hay camino.
     * * @param inicio Vértice de partida.
     * 
     * @param fin Vértice de llegada.
     * @return Distancia (número de aristas) o -1 si no conectado.
     */
    static int distanciaMasCorta(int inicio, int fin) {
        // 1. Caso base: si inicio y fin son el mismo vértice, la distancia es 0.
        if (inicio == fin)
            return 0;

        // 2. Inicializa estructuras de datos BFS:
        boolean[] visitado = new boolean[V]; // Para evitar ciclos y re-visitar.
        int[] distancia = new int[V]; // Para guardar la distancia desde el 'inicio' a cada nodo.
        int[] cola = new int[V]; // Implementa la cola (FIFO) para BFS.
        int frente = 0, fondo = 0; // Punteros de la cola.

        // 3. Configura el vértice inicial:
        cola[fondo++] = inicio; // Coloca el inicio en la cola.
        visitado[inicio] = true; // Marca el inicio como visitado.
        distancia[inicio] = 0; // Su distancia a sí mismo es 0.

        // 4. Procesa la cola mientras no esté vacía.
        while (frente < fondo) {
            // Saca el vértice 'actual' de la cola (desencolar).
            int actual = cola[frente++];

            // Itera sobre todos los posibles vecinos del 'actual'.
            for (int vecino = 0; vecino < V; vecino++) {
                // Verifica si hay una arista Y si el vecino NO ha sido visitado.
                if (matriz[actual][vecino] == 1 && !visitado[vecino]) {

                    cola[fondo++] = vecino; // Encola el vecino.
                    visitado[vecino] = true; // Marca como visitado.

                    // La distancia al vecino es la distancia del nodo actual + 1 arista.
                    distancia[vecino] = distancia[actual] + 1;

                    // Si encontramos el destino, retornamos la distancia inmediatamente.
                    // BFS garantiza que este es el camino más corto.
                    if (vecino == fin)
                        return distancia[vecino];
                }
            }
        }
        // 5. Si la cola se vacía y el 'fin' nunca fue encontrado, significa que no hay
        // camino.
        return -1;
    }

    // -------------------------------------------------------------------------------------------------

    /**
     * Ejercicio 8: Grado de un vértice (Entrada y Salida).
     * * Finalidad: Contar cuántas aristas SALEN de un vértice (grado de salida) y
     * cuántas aristas ENTRAN (grado de entrada).
     * Para qué: Mide la "influencia" de un nodo (salida) y la "popularidad" o
     * "dependencia" (entrada) en un dígrafo.
     * Cómo: Grado de Salida: Suma 1's en la fila. Grado de Entrada: Suma 1's en la
     * columna.
     * * @param vertice El vértice a consultar.
     * 
     * @return Un array [gradoSalida, gradoEntrada].
     */
    static int[] gradoVertice(int vertice) {
        int gradoSalida = 0;
        int gradoEntrada = 0;

        // 1. Grado de Salida (Out-degree): Se calcula sumando la fila del vértice.
        for (int j = 0; j < V; j++) {
            // La conexión matriz[vertice][j] = 1 significa una arista que SALE de
            // 'vertice'.
            gradoSalida += matriz[vertice][j];
        }

        // 2. Grado de Entrada (In-degree): Se calcula sumando la columna del vértice.
        for (int i = 0; i < V; i++) {
            // La conexión matriz[i][vertice] = 1 significa una arista que ENTRA a
            // 'vertice'.
            gradoEntrada += matriz[i][vertice];
        }

        // 3. Retorna un array con ambos grados.
        return new int[] { gradoSalida, gradoEntrada };
    }
    // -------------------------------------------------------------------------------------------------

    /**
     * Ejercicio 9: Verificar si es bipartito.
     * * Finalidad: Comprobar si el grafo se puede dividir en dos grupos
     * (particiones)
     * donde no hay conexiones internas (solo entre grupos).
     * Para qué: Útil en scheduling (ej. asignar colores a mapas) o modelar
     * conflictos (ej. bipartición en redes).
     * Cómo: Usa BFS para colorear vértices alternadamente (color 0 y 1). Si un
     * vecino tiene el mismo color, no es bipartito.
     * * @return true si es bipartito, false si no.
     */
    static boolean esBipartito() {
        // 1. Inicializa el arreglo de colores. -1: sin color, 0 o 1: colores.
        int[] color = new int[V];
        for (int i = 0; i < V; i++)
            color[i] = -1; // Inicialmente, todos los vértices no tienen color.

        // 2. Itera sobre todos los vértices. Esto maneja grafos con componentes
        // desconectadas.
        for (int i = 0; i < V; i++) {
            // 3. Si un vértice no tiene color, inicia una nueva comprobación BFS en su
            // componente.
            if (color[i] == -1) {
                // Si la comprobación BFS en esta componente falla, el grafo NO es bipartito.
                if (!esBipartitoBFS(i, color))
                    return false;
            }
        }
        // 4. Si todas las componentes son bipartitas, el grafo completo es bipartito.
        return true;
    }

    // Helper para esBipartito.
    private static boolean esBipartitoBFS(int inicio, int[] color) {
        // 1. Inicializa la cola para la búsqueda BFS.
        int[] cola = new int[V];
        int frente = 0, fondo = 0;

        // 2. Configura el vértice inicial:
        cola[fondo++] = inicio;
        color[inicio] = 0; // Asigna el primer color (0) al nodo de inicio.

        // 3. Procesa la cola mientras no esté vacía.
        while (frente < fondo) {
            int actual = cola[frente++];

            // Itera sobre todos los posibles vecinos.
            for (int vecino = 0; vecino < V; vecino++) {
                // 4. Verifica si hay una arista entre 'actual' y 'vecino'.
                if (matriz[actual][vecino] == 1) {

                    // 5. Caso A: Vecino no coloreado.
                    if (color[vecino] == -1) {
                        // Colorea al vecino con el color opuesto (1 - 0 = 1; 1 - 1 = 0).
                        color[vecino] = 1 - color[actual];
                        cola[fondo++] = vecino; // Encola para seguir explorando.
                    }
                    // 6. Caso B: Vecino ya coloreado.
                    // Verifica si el vecino tiene el mismo color que el actual.
                    else if (color[vecino] == color[actual]) {
                        // Si dos nodos conectados tienen el mismo color, NO es bipartito.
                        return false;
                    }
                }
            }
        }
        // 7. Si la BFS termina sin encontrar conflictos de color, esta componente es
        // bipartita.
        return true;
    }

    // -------------------------------------------------------------------------------------------------

    /**
     * Ejercicio 10: Verificar si es un Árbol (DAG acíclico y conectado).
     * * Finalidad: Comprobar si el dígrafo es un Árbol de Expansión Dirigido (un
     * DAG
     * con exactamente una raíz y todos los demás nodos alcanzables).
     * Para qué: Modela jerarquías estrictas (ej. jerarquía de clases o estructuras
     * de datos de árbol).
     * Cómo: 1. No debe haber ciclos. 2. Debe haber exactamente un vértice con grado
     * de entrada 0 (la raíz).
     * 3. Todos los demás vértices deben tener grado de entrada 1.
     * * @return true si es árbol dirigido, false si no.
     */
    static boolean esArbolDirigido() {
        // 1. Condición de aciclicidad: Un árbol no debe tener ciclos.
        if (tieneCiclo())
            return false;

        int raizCount = 0; // Contador de posibles raíces (nodos con grado de entrada 0).

        // 2. Verifica la estructura de grados de entrada.
        for (int i = 0; i < V; i++) {
            int gradoEntrada = gradoVertice(i)[1]; // Obtenemos el grado de entrada (posición 1)

            // a) Un árbol debe tener exactamente una raíz (grado de entrada 0).
            if (gradoEntrada == 0) {
                raizCount++;
                // b) Todos los nodos que no son raíz deben tener un solo padre (grado de
                // entrada 1).
            } else if (gradoEntrada > 1) {
                return false; // Más de un padre implica una estructura que no es de árbol.
            }
        }

        // 3. La condición final: Debe haber exactamente una raíz.
        if (raizCount != 1) {
            return false; // Si hay 0 raíces (ciclo completo) o más de 1 raíz (bosque), no es un solo
                          // árbol.
        }

        // 4. Se comprueba la conectividad total (todos los nodos deben ser alcanzables
        // desde la raíz).
        // Si es acíclico y tiene una única raíz con grado de entrada 0, solo
        // necesitamos asegurar
        // que la única componente existente sea accesible. Esto se verifica contando la
        // componente.
        if (contarComponentesConectadas() != 1)
            return false; // Si está desconectado, no es un solo árbol.

        return true; // Cumple todas las condiciones de un Árbol Dirigido.
    }
}