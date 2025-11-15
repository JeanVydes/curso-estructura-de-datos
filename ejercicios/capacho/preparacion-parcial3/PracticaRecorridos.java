/**
 * Clase para Monitoría: Recorridos de Grafos y Ejercicios Prácticos.
 * 
 * Estructura de la Clase:
 * - Parte 1: Métodos de Recorrido (BFS y DFS) tanto recursivamente como iterativamente.
 * - Parte 2: Ejercicios Prácticos
 * - Parte 3: Corremos el programa
 */
public class PracticaRecorridos {
    // Vértices (Nodos). Los numeramos de 0 a 3 para este ejemplo simple.
    static final int V = 4;

    // Matriz de adyacencia (Nuestro "mapa" del grafo). Es un array 2D donde
    // matriz[i][j] = 1 significa que hay una conexión (arista) entre i y j.
    static int[][] matriz = new int[V][V];

    // ========================================================================
    // --- PARTE 1: MÉTODOS DE RECORRIDO ---
    // ========================================================================

    /**
     * 1. Búsqueda en Anchura (BFS) - Iterativo
     */
    static void bfs(int inicio) {
        // Paso 1: Creamos un array de booleanos para marcar nodos visitados.
        // Inicialmente todos false (no visitados).
        // ¿Por qué usamos visitado[]? Para evitar visitar nodos repetidos y ciclos
        // infinitos.
        boolean[] visitado = new boolean[V];

        // Paso 2: Creamos el array para la cola (tamaño V, suficiente para todos los
        // nodos).
        // Estructura clave: COLA (Queue). Una cola funciona como una fila en un banco:
        // - FIFO: First In, First Out (el primero que entra es el primero que sale).
        // - Usamos un array simple.
        // - Dos índices: 'frente' (apunta al inicio de la fila, quién sale próximo)
        // y 'fondo' (apunta al final de la fila, dónde entra el nuevo elemento).
        // - Inicialmente, frente = 0 y fondo = 0 (cola vacía cuando frente == fondo).
        // - Para agregar (enqueue): ponemos el elemento en cola[fondo] y aumentamos
        // fondo++.
        // - Para sacar (dequeue): tomamos cola[frente] y aumentamos frente++.
        // - Si fondo llega al final del array, no hay problema en este ejemplo simple
        // (asumimos que V es pequeño y no se desborda).
        // Dibujo ASCII de una Cola Manual (ejemplo con nodos 0,1,2):
        //
        // Cola inicial (vacía):
        // +---+---+---+---+
        // | | | | | ← array de tamaño V=4
        // +---+---+---+---+
        // ↑ ↑
        // frente=0 fondo=0 (vacía, frente == fondo)
        //
        // Agregar 0 (enqueue):
        // +---+---+---+---+
        // | 0 | | | |
        // +---+---+---+---+
        // ↑ ↑
        // frente=0 fondo=1 (frente < fondo → no vacía)
        //
        // Agregar 1 y 2:
        // +---+---+---+---+
        // | 0 | 1 | 2 | |
        // +---+---+---+---+
        // ↑ ↑
        // frente=0 fondo=3
        //
        // Sacar 0 (dequeue):
        // +---+---+---+---+
        // | 0 | 1 | 2 | |
        // +---+---+---+---+
        // ↑ ↑
        // frente=1 fondo=3
        int[] cola = new int[V];
        int frente = 0; // Apunta al frente (quién sale).
        int fondo = 0; // Apunta al fondo (dónde entra).

        // Paso 3: Agregamos el nodo inicial a la cola (enqueue).
        // Ponemos 'inicio' en cola[0] y movemos fondo a 1.
        // Marcamos el inicial como visitado inmediatamente, para no agregarlo de nuevo.
        cola[fondo++] = inicio;
        visitado[inicio] = true;

        // Paso 4: Mientras la cola no esté vacía (frente < fondo)...
        // Condición del while: frente < fondo → Mientras haya elementos (fondo ha
        // avanzado más que frente).
        // ¿Por qué < ? Porque si frente == fondo, la cola está vacía (no hay nada entre
        // ellos).
        // Ejemplo: Si agregamos uno, fondo=1, frente=0 → 1 > 0, hay elemento.
        // Si sacamos, frente=1, ahora 1 == 1, vacía.
        while (frente < fondo) {

            // Paso 5: Sacamos el nodo del frente (dequeue).
            // Tomamos cola[frente] y movemos frente++.
            int actual = cola[frente++];

            // Paso 6: Visitamos el nodo (aquí solo imprimimos su orden).
            System.out.print(actual + " ");

            // Paso 7: Exploramos TODOS los vecinos de 'actual'.
            // Recorremos la fila de 'actual' en la matriz (de 0 a V-1).
            // ¿Por qué recorremos TODOS los vecinos del actual?
            // - Algorítmicamente: Para expandir el nivel actual completamente antes del
            // siguiente.
            // En BFS, al procesar 'actual', agregamos TODOS sus vecinos no visitados a la
            // cola.
            // Esto mantiene el orden por niveles: vecinos de actual van al final de la
            // cola.
            // Si solo recorriéramos uno, romperíamos el FIFO y el orden por anchura.
            // 
            // Para cada vecino posible (0 a V-1):
            // Si conectado Y no visitado:
            // Marcar visitado
            // Encolar vecino
            // - Esto asegura exhaustividad: visitamos todo lo alcanzable, nivel por nivel.
            // - Evita ciclos: marcar temprano previene agregar duplicados.
            for (int vecino = 0; vecino < V; vecino++) {

                // Paso 8: Condición doble para agregar un vecino:
                // - matriz[actual][vecino] == 1: Hay conexión (arista).
                // - !visitado[vecino]: No ha sido visitado aún (evita repeticiones).
                if (matriz[actual][vecino] == 1 && !visitado[vecino]) {

                    // Paso 9: Marcamos el vecino como visitado AHORA MISMO.
                    // ¿Por qué tan pronto? Para que si otro nodo lo ve después,
                    // no lo agregue de nuevo a la cola (evita duplicados).
                    visitado[vecino] = true;

                    // Paso 10: Agregamos el vecino al fondo de la cola (enqueue).
                    // Ponemos en cola[fondo] y fondo++.
                    cola[fondo++] = vecino;
                }
            }
        }
    }

    /**
     * BFS - Recursivo (versión alternativa, menos común pero posible).
     * Nota: BFS recursivo no es estándar porque la recursión usa pila (LIFO), no
     * cola (FIFO).
     * Para simular FIFO, usamos una cola y recursión para procesar el siguiente en
     * cola.
     * Puede causar stack overflow en grafos grandes, por eso preferimos iterativo.
     */
    static void bfsRecursivo(int inicio) {
        // Paso 1: Array visitado.
        boolean[] visitado = new boolean[V];

        // Paso 2: Cola manual.
        int[] cola = new int[V];
        int frente = 0;
        int fondo = 0;

        // Paso 3: Enqueue inicio y marca.
        cola[fondo++] = inicio;
        visitado[inicio] = true;

        // Paso 4: Llamada recursiva inicial.
        bfsRecursivoHelper(cola, frente, fondo, visitado);
    }

    // Helper recursivo para BFS.
    static void bfsRecursivoHelper(int[] cola, int frente, int fondo, boolean[] visitado) {
        // Caso base: Si cola vacía (frente >= fondo), termina.
        if (frente >= fondo) return;

        // Paso 5: "Dequeue" (toma de cola[frente], incrementa frente).
        int actual = cola[frente++];

        // Paso 6: Visita (imprime).
        System.out.print(actual + " ");

        // Paso 7: Agrega vecinos no visitados.
        for (int vecino = 0; vecino < V; vecino++) {
            if (matriz[actual][vecino] == 1 && !visitado[vecino]) {
                visitado[vecino] = true;
                cola[fondo++] = vecino;
            }
        }

        // Paso 8: Recursión para el siguiente en cola (nuevo frente y fondo).
        bfsRecursivoHelper(cola, frente, fondo, visitado);
    }

    /**
     * 2. Búsqueda en Profundidad (DFS) - Recursivo.
     */
    static void dfsRecursivo(int actual, boolean[] visitado) {
        // Paso 1: Marcamos el nodo actual como visitado inmediatamente al llegar.
        visitado[actual] = true;

        // Paso 2: Visitamos el nodo (imprimimos su orden).
        System.out.print(actual + " ");

        // Paso 3: Exploramos TODOS los vecinos de 'actual'.
        // Recorremos la fila de 'actual' en la matriz.
        // ¿Por qué recorremos TODOS los vecinos del actual?
        // - Algorítmicamente: Para explorar todos los ramales después de bucear en uno.
        // En DFS, el bucle for prueba un vecino (recursión: bucea profundo), luego
        // vuelve
        // y prueba el siguiente. Recorrer todos asegura backtracking completo: no
        // dejamos ramales sin explorar.
        // - Pseudocódigo del bucle:
        // Para cada vecino posible (0 a V-1):
        // Si conectado Y no visitado:
        // DFS_recursivo(vecino) // Bucea, pausa for, vuelve después
        // - Esto asegura exhaustividad: visitamos todo reachable, profundidad primero.
        // - Evita ciclos: check de visitado previene recursión infinita.
        for (int vecino = 0; vecino < V; vecino++) {

            // Paso 4: Condición: Si hay conexión y el vecino NO está visitado...
            if (matriz[actual][vecino] == 1 && !visitado[vecino]) {

                // Paso 5: Llamamos recursivamente a dfsRecursivo para el vecino.
                // Esto "mete" la llamada en la pila y pausa esta función aquí.
                // La ejecución salta al Paso 1 con el vecino como 'actual'.
                // Estructura clave: PILA (Stack) implícita en la recursión.
                // - No creamos una pila manual; usamos la "pila de llamadas" de Java.
                // - Cada llamada recursiva es como "push" (meter) un nodo en la pila.
                // - Cuando la función termina, es como "pop" (sacar).
                // - LIFO: Last In, First Out (el último en entrar sale primero).
                // Dibujo ASCII de la Pila de Llamadas (ejemplo recursión en nodo 0 con vecinos
                // 1 y 2):
                //
                // Inicial: Llamada a dfs(0)
                // Pila:
                // +-----+
                // | dfs(0) | ← tope
                // +-----+
                //
                // Dentro de dfs(0), llama a dfs(1):
                // Pila:
                // +-----+
                // | dfs(1) | ← tope (explorando profundo)
                // +-----+
                // | dfs(0) | (pausado en el for)
                // +-----+
                //
                // dfs(1) termina, pop:
                // Pila:
                // +-----+
                // | dfs(0) | ← vuelve aquí, continúa for a vecino 2
                // +-----+
                // Ventaja: Código simple y elegante.
                // Desventaja: Si el grafo es muy profundo, puede causar "stack overflow"
                // (desborde de pila).
                dfsRecursivo(vecino, visitado);

                // Paso 6: Cuando la llamada recursiva termina (todo el subcamino explorado),
                // volvemos aquí y continuamos el for con el siguiente vecino.
            }
        }

        // Paso 7: Cuando el for termina (no más vecinos no visitados),
        // la función termina y "sale" de la pila, volviendo a la llamada anterior.
    }

    /**
     * 3. Búsqueda en Profundidad (DFS) - Iterativo con Pila Manual.
     */
    static void dfsIterativo(int inicio) {
        // Paso 1: Array de visitados, todos false.
        boolean[] visitado = new boolean[V];

        // Paso 2: Creamos el array para la pila (tamaño V).
        // Estructura clave: PILA (Stack) manual con array.
        // - Usamos int[] pila para almacenar nodos.
        // - 'tope' es el índice del elemento superior (-1 = pila vacía).
        // - Para push (meter): pila[++tope] = nodo.
        // - Para pop (sacar): nodo = pila[tope--].
        // - LIFO: Último en entrar sale primero.
        // Dibujo ASCII de una Pila Manual (ejemplo con nodos 0,1,2):
        //
        // Pila inicial (vacía):
        // +---+---+---+---+
        // | | | | | ← array de tamaño V=4
        // +---+---+---+---+
        // tope = -1
        //
        // Push 0:
        // +---+---+---+---+
        // | 0 | | | |
        // +---+---+---+---+
        // ↑
        // tope=0
        //
        // Push 1 y 2:
        // +---+---+---+---+
        // | 0 | 1 | 2 | |
        // +---+---+---+---+
        // ↑
        // tope=2
        //
        // Pop 2:
        // +---+---+---+---+
        // | 0 | 1 | 2 | |
        // +---+---+---+---+
        // ↑
        // tope=1 (2 sacado)
        // Nota: Un nodo puede ser pushed varias veces, pero lo filtramos al pop.
        int[] pila = new int[V];
        int tope = -1; // Pila vacía.

        // Paso 3: Push del nodo inicial (mete en pila).
        pila[++tope] = inicio;

        // Paso 4: Mientras la pila no esté vacía (tope >= 0)...
        while (tope >= 0) {

            // Paso 5: Pop del nodo superior (saca de pila).
            int actual = pila[tope--];

            // Paso 6: Verificamos si ya fue visitado (filtro importante).
            // Si no, procedemos; si sí, ignoramos (continuamos el while).
            if (!visitado[actual]) {

                // Paso 7: Marcamos como visitado.
                visitado[actual] = true;

                // Paso 8: Visitamos (imprimimos).
                System.out.print(actual + " ");

                // Paso 9: Exploramos TODOS los vecinos de 'actual'.
                // ¿Por qué recorremos TODOS los vecinos del actual?
                // - Algorítmicamente: Para simular el backtracking de la recursión.
                // En DFS iterativo, push de todos los vecinos asegura que exploramos ramales
                // uno por uno (LIFO: último push sale primero, como recursión).
                // - Pseudocódigo del bucle:
                // Para cada vecino posible (V-1 a 0, inverso):
                // Si conectado Y no visitado:
                // Push vecino
                // - Esto asegura exhaustividad: visitamos todo reachable, profundidad primero.
                // - Evita ciclos: check al push y filtro al pop.
                // ¡TRUCO IMPORTANTE! Metemos a los vecinos en la pila
                // en ORDEN INVERSO (del más grande al más chico).
                // ¿Por qué al revés? Porque la Pila es LIFO.
                // Si 0 tiene vecinos 1 y 2:
                // - Metemos al 2.
                // - Metemos al 1 (encima del 2).
                // El próximo en salir será el 1 (el que está más arriba).
                // Esto hace que el recorrido sea idéntico al recursivo
                // (que también prueba el 1 primero).
                for (int vecino = V - 1; vecino >= 0; vecino--) {

                    // Paso 10: Si hay conexión y no visitado, push (mete en pila).
                    // No marcamos aquí; lo haremos al pop para evitar prematuras.
                    if (matriz[actual][vecino] == 1 && !visitado[vecino]) {
                        pila[++tope] = vecino;
                    }
                }
            }
        }
    }

    // ========================================================================
    // --- PARTE 2: MÉTODOS DE EJERCICIOS (La "Aplicación") ---
    // Aquí aplicamos BFS/DFS a problemas reales, paso a paso.
    // ========================================================================

    /**
     * EJERCICIO 1: Enunciado - Dado un grafo no dirigido, determina si existe un
     * camino
     * entre dos nodos dados (inicio y fin). Retorna true si hay camino, false si
     * no.
     * Solución: Usamos DFS para explorar desde inicio y ver si llegamos a fin.
     * (Podríamos usar BFS, pero DFS es simple para esto).
     */
    static boolean ejercicio1_ExisteCamino(int inicio, int fin) {
        // Paso 1: Creamos array de visitados (todos false).
        boolean[] visitados = new boolean[V];

        // Paso 2: Lanzamos DFS silencioso desde inicio (marca todos los nodos
        // reachable).
        dfsSilencioso(inicio, visitados);

        // Paso 3: Revisamos si 'fin' fue marcado como visitado. Si sí, hay camino.
        return visitados[fin];
    }

    /**
     * EJERCICIO 2: Enunciado - Dado un grafo no dirigido sin pesos, encuentra la
     * longitud
     * del camino más corto (en número de aristas/saltos) desde inicio hasta fin.
     * Retorna el número de saltos si hay camino, -1 si no.
     * Solución: Usamos BFS, ya que explora por niveles (distancia creciente).
     * Agregamos un array de distancias para rastrear los saltos.
     */
    static int ejercicio2_CaminoMasCorto(int inicio, int fin) {
        // Paso 1: Caso especial - Si inicio es fin, distancia 0.
        if (inicio == fin) {
            return 0;
        }

        // Paso 2: Inicializamos visitados, cola y distancias (-1 significa no
        // alcanzado).
        boolean[] visitado = new boolean[V];
        int[] cola = new int[V];
        int frente = 0;
        int fondo = 0;
        int[] distancia = new int[V];
        for (int i = 0; i < V; i++)
            distancia[i] = -1; // Inicial a -1.
        distancia[inicio] = 0; // Inicio a 0 saltos.

        // Paso 3: Enqueue inicio y marca como visitado.
        cola[fondo++] = inicio;
        visitado[inicio] = true;

        // Paso 4: Mientras la cola no esté vacía...
        while (frente < fondo) {
            // Paso 5: Dequeue el nodo actual.
            int actual = cola[frente++];

            // Paso 6: Exploramos todos los vecinos de actual.
            for (int vecino = 0; vecino < V; vecino++) {
                if (matriz[actual][vecino] == 1 && !visitado[vecino]) {
                    // Paso 7: Marca vecino, asigna distancia = distancia[actual] + 1, enqueue.
                    visitado[vecino] = true;
                    distancia[vecino] = distancia[actual] + 1;
                    cola[fondo++] = vecino;

                    // Paso 8: Si este vecino es 'fin', retornamos su distancia (es la mínima por
                    // BFS).
                    if (vecino == fin) {
                        return distancia[vecino];
                    }
                }
            }
        }

        // Paso 9: Si terminamos sin encontrar fin, no hay camino, retorna -1.
        return -1;
    }

    /**
     * EJERCICIO 3: Enunciado - Dado un grafo no dirigido (posiblemente
     * desconectado),
     * cuenta el número de componentes conectadas ("islas" o subgrafos conectados).
     * Retorna el número de componentes.
     * Solución: Recorremos todos los nodos; si uno no visitado, es nueva
     * componente,
     * lanzamos DFS para marcarla completa, y contamos.
     */
    static int ejercicio3_ContarComponentesConectadas() {
        // Paso 1: Array de visitados global (todos false).
        boolean[] visitadosGlobal = new boolean[V];

        // Paso 2: Contador de componentes inicia en 0.
        int contadorDeIslas = 0;

        // Paso 3: Recorremos todos los nodos (0 a V-1).
        for (int i = 0; i < V; i++) {

            // Paso 4: Si el nodo i no está visitado...
            if (!visitadosGlobal[i]) {
                // Paso 5: Es el inicio de una nueva componente, incrementamos contador.
                contadorDeIslas++;

                // Paso 6: Lanzamos DFS silencioso para marcar todos los nodos de esta
                // componente.
                dfsSilencioso(i, visitadosGlobal);
            }
        }

        // Paso 7: Retornamos el contador total de componentes.
        return contadorDeIslas;
    }

    /**
     * EJERCICIO 4: Enunciado - Dado un grafo no dirigido, determina si contiene al
     * menos
     * un ciclo (camino que vuelve a sí mismo). Retorna true si hay ciclo, false si
     * es acíclico.
     * Solución: Usamos DFS modificado con "padre" para detectar back edges (aristas
     * a visitados no padre).
     * Recorremos todas las componentes por si está desconectado.
     */
    static boolean ejercicio4_DetectarCiclo() {
        // Paso 1: Array de visitados (todos false).
        boolean[] visitado = new boolean[V];

        // Paso 2: Recorremos todos los nodos para cubrir componentes desconectadas.
        for (int i = 0; i < V; i++) {
            if (!visitado[i]) {
                // Paso 3: Lanzamos DFS de ciclo desde i, con padre -1 (ninguno).
                if (dfsCiclo(i, -1, visitado)) {
                    // Paso 4: Si detecta ciclo en esta componente, retorna true.
                    return true;
                }
            }
        }

        // Paso 5: Si no se detecta ciclo en ninguna componente, retorna false.
        return false;
    }

    // DFS auxiliar para detectar ciclo (con padre para ignorar arista de llegada).
    static boolean dfsCiclo(int actual, int padre, boolean[] visitado) {
        // Paso 1: Marca actual como visitado.
        visitado[actual] = true;

        // Paso 2: Recorre todos los vecinos.
        for (int vecino = 0; vecino < V; vecino++) {
            if (matriz[actual][vecino] == 1) {
                // Paso 3: Si vecino es padre, ignora (arista de llegada).
                if (vecino == padre)
                    continue;

                // Paso 4: Si vecino ya visitado (y no padre), es back edge → ciclo, retorna
                // true.
                if (visitado[vecino])
                    return true;

                // Paso 5: Si no visitado, recursión con actual como nuevo padre.
                if (dfsCiclo(vecino, actual, visitado))
                    return true;
            }
        }

        // Paso 6: No se encontró ciclo desde aquí, retorna false.
        return false;
    }

    /**
     * EJERCICIO 5: Enunciado - Dado un grafo no dirigido, un nodo inicio y un
     * entero K,
     * cuenta cuántos nodos están exactamente a distancia K (en saltos) desde
     * inicio.
     * Retorna el conteo (0 si ninguno o K inválido).
     * Solución: Usamos BFS para calcular distancias y contar en el nivel K.
     */
    static int ejercicio5_NodosADistanciaK(int inicio, int k) {
        // Paso 1: Validar K >= 0, si no, retorna 0.
        if (k < 0) return 0;

        // Paso 2: Inicializamos visitados, cola y distancias.
        boolean[] visitado = new boolean[V];
        int[] cola = new int[V];
        int frente = 0, fondo = 0;
        // Creamos array de distancias. Para rastrear la distancia en saltos desde el inicio a cada nodo.
        // Es decir distancia[i] = número de aristas desde 'inicio' hasta 'i'. Ejemplo:
        // Si inicio=0, y 0 conectado a 1 y 2, y 1 conectado a 3, tendríamos:
        // distancia[0] = 0, distancia[1] = 1, distancia[2] = 1, distancia[3] = 2
        int[] distancia = new int[V];
        // Inicializa distancias a -1 (no alcanzado).
        for (int i = 0; i < V; i++)
            distancia[i] = -1;

        // Distancia del inicio es 0. Porque está a 0 saltos de sí mismo.
        distancia[inicio] = 0;
        // Enqueue inicio y marca como visitado.
        cola[fondo++] = inicio;
        visitado[inicio] = true;

        // Paso 3: Contador inicia en 0.
        int count = 0;

        // Paso 4: Mientras cola no vacía...
        while (frente < fondo) {
            // Paso 5: Dequeue actual.
            int actual = cola[frente++];

            // Paso 6: Si distancia[actual] == k, incrementa contador.
            if (distancia[actual] == k)
                count++;

            // Paso 7: Si distancia[actual] > k, paramos (ya pasamos el nivel K).
            if (distancia[actual] > k)
                break;

            // Paso 8: Exploramos vecinos.
            for (int vecino = 0; vecino < V; vecino++) {
                if (matriz[actual][vecino] == 1 && !visitado[vecino]) {
                    // Paso 9: Marca, asigna distancia +1, enqueue.
                    visitado[vecino] = true;
                    distancia[vecino] = distancia[actual] + 1;
                    cola[fondo++] = vecino;
                }
            }
        }

        // Paso 10: Retorna el conteo de nodos en distancia K.
        return count;
    }

    /**
     * EJERCICIO 6: Enunciado - Dado un grafo no dirigido, determina si es un árbol:
     * es decir, conectado (una sola componente) y sin ciclos. Retorna true si es
     * árbol, false si no.
     * Solución: Combinamos ejercicios previos: verifica 1 componente (ejercicio 3)
     * y no ciclo (ejercicio 4).
     */
    static boolean ejercicio6_EsArbol() {
        // Paso 1: Verifica si hay exactamente 1 componente conectada.
        if (ejercicio3_ContarComponentesConectadas() != 1)
            return false;

        // Paso 2: Verifica si no hay ciclos.
        if (ejercicio4_DetectarCiclo())
            return false;

        // Paso 3: Si ambos ok, es árbol, retorna true.
        return true;
    }

    // ========================================================================
    // --- PARTE 3: MÉTODOS DE UTILIDAD ---
    // ========================================================================

    /**
     * El punto de entrada principal para la demostración.
     * Aquí configuramos el grafo y llamamos a los recorridos
     * y a los ejercicios prácticos.
     */
    public static void main(String[] args) {

        // ---- 1. CONFIGURACIÓN DEL GRAFO ----
        // Creamos las conexiones (aristas).
        agregarArista(0, 1);
        agregarArista(0, 2);
        agregarArista(1, 3);
        agregarArista(2, 3);
        // Grafo: 0 -- 1
        // | |
        // 2 -- 3
        System.out.println("Grafo a recorrer (Matriz de Adyacencia):");
        imprimirMatrizConBordes();
        System.out.println("----------------------------------------------\n");

        // ---- 2. RECORRIDOS BÁSICOS (Explicados) ----
        System.out.println("Recorrido BFS (Anchura) - (Iterativo con Cola Manual):");
        System.out.print(" Orden de visita: ");
        bfs(0);
        System.out.println("\n");

        System.out.println("Recorrido DFS (Profundidad) - (Recursivo):");
        System.out.print(" Orden de visita: ");
        boolean[] visitadosDfsRec = new boolean[V];
        dfsRecursivo(0, visitadosDfsRec);
        System.out.println("\n");

        System.out.println("Recorrido DFS (Profundidad) - (Iterativo con Pila Manual):");
        System.out.print(" Orden de visita: ");
        dfsIterativo(0);
        System.out.println("\n");

        // ---- 3. EJERCICIOS PRÁCTICOS (Tipo Parcial) ----

        System.out.println("----------------------------------------------");
        System.out.println("--- INICIO DE EJERCICIOS PRÁCTICOS ---");
        System.out.println("----------------------------------------------\n");

        // --- Ejercicio 1: ¿Existe un camino? ---
        int inicioE1 = 1;
        int finE1 = 2;
        System.out.println("EJERCICIO 1: ¿Existe un camino entre " + inicioE1 + " y " + finE1 + "?");
        System.out.println(" Respuesta: " + (ejercicio1_ExisteCamino(inicioE1, finE1) ? "Sí" : "No"));
        System.out.println("\n");

        // --- Ejercicio 2: Camino más corto (Nivel BFS) ---
        int inicioE2 = 0;
        int finE2 = 3;
        System.out
                .println("EJERCICIO 2: ¿Cuál es el camino más corto (en saltos) de " + inicioE2 + " a " + finE2 + "?");
        int dist = ejercicio2_CaminoMasCorto(inicioE2, finE2);
        System.out.println(" Respuesta: " + (dist == -1 ? "No hay camino" : dist + " saltos"));
        System.out.println("\n");

        // --- Ejercicio 3: Contar "Islas" (Componentes Conectadas) ---
        System.out.println("EJERCICIO 3: ¿Cuántas 'islas' (componentes conectadas) hay?");
        System.out.println(" Respuesta: " + ejercicio3_ContarComponentesConectadas() + " componente(s).");
        System.out.println("\n");

        // --- Ejercicio 4: Detectar ciclo ---
        System.out.println("EJERCICIO 4: ¿Hay un ciclo en el grafo?");
        System.out.println(" Respuesta: " + (ejercicio4_DetectarCiclo() ? "Sí" : "No"));
        System.out.println("\n");

        // --- Ejercicio 5: Nodos a distancia K ---
        int inicioE5 = 0;
        int k = 2;
        System.out.println("EJERCICIO 5: ¿Cuántos nodos están a distancia exactamente " + k + " de " + inicioE5 + "?");
        System.out.println(" Respuesta: " + ejercicio5_NodosADistanciaK(inicioE5, k));
        System.out.println("\n");

        // --- Ejercicio 6: Es árbol ---
        System.out.println("EJERCICIO 6: ¿El grafo es un árbol (conectado y sin ciclos)?");
        System.out.println(" Respuesta: " + (ejercicio6_EsArbol() ? "Sí" : "No"));
        System.out.println("\n");
    }

    // DFS recursivo que solo marca, pero no imprime nada.
    // Usado por los ejercicios 1, 3, etc.
    static void dfsSilencioso(int actual, boolean[] visitado) {
        visitado[actual] = true;
        for (int vecino = 0; vecino < V; vecino++) {
            if (matriz[actual][vecino] == 1 && !visitado[vecino]) {
                dfsSilencioso(vecino, visitado);
            }
        }
    }

    // Agrega arista bidireccional (grafo no dirigido).
    static void agregarArista(int a, int b) {
        matriz[a][b] = 1;
        matriz[b][a] = 1;
    }

    // Imprime la matriz con bordes bonitos.
    static void imprimirMatrizConBordes() {
        System.out.print("+");
        for (int i = 0; i < V + 1; i++)
            System.out.print("---+");
        System.out.println();
        System.out.print("| |");
        for (int i = 0; i < V; i++)
            System.out.print(" " + i + " |");
        System.out.println();
        System.out.print("+");
        for (int i = 0; i < V + 1; i++)
            System.out.print("---+");
        System.out.println();
        for (int i = 0; i < V; i++) {
            System.out.print("| " + i + " |");
            for (int j = 0; j < V; j++) {
                System.out.print(" " + matriz[i][j] + " |");
            }
            System.out.println();
            System.out.print("+");
            for (int k = 0; k < V + 1; k++)
                System.out.print("---+");
            System.out.println();
        }
    }
}