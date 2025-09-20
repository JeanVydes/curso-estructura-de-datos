package capacho20sep;

public class ListaCircularDoble {

    /**
     * El nodo 'cabeza' es nuestro ÚNICO punto de acceso a la lista.
     * Es como la puerta de entrada a un carrusel. Si perdemos la referencia
     * a la cabeza, perdemos la lista entera. Si la cabeza es 'null', la
     * lista está vacía.
     */
    Nodo cabeza = null;

    /**
     * Agrega un nuevo nodo con un código al final de la lista.
     * 
     * @param codigo El valor entero que contendrá el nuevo nodo.
     */
    public void agregar(int codigo) {
        Nodo nuevo = new Nodo(codigo);

        if (cabeza == null) {
            cabeza = nuevo;
            // Al ser circular y tener un solo nodo, este se apunta a sí mismo.
            // (anterior) <== [cabeza] ==> (siguiente)
            // ^-----------|-----------^
            cabeza.siguiente = cabeza;
            cabeza.anterior = cabeza;
        } else {
            // Si la lista NO está vacía, lo insertamos justo antes de la cabeza.
            Nodo ultimo = cabeza.anterior;

            // DIAGRAMA DE LA INSERCIÓN:
            //
            // ANTES: La lista es un círculo cerrado.
            // ... <=> (ultimo) <=> (cabeza) <=> ...
            //
            // Y tenemos nuestro nodo [nuevo] por fuera.
            // (nuevo)
            //
            // PASOS PARA INSERTARLO:
            // 1. El 'siguiente' del ultimo ahora será el 'nuevo'.
            ultimo.siguiente = nuevo;
            // 2. El 'anterior' del nuevo será el 'ultimo'.
            nuevo.anterior = ultimo;
            // 3. El 'siguiente' del nuevo será la 'cabeza' (para mantener el círculo).
            nuevo.siguiente = cabeza;
            // 4. El 'anterior' de la cabeza ahora será el 'nuevo'.
            cabeza.anterior = nuevo;
            //
            // DESPUÉS: El nuevo nodo está perfectamente integrado.
            // ... <=> (ultimo) <=> (nuevo) <=> (cabeza) <=> ...
        }
    }

    /**
     * Muestra todos los nodos de la lista en la consola.
     */
    public void mostrar() {
        if (cabeza == null) {
            System.out.println("La lista está vacía.");
            return;
        }
        Nodo actual = cabeza;
        // 🧠 ¿POR QUÉ UN BUCLE do-while?
        // Es perfecto para listas circulares. Un 'while' normal (while actual !=
        // cabeza)
        // no se ejecutaría si solo hay un nodo. El 'do-while' nos asegura que el
        // bloque de código se ejecuta AL MENOS UNA VEZ (para procesar la cabeza)
        // y luego comprueba si ya hemos vuelto al inicio.
        do {
            System.out.println("Nodo{codigo=" + actual.codigo + "}");
            actual = actual.siguiente;
        } while (actual != cabeza);
    }

    /*
     * PSEUDOCÓDIGO
     * 
     * Subrutina BUSCAR(PTR, Codigo, Lista) → Nodo
     * // Busca un nodo en la lista y devuelve su referencia
     * 
     * // Si la lista está vacía (el puntero inicial es nulo), no hay nada que
     * buscar.
     * Si (PTR = NULO ) entonces
     * Retorne NULO
     * Fin_Si
     * 
     * // Empezamos la búsqueda desde el primer nodo.
     * Actual ← PTR
     * 
     * // Usamos un bucle que se romperá manualmente.
     * Mientras (VERDADERO) haga
     * // Si encontramos el código en el nodo actual, lo retornamos.
     * Si (Lista[Actual].Codigo = Codigo ) entonces
     * Retorne Actual
     * Fin_Si
     * 
     * // Avanzamos al siguiente nodo.
     * Actual ← RLINK[Actual]
     * 
     * // Si hemos vuelto al inicio, significa que recorrimos toda la lista y no
     * encontramos el nodo.
     * Si (Actual = PTR) entonces
     * Romper
     * Fin_Si
     * Fin_Mientras
     * 
     * // Si el bucle se rompió, el código no fue encontrado.
     * Retorne NULO
     * Fin_Subrutina
     */

    /**
     * Busca un nodo por su código y lo devuelve.
     * 
     * @param codigo El código del nodo a buscar.
     * @return El Nodo si se encuentra, o null si no existe.
     */
    public Nodo buscar(int codigo) {
        if (cabeza == null) {
            return null;
        }
        Nodo actual = cabeza;
        do {
            if (actual.codigo == codigo) {
                return actual;
            }
            actual = actual.siguiente;
        } while (actual != cabeza);
        return null;
    }

    /*
     * PSEUDOCÓDIGO
     *
     * Subrutina ELIMINAR(PTR, Codigo, Lista)
     * // Elimina un nodo de la lista
     *
     * // Si la lista está vacía, no hay nada que hacer.
     * Si (PTR = NULO ) entonces
     * Retornar
     * Fin_Si
     *
     * // Busca el nodo que se va a eliminar.
     * Nodo ← BUSCAR(PTR, Codigo, Lista)
     *
     * // Si el nodo no se encuentra, no hay nada que hacer.
     * Si (Nodo = NULO) entonces // Se asume que 0 es NULO
     * Retornar
     * Fin_Si
     *
     * // El 'siguiente' del nodo ANTERIOR ahora apunta al 'siguiente' del nodo
     * actual.
     * RLINK[LLINK[Nodo]] ← RLINK[Nodo]
     *
     * // El 'anterior' del nodo SIGUIENTE ahora apunta al 'anterior' del nodo
     * actual.
     * LLINK[RLINK[Nodo]] ← LLINK[Nodo]
     *
     * // Si el nodo a eliminar era la cabeza, se reasigna la cabeza al siguiente.
     * Si (Nodo = PTR) entonces
     * PTR ← RLINK[Nodo]
     * Fin_Si
     * Fin_Subrutina
     *
     * NOTA: Este pseudocódigo no contempla explícitamente el caso de eliminar el
     * último nodo de la lista, pero la implementación en Java sí lo hace
     * correctamente.
     */

    /**
     * Busca un nodo por su código y lo elimina de la lista.
     * 
     * @param codigo El código del nodo a eliminar.
     * @return El nodo que fue eliminado, o null si no se encontró.
     */
    public Nodo eliminar(int codigo) {
        if (cabeza == null) {
            return null;
        }
        Nodo nodoAEliminar = buscar(codigo);
        if (nodoAEliminar == null) {
            return null;
        }

        // CASO 1: Es el único nodo en la lista.
        if (nodoAEliminar == cabeza && nodoAEliminar.siguiente == cabeza) {
            cabeza = null; // La lista queda vacía.
        } else {
            // CASO 2: Hay más de un nodo. Aquí re-enlazamos los punteros.
            //
            // IMAGINEMOS QUE QUEREMOS ELIMINAR [B] DE LA LISTA: ... <=> [A] <=> [B] <=> [C]
            // <=> ...
            //
            // Necesitamos que [A] y [C] se conecten directamente, ignorando a [B].

            // PASO 1: Conectar el 'siguiente' de [A] con [C].
            // El nodo anterior a [B] es [A]. Su 'siguiente' debe ser ahora [C].
            // [C] es el 'siguiente' del nodo a eliminar.
            //
            // Visualmente:
            // [A].siguiente ---> [C]
            //
            // ... <=> [A] [B] <=> [C] <=> ...
            // \___________^
            //
            nodoAEliminar.anterior.siguiente = nodoAEliminar.siguiente;

            // PASO 2: Conectar el 'anterior' de [C] con [A].
            // El nodo siguiente a [B] es [C]. Su 'anterior' debe ser ahora [A].
            // [A] es el 'anterior' del nodo a eliminar.
            //
            // Visualmente:
            // [A] <--- [C].anterior
            //
            // ... <=> [A] [B] [C] <=> ...
            // ^___________/
            //
            nodoAEliminar.siguiente.anterior = nodoAEliminar.anterior;

            // ESTADO FINAL: El nodo [B] ha sido "puenteado" y ya no forma parte de la
            // lista.
            // La lista ahora se ve así: ... <=> [A] <=> [C] <=> ...

            // CASO ESPECIAL: Si eliminamos la cabeza, debemos actualizarla.
            if (nodoAEliminar == cabeza) {
                cabeza = nodoAEliminar.siguiente; // La nueva cabeza es el siguiente nodo.
            }
        }
        return nodoAEliminar;
    }
}