package recursividad.java;

public class pila {
    private static class Nodo {
        int dato;
        Nodo siguiente;

        public Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo cima;

    public pila() {
        this.cima = null;
    }

    public void push(int nuevoDato) {
        Nodo nuevoNodo = new Nodo(nuevoDato);
        nuevoNodo.siguiente = cima;
        this.cima = nuevoNodo;
    }

    public int pop() {
        if (estaVacia()) {
            return -1;
        }
        int dato = cima.dato;
        this.cima = cima.siguiente;
        return dato;
    }

    public int peek() {
        if (estaVacia()) {
            return -1;
        }
        return cima.dato;
    }

    public boolean estaVacia() {
        return cima == null;
    }

    /**
     * EJERCICIO DE RECURSIVIDAD 1: Contar elementos de la pila.
     * 
     * ¿Cómo funciona?
     * En lugar de usar un bucle 'while', avanzamos por la estructura y decimos:
     * "La cantidad de elementos desde este nodo es: 1 (yo mismo) + (la cantidad del
     * resto)".
     * 
     * EJEMPLO: Pila [10, 20, 30] -> Cima = 30
     * contar(30) -> devuelve 1 + 2
     * contar(20) -> devuelve 1 + 1
     * contar(10) -> devuelve 1 + 0
     * contar(null) -> devuelve 0 (CASO BASE)
     * 
     * RESULTADO FINAL: 1 + (1 + (1 + 0)) = 3.
     * 
     * @param nodo El nodo actual desde donde contamos.
     * @return El número de elementos restantes en la pila.
     */
    public int contarRecursivo(Nodo nodo) {
        // CASO BASE: Si el nodo es nulo, hemos llegado al fondo de la pila,
        // no hay más elementos que contar, retornamos 0.
        if (nodo == null) {
            return 0;
        }

        // PASO RECURSIVO: Contamos este nodo (1) y preguntamos a la recursión
        // cuántos nodos hay en el resto de la pila (nodo.siguiente).
        return 1 + contarRecursivo(nodo.siguiente);
    }

    /**
     * EJERCICIO DE RECURSIVIDAD 2: Sumar todos los elementos de la pila.
     * 
     * Comparte el mismo principio de "conteo", pero en vez de sumar '1',
     * sumamos el valor del dato del nodo actual.
     * 
     * EJEMPLO ASCII:
     * [30] -> cima
     * [20]
     * [10]
     * 
     * La operación será: 30 + sumar([20, 10]) => 30 + 20 + sumar([10]) => 30 + 20 +
     * 10 + 0 = 60.
     * 
     * @param nodo El nodo actual.
     * @return La suma de los campos 'dato' de los nodos.
     */
    public int sumarElementosRecursivo(Nodo nodo) {
        // CASO BASE: Si llegamos al fondo, la suma aportada es 0.
        if (nodo == null) {
            return 0;
        }

        // PASO RECURSIVO: Sumamos el dato del nodo actual más la suma de todos los
        // datos de los nodos siguientes (la recursión resuelve el resto del problema).
        return nodo.dato + sumarElementosRecursivo(nodo.siguiente);
    }

    /**
     * EJERCICIO DE RECURSIVIDAD 3: Invertir la pila.
     * 
     * DIAGRAMA DEL PROCESO (Pila: Cima[A, B, C]Fondo):
     * =======================================================
     * invertirPila() // Pila actual: [A, B, C]
     * |__ dato = pop() -> 'A', Pila queda: [B, C]
     * |__ invertirPila()
     * |__ dato = pop() -> 'B', Pila queda: [C]
     * |__ invertirPila()
     * |__ dato = pop() -> 'C', Pila queda: []
     * |__ invertirPila()
     * |__ devuelve: (Caso Base, pila vacía)
     * 
     * EL REGRESO (Desenrollando la pila de llamadas):
     * Inserta 'C' al fondo de []. Pila queda: Cima[C]Fondo
     * Inserta 'B' al fondo de [C]. Pila queda: Cima[C, B]Fondo
     * Inserta 'A' al fondo de [C, B]. Pila queda: Cima[C, B, A]Fondo <-- Resultado
     * Final (Invertida)
     */
    public void invertirPila() {
        // CASO BASE: Si la pila está vacía, no hay nada que invertir.
        if (estaVacia()) {
            return;
        }

        // PASO RECURSIVO:
        // 1. Sacamos el elemento superior (cima).
        int dato = this.pop();

        // 2. Invertimos el resto de la pila mágicamente mediante recursión.
        invertirPila();

        // 3. Insertamos el elemento que sacamos al fondo, usando un bucle simple
        Nodo nuevoNodo = new Nodo(dato);
        if (estaVacia()) {
            cima = nuevoNodo;
        } else {
            Nodo actual = cima;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }

    /**
     * EJERCICIO DE RECURSIVIDAD 4: Buscar un elemento en la pila de forma
     * recursiva.
     * 
     * DIAGRAMA DEL PROCESO (Buscar 20 en [10, 20, 30]):
     * =======================================================
     * buscar(nodo: 10, valor: 20)
     * |__ 10 == 20? Falso. Devuelve: buscar(nodo: 20, valor: 20)
     * |__ 20 == 20? Verdadero. (Caso Base - Éxito)
     * |__ devuelve: true
     * 
     * EL REGRESO (Desenrollando la pila de llamadas):
     * Devuelve: true
     * Devuelve: true <-- Resultado Final
     * 
     * @param nodo  El nodo actual donde evaluar.
     * @param valor El valor a buscar en la pila.
     * @return true si se encuentra, false si llegamos al final sin encontrarlo.
     */
    public boolean buscarElementoRecursivo(Nodo nodo, int valor) {
        if (nodo == null) {
            return false;
        }
        if (nodo.dato == valor) {
            return true;
        }
        return buscarElementoRecursivo(nodo.siguiente, valor);
    }

    /**
     * EJERCICIO DE RECURSIVIDAD 5: Encontrar el valor máximo en la pila.
     * 
     * DIAGRAMA DEL PROCESO (Pila con valores [10, 50, 20]):
     * =======================================================
     * obtenerMaximoRecursivo(nodo: 10)
     * |__ maxResto = obtenerMaximoRecursivo(nodo: 50)
     * |__ maxResto = obtenerMaximoRecursivo(nodo: 20)
     * |__ maxResto = obtenerMaximoRecursivo(nodo: null)
     * |__ devuelve: Integer.MIN_VALUE (Caso Base)
     * 
     * EL REGRESO (Desenrollando la pila de llamadas):
     * Math.max(20, MIN_VALUE) = 20
     * Math.max(50, 20) = 50
     * Math.max(10, 50) = 50 <-- Resultado Final (Máximo)
     * 
     * @param nodo El nodo actual desde el cual buscar.
     * @return El valor máximo encontrado.
     */
    public int obtenerMaximoRecursivo(Nodo nodo) {
        if (nodo == null) {
            return Integer.MIN_VALUE;
        }
        int maxResto = obtenerMaximoRecursivo(nodo.siguiente);
        return Math.max(nodo.dato, maxResto);
    }

    public static void main(String[] args) {
        pila p = new pila();
        p.push(1);
        p.push(2);
        p.push(3);
        p.push(4);

        System.out.println("Cantidad de elementos (Recursivo): " + p.contarRecursivo(p.cima));
        System.out.println("Suma de elementos (Recursivo): " + p.sumarElementosRecursivo(p.cima));

        System.out.println("Cima antes de invertir: " + p.peek());
        p.invertirPila();
        System.out.println("Cima despues de invertir: " + p.peek());
    }
}
