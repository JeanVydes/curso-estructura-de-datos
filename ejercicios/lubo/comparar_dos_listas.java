package ejercicios.lubo;

/**
 * 🔍 EJERCICIO: Comparar dos Listas Enlazadas
 * 
 * Objetivo: Aprender a comparar dos listas enlazadas para determinar si son
 * iguales.
 * 
 * Una lista es igual a otra si:
 * 1. Tienen la misma cantidad de nodos.
 * 2. Los datos en cada posición correspondiente son idénticos.
 * 3. El orden de los elementos es el mismo.
 */
public class comparar_dos_listas {

    /**
     * PASO 1: Definir la estructura del Nodo
     * Cada nodo contiene:
     * - Un dato (el valor que guardamos)
     * - Un puntero 'siguiente' (referencia al próximo nodo)
     */
    private static class Nodo {
        int dato;
        Nodo siguiente;

        // Constructor: inicializa el nodo con un dato y siguiente = null
        public Nodo(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    /**
     * PASO 2: Definir la estructura de la Lista Enlazada
     * La lista tiene un único punto de acceso: la cabeza (el primer nodo)
     */
    private static class ListaEnlazada {
        Nodo cabeza;

        // Constructor: la lista comienza vacía (cabeza = null)
        public ListaEnlazada() {
            this.cabeza = null;
        }

        /**
         * MÉTODO: Insertar un dato al final de la lista
         * Pasos:
         * 1. Crear un nuevo nodo con el dato
         * 2. Si la lista está vacía, este es el primer nodo
         * 3. Si no está vacía, buscar el último nodo y enlazarlo
         */
        public void insertar(int dato) {
            Nodo nuevoNodo = new Nodo(dato);

            // Caso 1: La lista está vacía
            if (cabeza == null) {
                cabeza = nuevoNodo;
                return;
            }

            // Caso 2: La lista tiene nodos, buscar el último
            Nodo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }

            // Enlazar el nuevo nodo al final
            actual.siguiente = nuevoNodo;
        }

        /**
         * MÉTODO: Mostrar la lista
         * Recorre todos los nodos desde cabeza hasta null
         */
        public void mostrar() {
            Nodo actual = cabeza;
            System.out.print("Lista: ");
            while (actual != null) {
                System.out.print(actual.dato + " -> ");
                actual = actual.siguiente;
            }
            System.out.println("NULL");
        }
    }

    /**
     * PASO 3: FUNCIÓN PRINCIPAL DE COMPARACIÓN
     * 
     * Esta función compara dos listas enlazadas y devuelve TRUE si son iguales.
     * 
     * Algoritmo paso a paso:
     * 1. Empezar en la cabeza de ambas listas simultáneamente
     * 2. Recorrer ambas hasta que una llegue a null
     * 3. En cada paso, verificar que:
     * a) Ambos datos sean iguales
     * b) Si una llegó a null, la otra también debe llegar al mismo tiempo
     * 4. Si todo coincide, las listas son iguales
     * 
     * @param lista1 Primera lista a comparar
     * @param lista2 Segunda lista a comparar
     * @return true si las listas son iguales, false en caso contrario
     */
    public static boolean sonIguales(ListaEnlazada lista1, ListaEnlazada lista2) {
        // Paso 1: Obtener las cabezas de ambas listas
        Nodo nodo1 = lista1.cabeza;
        Nodo nodo2 = lista2.cabeza;

        // Paso 2: Recorrer ambas listas simultáneamente
        while (nodo1 != null && nodo2 != null) {
            // Verificación A: ¿Los datos son iguales?
            if (nodo1.dato != nodo2.dato) {
                System.out.println("  ❌ Datos diferentes encontrados: " + nodo1.dato + " ≠ " + nodo2.dato);
                return false; // No son iguales
            }

            // Los datos coinciden, pasar al siguiente nodo en ambas listas
            nodo1 = nodo1.siguiente;
            nodo2 = nodo2.siguiente;
        }

        // Paso 3: Verificación final
        // Si ambos nodos son null, las listas tienen el mismo tamaño y son iguales
        if (nodo1 == null && nodo2 == null) {
            System.out.println("  ✅ Las listas son idénticas en contenido y tamaño.");
            return true;
        }

        // Si uno es null pero el otro no, las listas tienen diferente tamaño
        System.out.println("  ❌ Las listas tienen diferente tamaño.");
        return false;
    }

    /**
     * PASO 4: MÉTODO MAIN - Ejemplos de uso
     * Aquí probamos diferentes casos de comparación
     */
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO: Comparar Dos Listas Enlazadas ===\n");

        // ========== EJEMPLO 1: Listas Iguales ==========
        System.out.println("📍 EJEMPLO 1: Dos listas con los mismos datos y tamaño");
        System.out.println("─".repeat(50));

        ListaEnlazada lista1 = new ListaEnlazada();
        ListaEnlazada lista2 = new ListaEnlazada();

        // Llenamos ambas listas con los mismos datos
        System.out.println("Insertando datos en Lista 1:");
        lista1.insertar(10);
        lista1.insertar(20);
        lista1.insertar(30);
        lista1.mostrar();

        System.out.println("Insertando datos en Lista 2:");
        lista2.insertar(10);
        lista2.insertar(20);
        lista2.insertar(30);
        lista2.mostrar();

        // Comparamos
        System.out.println("\n¿Son iguales?");
        boolean resultado1 = sonIguales(lista1, lista2);
        System.out.println("Resultado: " + resultado1 + "\n");

        // ========== EJEMPLO 2: Listas con diferentes datos ==========
        System.out.println("\n📍 EJEMPLO 2: Dos listas con el mismo tamaño pero datos diferentes");
        System.out.println("─".repeat(50));

        ListaEnlazada lista3 = new ListaEnlazada();
        ListaEnlazada lista4 = new ListaEnlazada();

        System.out.println("Insertando datos en Lista 3:");
        lista3.insertar(10);
        lista3.insertar(20);
        lista3.insertar(30);
        lista3.mostrar();

        System.out.println("Insertando datos en Lista 4:");
        lista4.insertar(10);
        lista4.insertar(25); // ← Diferente aquí
        lista4.insertar(30);
        lista4.mostrar();

        System.out.println("\n¿Son iguales?");
        boolean resultado2 = sonIguales(lista3, lista4);
        System.out.println("Resultado: " + resultado2 + "\n");

        // ========== EJEMPLO 3: Listas con diferente tamaño ==========
        System.out.println("\n📍 EJEMPLO 3: Dos listas con diferente cantidad de nodos");
        System.out.println("─".repeat(50));

        ListaEnlazada lista5 = new ListaEnlazada();
        ListaEnlazada lista6 = new ListaEnlazada();

        System.out.println("Insertando datos en Lista 5:");
        lista5.insertar(10);
        lista5.insertar(20);
        lista5.insertar(30);
        lista5.mostrar();

        System.out.println("Insertando datos en Lista 6:");
        lista6.insertar(10);
        lista6.insertar(20);
        lista6.mostrar(); // ← Una menos

        System.out.println("\n¿Son iguales?");
        boolean resultado3 = sonIguales(lista5, lista6);
        System.out.println("Resultado: " + resultado3 + "\n");

        // ========== EJEMPLO 4: Listas vacías ==========
        System.out.println("\n📍 EJEMPLO 4: Dos listas vacías");
        System.out.println("─".repeat(50));

        ListaEnlazada lista7 = new ListaEnlazada();
        ListaEnlazada lista8 = new ListaEnlazada();

        System.out.println("Lista 7:");
        lista7.mostrar();

        System.out.println("Lista 8:");
        lista8.mostrar();

        System.out.println("\n¿Son iguales?");
        boolean resultado4 = sonIguales(lista7, lista8);
        System.out.println("Resultado: " + resultado4 + "\n");
    }
}
