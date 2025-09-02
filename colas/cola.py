# Creamos la clase para el nodo
class Nodo:
    def __init__(self, dato=None):
        self.dato = dato
        self.siguiente = None

# Creamos la clase para la cola
class Cola:
    def __init__(self):
        self.frente = None
        self.final = None

    # Operación enqueue: añadir un elemento al final
    def enqueue(self, dato):
        nuevo_nodo = Nodo(dato)
        
        # Si la cola está vacía
        if self.final is None:
            self.frente = nuevo_nodo
            self.final = nuevo_nodo
        else:
            # El nodo actual al final apunta al nuevo nodo
            self.final.siguiente = nuevo_nodo
            # El nuevo nodo es ahora el final de la cola
            self.final = nuevo_nodo
        print(f"Enqueue: {dato}")

    # Operación dequeue: eliminar el elemento del frente
    def dequeue(self):
        if self.esta_vacia():
            print("Error: La cola está vacia.")
            return None
        
        dato = self.frente.dato
        self.frente = self.frente.siguiente
        
        if self.frente is None:
            self.final = None
        
        print(f"Dequeue: {dato}")
        return dato

    # Operación peek: ver el elemento del frente sin eliminarlo
    def peek(self):
        if self.esta_vacia():
            return None
        return self.frente.dato

    # Verificar si la cola está vacía
    def esta_vacia(self):
        return self.frente is None

# Creamos una instancia de la cola y usamos sus métodos
cola = Cola()

cola.enqueue(10)
cola.enqueue(20)
cola.enqueue(30)

print("Frente de la cola:", cola.peek())

cola.dequeue()
cola.dequeue()

print("Frente de la cola:", cola.peek())
