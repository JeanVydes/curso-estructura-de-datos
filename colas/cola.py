# La cola se representa con una lista
# El frente de la cola es el primer elemento de la lista (índice 0)
# El final de la cola es el último elemento de la lista (índice -1)
cola = []

# Método para añadir un elemento al final de la cola
def enqueue(nuevo_dato):
    cola.append(nuevo_dato)
    print(f"Enqueue: {nuevo_dato}")

# Método para eliminar y devolver el elemento del frente de la cola
def dequeue():
    if not esta_vacia():
        dato = cola.pop(0)
        print(f"Dequeue: {dato}")
        return dato
    else:
        print("Error: La cola está vacía.")
        return None

# Método para ver el elemento del frente sin eliminarlo
def peek():
    if not esta_vacia():
        return cola[0]
    else:
        print("La cola está vacía.")
        return None

# Método para verificar si la cola está vacía
def esta_vacia():
    return len(cola) == 0

# Método para invertir la cola
def invertir():
    if not esta_vacia():
        dato = dequeue()
        invertir()
        enqueue(dato)

# Método para mostrar todos los elementos de la cola
def mostrar():
    if esta_vacia():
        print("La cola está vacía.")
    else:
        print("Cola:", cola)

# --- Ejemplos de uso ---
print("--- Probar la funcionalidad de la cola ---")
enqueue(10)
enqueue(20)
enqueue(30)
mostrar()

dequeue()
dequeue()
mostrar()

enqueue(40)
enqueue(50)
mostrar()

print("\n--- Invirtiendo la cola ---")
invertir()
mostrar()

# --- Probar la función de unir y ordenar ---
print("\n--- Probar la función de unir y ordenar ---")
cola1 = [5, 15, 25]
cola2 = [10, 20, 30]

def unir_y_ordenar(c1, c2):
    # Unir las dos listas
    cola_final = c1 + c2
    
    # Ordenar la lista resultante
    cola_final.sort()
    
    return cola_final

cola_unida = unir_y_ordenar(cola1, cola2)
print("Cola 1:", cola1)
print("Cola 2:", cola2)
print("Cola unida y ordenada:", cola_unida)