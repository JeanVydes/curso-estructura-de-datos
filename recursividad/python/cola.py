class Nodo:
    def __init__(self, dato):
        self.dato = dato
        self.siguiente = None

class Cola:
    def __init__(self):
        self.frente = None
        self.final_de_cola = None

    def enqueue(self, nuevo_dato):
        nuevo_nodo = Nodo(nuevo_dato)
        if self.final_de_cola is None:
            self.frente = nuevo_nodo
            self.final_de_cola = nuevo_nodo
        else:
            self.final_de_cola.siguiente = nuevo_nodo
            self.final_de_cola = nuevo_nodo

    def dequeue(self):
        if self.esta_vacia():
            raise Exception("Cola vacía")
        dato = self.frente.dato
        self.frente = self.frente.siguiente
        if self.frente is None:
            self.final_de_cola = None
        return dato

    def peek(self):
        if self.esta_vacia():
            raise Exception("Cola vacía")
        return self.frente.dato

    def esta_vacia(self):
        return self.frente is None and self.final_de_cola is None

    def invertir_recursivo(self):
        if self.esta_vacia():
            return
        
        dato = self.dequeue()
        self.invertir_recursivo()
        self.enqueue(dato)

    def buscar_elemento_recursivo(self, nodo, valor):
        if nodo is None:
            return False
        
        if nodo.dato == valor:
            return True
            
        return self.buscar_elemento_recursivo(nodo.siguiente, valor)

    def mostrar(self):
        actual = self.frente
        print("Cola: ", end="")
        while actual is not None:
            print(f"{actual.dato} ", end="")
            actual = actual.siguiente
        print()

if __name__ == "__main__":
    c = Cola()
    c.enqueue(10)
    c.enqueue(20)
    c.enqueue(30)

    print("Cola original:")
    c.mostrar()

    c.invertir_recursivo()
    print("Cola invertida recursivamente:")
    c.mostrar()

    print(f"Buscar 20 (recursivo): {c.buscar_elemento_recursivo(c.frente, 20)}")
    print(f"Buscar 50 (recursivo): {c.buscar_elemento_recursivo(c.frente, 50)}")
