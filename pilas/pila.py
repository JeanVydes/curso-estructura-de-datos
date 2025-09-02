# Creamos la clase para el nodo
class Nodo:
    def __init__(self, dato=None):
        self.dato = dato
        self.siguiente = None

# Creamos la clase para la pila
class Pila:
    def __init__(self):
        self.cima = None

    # Operacion push: anadir un elemento
    def push(self, dato):
        nuevo_nodo = Nodo(dato)
        nuevo_nodo.siguiente = self.cima
        self.cima = nuevo_nodo
        print(f"Push: {dato}")

    # Operacion pop: eliminar el elemento superior
    def pop(self):
        if self.esta_vacia():
            print("Error: La pila esta vacia.")
            return None
        dato = self.cima.dato
        self.cima = self.cima.siguiente
        print(f"Pop: {dato}")
        return dato

    # Operacion peek: ver el elemento superior sin eliminarlo
    def peek(self):
        if self.esta_vacia():
            return None
        return self.cima.dato

    # Verificar si la pila esta vacia
    def esta_vacia(self):
        return self.cima is None

# Creamos una instancia de la pila y usamos sus metodos
pila = Pila()

pila.push(10)
pila.push(20)
pila.push(30)

print("Cima de la pila:", pila.peek())

pila.pop()
pila.pop()

print("Cima de la pila:", pila.peek())