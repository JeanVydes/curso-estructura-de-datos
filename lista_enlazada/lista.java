package lista_enlazada;

/**
 * 🚂 Implementación de una Lista Enlazada Simple.
 * Esta es la estructura de datos enlazada más fundamental. Podemos imaginarla
 * como
 * un tren: cada "Nodo" es un vagón que solo sabe cuál es el vagón que le sigue.
 * No podemos ir hacia atrás, solo hacia adelante.
 */
public class lista {

    /**
     * Clase interna que representa cada "vagón" de nuestro tren.
     * Es el bloque de construcción de la lista enlazada.
     */
    private static class Nodo {
        // El dato que queremos guardar (la "carga" del vagón).
        int dato;
        // El "enganche" que apunta al siguiente vagón en la fila.
        Nodo siguiente;

        // Constructor del nodo.
        public Nodo(int dato) {
            this.dato = dato;
            // Cuando creamos un vagón, inicialmente no está enganchado a nada.
            this.siguiente = null;
        }
    }

    // 'cabeza' es la "locomotora" de nuestro tren. Es el único punto de acceso
    // y el inicio de la lista. Si la cabeza es null, el tren no tiene vagones.
    Nodo cabeza = null;

    /**
     * Engancha un nuevo vagón justo al frente del tren (se convierte en la nueva
     * locomotora).
     * 
     * @param nuevoDato El dato del nodo a insertar.
     */
    public void insertarAlInicio(int nuevoDato) {
        // 1. Creamos el nuevo nodo (el nuevo vagón).
        Nodo nuevoNodo = new Nodo(nuevoDato);

        // 2. El 'siguiente' del nuevo nodo ahora apunta a la que era la cabeza antigua.
        // Es como enganchar la nueva locomotora al resto del tren.
        //
        // [nuevoNodo] ---> [cabeza_antigua] ---> ...
        nuevoNodo.siguiente = cabeza;

        // 3. Ahora, declaramos que la nueva locomotora es nuestro nuevo nodo.
        cabeza = nuevoNodo;
    }

    /**
     * Agrega un nuevo vagón al final del tren.
     * 
     * @param nuevoDato El dato del nodo a insertar.
     */
    public void insertarAlFinal(int nuevoDato) {
        Nodo nuevoNodo = new Nodo(nuevoDato);

        // Si el tren no tiene vagones, el nuevo nodo se convierte en la locomotora.
        if (cabeza == null) {
            cabeza = nuevoNodo;
            return;
        }

        // 🧠 Para llegar al final, debemos "caminar" por todo el tren.
        // Empezamos en la locomotora y avanzamos de vagón en vagón
        // hasta encontrar el último (aquel cuyo 'siguiente' es null).
        Nodo ultimo = cabeza;
        while (ultimo.siguiente != null) {
            ultimo = ultimo.siguiente;
        }

        // Una vez que estamos en el último vagón, lo enganchamos al nuevo.
        // ANTES: ... ---> [ultimo] ---> NULL
        //
        // DESPUÉS: ... ---> [ultimo] ---> [nuevoNodo] ---> NULL
        ultimo.siguiente = nuevoNodo;
    }

    /**
     * Inserta un nodo después del primer nodo que sea mayor o igual que una
     * condición.
     * 
     * @param nuevoDato El dato a insertar.
     * @param condicion El valor de referencia para la búsqueda.
     */
    public void insertarDespuesDeCualquierNodoQueSeaMayorQue(int nuevoDato, int condicion) {
        if (cabeza == null) {
            System.out.println("No se puede insertar, la lista está vacía.");
            return;
        }

        // 1. Recorremos la lista buscando el primer nodo que cumpla la condición.
        Nodo nodoActual = cabeza;
        while (nodoActual != null) {
            if (nodoActual.dato >= condicion) {
                // 2. ¡Lo encontramos! Ahora insertamos el nuevo nodo justo después.
                System.out.println("\n(Encontrado nodo [" + nodoActual.dato + "] que cumple la condición >= "
                        + condicion + ". Insertando [" + nuevoDato + "]...)");
                Nodo nuevoNodo = new Nodo(nuevoDato);

                // DIAGRAMA DE INSERCIÓN:
                // ANTES: ... -> [nodoActual] -> [siguienteDelActual] -> ...
                //
                // PASO A: El 'siguiente' del nuevo nodo apunta al que seguía al nodo actual.
                nuevoNodo.siguiente = nodoActual.siguiente;
                // PASO B: El 'siguiente' del nodo actual ahora apunta al nuevo nodo.
                nodoActual.siguiente = nuevoNodo;
                //
                // DESPUÉS: ... -> [nodoActual] -> [nuevoNodo] -> [siguienteDelActual] -> ...

                return; // Terminamos la función para no seguir buscando.
            }
            nodoActual = nodoActual.siguiente; // Si no cumple, pasamos al siguiente.
        }

        System.out.println(
                "\n(No se encontró ningún nodo que cumpla la condición >= " + condicion + ". No se insertó nada.)");
    }

    /**
     * Inserta un nodo justo después del tercer nodo de la lista.
     * 
     * @param nuevoDato El dato a insertar.
     */
    public void insertarDespuesDeLosTresPrimerosNodos(int nuevoDato) {
        // 🛡️ Medida de seguridad: Verificamos que existan al menos 3 nodos.
        // Si no hacemos esto, intentar acceder a 'cabeza.siguiente.siguiente'
        // en una lista corta causaría un error (NullPointerException).
        if (cabeza == null || cabeza.siguiente == null || cabeza.siguiente.siguiente == null) {
            System.out.println("\nError: La lista no tiene al menos tres nodos. No se puede insertar.");
            return;
        }

        // 1. Accedemos directamente al tercer nodo.
        // [cabeza] -> [cabeza.siguiente] -> [cabeza.siguiente.siguiente]
        // (1er nodo) (2do nodo) (3er nodo)
        Nodo tercerNodo = cabeza.siguiente.siguiente;

        // 2. Usamos la misma lógica de inserción de antes.
        System.out.println(
                "\n(Insertando [" + nuevoDato + "] después del tercer nodo, que es [" + tercerNodo.dato + "]...)");
        Nodo nuevoNodo = new Nodo(nuevoDato);
        nuevoNodo.siguiente = tercerNodo.siguiente;
        tercerNodo.siguiente = nuevoNodo;
    }

    /**
     * Elimina el primer nodo de la lista (la cabeza).
     * 
     * Pasos:
     * 1. Si la lista está vacía, no hacemos nada.
     * 2. Si hay nodos, movemos la cabeza al siguiente nodo.
     * 3. El primer nodo se desglosa automáticamente (garbage collection).
     */
    public void eliminarPrimero() {
        if (cabeza == null) {
            System.out.println("Error: La lista está vacía. No hay nada que eliminar.");
            return;
        }

        // ANTES: [cabeza] -> [nodo2] -> [nodo3] -> ...
        //
        // Movemos la cabeza al siguiente nodo:
        System.out.println("Eliminando primer nodo: " + cabeza.dato);
        cabeza = cabeza.siguiente;
        //
        // DESPUÉS: [nodo2] -> [nodo3] -> ...
        // (El anterior cabeza se descarta automáticamente)
    }

    /**
     * Elimina el último nodo de la lista.
     * 
     * Pasos:
     * 1. Si la lista está vacía, no hacemos nada.
     * 2. Si solo hay un nodo, lo eliminamos (cabeza = null).
     * 3. Si hay varios, buscamos el penúltimo y cortamos su enlace.
     */
    public void eliminarUltimo() {
        if (cabeza == null) {
            System.out.println("Error: La lista está vacía. No hay nada que eliminar.");
            return;
        }

        // Caso especial: solo hay un nodo
        if (cabeza.siguiente == null) {
            System.out.println("Eliminando último nodo (único): " + cabeza.dato);
            cabeza = null;
            return;
        }

        // Caso general: hay varios nodos
        // ANTES: [nodo1] -> [nodo2] -> ... -> [penúltimo] -> [último] -> NULL
        //
        // Buscamos el penúltimo nodo
        Nodo penultimo = cabeza;
        while (penultimo.siguiente.siguiente != null) {
            penultimo = penultimo.siguiente;
        }

        // Mostramos qué eliminamos
        System.out.println("Eliminando último nodo: " + penultimo.siguiente.dato);

        // Cortamos el enlace: penúltimo apunta a null
        penultimo.siguiente = null;
        //
        // DESPUÉS: [nodo1] -> [nodo2] -> ... -> [penúltimo] -> NULL
    }

    /**
     * Elimina el nodo en la posición n (1-indexed).
     * 
     * Ejemplo: Si posicion=1, elimina el primer nodo.
     * Si posicion=2, elimina el segundo nodo.
     * 
     * Pasos:
     * 1. Validar que la posición sea válida.
     * 2. Si es posición 1, simplemente eliminamos la cabeza.
     * 3. Si no, buscamos el nodo anterior y reenlazamos.
     * 
     * @param posicion La posición del nodo a eliminar (1-indexed).
     */
    public void eliminarPosicion(int posicion) {
        if (cabeza == null) {
            System.out.println("Error: La lista está vacía. No hay nada que eliminar.");
            return;
        }

        // Validar que la posición sea positiva
        if (posicion < 1) {
            System.out.println("Error: La posición debe ser >= 1.");
            return;
        }

        // Caso especial: eliminar el primer nodo
        if (posicion == 1) {
            System.out.println("Eliminando nodo en posición 1: " + cabeza.dato);
            cabeza = cabeza.siguiente;
            return;
        }

        // Caso general: buscar el nodo anterior al que queremos eliminar
        Nodo actual = cabeza;
        int contador = 1;

        // Recorremos hasta encontrar el nodo anterior al que queremos eliminar
        while (actual.siguiente != null && contador < posicion - 1) {
            actual = actual.siguiente;
            contador++;
        }

        // Validar que la posición existe
        if (actual.siguiente == null) {
            System.out.println("Error: La posición " + posicion + " está fuera de rango.");
            return;
        }

        // ANTES: ... -> [actual] -> [nodoAEliminar] -> [siguiente] -> ...
        //
        Nodo nodoAEliminar = actual.siguiente;
        System.out.println("Eliminando nodo en posición " + posicion + ": " + nodoAEliminar.dato);

        // Re-enlazamos saltándonos el nodo a eliminar
        actual.siguiente = nodoAEliminar.siguiente;
        //
        // DESPUÉS: ... -> [actual] -> [siguiente] -> ...
        // (nodoAEliminar se descarta automáticamente)
    }

    /**
     * Muestra la lista completa desde el inicio hasta el fin.
     */
    public void mostrarLista() {
        // Empezamos en la locomotora.
        Nodo nodo = cabeza;
        // Mientras no nos hayamos "caído" del tren (llegado a null)...
        while (nodo != null) {
            // ...imprimimos la carga del vagón actual...
            System.out.print(nodo.dato + " -> ");
            // ...y nos movemos al siguiente.
            nodo = nodo.siguiente;
        }
        System.out.println("NULL");
    }

    public static void main(String[] args) {
        lista lista = new lista();

        // Creamos la lista inicial: 10 -> 20 -> 30
        lista.insertarAlInicio(30);
        lista.insertarAlInicio(20);
        lista.insertarAlInicio(10);

        System.out.println("La lista inicial es: ");
        lista.mostrarLista();

        // Agregamos al final para tener: 10 -> 20 -> 30 -> 40 -> 50
        lista.insertarAlFinal(40);
        lista.insertarAlFinal(50);

        System.out.println("\nLa lista después de insertar al final es:");
        lista.mostrarLista();

        System.out.println("\n=== PRUEBAS DE ELIMINACIÓN ===");

        // EJEMPLO 1: Eliminar el primer nodo
        System.out.println("\n1. Eliminando el primer nodo:");
        lista.eliminarPrimero();
        lista.mostrarLista();

        // EJEMPLO 2: Eliminar el último nodo
        System.out.println("\n2. Eliminando el último nodo:");
        lista.eliminarUltimo();
        lista.mostrarLista();

        // EJEMPLO 3: Eliminar en posición específica
        System.out.println("\n3. Eliminando el nodo en posición 2:");
        lista.eliminarPosicion(2);
        lista.mostrarLista();

        // EJEMPLO 4: Intentar eliminar en posición inválida
        System.out.println("\n4. Intentando eliminar en posición 20 (fuera de rango):");
        lista.eliminarPosicion(20);
        lista.mostrarLista();
    }
}