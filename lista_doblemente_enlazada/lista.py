# Creamos la clase para el nodo
class Nodo:
    def __init__(self, dato=None):
        # el dato que queremos guardar
        self.dato = dato
        # la referencia al nodo anterior
        self.anterior = None
        # la referencia al siguiente nodo
        self.siguiente = None

# Creamos la clase para la lista doblemente enlazada
class ListaDobleEnlazada:
    def __init__(self):
        # la cabeza de la lista
        self.cabeza = None

    # Metodo para insertar un nuevo nodo al inicio
    def insertar_al_inicio(self, dato):
        # 1. Crear el nuevo nodo
        nuevo_nodo = Nodo(dato)
        
        # 2. El anterior del nuevo nodo es None
        nuevo_nodo.anterior = None
        
        # 3. El siguiente del nuevo nodo es la cabeza actual
        nuevo_nodo.siguiente = self.cabeza
        
        # 4. Si la lista no estaba vacia, el anterior de la cabeza actual es el nuevo nodo
        if self.cabeza:
            self.cabeza.anterior = nuevo_nodo
            
        # 5. La cabeza de la lista es ahora el nuevo nodo
        self.cabeza = nuevo_nodo
    
    # Metodo para insertar un nuevo nodo al final
    def insertar_al_final(self, dato):
        # 1. Crear el nuevo nodo
        nuevo_nodo = Nodo(dato)
        
        # 2. El siguiente del nuevo nodo es None porque será el ultimo
        nuevo_nodo.siguiente = None
        
        # 3. Si la lista está vacía, el nuevo nodo es la cabeza
        if not self.cabeza:
            nuevo_nodo.anterior = None
            self.cabeza = nuevo_nodo
            return
            
        # 4. Recorremos la lista para encontrar el ultimo nodo
        ultimo = self.cabeza
        while ultimo.siguiente:
            ultimo = ultimo.siguiente
            
        # 5. Enlazamos el ultimo nodo al nuevo nodo
        ultimo.siguiente = nuevo_nodo
        nuevo_nodo.anterior = ultimo

    # Metodo para mostrar la lista hacia adelante
    def mostrar(self):
        temp = self.cabeza
        while temp:
            print(temp.dato, end=" <-> ")
            temp = temp.siguiente
        print("None")

# Creamos una instancia de la lista y agregamos elementos
lista = ListaDobleEnlazada()
lista.insertar_al_inicio(30)
lista.insertar_al_inicio(20)
lista.insertar_al_inicio(10)

print("La lista despues de insertar al inicio es:")
lista.mostrar()

lista.insertar_al_final(40)
lista.insertar_al_final(50)

print("\nLa lista despues de insertar al final es:")
lista.mostrar()
