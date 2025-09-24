package capacho.sistemas_sid;

/**
 * La clase Club gestiona su propia lista de jugadores. ⚽
 * En este diseño, la clase Club actúa COMO una Lista Doblemente Enlazada Circular,
 * manejando directamente los nodos de jugadores.
 */
public class Club {
    /**
     * El 'cabeza' es el punto de entrada a la lista de jugadores del club.
     * Si cabeza es null, el club no tiene jugadores inscritos.
     */
    NodoJugador cabeza = null;

    /**
     * Inscribe un nuevo jugador en el club.
     * @param codigo El código único del jugador.
     * @param nombre El nombre del jugador.
     */
    public void agregar(int codigo, String nombre) {
        // Creamos el nuevo jugador que vamos a inscribir.
        NodoJugador nuevo = new NodoJugador(codigo, nombre);
        
        // Verificamos si es el primer jugador del club.
        if (cabeza == null) {
            cabeza = nuevo;
            // Al ser el único jugador, sus punteros 'siguiente' y 'anterior'
            // se apuntan a sí mismo para formar el círculo.
            // (anterior) <== [cabeza] ==> (siguiente)
            //      ^-----------|-----------^
            cabeza.siguiente = cabeza;
            cabeza.anterior = cabeza;
        } else {
            // Si ya hay jugadores, lo agregamos al final del círculo.
            // El "último" jugador es el que está justo antes de la cabeza.
            NodoJugador ultimo = cabeza.anterior;

            // DIAGRAMA DE INSERCIÓN:
            //
            // ANTES: ... <=> (ultimo) <=> (cabeza) <=> ...
            // Y por fuera tenemos a: (nuevo)
            //
            // PASOS PARA INTEGRARLO:
            // 1. El 'siguiente' del ultimo ahora será el 'nuevo'.
            ultimo.siguiente = nuevo;
            // 2. El 'anterior' del nuevo será el 'ultimo'.
            nuevo.anterior = ultimo;
            // 3. El 'siguiente' del nuevo será la 'cabeza'.
            nuevo.siguiente = cabeza;
            // 4. El 'anterior' de la cabeza ahora será el 'nuevo'.
            cabeza.anterior = nuevo;
            //
            // DESPUÉS: El círculo ahora incluye al nuevo jugador.
            // ... <=> (ultimo) <=> (nuevo) <=> (cabeza) <=> ...
        }
    }

    /**
     * Busca un jugador por su código.
     * @param codigo El código del jugador a buscar.
     * @return El NodoJugador si se encuentra, o null si no.
     */
    public NodoJugador buscar(int codigo) {
        if (cabeza == null) {
            return null; // No hay jugadores en el club.
        }

        NodoJugador actual = cabeza;
        // 🧠 Usamos un bucle do-while porque es la forma más eficiente
        // de recorrer una lista circular, asegurando que evaluamos
        // el primer nodo (cabeza) antes de comprobar si hemos completado el círculo.
        do {
            if (actual.codigo == codigo) {
                return actual; // ¡Encontrado!
            }
            actual = actual.siguiente;
        } while (actual != cabeza);

        return null; // Se recorrió toda la lista y no se encontró.
    }

    /**
     * Cuenta cuántos jugadores hay inscritos en el club.
     * @return El número total de jugadores.
     */
    public int contar() {
        if (cabeza == null) {
            return 0; // Si no hay cabeza, no hay jugadores.
        }

        int contador = 0;
        NodoJugador actual = cabeza;
        do {
            contador++; // Incrementamos por cada jugador en la lista.
            actual = actual.siguiente;
        } while (actual != cabeza);

        return contador;
    }

    /**
     * Da de baja a un jugador del club usando su código.
     * @param codigo El código del jugador a eliminar.
     * @return El jugador eliminado, o null si no se encontró.
     */
    public NodoJugador eliminar(int codigo) {
        if (cabeza == null) {
            return null;
        }

        // Reutilizamos nuestro método 'buscar' para encontrar al jugador.
        NodoJugador nodo = buscar(codigo);

        if (nodo == null) {
            return null; // El jugador no está en el club.
        }

        // CASO 1: Es el único jugador del club.
        if (nodo == cabeza && nodo.siguiente == cabeza) {
            cabeza = null; // El club se queda sin jugadores.
        } else {
            // CASO 2: Hay más jugadores. Debemos re-enlazar los punteros.
            //
            // IMAGINEMOS QUE QUEREMOS ELIMINAR A [B] DE LA LISTA: ... <=> [A] <=> [B] <=> [C] <=> ...
            //
            // El objetivo es que [A] y [C] se conecten directamente, ignorando a [B].

            // PASO 1: El 'siguiente' de [A] ahora debe apuntar a [C].
            // [A] es el nodo 'anterior' al que eliminamos.
            // [C] es el nodo 'siguiente' al que eliminamos.
            //
            //      ... <=> [A]     [B] <=> [C] <=> ...
            //                 \___________^
            //
            nodo.anterior.siguiente = nodo.siguiente;

            // PASO 2: El 'anterior' de [C] ahora debe apuntar a [A].
            //
            //      ... <=> [A]     [B]     [C] <=> ...
            //            ^___________/
            //
            nodo.siguiente.anterior = nodo.anterior;

            // ¡LISTO! El jugador [B] ha quedado fuera del círculo.

            // CASO ESPECIAL: Si el jugador eliminado era el capitán (la cabeza).
            if (nodo == cabeza) {
                // El nuevo capitán es el siguiente jugador en la lista.
                cabeza = nodo.siguiente;
            }
        }

        return nodo; // Devolvemos el jugador que fue dado de baja.
    }

    /**
     * Muestra la lista completa de jugadores del club.
     */
    public void mostrar() {
        if (cabeza == null) {
            System.out.println("El club no tiene jugadores inscritos.");
            return;
        }

        NodoJugador actual = cabeza;

        System.out.println("--- Lista de Jugadores ---");
        do {
            System.out.println("Jugador{codigo=" + actual.codigo + ", nombre=" + actual.nombre + "}");
            actual = actual.siguiente;
        } while (actual != cabeza);
        System.out.println("--------------------------");
    }
}