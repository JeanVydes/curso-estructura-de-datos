class ColaConLimite:
    def __init__(self, limite):
        self.limite = limite
        self.cola = []

    def enqueue(self, nuevo_dato):
        if len(self.cola) >= self.limite:
            print(f"Error: La cola ha alcanzado su límite de {self.limite} elementos.")
            return False
        
        self.cola.append(nuevo_dato)
        print(f"Enqueue: {nuevo_dato}")
        return True

    def dequeue(self):
        if self.esta_vacia():
            print("Error: La cola está vacía, no se puede realizar 'dequeue'.")
            return None
        
        dato = self.cola.pop(0)
        print(f"Dequeue: {dato}")
        return dato

    def peek(self):
        if self.esta_vacia():
            print("La cola está vacía, no se puede realizar 'peek'.")
            return None
        return self.cola[0]

    def esta_vacia(self):
        return len(self.cola) == 0

    def mostrar(self):
        if self.esta_vacia():
            print("La cola está vacía.")
        else:
            print("Estado de la cola:", self.cola)

# --- Ejemplo de uso ---
print("--- Probando la funcionalidad de la cola con límite ---")
mi_cola = ColaConLimite(3)

mi_cola.enqueue(10)
mi_cola.enqueue(20)
mi_cola.enqueue(30)
mi_cola.mostrar()

print("El frente de la cola es:", mi_cola.peek())

# Intentar agregar un cuarto elemento
mi_cola.enqueue(40)
mi_cola.mostrar()

mi_cola.dequeue()
mi_cola.mostrar()

mi_cola.enqueue(50)
mi_cola.mostrar()