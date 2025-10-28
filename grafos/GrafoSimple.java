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
        // Recorremos todas las filas y columnas para poner todo en 0.|
        // Esto asegura que no haya conexiones por defecto.
        for (int i = 0; i < V; i++) { // i es el índice de la fila (vértice origen)
            for (int j = 0; j < V; j++) { // j es el índice de la columna (vértice destino)
                matriz[i][j] = 0; // No hay arista entre i y j inicialmente
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

        {
            // PASO 3: Imprimimos la matriz de adyacencia como una tabla con bordes.
            // Esto hace que se vea más claro, como una tabla real con líneas.
            // Usamos caracteres como '+' , '-' y '|' para dibujar los bordes.
            System.out.println("Matriz de Adyacencia (representación del grafo):");
            imprimirMatrizConBordes();
        }

        // PASO 4: Ejecutamos BFS (Búsqueda en Anchura) desde el vértice 0.
        // BFS explora el grafo nivel por nivel, empezando desde el vértice inicial.
        System.out.print("BFS desde 0: ");
        bfs(0);

        // PASO 5: Ejecutamos DFS (Búsqueda en Profundidad) desde el vértice 0.
        // DFS explora lo más profundo posible por un camino antes de retroceder.
        System.out.print("\nDFS desde 0: ");
        boolean[] visitadosDfs = new boolean[V];
        dfsRecursivo(0, visitadosDfs);

        {
            System.out.println("\n--- Ejercicios Agregados ---");
            System.out.println("Ejercicio 5: ¿Hay ciclo? " + tieneCiclo());
            System.out.println("Ejercicio 6: Número de componentes conectadas: " + contarComponentesConectadas());
            System.out.println("Ejercicio 7: Distancia más corta de 0 a 3: " + distanciaMasCorta(0, 3));
            int[] grados = gradoVertice(0);
            System.out.println("Ejercicio 8: Grado del vértice 0 - Salida: " + grados[0] + ", Entrada: " + grados[1]);
            System.out.println("Ejercicio 9: ¿Es bipartito? " + esBipartito());
            System.out.println("Ejercicio 10: ¿Es un árbol? " + esArbol());
            // System.out.println("Ejercicio 11: Orden Topológico: " + ordenTopologico());
        }

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
     * Ejercicio 10: Verificar si es un árbol.
     * * Finalidad: Comprobar si el grafo es conectado y sin ciclos (un árbol:
     * exactamente V-1 aristas, que se cumple si es conectado y acíclico).
     * Para qué: Modela estructuras jerárquicas (ej. árboles genealógicos o redes
     * sin redundancias).
     * Cómo: Verifica que haya exactamente 1 componente conectada y no haya ciclos.
     * * @return true si es árbol, false si no.
     */
    static boolean esArbol() {
        // 1. Condición de conectividad: Debe tener exactamente una componente.
        if (contarComponentesConectadas() != 1)
            return false; // Si tiene 0 o más de 1 componente, está desconectado o vacío.

        // 2. Condición de aciclicidad: No debe tener ciclos.
        if (tieneCiclo())
            return false; // Si tiene ciclos, no es un árbol sino un grafo general.

        // 3. Si ambas condiciones se cumplen (conectado y acíclico), es un árbol.
        // (Implícitamente, un grafo conectado y acíclico con V vértices tiene V-1
        // aristas, que es la definición de árbol).
        return true;
    }

    /**
     * Ejercicio 11: Orden Topológico (Algoritmo de Kahn).
     * * Finalidad: Producir una secuencia lineal de los vértices de un DAG, de tal
     * forma
     * que para cada arista U -> V, el vértice U siempre aparezca antes que V en la
     * secuencia.
     * Para qué: Modelar dependencias (ej. tareas de construcción o compilación de
     * código)
     * donde una tarea debe completarse antes que otra.
     * Cómo: Utiliza los grados de entrada. Comienza con nodos de grado 0 (sin
     * dependencias)
     * y los elimina, actualizando los grados de entrada de sus vecinos.
     * * @return Una lista de vértices en orden topológico, o una lista vacía si hay
     * un ciclo.
     */
    static int[] ordenTopologico() {
        // 1. Verificar si hay ciclos. El Orden Topológico solo funciona en DAGs.
        if (tieneCiclo()) {
            System.out.println("Error: El grafo no es un DAG (contiene ciclos).");
            return new int[0]; // Retorna un array vacío para indicar fallo.
        }

        // 2. Inicializar un array para almacenar los grados de entrada (In-degree).
        int[] inDegree = new int[V];
        for (int i = 0; i < V; i++) {
            // Llama a la función modificada para obtener el grado de entrada.
            inDegree[i] = gradoVertice(i)[1];
        }

        // 3. Inicializar la cola con todos los nodos que no tienen dependencias (grado
        // de entrada 0).
        int[] cola = new int[V];
        int frente = 0, fondo = 0;
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                cola[fondo++] = i;
            }
        }

        // 4. Inicializar la lista que contendrá el orden final.
        int[] orden = new int[V];
        int k = 0; // Puntero para el array 'orden'

        // 5. Procesar la cola (Kahn's Algorithm).
        while (frente < fondo) {
            int u = cola[frente++]; // Saca un nodo sin dependencias completas.
            orden[k++] = u; // Agrega el nodo a la secuencia de orden topológico.

            // Itera sobre todos los vecinos 'v' de 'u'.
            for (int v = 0; v < V; v++) {
                if (matriz[u][v] == 1) { // Si hay una arista u -> v:

                    inDegree[v]--; // Decrementa el grado de entrada de 'v' (su dependencia 'u' ha sido
                                   // satisfecha).

                    // Si 'v' ahora no tiene dependencias restantes, agrégalo a la cola.
                    if (inDegree[v] == 0) {
                        cola[fondo++] = v;
                    }
                }
            }
        }

        // 6. Validación (Opcional): Si k es menor que V, significa que hubo un ciclo
        // que la función 'tieneCiclo()' no detectó o un error, y no todos los nodos se
        // procesaron.
        if (k != V) {
            System.out.println(
                    "Advertencia: No se pudo obtener el orden topológico completo (posible ciclo en DFS/Warshall).");
        }

        return orden;
    }
}