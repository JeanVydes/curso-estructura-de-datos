class Nodo:
    def __init__(self, dato):
        self.dato = dato
        self.siguiente = None

class Pila:
    def __init__(self):
        self.cima = None

    def push(self, nuevo_dato):
        nuevo_nodo = Nodo(nuevo_dato)
        nuevo_nodo.siguiente = self.cima
        self.cima = nuevo_nodo

    def pop(self):
        if self.esta_vacia():
            return -1
        dato = self.cima.dato
        self.cima = self.cima.siguiente
        return dato

    def peek(self):
        if self.esta_vacia():
            return -1
        return self.cima.dato

    def esta_vacia(self):
        return self.cima is None

    def contar_recursivo(self, nodo):
        """
        EJERCICIO DE RECURSIVIDAD 1: Contar elementos de la pila.
        ¿Cómo funciona?
        En lugar de usar un bucle 'while', avanzamos por la estructura y decimos:
        "La cantidad de elementos desde este nodo es: 1 (yo mismo) + (la cantidad del resto)".

        EJEMPLO: Pila [10, 20, 30] -> Cima = 30
        contar(30) -> devuelve 1 + 2
        contar(20) -> devuelve 1 + 1
        contar(10) -> devuelve 1 + 0
        contar(None) -> devuelve 0 (CASO BASE)

        RESULTADO FINAL: 1 + (1 + (1 + 0)) = 3.
        """
        if nodo is None:
            return 0
        return 1 + self.contar_recursivo(nodo.siguiente)

    def sumar_elementos_recursivo(self, nodo):
        """
        EJERCICIO DE RECURSIVIDAD 2: Sumar todos los elementos de la pila.
        Comparte el mismo principio de "conteo", pero en vez de sumar '1',
        sumamos el valor del dato del nodo actual.

        EJEMPLO ASCII:
        [30] -> cima
        [20]
        [10]

        La operación será: 30 + sumar([20, 10]) => 30 + 20 + sumar([10]) => 30 + 20 + 10 + 0 = 60.
        """
        if nodo is None:
            return 0
        return nodo.dato + self.sumar_elementos_recursivo(nodo.siguiente)

    def invertir_pila(self):
        """
        EJERCICIO DE RECURSIVIDAD 3: Invertir la pila.

        MÁGIA DE LA PILA DE LLAMADAS:
        Si nosotros extraemos (pop) todos los elementos de la pila y guardamos
        cada uno en una llamada recursiva, al regresar de la recursión los habremos
        sacado desde el primero hasta el último. Al meterlos "al fondo", el orden
        queda completamente invertido.

        PROCESO EJEMPLIFICADO (Pila original: Cima[A, B, C]Fondo):

        -> invertir_pila([A, B, C])
        Saca 'A', llama invertir_pila([B, C])

        -> invertir_pila([B, C])
        Saca 'B', llama invertir_pila([C])

        -> invertir_pila([C])
        Saca 'C', llama invertir_pila([])

        -> invertir_pila([]) -> Caso Base (retorna)

        <- Inserta 'C' en el fondo de [] => Pila: [C]

        <- Inserta 'B' en el fondo de [C] => Pila: Cima[C, B]Fondo

        <- Inserta 'A' en el fondo de [C, B] => Pila: Cima[C, B, A]Fondo
        (¡Invertida!)
        """
        if self.esta_vacia():
            return
        dato = self.pop()
        self.invertir_pila()
        self.insertar_en_el_fondo(dato)

    def insertar_en_el_fondo(self, dato):
        """
        FUNCIÓN AUXILIAR RECURSIVA: Insertar en el fondo.

        ¿Por qué necesitamos esto? En una pila tradicional, `push` sólo pone cosas
        en la cima (arriba). Si queremos poner algo hasta abajo (fondo), tenemos
        que sacar todo lo que está estorbando, poner nuestro elemento, y regresar
        lo que sacamos en su mismo orden.

        ILUSTRACIÓN ASCII (Insertar 'X' en el fondo de [1, 2]):
        ==============================================================
        LLAMADA 1: insertar_en_el_fondo(X) en Pila: Cima[1, 2]Fondo
        - ¿Vacía? NO.
        - dato_temporal = pop() -> ¡Sacamos el 1! Pila queda [2]
        - LLAMADA RECURSIVA a sí misma: insertar_en_el_fondo(X)

        LLAMADA 2: insertar_en_el_fondo(X) en Pila: Cima[2]Fondo
        - ¿Vacía? NO.
        - dato_temporal = pop() -> ¡Sacamos el 2! Pila queda []
        - LLAMADA RECURSIVA a sí misma: insertar_en_el_fondo(X)

        LLAMADA 3 (Caso Base): insertar_en_el_fondo(X) en Pila: Cima[]Fondo
        - ¿Vacía? SÍ. -> push(X). Pila queda [X].
        - Terminamos y vamos de regreso arriba...

        <- VOLVEMOS A LLAMADA 2: push(dato_temporal) -> push(2). Pila queda [2, X]

        <- VOLVEMOS A LLAMADA 1: push(dato_temporal) -> push(1). Pila queda [1, 2, X]
        ==============================================================
        """
        if self.esta_vacia():
            self.push(dato)
            return

        dato_temporal = self.pop()
        self.insertar_en_el_fondo(dato)
        self.push(dato_temporal)

if __name__ == "__main__":
    p = Pila()
    p.push(1)
    p.push(2)
    p.push(3)
    p.push(4)

    print(f"Cantidad de elementos (Recursivo): {p.contar_recursivo(p.cima)}")
    print(f"Suma de elementos (Recursivo): {p.sumar_elementos_recursivo(p.cima)}")
    
    print(f"Cima antes de invertir: {p.peek()}")
    p.invertir_pila()
    print(f"Cima despues de invertir: {p.peek()}")
