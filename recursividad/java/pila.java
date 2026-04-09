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
     * MÁGIA DE LA PILA DE LLAMADAS:
     * Si nosotros extraemos (pop) todos los elementos de la pila y guardamos
     * cada uno en una llamada recursiva, al regresar de la recursión los habremos
     * sacado desde el primero hasta el último. Al meterlos "al fondo", el orden
     * queda completamente invertido.
     * 
     * PROCESO EJEMPLIFICADO (Pila original: Cima[A, B, C]Fondo):
     * 
     * -> invertirPila([A, B, C])
     * Saca 'A', llama invertirPila([B, C])
     * 
     * -> invertirPila([B, C])
     * Saca 'B', llama invertirPila([C])
     * 
     * -> invertirPila([C])
     * Saca 'C', llama invertirPila([])
     * 
     * -> invertirPila([]) -> Caso Base (retorna)
     * 
     * <- Inserta 'C' en el fondo de [] => Pila: [C]
     * 
     * <- Inserta 'B' en el fondo de [C] => Pila: Cima[C, B]Fondo
     * 
     * <- Inserta 'A' en el fondo de [C, B] => Pila: Cima[C, B, A]Fondo
     * (¡Invertida!)
     */
    public void invertirPila() {
        // CASO BASE: Si la pila está vacía, no hay nada que invertir.
        // La recursión se detiene.
        if (estaVacia()) {
            return;
        }

        // PASO RECURSIVO:
        // 1. Sacamos el elemento superior (cima).
        int dato = this.pop();

        // 2. Invertimos el resto de la pila mágicamente mediante recursión.
        invertirPila();

        // 3. Insertamos el elemento que sacamos al inicio, pero en el fondo de la pila.
        insertarEnElFondo(dato);
    }

    /**
     * FUNCIÓN AUXILIAR RECURSIVA: Insertar en el fondo.
     * 
     * ¿Por qué necesitamos esto? En una pila tradicional, `push` sólo pone cosas
     * en la cima (arriba). Si queremos poner algo hasta abajo (fondo), tenemos
     * que sacar todo lo que está estorbando, poner nuestro elemento, y regresar
     * lo que sacamos en su mismo orden.
     * 
     * ILUSTRACIÓN ASCII (Insertar 'X' en el fondo de [1, 2]):
     * ==============================================================
     * LLAMADA 1: insertarEnElFondo(X) en Pila: Cima[1, 2]Fondo
     * - ¿Vacía? NO.
     * - datoTemporal = pop() -> ¡Sacamos el 1! Pila queda [2]
     * - LLAMADA RECURSIVA a sí misma: insertarEnElFondo(X)
     * 
     * LLAMADA 2: insertarEnElFondo(X) en Pila: Cima[2]Fondo
     * - ¿Vacía? NO.
     * - datoTemporal = pop() -> ¡Sacamos el 2! Pila queda []
     * - LLAMADA RECURSIVA a sí misma: insertarEnElFondo(X)
     * 
     * LLAMADA 3 (Caso Base): insertarEnElFondo(X) en Pila: Cima[]Fondo
     * - ¿Vacía? SÍ. -> push(X). Pila queda [X].
     * - Terminamos y vamos de regreso arriba...
     * 
     * <- VOLVEMOS A LLAMADA 2: push(datoTemporal) -> push(2). Pila queda [2, X]
     * 
     * <- VOLVEMOS A LLAMADA 1: push(datoTemporal) -> push(1). Pila queda [1, 2, X]
     * ==============================================================
     */
    private void insertarEnElFondo(int dato) {
        // CASO BASE: Si la pila está vacía, el fondo es la cima, podemos usar push.
        if (estaVacia()) {
            this.push(dato);
            return;
        }

        // PASO RECURSIVO:
        // Si no está vacía, quitamos el de arriba para "hacer espacio"...
        int datoTemporal = this.pop();

        // ... llamamos recursivamente para poner nuestro 'dato' en el fondo
        insertarEnElFondo(dato);

        // ... y finalmente regresamos a su lugar original el dato que quitamos
        // como paso previo (ya que todo lo que estaba abajo ya se acomodó).
        this.push(datoTemporal);
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
