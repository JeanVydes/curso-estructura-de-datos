package colas;

public class cola {
    // Clase interna anidada (nested class) para representar un nodo en la cola.
    // Es 'static' porque no necesita una instancia de 'cola' para existir.
    private static class Nodo {
        int dato;     // El valor (dato) que el nodo va a almacenar.
        Nodo siguiente; // Un puntero al siguiente nodo en la secuencia.

        public Nodo(int dato) {
            this.dato = dato;
            // Al crear un nuevo nodo, 'siguiente' siempre apunta a 'null'
            // porque, por definición, es el último en la cadena por ahora.
            this.siguiente = null; 
        }
    }

    // Puntero al frente (o cabeza) de la cola.
    // Aquí es donde siempre se eliminan los elementos (dequeue).
    private Nodo frente; 
    
    // Puntero al final (o cola) de la cola.
    // Aquí es donde siempre se añaden los elementos (enqueue).
    private Nodo final_de_cola; 

    // Constructor de la cola.
    // Al inicializar, la cola está vacía, así que ambos punteros son 'null'.
    public cola() {
        this.frente = null;
        this.final_de_cola = null;
    }

    // Método para añadir un elemento al final de la cola (enqueue).
    // Esta operación se realiza en tiempo constante O(1).
    public void enqueue(int nuevoDato) {
        // 1. Crea un nuevo nodo con el dato proporcionado.
        Nodo nuevoNodo = new Nodo(nuevoDato);

        // 2. Si la cola está vacía (es decir, el 'final_de_cola' es null)...
        if (final_de_cola == null) {
            // El nuevo nodo se convierte en el único nodo,
            // por lo que es tanto el frente como el final.
            frente = nuevoNodo;
            final_de_cola = nuevoNodo;
        } else {
            // Si la cola no está vacía...
            // a) El 'siguiente' del nodo que estaba al final ahora
            //    apunta al nuevo nodo.
            final_de_cola.siguiente = nuevoNodo;
            // b) El nuevo nodo se convierte en el nuevo 'final_de_cola'.
            final_de_cola = nuevoNodo;
        }
    }

    // Método para eliminar y devolver el elemento del frente de la cola (dequeue).
    // Esta operación también se realiza en tiempo constante O(1).
    public int dequeue() {
        // 1. Verifica si la cola está vacía. Si es así, lanza una excepción
        //    para evitar errores.
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía, no se puede realizar 'dequeue'.");
        }

        // 2. Guarda el dato del nodo del frente para devolverlo más tarde.
        int dato = frente.dato;

        // 3. Mueve el puntero del 'frente' al siguiente nodo.
        // El recolector de basura de Java se encargará del nodo anterior.
        frente = frente.siguiente;

        // 4. Si después de mover el 'frente', este se vuelve 'null',
        //    significa que la cola se ha quedado vacía.
        if (frente == null) {
            // Si la cola está vacía, el puntero 'final_de_cola' también debe ser 'null'.
            final_de_cola = null;
        }

        // 5. Devuelve el dato que estaba en el frente.
        return dato;
    }

    // Método para ver el elemento del frente sin eliminarlo (peek).
    public int peek() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía, no se puede realizar 'peek'.");
        }
        return frente.dato;
    }

    // Método para verificar si la cola está vacía.
    // Es una simple verificación del puntero del frente o del final.
    public boolean estaVacia() {
        return frente == null && final_de_cola == null;
    }
    
    // Método para invertir la cola usando recursividad.
    // Llama al método 'dequeue' recursivamente para sacar todos los elementos,
    // y luego los vuelve a añadir al final con 'enqueue' a medida que
    // las llamadas recursivas se resuelven (del último al primero).
    public void invertir() {
        // Caso base para la recursividad: si la cola está vacía, no hace nada.
        if (estaVacia()) {
            return;
        }

        // Saca el primer elemento.
        int dato = dequeue();
        // Llama a 'invertir' de nuevo con la cola más corta.
        invertir();
        // Cuando las llamadas recursivas terminan, vuelve a agregar el dato.
        // Esto hace que el último elemento que salió sea el primero en volver a entrar,
        // invirtiendo el orden.
        enqueue(dato);
    }

    // Método para imprimir todos los elementos de la cola.
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("La cola está vacía.");
            return;
        }

        // 'actual' es un puntero temporal que usamos para recorrer la cola.
        Nodo actual = frente;
        System.out.print("Cola: ");
        // Recorre la cola mientras 'actual' no sea 'null'.
        while (actual != null) {
            System.out.print(actual.dato + " ");
            // Mueve el puntero al siguiente nodo.
            actual = actual.siguiente;
        }
        System.out.println();
    }

    public static cola unirYOrdenarDosColas(cola c1, cola c2) {
        // 1. Crear una nueva cola que servirá como resultado final.
        cola cola_final = new cola();

        // 2. Transfiere todos los elementos de la primera cola (c1) a la cola final.
        while (!c1.estaVacia()) {
            cola_final.enqueue(c1.dequeue());
        }

        // 3. Transfiere todos los elementos de la segunda cola (c2) a la cola final.
        while (!c2.estaVacia()) {
            cola_final.enqueue(c2.dequeue());
        }

        return bubbleSortOrdenar(cola_final);
    }

    public static cola bubbleSortOrdenar(cola c) {
        // 1. Si la cola está vacía, no hay nada que ordenar, así que se devuelve directamente.
        if (c.estaVacia()) {
            return c;
        }

        // 2. Implementación del algoritmo de ordenamiento de burbuja (Bubble Sort).
        // NOTA: Este es un enfoque muy ineficiente. Su complejidad de tiempo es O(n^2),
        // lo que significa que su rendimiento se degrada rápidamente con colas grandes.
        // Un método más eficiente sería usar un enfoque diferente, como el merge sort.

        // 'intercambio' es una señal que se usa para saber si se ha realizado
        // algún cambio en una pasada iteracion. Si no hay cambios, la cola ya está ordenada.
        boolean intercambio;
        do {
            intercambio = false;
            // 'actual' es un puntero que recorre la lista.
            Nodo actual = c.frente;
            // Bucle interno para una pasada iteracion de ordenamiento.
            while (actual != null && actual.siguiente != null) {
                // Compara el dato del nodo actual con el dato del siguiente nodo.
                // Si el actual es mayor que el siguiente, están en el orden incorrecto
                // (para un orden ascendente), por lo que se deben intercambiar.
                if (actual.dato < actual.siguiente.dato) {
                    // Realiza el intercambio de datos entre los dos nodos.
                    // Es importante notar que solo se intercambian los valores, no los nodos.
                    int temp = actual.dato;
                    actual.dato = actual.siguiente.dato;
                    actual.siguiente.dato = temp;
                    // Si hubo un intercambio, la señal se pone en 'true'.
                    intercambio = true;
                }
                // Mueve el puntero al siguiente nodo para la siguiente comparación.
                actual = actual.siguiente;
            }
        } while (intercambio); // El bucle se repite mientras haya habido un intercambio.

        // 3. Devuelve la cola que ahora está ordenada ascendentemente.
        return c;
    }

    // Método principal para probar la funcionalidad de la clase 'cola'.
    public static void main(String[] args) {
        // 1. Crea una nueva instancia de la clase 'cola'.
        cola cola = new cola();

        // 2. Añade varios elementos a la cola (enqueue).
        cola.enqueue(10);
        cola.enqueue(20);
        cola.enqueue(30);

        // 3. Muestra la cola actual.
        System.out.println("Cola inicial:");
        cola.mostrar(); // Salida: Cola: 10 20 30

        // 4. Saca dos elementos de la cola (dequeue).
        cola.dequeue();
        cola.dequeue();

        // 5. Muestra la cola después de las eliminaciones.
        System.out.println("Después de dos 'dequeue':");
        cola.mostrar(); // Salida: Cola: 30

        // 6. Añade más elementos.
        cola.enqueue(40);
        cola.enqueue(50);

        // 7. Muestra la cola actualizada.
        cola.mostrar(); // Salida: Cola: 30 40 50

        // 8. Invierte la cola.
        cola.invertir();

        // 9. Muestra la cola después de la inversión.
        cola.mostrar(); // Salida: Cola: 50 40 30



        // 10. Prueba el método unirYOrdenarDosColas
        System.out.println("Probando unirYOrdenarDosColas:");
        cola c1 = new cola();
        c1.enqueue(5);
        c1.enqueue(15);
        c1.enqueue(25);
        System.out.println("Cola 1:");
        c1.mostrar();

        cola c2 = new cola();
        c2.enqueue(10);
        c2.enqueue(20);
        c2.enqueue(30);
        System.out.println("Cola 2:");
        c2.mostrar();

        cola cola_unida = unirYOrdenarDosColas(c1, c2);
        cola_unida.mostrar();
    }
}