# arbol_recursivo_simple.py

"""
IMPLEMENTACIÓN DE UN ÁRBOL BINARIO DE BÚSQUEDA (ABB) RECURSIVO EN PYTHON

Este módulo implementa un Árbol Binario de Búsqueda utilizando exclusivamente
métodos recursivos. La recursión es un enfoque muy natural y elegante para
las estructuras de datos jerárquicas como los árboles, ya que las operaciones
en un árbol completo a menudo se pueden definir en términos de la misma
operación en sus subárboles (izquierdo y derecho).
"""


class Nodo:
    """
    ESTRUCTURA DEL NODO
    ¿Qué es? La plantilla para cada elemento del árbol. Es el "ladrillo"
    con el que construiremos toda la estructura.
    ¿Por qué? Necesitamos una forma de almacenar un dato y, al mismo tiempo,
    tener "caminos" o "enlaces" a otros nodos.
    """
    def __init__(self, dato):
        # ¿Qué hace? Almacena el valor numérico del nodo.
        self.dato = dato
        # ¿Qué hacen? Son referencias. Una referencia es como una flecha que apunta a
        # otro objeto Nodo en la memoria. 'izquierdo' apunta a un nodo con un
        # valor menor, y 'derecho' a uno con un valor mayor.
        self.izquierdo = None
        self.derecho = None


class ArbolBinarioBusqueda:
    """
    Árbol Binario de Búsqueda con implementación recursiva.
    """
    
    def __init__(self):
        self.raiz = None

    # --- MÉTODOS PÚBLICOS DE INTERFAZ ---
    # ¿Qué hacen? Son el punto de entrada para el usuario.
    # ¿Por qué? Ocultan la complejidad de la recursión. El usuario no necesita
    # saber sobre 'nodo_actual', solo quiere insertar un valor en "el árbol".
    # Estas funciones inician el proceso recursivo desde la raíz.

    def insertar(self, valor):
        """
        Inserta un valor en el árbol de forma recursiva.
        """
        self.raiz = self._insertar_recursivo(self.raiz, valor)

    def eliminar(self, valor):
        """
        Elimina un valor del árbol de forma recursiva.
        """
        self.raiz = self._eliminar_recursivo(self.raiz, valor)

    def recorrido_in_orden(self):
        """
        Recorrido In-Orden: Izquierda -> Raíz -> Derecha
        """
        print("In-Orden (Recursivo):  ", end="")
        self._in_orden_recursivo(self.raiz)
        print()

    def recorrido_pre_orden(self):
        """
        Recorrido Pre-Orden: Raíz -> Izquierda -> Derecha
        """
        print("Pre-Orden (Recursivo): ", end="")
        self._pre_orden_recursivo(self.raiz)
        print()

    def recorrido_post_orden(self):
        """
        Recorrido Post-Orden: Izquierda -> Derecha -> Raíz
        """
        print("Post-Orden (Recursivo):", end="")
        self._post_orden_recursivo(self.raiz)
        print()

    def calcular_altura(self):
        """
        Calcula la altura del árbol.
        """
        return self._calcular_altura_recursivo(self.raiz)

    def es_arbol_valido(self):
        """
        Verifica si el árbol es un ABB válido.
        """
        return self._es_arbol_valido_recursivo(self.raiz, None, None)

    # --- MÉTODOS PRIVADOS RECURSIVOS ---

    def _insertar_recursivo(self, nodo_actual, valor):
        """
        INSERCIÓN RECURSIVA (Método auxiliar)
        
        --- PASO 1: CASO BASE - EL PUNTO DE PARADA ---
        ¿Qué hace? Comprueba si hemos llegado a un espacio vacío (None).
        ¿Por qué? La recursión debe terminar. Este es el final del camino: hemos
        encontrado el lugar exacto donde debe ir el nuevo nodo. Creamos el nodo
        y lo retornamos para que la llamada anterior (su padre) lo "enganche".
        """
        if nodo_actual is None:
            return Nodo(valor)

        # --- PASO 2: PASO RECURSIVO - LA DECISIÓN Y LA LLAMADA ---
        # ¿Qué hace? Compara el valor con el dato del nodo actual para decidir a dónde ir.
        # ¿Por qué? Para mantener la propiedad del ABB.
        if valor < nodo_actual.dato:
            # ¿Qué hace? Llama a la misma función, pero para el subárbol izquierdo.
            # ¿Por qué? Delega la tarea a un problema más pequeño. La clave es la
            # asignación. El padre (nodo_actual) actualiza su puntero izquierdo
            # con el resultado de la inserción en su subárbol.
            nodo_actual.izquierdo = self._insertar_recursivo(nodo_actual.izquierdo, valor)
        elif valor > nodo_actual.dato:
            # La misma lógica, pero para el lado derecho.
            nodo_actual.derecho = self._insertar_recursivo(nodo_actual.derecho, valor)
        
        # Si valor == nodo_actual.dato, no hacemos nada (no permitimos duplicados)

        # --- PASO 3: EL RETORNO - MANTENIENDO LA CADENA ---
        # ¿Qué hace? Devuelve la referencia al nodo actual.
        # ¿Por qué? Si el valor ya existía, o si la inserción ocurrió más abajo,
        # este nodo no cambia. Devolverlo asegura que los enlaces por encima de él
        # en la cadena de recursión permanezcan intactos.
        return nodo_actual

    def _eliminar_recursivo(self, nodo_actual, valor):
        """
        ELIMINACIÓN RECURSIVA (Método auxiliar)
        
        --- PASO 1: BÚSQUEDA - ENCONTRAR EL NODO ---
        ¿Qué hace? Si llegamos a None, el valor no existe. Es el caso base de la búsqueda.
        """
        if nodo_actual is None:
            return None

        # ¿Qué hace? Llama recursivamente para bajar por el árbol hasta encontrar el nodo.
        if valor < nodo_actual.dato:
            nodo_actual.izquierdo = self._eliminar_recursivo(nodo_actual.izquierdo, valor)
        elif valor > nodo_actual.dato:
            nodo_actual.derecho = self._eliminar_recursivo(nodo_actual.derecho, valor)
        else:
            # --- PASO 2: NODO ENCONTRADO - LÓGICA DE ELIMINACIÓN ---

            # CASO 1: Nodo con 0 hijos (hoja).
            # ¿Qué hace? Devuelve None.
            # ¿Por qué? El padre que llamó a esta función recibirá None y lo asignará
            # a su puntero de hijo, efectivamente "cortando" la hoja del árbol.
            if nodo_actual.izquierdo is None and nodo_actual.derecho is None:
                return None

            # CASO 2: Nodo con 1 hijo.
            # ¿Qué hace? Devuelve la referencia a su único hijo.
            # ¿Por qué? El padre del nodo eliminado "adoptará" directamente a su nieto,
            # "puenteando" el nodo eliminado y manteniendo la estructura.
            if nodo_actual.derecho is None:
                return nodo_actual.izquierdo
            if nodo_actual.izquierdo is None:
                return nodo_actual.derecho

            # CASO 3: Nodo con 2 hijos.
            # ¿Qué hace? Encuentra el sucesor, copia su valor al nodo actual, y luego
            # elimina el sucesor de su posición original.
            # ¿Por qué? Es la única forma de eliminar el valor sin romper la estructura.
            # No eliminamos el nodo, solo "robamos" el valor del sucesor. El problema
            # se convierte en el más fácil de eliminar el sucesor, que a lo sumo
            # tendrá un hijo (Caso 1 o 2).
            sucesor = self._encontrar_minimo(nodo_actual.derecho)
            nodo_actual.dato = sucesor
            nodo_actual.derecho = self._eliminar_recursivo(nodo_actual.derecho, sucesor)

        return nodo_actual

    def _encontrar_minimo(self, nodo):
        """
        ENCONTRAR MÍNIMO (Función auxiliar para eliminar)
        
        ¿Qué hace? Sigue el camino de referencias izquierdas hasta el final.
        ¿Por qué? Por definición, en un ABB, el valor más pequeño siempre está en
        el nodo más a la izquierda posible.
        """
        if nodo.izquierdo is None:
            return nodo.dato
        return self._encontrar_minimo(nodo.izquierdo)

    def _in_orden_recursivo(self, nodo):
        """
        Recorrido In-Orden recursivo: Izquierda -> Raíz -> Derecha
        Visita los nodos en orden ascendente.
        """
        if nodo is not None:
            self._in_orden_recursivo(nodo.izquierdo)   # 1. Resuelve todo lo de la izquierda primero.
            print(nodo.dato, end=" ")                  # 2. Luego visita la raíz actual.
            self._in_orden_recursivo(nodo.derecho)     # 3. Finalmente, resuelve todo lo de la derecha.

    def _pre_orden_recursivo(self, nodo):
        """
        Recorrido Pre-Orden recursivo: Raíz -> Izquierda -> Derecha
        """
        if nodo is not None:
            print(nodo.dato, end=" ")                  # 1. Visita la raíz primero.
            self._pre_orden_recursivo(nodo.izquierdo)  # 2. Luego la izquierda.
            self._pre_orden_recursivo(nodo.derecho)    # 3. Finalmente la derecha.

    def _post_orden_recursivo(self, nodo):
        """
        Recorrido Post-Orden recursivo: Izquierda -> Derecha -> Raíz
        """
        if nodo is not None:
            self._post_orden_recursivo(nodo.izquierdo)  # 1. Izquierda primero.
            self._post_orden_recursivo(nodo.derecho)    # 2. Luego derecha.
            print(nodo.dato, end=" ")                   # 3. Finalmente la raíz.

    def _calcular_altura_recursivo(self, nodo):
        """
        CALCULAR ALTURA (Método auxiliar)
        
        --- CASO BASE ---
        ¿Qué hace? Si el nodo es None, devuelve -1.
        ¿Por qué? Un espacio vacío no tiene altura. El -1 hace que la matemática
        para un nodo hoja (un nodo sin hijos) sea correcta: 1 + max(-1, -1) = 0.
        """
        if nodo is None:
            return -1

        # --- PASO RECURSIVO ---
        # ¿Qué hace? Calcula la altura de cada subárbol por separado.
        # ¿Por qué? La altura es el camino más largo. Debemos explorar ambos
        # caminos (izquierdo y derecho) para saber cuál es más largo.
        altura_izquierda = self._calcular_altura_recursivo(nodo.izquierdo)
        altura_derecha = self._calcular_altura_recursivo(nodo.derecho)

        # ¿Qué hace? Devuelve 1 más la altura del subárbol más alto.
        # ¿Por qué? El '1' cuenta el nivel del nodo actual, y el 'max' elige el
        # camino más largo que encontró entre sus hijos.
        return 1 + max(altura_izquierda, altura_derecha)

    def _es_arbol_valido_recursivo(self, nodo, minimo, maximo):
        """
        VALIDAR ABB (Método auxiliar)
        
        --- CASO BASE ---
        ¿Qué hace? Si el nodo es None, la rama es válida.
        ¿Por qué? Un subárbol vacío no puede violar ninguna regla.
        """
        if nodo is None:
            return True

        # --- LÓGICA DE VALIDACIÓN ---
        # ¿Qué hace? Comprueba si el valor del nodo actual está fuera del rango
        # [minimo, maximo] permitido por sus ancestros.
        # ¿Por qué? No basta con comparar con el padre directo. Un nodo debe respetar a
        # TODOS sus ancestros. Este rango que se va estrechando garantiza esta propiedad global.
        if (minimo is not None and nodo.dato <= minimo) or \
           (maximo is not None and nodo.dato >= maximo):
            return False

        # --- PASO RECURSIVO ---
        # ¿Qué hace? Llama a la función para sus hijos con los rangos actualizados.
        # ¿Por qué? Al ir a la izquierda, el valor del nodo actual se convierte en el nuevo MÁXIMO
        # permitido. Al ir a la derecha, se convierte en el nuevo MÍNIMO.
        return self._es_arbol_valido_recursivo(nodo.izquierdo, minimo, nodo.dato) and \
               self._es_arbol_valido_recursivo(nodo.derecho, nodo.dato, maximo)


def main():
    """
    Función principal para probar la implementación del árbol.
    """
    arbol = ArbolBinarioBusqueda()
    
    print("--- ÁRBOL RECURSIVO EN PYTHON ---")
    valores = [10, 5, 15, 3, 7, 12, 18]
    print("Insertando:", end=" ")
    for valor in valores:
        print(valor, end=" ")
        arbol.insertar(valor)
    print("\n")

    arbol.recorrido_in_orden()
    arbol.recorrido_pre_orden()
    arbol.recorrido_post_orden()

    print(f"\nLa altura del arbol es: {arbol.calcular_altura()}")
    print(f"El arbol es un ABB valido? {'Si' if arbol.es_arbol_valido() else 'No'}")

    print("\nEliminando el 15 (nodo con dos hijos)...")
    arbol.eliminar(15)
    arbol.recorrido_in_orden()


if __name__ == "__main__":
    main()
