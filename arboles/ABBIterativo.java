
import java.util.LinkedList;

/**
 * ============================================================================
 *   🌲 ÁRBOL BINARIO DE BÚSQUEDA (ABB) - IMPLEMENTACIÓN ITERATIVA 🌲
 * ============================================================================
 *
 * ¿QUÉ ES ESTE ARCHIVO?
 * Este archivo contiene la implementación "mecánica" y extremadamente eficiente
 * en memoria de un ABB. En lugar de usar llamadas recursivas, utiliza bucles
 * clásicos (while) e inteligentemente dos punteros para viajar a pie por los
 * nodos del árbol.
 *
 * ¿POR QUÉ USAR UNA VERSIÓN ITERATIVA?
 * La recursividad es hermosa y el código queda muy corto (Arbol.java), pero
 * tiene un defecto mortal para árboles extremadamente desbalanceados: si ingresas
 * 100,000 números ordenados (1, 2, 3, 4...), tendrías un árbol sin hijos
 * izquierdos, literalemente una línea recta gigante.
 *
 * La recursividad haría 100,000 "pausas" o llamadas a función, consumiendo toda
 * tu Pila de Llamadas del sistema y causando un error trágico (Stack Overflow).
 * El enfoque iterativo navega el árbol usando memoria constante O(1), no importa
 * lo deforme que se vuelva. Protege la memoria a cambio de código más extenso.
 *
 * ----------------------------------------------------------------------------
 * 🚀 EJEMPLO VISUAL PASO A PASO: INSERTAR EL NÚMERO 7
 * ----------------------------------------------------------------------------
 * Usamos un "vehículo" explorador llamado `actual` y un "copiloto" llamado
 * `padre` que recuerda la última posición válida antes de caer al vacío.
 * 
 *         [10]
 *        /    \
 *      [5]    [15]
 *        \
 *        [8]
 *
 * INICIO DEL VIAJE (Bucle While buscando un nulo):
 * `actual` apunta al [10]. `padre` empieza en null.
 * 
 * PASO 1 (bucle en 10):
 *        `padre` copia la posición del `actual`. Ahora `padre` = 10.
 *         ¿7 < 10? SÍ. `actual` avanza a su izquierda (que es 5).
 *
 * PASO 2 (bucle en 5):
 *         `padre` se queda estacionado en tu última posición (`padre` = 5).
 *         ¿7 < 5? NO. ¿7 > 5? SÍ. `actual` avanza a su derecha (que es 8).
 *
 * PASO 3 (bucle en 8):
 *         `padre` toma nota (`padre` = 8).
 *         ¿7 < 8? SÍ. `actual` avanza a su izquierda...
 * 
 * ¡BUM! `actual` CAYÓ EN NULL. El bucle while termina, deteniendo el coche.
 * Sin embargo, el `padre` nos salvó, recordando que antes de caer estábamos
 * en el 8.
 *
 * EL DESENLACE:
 * Solo queda conectar el [7] nuevo adonde nos diga el copiloto `padre`.
 * Si el 7 es menor al 8, engánchalo a su izquierda. ¡Listo!
 *         [8]
 *        /
 *      [7] <-- ¡Nuevo nodo conectado perfectamente!
 * ============================================================================
 */
public class ABBIterativo {

    static class Nodo {

        int dato;
        Nodo izquierdo;
        Nodo derecho;

        public Nodo(int dato) {
            this.dato = dato;
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    Nodo raiz;

    public ABBIterativo() {
        this.raiz = null;
    }

    /**
     * Inserta un valor en el árbol de forma iterativa.
     * 
     * ¿Qué se busca? Encontrar la ubicación correcta para el nuevo valor sin romper
     * la regla del ABB. Un nuevo nodo siempre se insertará como una "hoja" (un nodo sin hijos).
     * 
     * ¿Cómo se logra? Se desciende por el árbol de nodo en nodo, usando un bucle while,
     * hasta encontrar un lugar vacío (un puntero null) donde enganchar el nuevo nodo.
     */
    public void insertar(int valor) {
        Nodo nuevoNodo = new Nodo(valor);

        // --- CASO ESPECIAL: ÁRBOL VACÍO ---
        // Si no hay raíz, el nuevo nodo se convierte en la raíz y terminamos.
        if (raiz == null) {
            raiz = nuevoNodo;
            return;
        }

        // --- BÚSQUEDA DE LA POSICIÓN ---
        // Usamos dos punteros: 'actual' para explorar y 'padre' para recordar
        // el nodo al que nos conectaremos. 'padre' siempre está un paso detrás.
        Nodo actual = raiz;
        Nodo padre = null;

        // Este bucle es el corazón de la inserción. Desciende por el árbol.
        while (actual != null) {
            // Antes de mover 'actual', guardamos su posición en 'padre'.
            // Cuando 'actual' se vuelva null, 'padre' será el nodo correcto.
            padre = actual;

            if (valor < actual.dato) {
                // El valor es menor, así que debe ir en el subárbol izquierdo.
                actual = actual.izquierdo;
            } else if (valor > actual.dato) {
                // El valor es mayor, debe ir en el subárbol derecho.
                actual = actual.derecho;
            } else {
                // El valor ya existe. En un ABB simple, no se permiten duplicados.
                return;
            }
        }

        // --- CONEXIÓN DEL NUEVO NODO ---
        // Al salir del bucle, 'actual' es null, pero 'padre' apunta al
        // nodo hoja que será el padre del nuevo nodo.
        if (valor < padre.dato) {
            // Si el valor es menor que el del padre, se convierte en su hijo izquierdo.
            padre.izquierdo = nuevoNodo;
        } else {
            // Si es mayor, se convierte en su hijo derecho.
            padre.derecho = nuevoNodo;
        }
    }

    /**
     * Elimina un nodo del árbol de forma iterativa.
     *
     * ¿Qué se busca? Quitar un nodo y "reparar" la estructura para que la regla
     * del ABB se mantenga.
     *
     * ¿Por qué hay diferentes casos? La reparación depende de cuántos hijos
     * tiene el nodo a eliminar. La complejidad varía si tiene 0, 1 o 2 hijos.
     */
    public void eliminar(int valor) {
        if (raiz == null) {
            return;
        }

        // --- PASO 1: BÚSQUEDA DEL NODO A ELIMINAR Y SU PADRE ---
        Nodo padre = null;
        Nodo actual = raiz;

        // Bucle para localizar el nodo 'actual' que contiene el valor y
        // mantener una referencia a su 'padre'.
        while (actual != null && actual.dato != valor) {
            padre = actual;
            if (valor < actual.dato) {
                actual = actual.izquierdo;
            } else {
                actual = actual.derecho;
            }
        }

        // Si actual es null, el valor no estaba en el árbol.
        if (actual == null) {
            return;
        }

        // --- PASO 2: LÓGICA DE ELIMINACIÓN BASADA EN EL NÚMERO DE HIJOS ---
        // --- CASO 1 (hoja) Y CASO 2 (un hijo) ---
        // Estos dos casos se pueden manejar con la misma lógica.
        // La idea es "puentear" el nodo a eliminar. El padre adoptará al nieto.
        if (actual.izquierdo == null || actual.derecho == null) {
            Nodo hijo;

            // Determina cuál es el único hijo (si existe).
            // Si 'actual' es una hoja, 'hijo' será null.
            if (actual.izquierdo != null) {
                hijo = actual.izquierdo;
            } else {
                hijo = actual.derecho;
            }

            // Subcaso: se está eliminando la raíz.
            if (padre == null) {
                raiz = hijo;
            } // Si 'actual' es un hijo izquierdo, su padre ahora apunta a 'hijo'.
            else if (actual == padre.izquierdo) {
                /*
                 * (padre)             (padre)
                 * |                   |
                 * (actual)    --->    (hijo)
                 * |
                 * (hijo)
                 */
                padre.izquierdo = hijo;
            } // Si 'actual' es un hijo derecho, su padre ahora apunta a 'hijo'.
            else {
                padre.derecho = hijo;
            }
        } // --- CASO 3: EL NODO TIENE DOS HIJOS ---
        // Este es el caso más complejo. No podemos simplemente eliminarlo.
        // Estrategia: Reemplazar el valor del nodo a eliminar con un valor
        // adecuado de uno de sus subárboles y luego eliminar ese nodo sustituto.
        // El sustituto ideal es el "sucesor in-orden": el valor más pequeño
        // del subárbol derecho.
        else {
            // 1. Encontrar el sucesor (el nodo más a la izquierda del subárbol derecho).
            Nodo padreSucesor = actual;
            Nodo sucesor = actual.derecho;
            while (sucesor.izquierdo != null) {
                padreSucesor = sucesor;
                sucesor = sucesor.izquierdo;
            }

            // 2. Copiar el valor del sucesor al nodo que originalmente queríamos "eliminar".
            // Ahora, lógicamente, el nodo 'actual' está a salvo, pero tenemos un duplicado.
            actual.dato = sucesor.dato;

            // 3. Eliminar el nodo sucesor de su posición original.
            // Esta es una eliminación mucho más fácil, porque el sucesor, por definición,
            // tendrá como máximo un hijo (un hijo derecho), cayendo en el Caso 1 o 2.
            if (sucesor == padreSucesor.izquierdo) {
                padreSucesor.izquierdo = sucesor.derecho;
            } else {
                padreSucesor.derecho = sucesor.derecho;
            }
        }
    }

    /**
     * RECORRIDOS DE MORRIS (SIN PILA NI RECURSIÓN)
     * ¿Qué se busca? Recorrer el árbol iterativamente sin sobrecargar la
     * memoria con un Stack de llamadas.
     *
     * ¿Cómo se logra la proeza? A través de hilos (Threads mágicos).
     * Se modifica temporalmente el árbol en tiempo real creando "hilos" o uniones
     * entre el último nodo derecho inferior y la raíz. Son como cuerdas para
     * no perdernos y poder regresar sin que nadie se acuerde con la Pila de dónde
     * estábamos.
     * Al terminar de explorar esa rama izquierda, el hilo se corta para dejar el
     * árbol original intacto. Es ingeniería pura en estructuras de datos con un
     * costo exacto de memoria O(1) literal.
     */
    public void recorridoInOrdenMorris() {
        System.out.print("In-Orden (Morris):   ");
        Nodo actual = raiz;
        while (actual != null) {
            // Si no hay subárbol izquierdo, es simple: visitamos el nodo y vamos a la derecha.
            if (actual.izquierdo == null) {
                System.out.print(actual.dato + " ");
                actual = actual.derecho;
            } else {
                // Si hay subárbol izquierdo, buscamos a su predecesor in-orden.
                // El predecesor es el nodo más a la derecha del subárbol izquierdo.
                Nodo predecesor = actual.izquierdo;
                while (predecesor.derecho != null && predecesor.derecho != actual) {
                    predecesor = predecesor.derecho;
                }

                // Si el puntero derecho del predecesor es null, es nuestra primera visita.
                if (predecesor.derecho == null) {
                    // --- CREAR HILO ---
                    // Creamos un enlace temporal del predecesor de vuelta a 'actual'.
                    predecesor.derecho = actual;
                    // Ahora, descendemos por la izquierda para procesar ese subárbol.
                    actual = actual.izquierdo;
                } // Si el puntero derecho ya apunta a 'actual', significa que el hilo ya existía.
                // Esto nos indica que ya terminamos de recorrer el subárbol izquierdo.
                else {
                    // --- QUITAR HILO ---
                    predecesor.derecho = null; // Restauramos el puntero original del árbol.
                    // AHORA es el momento de visitar el nodo en In-Orden (Izquierda -> *Raíz* -> Derecha).
                    System.out.print(actual.dato + " ");
                    // Ya terminamos con la izquierda y la raíz, ahora vamos a la derecha.
                    actual = actual.derecho;
                }
            }
        }
        System.out.println();
    }

    public void recorridoPreOrdenMorris() {
        System.out.print("Pre-Orden (Morris):  ");
        Nodo actual = raiz;
        while (actual != null) {
            if (actual.izquierdo == null) {
                System.out.print(actual.dato + " ");
                actual = actual.derecho;
            } else {
                Nodo predecesor = actual.izquierdo;
                while (predecesor.derecho != null && predecesor.derecho != actual) {
                    predecesor = predecesor.derecho;
                }

                if (predecesor.derecho == null) {
                    // LA DIFERENCIA CLAVE: En Pre-Orden (*Raíz* -> Izquierda -> Derecha),
                    // visitamos el nodo la PRIMERA vez que lo encontramos, ANTES de crear
                    // el hilo y descender por la izquierda.
                    System.out.print(actual.dato + " ");
                    predecesor.derecho = actual;
                    actual = actual.izquierdo;
                } else {
                    predecesor.derecho = null;
                    actual = actual.derecho;
                }
            }
        }
        System.out.println();
    }

    public void recorridoPostOrdenMorris() {
        System.out.print("Post-Orden (Morris): ");
        // Lógica: Post-Orden directo (Izquierda -> Derecha -> Raíz) es muy complejo con Morris.
        // El truco es hacer un recorrido modificado (Raíz -> Derecha -> Izquierda)
        // y añadir cada elemento visitado al FRENTE de una lista. Esto revierte el orden.
        // El resultado de revertir (Raíz, Derecha, Izquierda) es (Izquierda, Derecha, Raíz).
        LinkedList<Integer> resultado = new LinkedList<>();
        Nodo actual = raiz;
        while (actual != null) {
            // Simétrico al In-Orden, pero trabajando con el subárbol derecho.
            if (actual.derecho == null) {
                resultado.addFirst(actual.dato);
                actual = actual.izquierdo;
            } else {
                // Buscamos el sucesor (el nodo más a la izquierda del subárbol derecho).
                Nodo sucesor = actual.derecho;
                while (sucesor.izquierdo != null && sucesor.izquierdo != actual) {
                    sucesor = sucesor.izquierdo;
                }
                if (sucesor.izquierdo == null) {
                    // Visitamos (añadimos al resultado) y creamos el hilo.
                    resultado.addFirst(actual.dato);
                    sucesor.izquierdo = actual;
                    actual = actual.derecho;
                } else {
                    // Rompemos el hilo y avanzamos.
                    sucesor.izquierdo = null;
                    actual = actual.izquierdo;
                }
            }
        }
        // Imprimimos el resultado final que ya está en el orden correcto.
        for (int dato : resultado) {
            System.out.print(dato + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArbolBinarioBusquedaIterativo arbol = new ArbolBinarioBusquedaIterativo();
        System.out.println("--- ÁRBOL ITERATIVO ---");
        int[] valores = {10, 5, 15, 3, 7, 12, 18};
        System.out.print("Insertando: ");
        for (int valor : valores) {
            System.out.print(valor + " ");
            arbol.insertar(valor);
        }
        System.out.println("\n");

        arbol.recorridoInOrdenMorris();
        arbol.recorridoPreOrdenMorris();
        arbol.recorridoPostOrdenMorris();

        System.out.println("\nEliminando el 15 (nodo con dos hijos)...");
        arbol.eliminar(15);
        arbol.recorridoInOrdenMorris();
    }
}
