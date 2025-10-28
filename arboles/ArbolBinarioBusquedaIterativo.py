# arbol_iterativo_completo.py

"""
IMPLEMENTACIÓN DE UN ÁRBOL BINARIO DE BÚSQUEDA (ABB) ITERATIVO EN PYTHON

Esta clase implementa un Árbol Binario de Búsqueda (ABB) utilizando
exclusivamente métodos iterativos (bucles while) en lugar de recursión.
Esto puede ser más eficiente en términos de uso de memoria para árboles
muy profundos, ya que evita el desbordamiento de la pila de llamadas
(Stack Overflow).

Incluye también los algoritmos de Morris para recorridos sin usar pila
ni recursión, lo que los hace extremadamente eficientes en memoria O(1).
"""

from collections import deque


class Nodo:
    """
    ESTRUCTURA DEL NODO
    ¿Qué es? Define la unidad básica de nuestro árbol. Es el "ladrillo"
    con el que construiremos toda la estructura.
    """
    def __init__(self, dato):
        # ¿Qué hace? Almacena el valor numérico del nodo.
        self.dato = dato
        # ¿Qué hacen? Son referencias. Una referencia es como una flecha que apunta a
        # otro objeto Nodo en la memoria. 'izquierdo' apunta a un nodo con un
        # valor menor, y 'derecho' a uno con un valor mayor.
        self.izquierdo = None
        self.derecho = None


class ArbolBinarioBusquedaIterativo:
    """
    Árbol Binario de Búsqueda con implementación iterativa y algoritmos de Morris.
    """
    
    def __init__(self):
        self.raiz = None

    def insertar(self, valor):
        """
        INSERCIÓN ITERATIVA
        
        ¿Qué se busca? Encontrar la ubicación correcta para el nuevo valor sin
        romper la regla del ABB. Un nuevo nodo siempre se insertará como una
        "hoja" (un nodo sin hijos).
        
        ¿Cómo se logra? Se desciende por el árbol, comparando en cada nivel,
        hasta encontrar un lugar vacío (None) donde enganchar el nuevo nodo.
        """
        # --- PASO 1: PREPARACIÓN - CREAR EL NUEVO NODO ---
        nuevo_nodo = Nodo(valor)

        # --- PASO 2: EL CASO MÁS SIMPLE - EL ÁRBOL VACÍO ---
        if self.raiz is None:
            self.raiz = nuevo_nodo
            return

        # --- PASO 3: PREPARANDO LA BÚSQUEDA - LOS PUNTEROS GUÍA ---
        # 'actual' se inicializa en la raíz. Será nuestro explorador.
        actual = self.raiz
        # 'padre' se inicializa en None. Siempre irá un paso por detrás de 'actual'.
        padre = None

        # --- PASO 4: EL DESCENSO POR EL ÁRBOL - EL BUCLE 'while' ---
        while actual is not None:
            # 'padre' se actualiza para que apunte a 'actual'.
            padre = actual

            if valor < actual.dato:
                actual = actual.izquierdo
            elif valor > actual.dato:
                actual = actual.derecho
            else:
                # El valor ya existe. No permitimos duplicados.
                return

        # --- PASO 5: LA CONEXIÓN FINAL - ENGANCHAR EL NODO ---
        if valor < padre.dato:
            padre.izquierdo = nuevo_nodo
        else:
            padre.derecho = nuevo_nodo

    def eliminar(self, valor):
        """
        ELIMINACIÓN ITERATIVA
        
        ¿Qué se busca? Quitar un nodo y "reparar" la estructura para que la
        regla del ABB se mantenga.
        
        ¿Por qué hay diferentes casos? La reparación depende de cuántos hijos
        tiene el nodo a eliminar. La complejidad varía si tiene 0, 1 o 2 hijos.
        """
        if self.raiz is None:
            return

        # --- PASO 1: BÚSQUEDA DEL NODO A ELIMINAR Y SU PADRE ---
        padre = None
        actual = self.raiz

        while actual is not None and actual.dato != valor:
            padre = actual
            if valor < actual.dato:
                actual = actual.izquierdo
            else:
                actual = actual.derecho

        # Si actual es None, el valor no estaba en el árbol.
        if actual is None:
            return

        # --- PASO 2: LÓGICA DE ELIMINACIÓN BASADA EN EL NÚMERO DE HIJOS ---
        
        # CASO 1 (hoja) Y CASO 2 (un hijo)
        if actual.izquierdo is None or actual.derecho is None:
            # Determina cuál es el único hijo (si existe).
            hijo = actual.izquierdo if actual.izquierdo is not None else actual.derecho

            # Subcaso: se está eliminando la raíz.
            if padre is None:
                self.raiz = hijo
            # Si 'actual' es un hijo izquierdo, su padre ahora apunta a 'hijo'.
            elif actual == padre.izquierdo:
                padre.izquierdo = hijo
            # Si 'actual' es un hijo derecho, su padre ahora apunta a 'hijo'.
            else:
                padre.derecho = hijo

        # CASO 3: EL NODO TIENE DOS HIJOS
        else:
            # Estrategia: Reemplazar el valor, no el nodo.
            # 1. Encontrar el sucesor (el nodo más pequeño del subárbol derecho).
            padre_sucesor = actual
            sucesor = actual.derecho
            while sucesor.izquierdo is not None:
                padre_sucesor = sucesor
                sucesor = sucesor.izquierdo

            # 2. Copiamos el valor del sucesor al nodo que queremos "eliminar".
            actual.dato = sucesor.dato

            # 3. Eliminar el nodo sucesor de su posición original.
            if sucesor == padre_sucesor.derecho:
                padre_sucesor.derecho = sucesor.derecho
            else:
                padre_sucesor.izquierdo = sucesor.derecho

    def recorrido_in_orden_morris(self):
        """
        RECORRIDO IN-ORDEN CON MORRIS
        
        ¿Qué se busca? Recorrer el árbol sin usar recursión ni una pila explícita.
        
        ¿Cómo se logra? (La idea de los "Hilos")
        Se modifica temporalmente el árbol creando "hilos" (punteros de vuelta)
        desde el nodo más a la derecha de un subárbol izquierdo hacia su raíz.
        Este hilo actúa como una "miga de pan" para saber cómo volver a subir.
        """
        print("In-Orden (Morris):   ", end="")
        actual = self.raiz

        while actual is not None:
            # Si no hay subárbol izquierdo, es simple: visitamos el nodo y vamos a la derecha.
            if actual.izquierdo is None:
                print(actual.dato, end=" ")
                actual = actual.derecho
            else:
                # Si hay subárbol izquierdo, buscamos a su predecesor in-orden.
                predecesor = actual.izquierdo
                while predecesor.derecho is not None and predecesor.derecho != actual:
                    predecesor = predecesor.derecho

                # Si el puntero derecho del predecesor es None, es nuestra primera visita.
                if predecesor.derecho is None:
                    # --- CREAR HILO ---
                    predecesor.derecho = actual
                    actual = actual.izquierdo
                else:
                    # --- QUITAR HILO ---
                    predecesor.derecho = None
                    print(actual.dato, end=" ")
                    actual = actual.derecho

        print()

    def recorrido_pre_orden_morris(self):
        """
        RECORRIDO PRE-ORDEN CON MORRIS
        
        La diferencia clave con In-Orden: En Pre-Orden (Raíz -> Izquierda -> Derecha),
        visitamos el nodo la PRIMERA vez que lo encontramos, ANTES de crear
        el hilo y descender por la izquierda.
        """
        print("Pre-Orden (Morris):  ", end="")
        actual = self.raiz

        while actual is not None:
            if actual.izquierdo is None:
                print(actual.dato, end=" ")
                actual = actual.derecho
            else:
                predecesor = actual.izquierdo
                while predecesor.derecho is not None and predecesor.derecho != actual:
                    predecesor = predecesor.derecho

                if predecesor.derecho is None:
                    # LA DIFERENCIA CLAVE: visitamos antes de crear el hilo
                    print(actual.dato, end=" ")
                    predecesor.derecho = actual
                    actual = actual.izquierdo
                else:
                    predecesor.derecho = None
                    actual = actual.derecho

        print()

    def recorrido_post_orden_morris(self):
        """
        RECORRIDO POST-ORDEN CON MORRIS
        
        Lógica: Post-Orden directo (Izquierda -> Derecha -> Raíz) es muy complejo
        con Morris. El truco es hacer un recorrido modificado (Raíz -> Derecha -> Izquierda)
        y añadir cada elemento visitado al FRENTE de una lista. Esto revierte el orden.
        El resultado de revertir (Raíz, Derecha, Izquierda) es (Izquierda, Derecha, Raíz).
        """
        print("Post-Orden (Morris): ", end="")
        resultado = deque()  # Usamos deque para appendleft eficiente
        actual = self.raiz

        while actual is not None:
            if actual.derecho is None:
                resultado.appendleft(actual.dato)
                actual = actual.izquierdo
            else:
                # Buscamos el sucesor (el nodo más a la izquierda del subárbol derecho).
                sucesor = actual.derecho
                while sucesor.izquierdo is not None and sucesor.izquierdo != actual:
                    sucesor = sucesor.izquierdo

                if sucesor.izquierdo is None:
                    # Visitamos (añadimos al resultado) y creamos el hilo.
                    resultado.appendleft(actual.dato)
                    sucesor.izquierdo = actual
                    actual = actual.derecho
                else:
                    # Rompemos el hilo y avanzamos.
                    sucesor.izquierdo = None
                    actual = actual.izquierdo

        # Imprimimos el resultado final que ya está en el orden correcto.
        print(" ".join(map(str, resultado)))


def main():
    """
    Función principal para probar la implementación del árbol iterativo.
    """
    arbol = ArbolBinarioBusquedaIterativo()
    
    print("--- ÁRBOL ITERATIVO EN PYTHON ---")
    valores = [10, 5, 15, 3, 7, 12, 18]
    print("Insertando:", end=" ")
    for valor in valores:
        print(valor, end=" ")
        arbol.insertar(valor)
    print("\n")

    arbol.recorrido_in_orden_morris()
    arbol.recorrido_pre_orden_morris()
    arbol.recorrido_post_orden_morris()

    print("\nEliminando el 15 (nodo con dos hijos)...")
    arbol.eliminar(15)
    arbol.recorrido_in_orden_morris()


if __name__ == "__main__":
    main()
