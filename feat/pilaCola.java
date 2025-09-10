package feat;

public class pilaCola {
    private class Nodo {
        int dato;
        Nodo siguiente;

        public Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    public class Pila {
        private Nodo cima;

        public Pila() {
            this.cima = null;
        }

        public void push(int nuevoDato) {
            Nodo nuevoNodo = new Nodo(nuevoDato);
            nuevoNodo.siguiente = cima;
            this.cima = nuevoNodo;
            System.out.println("Push: " + nuevoDato);
        }

        public int pop() {
            if (estaVacia()) {
                System.out.println("Error: La pila esta vacia.");
                return -1;
            }
            int dato = cima.dato;
            this.cima = cima.siguiente;
            System.out.println("Pop: " + dato);
            return dato;
        }

        public boolean estaVacia() {
            return this.cima == null;
        }
    }

    public class Cola {
        private Nodo frente;
        private Nodo atras;

        public Cola() {
            this.frente = null;
            this.atras = null;
        }

        public void encolar(int nuevoDato) {
            Nodo nuevoNodo = new Nodo(nuevoDato);
            if (estaVacia()) {
                this.frente = nuevoNodo;
                this.atras = nuevoNodo;
            } else {
                this.atras.siguiente = nuevoNodo;
                this.atras = nuevoNodo;
            }
        }

        public int desencolar() {
            if (estaVacia()) {
                System.out.println("Error: La cola esta vacia.");
                return -1;
            }
            int dato = frente.dato;
            this.frente = this.frente.siguiente;
            if (this.frente == null) {
                this.atras = null;
            }
            return dato;
        }

        public boolean estaVacia() {
            return this.frente == null;
        }

        public void mostrar() {
            if (estaVacia()) {
                System.out.println("La cola está vacía.");
                return;
            }
            Nodo actual = frente;
            System.out.print("Cola: ");
            while (actual != null) {
                System.out.print(actual.dato + " ");
                actual = actual.siguiente;
            }
            System.out.println();
        }
    }

    // Invertir los primeros N elementos de una cola usando una pila auxiliar.
    public Cola invertirPrimerosNDeUnaCola(Cola cola, int nInvertidos) {
        // Primeros comprobamos que n sea válido y que la cola no esté vacía.
        if (nInvertidos <= 0 || cola.estaVacia()) {
            throw new IllegalArgumentException("n debe ser mayor que 0 y la cola no debe estar vacía.");
        }

        // Esta pila auxiliar es donde almacenaremos los primeros N elementos. Gracias al principio LIFO (Last In, First Out)
        // de las pilas, al sacar y meter los elementos de la cola en la pila y luego volver a sacarlos de la pila a la cola,
        // los elementos quedarán invertidos.
        Pila pila = new Pila();

        // Aqui haremos un recorrido por N iteraciones o hasta que la cola esté vacía,
        // lo que ocurra primero. En cada iteración, sacamos un elemento de la cola y lo metemos en la pila.
        for (int i = 0; i < nInvertidos && !cola.estaVacia(); i++) {
            // Desencolamos y sacamos el elemento del frente de la cola y lo empujamos a la pila.
            pila.push(cola.desencolar());
        }

        // Ahora, sacamos todos los elementos de la pila y los volvemos a meter en la cola.
        // Debido a la naturaleza de la pila, los elementos saldrán en orden inverso
        while (!pila.estaVacia()) {
            cola.encolar(pila.pop());
        }

        // Finalmente, para mantener el orden original de los elementos que no fueron invertidos,
        // sacamos cada uno de ellos y los volvemos a meter al final de la cola.
        // Esto asegura que los elementos que no fueron parte de la inversión permanezcan en su orden original.
        // Calculamos el tamaño actual de la cola para saber cuántos elementos mover.
        int size = 0;
        Nodo actual = cola.frente;
        // Contamos los elementos en la cola.
        while (actual != null) {
            size++;
            actual = actual.siguiente;
        }

        // Movemos los elementos que no fueron invertidos al final de la cola.
        // Esto se hace sacando cada elemento del frente y volviéndolo a meter al final. Los que estan
        // en el frente de la cola son los que no fueron invertidos.
        // Hacemos esto size - nInvertidos veces, ya que los primeros nInvertidos elementos ya fueron invertidos.
        int numeroDeElementosNoInvertidos = size - nInvertidos;
        
        // Ejemplo: Si la cola tiene 5 elementos y nInvertidos es 3, entonces
        // numeroDeElementosNoInvertidos será 2, y moveremos esos 2 elementos al final.
        // Si nInvertidos es mayor o igual al tamaño de la cola,
        // entonces no hay elementos no invertidos que mover.
        if (numeroDeElementosNoInvertidos < 0) {
            numeroDeElementosNoInvertidos = 0;
        }

        for (int i = 0; i < numeroDeElementosNoInvertidos; i++) {
            cola.encolar(cola.desencolar());
        }

        return cola;
    }

    public static void main(String[] args) {
        pilaCola pc = new pilaCola();
        Cola cola = pc.new Cola();

        // Encolamos algunos elementos en la cola.
        cola.encolar(1);
        cola.encolar(2);
        cola.encolar(3);
        cola.encolar(4);
        cola.encolar(5);

        System.out.println("Elementos de la cola antes de invertir los primeros 3:");
        cola.mostrar();

        // Invertimos los primeros 3 elementos de la cola.
        Cola colaInvertida = pc.invertirPrimerosNDeUnaCola(cola, 3);

        // Mostramos los elementos de la cola invertida.
        System.out.println("Elementos de la cola después de invertir los primeros 3:");
        colaInvertida.mostrar();
    }
}
