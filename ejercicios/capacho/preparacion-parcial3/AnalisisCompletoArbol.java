import java.util.ArrayList;
import java.util.List;

/**
 * MONITORÍA: ANÁLISIS VISUAL Y LÓGICO DE ÁRBOLES EN MATRIZ
 * ---------------------------------------------------------
 *
 * CONCEPTOS CLAVE EN LA MATRIZ:
 * * 1. ¿QUÉ SIGNIFICA UNA FILA VACÍA? (Todo ceros)
 * Fila i = [0, 0, 0, 0] -> Del nodo 'i' NO SALEN flechas.
 * Conclusión: El nodo 'i' no tiene hijos. ES UNA HOJA.
 *
 * 2. ¿QUÉ SIGNIFICA UNA COLUMNA VACÍA? (Todo ceros)
 * Columna j =
 * [0]
 * [0]
 * [0] -> Al nodo 'j' NO ENTRAN flechas.
 * [0]
 * Conclusión: El nodo 'j' no tiene padre. ES LA RAÍZ.
 * * 3. EL CONCEPTO DE -1:
 * Como los arrays empiezan en 0, el 0 es un nodo válido.
 * Usamos -1 para decir "NULL", "VACÍO" o "NO EXISTE".
 */
public class AnalisisCompletoArbol {

    // Definimos 6 nodos para el ejemplo
    static final int V = 6;
    static int[][] matriz = new int[V][V];
    static int[] valores = new int[V]; // El dato numérico dentro del círculo

    public static void main(String[] args) {
        // --- CONSTRUCCIÓN DEL ÁRBOL (ARQUITECTURA) ---
        // Vamos a armar un Árbol Binario de Búsqueda (BST) válido.
        // Estructura Visual:
        // 50 (Nodo 0) <- RAÍZ
        // / \
        // 30 70 (Nodos 1 y 2)
        // / \ \
        // 20 40 80 (Nodos 3, 4, 5)

        // 1. Asignar valores
        valores[0] = 50; // Raíz
        valores[1] = 30; // Izq de 50
        valores[2] = 70; // Der de 50
        valores[3] = 20; // Izq de 30
        valores[4] = 40; // Der de 30
        valores[5] = 80; // Der de 70

        // 2. Conectar en la Matriz (Padre -> Hijo)
        conectar(0, 1);
        conectar(0, 2);
        conectar(1, 3);
        conectar(1, 4);
        conectar(2, 5);

        // --- REPRESENTACIÓN VISUAL EN CONSOLA ---
        imprimirMatrizExplicada();

        System.out.println("\n=== DIAGNÓSTICO DEL INSPECTOR DE CÓDIGO ===");

        // 1. Encontrar al "Jefe" (Raíz) y a los "Trabajadores finales" (Hojas)
        int raiz = analizarEstructuraBasica();

        if (raiz != -1) {
            // 2. Calcular Altura (Profundidad máxima)
            System.out.println("\n[MÉTRICAS]");
            int altura = calcularAltura(raiz);
            System.out.println(" -> La altura del árbol es: " + altura);

            // 3. Calcular Nivel de un nodo específico (ej. Nodo 4 con valor 40)
            int nodoObjetivo = 4;
            int nivel = obtenerNivel(nodoObjetivo);
            System.out.println(
                    " -> El nodo " + nodoObjetivo + " (" + valores[nodoObjetivo] + ") está en el nivel: " + nivel);

            // 4. Validación de Tipos de Árbol
            System.out.println("\n[VALIDACIÓN]");
            if (esArbolBinario()) {
                System.out.println(" -> [OK] Estructura: Es un Árbol Binario (Nadie tiene > 2 hijos).");

                if (esBST(raiz)) {
                    System.out.println(" -> [OK] Lógica: Es un BST (Los valores están ordenados).");
                } else {
                    System.out.println(" -> [X] Lógica: NO es BST (Los valores están desordenados).");
                }
            } else {
                System.out.println(" -> [X] Estructura: NO es binario (Alguien tiene > 2 hijos).");
            }
        } else {
            System.out.println("ERROR: No se encontró raíz (¿Es un ciclo?).");
        }
    }

    // ==================================================================
    // MÉTODOS DE LÓGICA
    // ==================================================================

    /**
     * Busca Raíz y Hojas leyendo filas y columnas.
     * Retorna el índice de la raíz o -1 si no existe.
     */
    static int analizarEstructuraBasica() {
        int raizEncontrada = -1;

        System.out.println("[ANÁLISIS ESTRUCTURAL]");

        // PASO A: Buscar Raíz (COLUMNA VACÍA)
        // Si recorremos la columna 'j' y nunca encontramos un 1,
        // significa que nadie es padre de 'j'.
        for (int j = 0; j < V; j++) {
            boolean tienePadre = false;
            for (int i = 0; i < V; i++) {
                if (matriz[i][j] == 1) {
                    tienePadre = true;
                    break;
                }
            }
            
            if (!tienePadre) {
                raizEncontrada = j;
                System.out.println(" 1. RAÍZ DETECTADA: Nodo " + j + " (Valor: " + valores[j]
                        + ") -> Su columna está vacía (nadie lo manda).");
            }
        }

        // PASO B: Buscar Hojas (FILA VACÍA)
        // Si recorremos la fila 'i' y nunca encontramos un 1,
        // significa que 'i' no tiene hijos.
        System.out.print(" 2. HOJAS DETECTADAS: ");
        for (int i = 0; i < V; i++) {
            boolean tieneHijos = false;
            for (int j = 0; j < V; j++) {
                if (matriz[i][j] == 1) {
                    tieneHijos = true;
                    break;
                }
            }
            if (!tieneHijos) {
                System.out.print("[" + i + ": val " + valores[i] + "] ");
            }
        }
        System.out.println("-> Sus filas están vacías (no mandan a nadie).");

        return raizEncontrada;
    }

    /**
     * Calcula el NIVEL subiendo por la matriz.
     * Lógica inversa: En lugar de bajar desde la raíz,
     * trepamos buscando al padre en la columna hasta que no haya padre (-1).
     */
    static int obtenerNivel(int nodoObjetivo) {
        int nivel = 0;
        int actual = nodoObjetivo;

        while (true) {
            int padre = -1; // Asumimos que no tiene padre (-1 significa NULL)

            // Buscamos en la COLUMNA del actual quién le apunta
            for (int i = 0; i < V; i++) {
                if (matriz[i][actual] == 1) {
                    padre = i; // Encontramos al padre
                    break; // Un nodo solo tiene 1 padre en árboles, rompemos
                }
            }

            if (padre == -1) {
                // Si sigue siendo -1, llegamos a la raíz (techo)
                break;
            } else {
                // Subimos un piso
                actual = padre;
                nivel++;
            }
        }
        return nivel;
    }

    /**
     * Calcula ALTURA usando DFS Recursivo.
     * La altura es el camino más largo hacia abajo.
     */
    static int calcularAltura(int nodoActual) {
        int maxAlturaHijos = -1; // Base para sumar 1 luego.

        // Barrido de fila para encontrar hijos
        for (int i = 0; i < V; i++) {
            if (matriz[nodoActual][i] == 1) {
                // Recursión: Preguntar altura al hijo
                int h = calcularAltura(i);
                if (h > maxAlturaHijos)
                    maxAlturaHijos = h;
            }
        }
        // Retornamos 1 + la altura del hijo más alto
        // Si era hoja (maxAlturaHijos = -1), retorna 0.
        return maxAlturaHijos + 1;
    }

    /**
     * Valida si es BINARIO.
     * Regla: Suma de fila <= 2.
     */
    static boolean esArbolBinario() {
        for (int i = 0; i < V; i++) {
            int conteo = 0;
            for (int j = 0; j < V; j++) {
                if (matriz[i][j] == 1)
                    conteo++;
            }
            if (conteo > 2)
                return false;
        }
        return true;
    }

    /**
     * Valida si es BST (Binary Search Tree) usando IN-ORDER.
     * Regla: El recorrido In-Order debe generar una lista ordenada ascendente.
     */
    static boolean esBST(int raiz) {
        List<Integer> listaValores = new ArrayList<>();

        // Llenamos la lista con el recorrido
        recorridoInOrder(raiz, listaValores);

        System.out.println(" -> Recorrido In-Order generado: " + listaValores);

        // Verificamos si la lista está ordenada
        for (int k = 0; k < listaValores.size() - 1; k++) {
            if (listaValores.get(k) >= listaValores.get(k + 1)) {
                return false; // Falló el orden
            }
        }
        return true;
    }

    /**
     * Recorrido In-Order "Inteligente" para Matriz.
     * Como la matriz no tiene "izq" o "der", usamos los VALORES para decidir.
     * Hijo con valor < Padre va a la izq.
     * Hijo con valor > Padre va a la der.
     */
    static void recorridoInOrder(int actual, List<Integer> lista) {
        int hijoIzq = -1;
        int hijoDer = -1;

        // Buscamos hijos en la fila
        for (int i = 0; i < V; i++) {
            if (matriz[actual][i] == 1) {
                if (valores[i] < valores[actual])
                    hijoIzq = i;
                else
                    hijoDer = i;
            }
        }

        // 1. Visitar Subárbol Izquierdo
        if (hijoIzq != -1)
            recorridoInOrder(hijoIzq, lista);

        // 2. Visitar Raíz (Agregar a lista)
        lista.add(valores[actual]);

        // 3. Visitar Subárbol Derecho
        if (hijoDer != -1)
            recorridoInOrder(hijoDer, lista);
    }

    // ==================================================================
    // HERRAMIENTAS VISUALES
    // ==================================================================

    static void conectar(int padre, int hijo) {
        matriz[padre][hijo] = 1;
    }

    static void imprimirMatrizExplicada() {
        System.out.println("\n=== VISUALIZACIÓN DE LA MATRIZ ===");
        System.out.println("   (Hijos/Destinos)");
        System.out.print("Nodos: ");
        for (int i = 0; i < V; i++)
            System.out.print(i + " ");
        System.out.println("\n      -------------");

        for (int i = 0; i < V; i++) {
            System.out.print(i + " |   "); // Etiqueta fila
            boolean esFilaVacia = true;

            for (int j = 0; j < V; j++) {
                System.out.print(matriz[i][j] + " ");
                if (matriz[i][j] == 1)
                    esFilaVacia = false;
            }

            // Explicación visual en cada línea
            if (esFilaVacia) {
                System.out.print(" <- Fila Ceros (HOJA: No tiene hijos)");
            } else {
                System.out.print(" <- Tiene hijos");
            }
            System.out.println();
        }
        System.out.println("\n      ^ ^ ^");
        System.out.println("      | | |");
        System.out.println("      Revisar Columnas: Si una columna tiene solo ceros verticalmente");
        System.out.println("      significa que nadie apunta a ese nodo (ES LA RAÍZ).");
        System.out.println("==================================\n");
    }
}