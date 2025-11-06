/**
 * Clase RedOleoducto
 * Esta clase representa un sistema de tuberías para petróleo usando ideas de
 * grafos.
 * Un grafo es como un mapa de puntos conectados por líneas.
 * Aquí, el sistema es:
 * 1. Dirigido: El petróleo solo fluye en una dirección, como de un pozo a una
 * refinería.
 * 2. Con capacidades: Cada tubería tiene un límite de cuánto petróleo puede
 * llevar, como barriles por hora.
 * Usamos un problema llamado "flujo máximo" para calcular cuánto petróleo
 * podemos enviar en total desde el inicio (fuente) hasta el final (sumidero).
 * El algoritmo que usamos se llama Edmonds-Karp, que encuentra caminos paso a
 * paso usando una búsqueda en anchura (como explorar un laberinto nivel por
 * nivel).
 * El grafo tiene N puntos (nodos) y varias conexiones (arcos o tuberías).
 *
 * Imagen simple del grafo de ejemplo (en texto):
 * Inicio(0) --16--> (1) --10--> (2) --14--> (4) --7--> (3) --20--> Final(5)
 * --13--> (2) <--4-- (1) ^ |
 * | | |
 * 9 4 v
 * v Final(5)
 * (3) <--12-- (1)
 *
 * Nota: Las flechas muestran la dirección del flujo. Los números son los
 * límites de cada tubería.
 * Por ejemplo: Un camino posible es 0-1-3-5, con límite mínimo de 12. Otro es
 * 0-2-4-5, con límite 4.
 * El algoritmo suma flujos de varios caminos hasta el máximo posible.
 */
public class RedOleoducto {
    // N: Número de puntos (nodos) en el sistema. Usamos 6 en este ejemplo.
    static final int N = 6;

    // Matriz de límites (capacidades). límites[i][j] > 0 significa hay tubería de i
    // a j con ese límite.
    // 0 significa no hay tubería directa.
    static int[][] limites = new int[N][N];

    // -----------------------------------------------------------------------------------
    // --- PARTES PRINCIPALES PARA CALCULAR EL FLUJO MÁXIMO (ALGORITMO EDMONDS-KARP)
    // ---
    // -----------------------------------------------------------------------------------

    /**
     * Algoritmo Edmonds-Karp para calcular el flujo máximo.
     * Explicación simple: Imagina que quieres enviar la mayor cantidad de petróleo
     * posible desde el inicio hasta el final.
     * El algoritmo encuentra caminos uno por uno donde aún cabe más petróleo
     * (caminos de aumento).
     * Para cada camino, envía lo máximo que quepa (el límite más bajo en ese
     * camino) y actualiza cuánto queda disponible.
     * Repite hasta que no queden más caminos posibles.
     * 
     * ¿Qué es un "camino de aumento"? Es un ruta desde el inicio al final donde
     * cada tubería aún tiene espacio libre (>0).
     * "Aumento" porque permite aumentar el flujo total enviando más por ahí.
     * 
     * Imagen simple del proceso (en texto):
     * Inicio → Camino1 (límite min=X) → Final → Actualiza espacios libres
     * ↓ (Mientras haya caminos)
     * Inicio → Camino2 (límite min=Y) → Final → Suma al total
     * ↓ (Hasta no más)
     * Flujo máximo = X + Y + ...
     * 
     * @param inicio Punto de partida (ej. 0).
     * @param final  Punto de llegada (ej. 5).
     * @return La cantidad máxima total de petróleo que se puede enviar.
     */
    static int edmondsKarp(int inicio, int fin) {
        // grafoRestante: Copia de los límites para ver cuánto espacio queda en cada
        // tubería.
        // Al inicio, es igual a los límites originales.
        int[][] grafoRestante = new int[N][N];
        for (int u = 0; u < N; u++) {
            for (int v = 0; v < N; v++) {
                grafoRestante[u][v] = limites[u][v];
            }
        }

        // padre: Lista para recordar el camino encontrado (padre[v] = u significa vine
        // a v desde u).
        int[] padre = new int[N];

        // flujoTotal: Suma de todo el petróleo enviado.
        int flujoTotal = 0;

        // Bucle principal: Mientras encontremos un camino de aumento (la búsqueda
        // devuelve verdadero).
        // Si la búsqueda devuelve verdadero, hay un camino con espacio; podemos enviar
        // más.
        // Si falso, no hay más caminos; hemos llegado al máximo.
        while (busquedaAnchuraRestante(grafoRestante, inicio, fin, padre)) {
            // flujoCamino: El límite más bajo en este camino (cuánto podemos enviar sin
            // desbordar).
            int flujoCamino = Integer.MAX_VALUE;
            // Revisamos el camino al revés usando la lista padre para encontrar el mínimo.
            for (int v = fin; v != inicio; v = padre[v]) {
                int u = padre[v];
                flujoCamino = Math.min(flujoCamino, grafoRestante[u][v]);
            }

            // Actualizamos el grafoRestante: Quitamos el flujo enviado en la dirección
            // normal,
            // y agregamos en la opuesta (por si necesitamos "corregir" después).
            for (int v = fin; v != inicio; v = padre[v]) {
                int u = padre[v];
                grafoRestante[u][v] -= flujoCamino; // Menos espacio en la ida.
                grafoRestante[v][u] += flujoCamino; // Más espacio en la vuelta (residual).
            }

            // Sumamos al total.
            flujoTotal += flujoCamino;

            {
                System.out.println(" -> Encontramos un camino para aumentar. Enviamos: " + flujoCamino
                        + ". Total hasta ahora: " + flujoTotal);
            }
        }

        // Devolvemos el total cuando no hay más caminos.
        return flujoTotal;
    }

    /**
     * Búsqueda en anchura modificada para el grafo restante.
     * Explicación simple: Es como explorar un mapa nivel por nivel (usando una fila
     * o cola).
     * Busca un camino desde inicio a fin donde cada tubería tenga espacio >0.
     * Si lo encuentra, guarda el camino en la lista padre y devuelve verdadero.
     * Si no, devuelve falso (no se puede aumentar más).
     * 
     * ¿Qué es búsqueda en anchura? Imagina tirar una piedra en un lago: las ondas
     * se expanden capa por capa.
     * Aquí, visitamos puntos cercanos primero, luego los siguientes, etc.
     * 
     * @param grafoRestante Matriz de espacios restantes.
     * @param inicio        Punto de partida.
     * @param fin           Punto de llegada.
     * @param padre         Lista para guardar el camino.
     * @return Verdadero si hay camino, falso si no.
     */
    static boolean busquedaAnchuraRestante(int[][] grafoRestante, int inicio, int fin, int[] padre) {
        // visitado: Marca los puntos ya explorados para no repetir.
        boolean[] visitado = new boolean[N];

        // cola: Lista simple como una fila para los puntos a explorar.
        int[] cola = new int[N];
        // frente: Posición para sacar el siguiente (como el frente de la fila).
        int frente = 0;
        // fondo: Posición para agregar al final.
        int fondo = 0;

        // Empezamos desde el inicio.
        cola[fondo++] = inicio;
        visitado[inicio] = true;
        padre[inicio] = -1;

        // Bucle mientras haya puntos en la cola.
        while (frente < fondo) {
            // u: Punto actual (sacamos del frente).
            int u = cola[frente++];

            // Miramos puntos conectados (v).
            for (int v = 0; v < N; v++) {
                // Si no visitado y hay espacio >0.
                if (!visitado[v] && grafoRestante[u][v] > 0) {
                    // Agregamos v a la cola.
                    cola[fondo++] = v;
                    visitado[v] = true;
                    padre[v] = u;

                    // Si v es el fin, ¡listo! Hay camino.
                    if (v == fin) {
                        return true;
                    }
                }
            }
        }

        // No encontramos camino.
        return false;
    }

    // -----------------------------------------------------------------------------------
    // --- PARTE PRINCIPAL PARA CERRADURA TRANSITIVA (ALGORITMO FLOYD-WARSHALL) ---
    // -----------------------------------------------------------------------------------

    /**
     * Algoritmo para cerradura transitiva (usando Floyd-Warshall).
     * Explicación simple: Calcula si puedes llegar de un punto a otro por cualquier
     * ruta (directa o con paradas).
     * La matriz final tiene 1 si sí, 0 si no.
     * 
     * ¿Qué es cerradura transitiva? Es ver la conexión completa: no solo directo,
     * sino pasando por otros puntos.
     * Útil para saber si el petróleo puede llegar de A a B de alguna forma.
     * 
     * Imagen simple (en texto):
     * Al inicio: 1 si directo o mismo punto.
     * Bucle: Para cada punto intermedio k, si vas de i a k y de k a j, entonces de
     * i a j.
     * Al final: Todas las conexiones posibles.
     * 
     * @return Matriz de si se puede llegar (1/0).
     */
    static int[][] cerraduraTransitiva() {
        // alcanzable: Matriz de 1/0 para rutas.
        int[][] alcanzable = new int[N][N];

        // Al inicio: 1 si mismo punto o hay tubería directa.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j || limites[i][j] > 0) {
                    alcanzable[i][j] = 1;
                } else {
                    alcanzable[i][j] = 0;
                }
            }
        }

        // Algoritmo Floyd-Warshall: Revisamos todos los intermedios.
        for (int k = 0; k < N; k++) { // k: Punto intermedio.
            for (int i = 0; i < N; i++) { // i: Inicio.
                for (int j = 0; j < N; j++) { // j: Fin.
                    // Si i a k y k a j, entonces i a j.
                    if (alcanzable[i][k] == 1 && alcanzable[k][j] == 1) {
                        alcanzable[i][j] = 1;
                    }
                }
            }
        }

        return alcanzable;
    }

    // -----------------------------------------------------------------------------------
    // --- PARTES DE AYUDA (NO PRINCIPALES: CREAR, MOSTRAR, PRINCIPAL) ---
    // -----------------------------------------------------------------------------------

    /**
     * Método para agregar una tubería con límite.
     * Explicación: Pone el límite en la dirección de u a v (solo una dirección).
     * 
     * @param u   Inicio.
     * @param v   Fin.
     * @param lim Límite (>0).
     */
    static void agregarTuberia(int u, int v, int lim) {
        limites[u][v] = lim;
    }

    /**
     * Método para mostrar la matriz de límites.
     * Explicación: Imprime la tabla con bordes para ver los límites.
     */
    static void mostrarMatrizLimites() {
        System.out.print("+");
        for (int i = 0; i < N + 1; i++) {
            System.out.print("-----");
        }
        System.out.println("+");

        System.out.print("|   |");
        for (int i = 0; i < N; i++) {
            System.out.printf(" %3d |", i);
        }
        System.out.println();

        System.out.print("+");
        for (int i = 0; i < N + 1; i++) {
            System.out.print("-----+");
        }
        System.out.println();

        for (int i = 0; i < N; i++) {
            System.out.printf("| %3d |", i);
            for (int j = 0; j < N; j++) {
                System.out.printf(" %3d |", limites[i][j]);
            }
            System.out.println();

            System.out.print("+");
            for (int k = 0; k < N + 1; k++) {
                System.out.print("-----+");
            }
            System.out.println();
        }
    }

    /**
     * Método para mostrar matriz de si se puede llegar (1/0).
     * Explicación: Similar a mostrar límites, pero para 1 o 0.
     * 
     * @param titulo Nombre de la tabla.
     * @param matriz La matriz a mostrar.
     */
    static void mostrarMatrizAlcanzable(String titulo, int[][] matriz) {
        System.out.println(titulo + ":");

        System.out.print("+");
        for (int i = 0; i < N + 1; i++) {
            System.out.print("---+");
        }
        System.out.println();

        System.out.print("|   |");
        for (int i = 0; i < N; i++) {
            System.out.printf(" %d |", i);
        }
        System.out.println();

        System.out.print("+");
        for (int i = 0; i < N + 1; i++) {
            System.out.print("---+");
        }
        System.out.println();

        for (int i = 0; i < N; i++) {
            System.out.printf("| %d |", i);
            for (int j = 0; j < N; j++) {
                System.out.printf(" %d |", matriz[i][j]);
            }
            System.out.println();

            System.out.print("+");
            for (int k = 0; k < N + 1; k++) {
                System.out.print("---+");
            }
            System.out.println();
        }
    }

    /**
     * Método principal para correr el programa.
     * Crea el sistema, muestra la matriz, calcula el flujo máximo y la cerradura
     * transitiva.
     */
    public static void main(String[] args) {
        agregarTuberia(0, 1, 16);
        agregarTuberia(0, 2, 13);
        agregarTuberia(1, 2, 10);
        agregarTuberia(1, 3, 12);
        agregarTuberia(2, 1, 4);
        agregarTuberia(2, 4, 14);
        agregarTuberia(3, 2, 9);
        agregarTuberia(3, 5, 20);
        agregarTuberia(4, 3, 7);
        agregarTuberia(4, 5, 4);

        System.out.println("Matriz de Límites del Sistema de Tuberías:");
        mostrarMatrizLimites();

        System.out.println("\nCalculando el Flujo Máximo de 0 a 5...");
        int flujoMax = edmondsKarp(0, 5);
        System.out.println("-------------------------------------------------");
        System.out.println("El flujo máximo total es: " + flujoMax);
        System.out.println("-------------------------------------------------");

        System.out.println("\nCalculando si se Puede Llegar (Cerradura Transitiva)...");
        int[][] conexiones = cerraduraTransitiva();
        mostrarMatrizAlcanzable("Matriz de si se Puede Llegar", conexiones);
        System.out.println("(1 = Sí hay ruta, 0 = No hay ruta)");
    }
}