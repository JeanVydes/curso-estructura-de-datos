/**
 * ÁRBOL BINARIO DE BÚSQUEDA (ABB)
 *
 * ¿QUÉ ES ESTE ARCHIVO?
 * Este archivo contiene la implementación "elegante" y matemática de un ABB.
 * Utiliza la RECURSIVIDAD para todas sus operaciones principales (insertar,
 * eliminar, buscar, recorrer).
 *
 * LA MAGIA DE LA RECURSIÓN EN ÁRBOLES
 * Piensa en un árbol no como una gran estructura monolítica, sino como un
 * nodo unido a dos "mini-árboles" (sus subárboles).
 * 
 * (10) <-- Nodo actual
 * / \
 * (5) (15) <-- Cada hijo es, a su vez, la raíz de su propio mini-árbol.
 * 
 * La recursividad nos permite pensar así: "Para insertar un número en el árbol
 * gigante, solo lo comparo con la raíz actual. Si es menor, le digo al subárbol
 * izquierdo que se encargue él del problema repitiendo este mismo proceso".
 */
public class ABBRecursivo {

    /**
     * ESTRUCTURA DEL NODO
     * ¿Qué es? La plantilla para cada elemento del árbol. Es el "ladrillo"
     * con el que construiremos toda la estructura.
     * ¿Por qué? Necesitamos una forma de almacenar un dato y, al mismo tiempo,
     * tener "caminos" o "enlaces" a otros nodos.
     */
    static class Nodo {
        // ¿Qué hace? Almacena el valor numérico del nodo.
        int dato;
        // ¿Qué hacen? Son referencias. Una referencia es como una flecha que apunta a
        // otro objeto Nodo en la memoria. 'izquierdo' apunta a un nodo con un
        // valor menor, y 'derecho' a uno con un valor mayor.
        Nodo izquierdo;
        Nodo derecho;

        // ¿Qué es? Un constructor. Es una función especial que se ejecuta al crear un
        // nuevo Nodo.
        // ¿Por qué? Facilita la vida. Con una sola línea (ej: new Nodo(10)), crea el
        // nodo, le asigna el valor y se asegura de que sus hijos apunten a 'nada'
        // (null) inicialmente.
        public Nodo(int dato) {
            this.dato = dato;
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    Nodo raiz;

    public ABBRecursivo() {
        this.raiz = null;
    }

    // --- MÉTODOS DE RECORRIDO ---

    /**
     * RECORRIDO IN-ORDEN
     * 
     * ¿QUÉ SE BUSCA?: Visitar los nodos ordenados de menor a mayor.
     * 
     * COMPORTAMIENTO AL "DEVOLVERSE" (Call Stack):
     * La recursión "baja" todo lo que puede hacia la izquierda. Cuando choca con
     * null,
     * se "devuelve" al nodo anterior, lo imprime, y luego intenta bajar por la
     * derecha.
     * Al terminar con un nodo, se devuelve a su padre para continuar.
     * 
     * PASO A PASO (Ejemplo)
     * Árbol:
     * [10]
     * / \
     * [5] [15]
     * 
     * L1: inOrden(10) -> 1.Llama Izq. [SE PAUSA]
     * L2: inOrden(5) -> 1.Llama Izq(null). Retorna. -> 2.Imprime [5]. -> 3.Llama
     * Der(null). Retorna. [TERMINA Y SE DEVUELVE A L1]
     * L1: inOrden(10) -> [SE REANUDA] -> 2.Imprime [10]. -> 3.Llama Der. [SE PAUSA]
     * L3: inOrden(15) -> 1.Llama Izq(null). Retorna. -> 2.Imprime [15]. -> 3.Llama
     * Der(null). Retorna. [TERMINA Y SE DEVUELVE A L1]
     * L1: inOrden(10) -> [SE REANUDA] -> [TERMINA Y FIN]
     * Resultado: 5 10 15
     */
    private void inOrdenRecursivo(Nodo nodo) {
        if (nodo != null) {
            inOrdenRecursivo(nodo.izquierdo); // 1. Resuelve todo lo de la izquierda primero.
            System.out.print(nodo.dato + " "); // 2. Luego visita la raíz actual.
            inOrdenRecursivo(nodo.derecho); // 3. Finalmente, resuelve todo lo de la derecha.
        }
    }

    /**
     * RECORRIDO PRE-ORDEN
     * 
     * ¿QUÉ SE BUSCA?: Visitar el nodo actual ANTES de sus hijos. Útil para
     * copiar el árbol.
     * 
     * PASO A PASO (Mismo árbol: 10, 5, 15)
     * L1: preOrden(10) -> 1.Imprime [10] -> 2.Llama Izq [PAUSA]
     * L2: preOrden(5) -> 1.Imprime [5] -> 2.Llama Izq(null) -> 3.Llama Der(null)
     * [DEVUELVE A L1]
     * L1: preOrden(10) -> [REANUDA] -> 3.Llama Der [PAUSA]
     * L3: preOrden(15) -> 1.Imprime [15] -> 2.Llama Izq(null) -> 3.Llama Der(null)
     * [DEVUELVE A L1]
     * L1: termina.
     * Resultado: 10 5 15
     */
    private void preOrdenRecursivo(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.dato + " "); // 1. Visita la raíz primero.
            preOrdenRecursivo(nodo.izquierdo); // 2. Luego resuelve la izquierda.
            preOrdenRecursivo(nodo.derecho); // 3. Finalmente resuelve la derecha.
        }
    }

    /**
     * RECORRIDO POST-ORDEN
     * 
     * 🎯 ¿QUÉ SE BUSCA?: Visitar a los hijos ANTES que al padre. Útil para eliminar
     * el árbol desde abajo.
     * 
     * 🚀 PASO A PASO (Mismo árbol: 10, 5, 15)
     * L1: postOrden(10) -> 1.Llama Izq [PAUSA]
     * L2: postOrden(5) -> 1.Llama Izq(null) -> 2.Llama Der(null) -> 3.Imprime [5]
     * [DEVUELVE A L1]
     * L1: postOrden(10) -> [REANUDA] -> 2.Llama Der [PAUSA]
     * L3: postOrden(15) -> 1.Llama Izq(null) -> 2.Llama Der(null) -> 3.Imprime [15]
     * [DEVUELVE A L1]
     * L1: postOrden(10) -> [REANUDA] -> 3.Imprime [10] -> [TERMINA]
     * Resultado: 5 15 10
     */
    private void postOrdenRecursivo(Nodo nodo) {
        if (nodo != null) {
            postOrdenRecursivo(nodo.izquierdo); // 1. Izquierda primero.
            postOrdenRecursivo(nodo.derecho); // 2. Luego derecha.
            System.out.print(nodo.dato + " "); // 3. Finalmente la raíz.
        }
    }

    /**
     * SABER SI UN NODO ES HOJA
     * 
     * ¿QUÉ SE BUSCA?: Encontrar un nodo dado su valor y confirmar si
     * carece totalmente de ramas inferiores (si es una hoja aislada en el fondo del
     * árbol).
     * 
     * LÓGICA Y CÓMO SE HACE:
     * - Se aplica una búsqueda binaria bajando por las ramas.
     * - Si llegamos a un nodo.dato que machaca exactamente nuestro valor, allí
     * mismo
     * miramos si sus ramales (izquierdo y derecho) son 'null'.
     * - Si un puntero es null o fallamos la búsqueda, se asume que no existe hoja
     * con
     * el valor solicitado (retorna false).
     */
    private boolean esHojaRecursivo(Nodo nodo, int valor) {
        if (nodo == null) {
            return false; // 1. Casos donde ni siquiera existe el número apuntado
        }

        if (valor < nodo.dato) {
            return esHojaRecursivo(nodo.izquierdo, valor); // Bajar a la izquierda
        } else if (valor > nodo.dato) {
            return esHojaRecursivo(nodo.derecho, valor); // Bajar a la derecha
        } else {
            // 2. ¡Lo encontramos! Verificación simple: ¿Es su izquierda y su derecha NULOS?
            return (nodo.izquierdo == null && nodo.derecho == null);
        }
    }

    /**
     * ENCONTRAR MÍNIMO NODO DE UN ARBOL O UN SUBARBOL
     * 
     * Metodo importante para eliminar un nodo
     */
    private int encontrarMinimo(Nodo nodo) {
        // ¿Qué hace? Sigue el camino de referencias izquierdas hasta el final.
        // ¿Por qué? Por definición, en un ABB, el valor más pequeño siempre está en
        // el nodo más a la izquierda posible.
        return nodo.izquierdo == null ? nodo.dato : encontrarMinimo(nodo.izquierdo);
    }

    private int encontrarMaximo(Nodo nodo) {
        // ¿Qué hace? Sigue el camino de referencias derechas hasta el final.
        // ¿Por qué? Por definición, en un ABB, el valor más grande siempre está en el
        // nodo más a la derecha posible.
        return nodo.derecho == null ? nodo.dato : encontrarMaximo(nodo.derecho);
    }

    /**
     * CALCULAR ALTURA RECURSIVA (Método auxiliar)
     * 
     * ¿QUÉ SE BUSCA?: Saber la "profundidad" o el camino más largo desde la raíz
     * hacia
     * la hoja más lejana. La altura de una hoja es de 0.
     * 
     * LÓGICA Y CÓMO SE HACE:
     * - Se aplica una estrategia Bottom-Up (Post-Orden). Primero preguntamos la
     * altura
     * de las ramas inferiores.
     * - Una posición "vacía" (null) devuelve una altura de -1.
     * - Cada nodo recibe la altura de su lado izquierdo y de su derecho. Selecciona
     * siempre al que haya vuelto con un número mayor (max) y le suma "1" para
     * contabilizarse a sí mismo en ese camino continuo.
     * 
     * EJEMPLO VISUAL PASO A PASO: Calcular la altura de un árbol
     * Árbol Inicial:
     * [10] <-- Nivel 2
     * / \
     * [5] [15] <-- Nivel 1
     * \
     * [8] <-- Nivel 0
     * 
     * Llamada 1: calcularAlturaRecursivo([10])
     * ↳ Busca izquierda [5] y derecha [15].
     * 
     * [Rama Izquierda]:
     * Llamada 2: calcularAlturaRecursivo([5])
     * ↳ Llama a su izq (null) -> Retorna -1.
     * ↳ Llama a su der [8] -> Llamada 3.
     * [Llamada 3]: calcularAlturaRecursivo([8])
     * ↳ Llama a izq (null) y der (null).
     * ↳ Ambos retornan -1.
     * ↳ Math.max(-1, -1) = -1. Retorna 1 + (-1) = 0. (La altura de la hoja [8] es
     * 0).
     * ↳ Volviendo a [5]: izq dio -1, der dio 0.
     * ↳ Math.max(-1, 0) = 0. Retorna 1 + 0 = 1. (El nodo [5] tiene altura 1).
     * 
     * [Rama Derecha]:
     * Llamada 4: calcularAlturaRecursivo([15])
     * ↳ Es una hoja. Hace cálculos (null), y como el nodo 8, retorna 0.
     * 
     * [Resolviendo la Llamada 1 (Raíz)]:
     * ↳ Su subárbol izquierdo [5] retornó 1.
     * ↳ Su subárbol derecho [15] retornó 0.
     * ↳ Math.max(1, 0) = 1.
     * ↳ Retorna 1 + (max=1) = 2. (El árbol tiene altura total 2).
     */
    private int calcularAlturaRecursivo(Nodo nodo) {
        // --- CASO BASE ---
        // ¿Qué hace? Si el nodo es nulo, devuelve -1.
        // ¿Por qué? Un espacio vacío no tiene altura. El -1 hace que la matemática
        // para un nodo hoja (un nodo sin hijos) sea correcta: 1 + max(-1, -1) = 0.
        if (nodo == null) {
            return -1;
        }
        // --- PASO RECURSIVO ---
        // ¿Qué hace? Calcula la altura de cada subárbol por separado.
        // ¿Por qué? La altura es el camino más largo. Debemos explorar ambos
        // caminos (izquierdo y derecho) para saber cuál es más largo.
        int alturaIzquierda = calcularAlturaRecursivo(nodo.izquierdo);
        int alturaDerecha = calcularAlturaRecursivo(nodo.derecho);
        int elMasAlto = Math.max(alturaIzquierda, alturaDerecha);
        // ¿Qué hace? Devuelve 1 más la altura del subárbol más alto.
        // ¿Por qué? El '1' cuenta el nivel del nodo actual, y el 'max' elige el
        // camino más largo que encontró entre sus hijos.
        return 1 + elMasAlto;
    }

    // esABB(raiz, null, null)
    // [10]
    // / \
    // [8] [15]
    // \
    // [7]
    // esABB([10], null, null)
    //   esABB([8], null, 10)
    //      esABB([7], 8, 10) INVALIDO, porque 7 no puede ser menor o igual a 8 (su ancestro izquierdo)
    //   esABB([15], 10, null)
    private boolean esABB(Nodo nodo, Integer min, Integer max) {
        // --- CASO BASE ---
        // ¿Qué hace? Si el nodo es nulo, la rama es válida.
        // ¿Por qué? Un subárbol vacío no puede violar ninguna regla.
        if (nodo == null) {
            return true;
        }
        // --- LÓGICA DE VALIDACIÓN ---
        // ¿Qué hace? Comprueba si el valor del nodo actual está fuera del rango [min,
        // max]
        // permitido por sus ancestros.
        // ¿Por qué? No basta con comparar con el padre directo. Un nodo debe respetar a
        // TODOS sus ancestros. Este rango que se va estrechando garantiza esta
        // propiedad global.
        if ((min != null && nodo.dato <= min) // El nodo actual no puede ser menor o igual al mínimo permitido (que
                                              // viene de un ancestro izquierdo).
                || // o, osea cualquiera de las dos condiciones es suficiente para invalidar el ABB
                (max != null && nodo.dato >= max)) // El nodo actual no puede ser mayor o igual al máximo permitido (que
                                                   // viene de un ancestro derecho).
        {
            return false;
        }
        // --- PASO RECURSIVO ---
        // ¿Qué hace? Llama a la función para sus hijos con los rangos actualizados.
        // ¿Por qué? Al ir a la izquierda, el valor del nodo actual se convierte en el
        // nuevo MÁXIMO
        // permitido. Al ir a la derecha, se convierte en el nuevo MÍNIMO.
        // Esto asegura que cada nodo respete la propiedad de ordenamiento con respecto
        // a
        // todos sus ancestros, no solo el padre directo.
        // es decir, vamos estrechando el rango de valores permitidos a medida que
        // bajamos por el árbol, lo que garantiza que no haya violaciones de la
        // propiedad del ABB en ningún nivel.
        return esABB(nodo.izquierdo, min, nodo.dato) &&
                esABB(nodo.derecho, nodo.dato, max);
    }

    // ¿Qué hacen? Son el punto de entrada para el usuario.
    // ¿Por qué? Ocultan la complejidad de la recursión. El usuario no necesita
    // saber sobre 'nodoActual', solo quiere insertar un valor en "el árbol".
    // Estas funciones inician el proceso recursivo desde la raíz.
    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

    public void eliminar(int valor) {
        raiz = eliminarRecursivo(raiz, valor);
    }

    /**
     * INSERCIÓN RECURSIVA
     * 
     * ¿QUÉ SE BUSCA?: Insertar un nuevo valor en el árbol, encontrando
     * exactamente
     * su posición adecuada para que el árbol siga siendo un ABB válido.
     * 
     * LÓGICA Y CÓMO SE HACE:
     * - Si el árbol (o subárbol) está vacío (null), encontramos el lugar. Creamos
     * el nodo.
     * - Si el valor es menor que el nodo actual, delegamos el problema a la rama
     * izquierda.
     * - Si el valor es mayor que el nodo actual, delegamos el problema a la rama
     * derecha.
     * Al volver de la recursión, cada padre actualiza o "re-engancha" sus punteros.
     * 
     * EJEMPLO VISUAL PASO A PASO: Insertar el número 7
     * Árbol Inicial:
     * [10]
     * / \
     * [5] [15]
     * \
     * [8]
     * 
     * [Llamada 1]: insertarRecursivo(nodo=10, valor=7)
     * ↳ 7 < 10 -> Ir a la izquierda.
     * [Llamada 2]: insertarRecursivo(nodo=5, valor=7)
     * ↳ 7 > 5 -> Ir a la derecha.
     * [Llamada 3]: insertarRecursivo(nodo=8, valor=7)
     * ↳ 7 < 8 -> Ir a la izquierda.
     * [Llamada 4]: insertarRecursivo(nodo=null, valor=7)
     * ↳ ¡CASO BASE! Retorna new Nodo(7).
     * 
     * [Retornos]:
     * Llamada 3: nodo 8 enlaza su izquierda al nodo 7. Retorna 8.
     * Llamada 2: nodo 5 mantiene su derecha en 8. Retorna 5.
     * Llamada 1: nodo 10 mantiene su izquierda en 5. Retorna 10.
     */
    private Nodo insertarRecursivo(Nodo nodoActual, int valor) {
        // --- PASO 1: CASO BASE - EL PUNTO DE PARADA ---
        // ¿Qué hace? Comprueba si hemos llegado a un espacio vacío (una referencia
        // nula).
        // ¿Por qué? La recursión debe terminar. Este es el final del camino: hemos
        // encontrado el lugar exacto donde debe ir el nuevo nodo. Creamos el nodo
        // y lo retornamos para que la llamada anterior (su padre) lo "enganche".
        if (nodoActual == null) {
            return new Nodo(valor);
        }

        // --- PASO 2: PASO RECURSIVO - LA DECISIÓN Y LA LLAMADA ---
        // ¿Qué hace? Compara el valor con el dato del nodo actual para decidir a dónde
        // ir.
        // ¿Por qué? Para mantener la propiedad del ABB.
        if (valor < nodoActual.dato) {
            // ¿Qué hace? Llama a la misma función, pero para el subárbol izquierdo.
            // ¿Por qué? Delega la tarea a un problema más pequeño. La clave es la
            // asignación `nodoActual.izquierdo = ...`. El padre (nodoActual) actualiza
            // su puntero izquierdo con el resultado de la inserción en su subárbol.
            nodoActual.izquierdo = insertarRecursivo(nodoActual.izquierdo, valor);
        } else if (valor > nodoActual.dato) {
            // La misma lógica, pero para el lado derecho.
            nodoActual.derecho = insertarRecursivo(nodoActual.derecho, valor);
        }

        // --- PASO 3: EL RETORNO - MANTENIENDO LA CADENA ---
        // ¿Qué hace? Devuelve el puntero al nodo actual.
        // ¿Por qué? Si el valor ya existía, o si la inserción ocurrió más abajo,
        // este nodo no cambia. Devolverlo asegura que los enlaces por encima de él
        // en la cadena de recursión permanezcan intactos.
        return nodoActual;
    }

    private Nodo buscarAncestroComún(Nodo nodoActual, int valor1, int valor2) {
        if (nodoActual == null) {
            return null;
        }

        if (valor1 < nodoActual.dato && valor2 < nodoActual.dato) {
            return buscarAncestroComún(nodoActual.izquierdo, valor1, valor2);
        } else if (valor1 > nodoActual.dato && valor2 > nodoActual.dato) {
            return buscarAncestroComún(nodoActual.derecho, valor1, valor2);
        } else {
            return nodoActual; // Este nodo es el ancestro común
        }
    }

    /**
     * ELIMINACIÓN RECURSIVA (Método auxiliar)
     * 
     * ¿QUÉ SE BUSCA?: Localizar un nodo específico por su valor y eliminarlo por
     * completo
     * sin dejar "agujeros" ni romper las reglas de ordenamiento del ABB.
     * 
     * LÓGICA Y CÓMO SE HACE:
     * - Fase 1 (Búsqueda): Recursión para hallar el nodo (igual que insertar).
     * - Fase 2 (Ajuste estructural):
     * Caso 1 (Hoja): Se devuelve null para que su padre corte la conexión.
     * Caso 2 (1 Hijo): Se devuelve el hijo existente para que su padre lo herede.
     * Caso 3 (2 Hijos): Se reemplaza el valor de este nodo con el "sucesor
     * in-orden"
     * (el más pequeño de su rama derecha), y se manda a borrar recursivamente
     * a ese sucesor (que será, a lo sumo, un Caso 1 o 2).
     * 
     * EJEMPLO VISUAL PASO A PASO: Eliminar un nodo con 2 hijos (ej: el 10)
     * Árbol Inicial:
     * [10] <-- ¡A eliminar!
     * / \
     * [5] [15]
     * / \ / \
     * [3] [8] [12][18]
     * 
     * PASO 1: Buscar el nodo conteniendo 10.
     * ↳ Found. Es un Caso 3 (tiene hijo izquierdo y derecho).
     * 
     * PASO 2: Buscar su sucesor (el MÍNIMO del subárbol derecho).
     * ↳ Bajar al subárbol derecho (raíz 15) e ir todo a la izquierda = [12].
     * 
     * PASO 3: Copiar valor.
     * ↳ Copiar '12' dentro del nodo '10'. El árbol temporalmente queda:
     * [12] <-- Valor copiado (antes era 10)
     * / \
     * [5] [15]
     * / \ / \
     * [3] [8] [12][18] <-- Aún existe el 12 original abajo
     * 
     * PASO 4: Eliminar el '12' duplicado en el subárbol derecho.
     * ↳ Se llama a eliminarRecursivo(nodo.derecho=15, valor=12)
     * ↳ Allí, '12' es una hoja (Caso 1), devolverá null, "cortándolo".
     * 
     * RESULTADO FINAL:
     * [12]
     * / \
     * [5] [15]
     * / \ \
     * [3] [8] [18]
     */
    private Nodo eliminarRecursivo(Nodo nodoActual, int valor) {
        // --- PASO 1: BÚSQUEDA - ENCONTRAR EL NODO ---
        // ¿Qué hace? Si llegamos a un nulo, el valor no existe. Es el caso base de la
        // búsqueda.
        if (nodoActual == null) {
            return null;
        }
        // ¿Qué hace? Llama recursivamente para bajar por el árbol hasta encontrar el
        // nodo.
        if (valor < nodoActual.dato) {
            nodoActual.izquierdo = eliminarRecursivo(nodoActual.izquierdo, valor);
        } else if (valor > nodoActual.dato) {
            nodoActual.derecho = eliminarRecursivo(nodoActual.derecho, valor);
        } else {
            // --- PASO 2: NODO ENCONTRADO - LÓGICA DE ELIMINACIÓN ---

            // CASO 1: Nodo con 0 hijos (hoja).
            // ¿Qué hace? Devuelve null.
            // ¿Por qué? El padre que llamó a esta función recibirá null y lo asignará
            // a su puntero de hijo, efectivamente "cortando" la hoja del árbol.
            if (nodoActual.izquierdo == null && nodoActual.derecho == null) {
                return null;
            }

            // CASO 2: Nodo con 1 hijo.
            // ¿Qué hace? Devuelve la referencia a su único hijo.
            // ¿Por qué? El padre del nodo eliminado "adoptará" directamente a su nieto,
            // "puenteando" el nodo eliminado.
            // PREGUNTA: ¿Por qué esto mantiene la propiedad ABB?
            // Porque todos los nodos en ese nieto y sus descendientes seguían respetando a
            // su "abuelo". Literalmente hemos quitado un eslabón innecesario de la cadena,
            // manteniendo el equilibrio numérico y simplemente subiendo de nivel a los
            // sobrevivientes.
            if (nodoActual.derecho == null) {
                return nodoActual.izquierdo;
            }
            if (nodoActual.izquierdo == null) {
                return nodoActual.derecho;
            }

            // CASO 3: Nodo con 2 hijos.
            // ¿Qué hace? Encuentra el sucesor, copia su valor al nodo actual, y luego
            // elimina el sucesor de su posición original.
            // PREGUNTA: ¿Por qué necesitamos el MÍNIMO del subárbol DERECHO?
            // Necesitamos un reemplazo "perfecto" que pueda ir en este cruce importante.
            // 1. Al buscarlo del lado derecho, aseguramos que será MAYOR que todo el lado
            // izquierdo.
            // 2. Al buscar el más bajo (mínimo) de ese lado derecho, aseguramos que será
            // MENOR
            // que el resto de posibles candidatos derechos.
            // Es básicamente el "siguiente número" en la secuencia ascendente. El sustituto
            // ideal para no romper nada.
            // int predecesor = encontrarMaximo(nodoActual.izquierdo); // Alternativa: usar
            // el máximo del lado izquierdo
            int sucesor = encontrarMinimo(nodoActual.derecho);
            nodoActual.dato = sucesor;
            nodoActual.derecho = eliminarRecursivo(nodoActual.derecho, sucesor);
        }
        return nodoActual;
    }

    private Nodo buscarRecursivo(Nodo nodoActual, int valor) {
        if (nodoActual == null) {
            return null;
        }
        if (valor < nodoActual.dato) {
            return buscarRecursivo(nodoActual.izquierdo, valor);
        } else if (valor > nodoActual.dato) {
            return buscarRecursivo(nodoActual.derecho, valor);
        } else {
            return nodoActual; // Nodo encontrado
        }
    }

    public static void main(String[] args) {
        ABBRecursivo arbol = new ABBRecursivo();
        System.out.println("--- ÁRBOL RECURSIVO ---");
        int[] valores = { 10, 5, 15, 3, 7, 12, 18 };
        System.out.print("Insertando: ");
        for (int valor : valores) {
            System.out.print(valor + " ");
            arbol.insertar(valor);
        }
        System.out.println("\n");

        System.out.print("In-Orden:  ");
        arbol.inOrdenRecursivo(arbol.raiz);
        System.out.println();

        System.out.print("Pre-Orden: ");
        arbol.preOrdenRecursivo(arbol.raiz);
        System.out.println();

        System.out.print("Post-Orden: ");
        arbol.postOrdenRecursivo(arbol.raiz);
        System.out.println();

        System.out.println("\nLa altura del arbol es: " + arbol.calcularAlturaRecursivo(arbol.raiz));
        System.out.println("El arbol es un ABB valido? " + (arbol.esABB(arbol.raiz, null, null) ? "Si" : "No"));

        System.out.println("\n--- COMPROBANDO HOJAS ---");
        System.out.println("El numero 8 es una hoja? " + (arbol.esHojaRecursivo(arbol.raiz, 8) ? "Si" : "No"));
        System.out.println("El numero 10 (la raiz con hijos) es una hoja? "
                + (arbol.esHojaRecursivo(arbol.raiz, 10) ? "Si" : "No"));

        System.out.println("\nEliminando el 15 (nodo con dos hijos)...");
        arbol.eliminar(15);

        System.out.print("In-Orden modificado: ");
        arbol.inOrdenRecursivo(arbol.raiz);
        System.out.println();
    }
}