
/**
 * ============================================================================
 *   🌳 ÁRBOL GENERAL (N-ARIO) - IMPLEMENTACIÓN BÁSICA 🌳
 * ============================================================================
 *
 * ¿QUÉ ES ESTE ARCHIVO?
 * Este archivo implementa un árbol general. A diferencia del Árbol Binario (que
 * limita los hijos a 2), en un árbol general o N-Ario cada nodo puede tener de 
 * 0 a INFINITOS hijos. Es el modelo utilizado para representar sistemas de 
 * archivos (carpetas y subcarpetas) o jerarquías de empresas.
 *
 * ----------------------------------------------------------------------------
 * 🚀 COMPARATIVA: ÁRBOL GENERAL vs ÁRBOL BINARIO DE BÚSQUEDA (ABB)
 * ----------------------------------------------------------------------------
 * ABB: 
 *   - Límite de hijos: 2 (Izquierdo y Derecho).
 *   - Regla de oro: Izquierda es menor, Derecha es mayor. 
 *   - Propósito principal: Búsquedas híper-rápidas (como buscar en un diccionario).
 *
 * Árbol General:
 *   - Límite de hijos: No hay límite (N). Se usa una Lista o Arreglo.
 *   - Regla de oro: No hay regla estructural de orden (depende de la lógica).
 *   - Propósito principal: Representar jerarquías y ramificaciones naturales.
 *
 * ----------------------------------------------------------------------------
 * 🚀 EJEMPLO VISUAL:
 * ----------------------------------------------------------------------------
 * Usamos una Lista para guardar los "hijos".
 * 
 *           [Carpeta Raíz: /]
 *          /        |        \
 *     [etc]       [home]      [var]
 *                    | 
 *                 [Jean]
 *                /      \
 *           [docs]     [downloads]
 * 
 */

import java.util.ArrayList;
import java.util.List;

public class ArbolGeneral {

    // ESTRUCTURA DEL NODO: En vez de Nodo izquierdo/derecho, usamos una Lista de
    // Hijos.
    static class Nodo {
        String dato;
        List<Nodo> hijos;

        public Nodo(String dato) {
            this.dato = dato;
            // Inicializar la lista al crear el nodo es vital para no tener errores
            // NullPointer
            this.hijos = new ArrayList<>();
        }
    }

    Nodo raiz;

    // Constructor: Todo árbol empieza con una raíz.
    public ArbolGeneral(String raizDato) {
        this.raiz = new Nodo(raizDato);
    }

    /**
     * 1. BÚSQUEDA: Buscar un nodo específico por su dato.
     * Retorna el nodo si lo encuentra, o null si no existe.
     */
    public Nodo buscar(Nodo nodoActual, String datoABuscar) {
        if (nodoActual == null)
            return null;

        // ¿Es el nodo actual el que busco?
        if (nodoActual.dato.equals(datoABuscar)) {
            return nodoActual;
        }

        // Si no es el actual, buscamos recursivamente en todos sus hijos.
        // Como tenemos una lista, usamos un ciclo for.
        for (Nodo hijo : nodoActual.hijos) {
            Nodo nodoEncontrado = buscar(hijo, datoABuscar);
            if (nodoEncontrado != null) {
                return nodoEncontrado; // ¡Encontrado en alguna de las ramas!
            }
        }

        return null; // Si revisamos todo y no está, devolvemos null.
    }

    /**
     * 2. INSERCIÓN: Agrega un nodo nuevo como hijo de un nodo padre existente.
     * Busca al padre por su nombre, y si existe, engrana el nuevo hijo a él.
     */
    public boolean insertar(Nodo nodoActual, String nombrePadre, String nuevoDato) {
        // Aprovechamos nuestro método buscar para localizar al padre.
        Nodo padre = buscar(nodoActual, nombrePadre);

        if (padre != null) {
            padre.hijos.add(new Nodo(nuevoDato));
            return true; // Se insertó exitosamente
        }

        System.out.println("Error: El padre '" + nombrePadre + "' no existe.");
        return false;
    }

    /**
     * 3. ELIMINACIÓN: Elimina un nodo buscando a su padre y cortando el enlace.
     * ADVERTENCIA: En un árbol general, eliminar a un nodo también borra TODA
     * la rama o subárbol que desciende de él (igual que al borrar una carpeta).
     */
    public boolean eliminar(Nodo nodoActual, String datoAEliminar) {
        if (nodoActual == null)
            return false;

        // Nota: No podemos eliminar la raíz de esta forma tan simple
        if (nodoActual.dato.equals(datoAEliminar)) {
            System.out.println("No se puede eliminar la raíz directamente por este método.");
            return false;
        }

        // Revisamos los hijos del nodoActual para ver si uno de ellos es el que
        // queremos borrar.
        for (int i = 0; i < nodoActual.hijos.size(); i++) {
            Nodo hijo = nodoActual.hijos.get(i);

            // Si encontramos al condenado entre los hijos directos del actual, ¡lo
            // borramos!
            if (hijo.dato.equals(datoAEliminar)) {
                nodoActual.hijos.remove(i);
                return true;
            }

            // Si este hijo no es, le pedimos recursivamente que revise a sus propios hijos.
            boolean eliminadoMasAbajo = eliminar(hijo, datoAEliminar);
            if (eliminadoMasAbajo) {
                return true; // Se logró eliminar más profundo en esta rama.
            }
        }
        return false;
    }

    /**
     * 4. RECORRIDO PRE-ORDEN (Raíz -> Hijos de Izquierda a Derecha)
     * El clásico árbol jerárquico. Vemos al contenedor primero y luego al
     * contenido.
     */
    public void recorridoPreOrden(Nodo nodoActual) {
        if (nodoActual == null)
            return;

        // 1. Visitamos la RAÍZ (padre) primero
        System.out.print(nodoActual.dato + " ");

        // 2. Visitamos consecutivamente cada HIJO recursivamente
        for (Nodo hijo : nodoActual.hijos) {
            recorridoPreOrden(hijo);
        }
    }

    /**
     * 5. RECORRIDO POST-ORDEN (Hijos de Izquierda a Derecha -> Raíz)
     * Perfecto para eliminar. Se aseguran de revisar al contenido antes que el
     * contenedor.
     */
    public void recorridoPostOrden(Nodo nodoActual) {
        if (nodoActual == null)
            return;

        // 1. Visitamos TODO el contenido de los HIJOS primero
        for (Nodo hijo : nodoActual.hijos) {
            recorridoPostOrden(hijo);
        }

        // 2. Al final, visitamos la RAÍZ (padre)
        System.out.print(nodoActual.dato + " ");
    }

    /**
     * 6. RECORRIDO IN-ORDEN (Primer Hijo -> Raíz -> Demás Hijos)
     * OJO: ¡El In-Orden en árboles N-Arios NO ESTÁ ESTANDARIZADO formalmente!
     * En los árboles binarios encaja perfecto (Izq -> Raíz -> Der). En General,
     * suele hacerse visitando el primer hijo, luego la raíz, y por último el
     * resto de los descendientes.
     */
    public void recorridoInOrden(Nodo nodoActual) {
        if (nodoActual == null)
            return;

        if (nodoActual.hijos.isEmpty()) {
            // Si es hoja, nos visitamos y listo.
            System.out.print(nodoActual.dato + " ");
            return;
        }

        // 1. Visitamos primero al "Hijo Mayor" (El de más a la izquierda)
        recorridoInOrden(nodoActual.hijos.get(0));

        // 2. Visitamos la Raíz
        System.out.print(nodoActual.dato + " ");

        // 3. Visitamos al resto de hijos (Hijos de en medio y la derecha)
        for (int i = 1; i < nodoActual.hijos.size(); i++) {
            recorridoInOrden(nodoActual.hijos.get(i));
        }
    }
}
