# Creamos la clase para el nodo
class Nodo:
    def __init__(self, dato=None):
        self.dato = dato
        self.siguiente = None

# Creamos la clase para la lista enlazada circular
class ListaCircular:
    def __init__(self):
        # El ultimo nodo de la lista
        self.ultimo = None

    # Metodo para insertar un nuevo nodo al inicio
    def insertar_al_inicio(self, dato):
        nuevo_nodo = Nodo(dato)
        
        # Si la lista esta vacia
        if self.ultimo is None:
            # el nuevo nodo se apunta a si mismo
            self.ultimo = nuevo_nodo
            nuevo_nodo.siguiente = self.ultimo
        else:
            # El nuevo nodo apunta a la cabeza actual
            nuevo_nodo.siguiente = self.ultimo.siguiente
            # El ultimo nodo apunta al nuevo nodo
            self.ultimo.siguiente = nuevo_nodo
    
    # Metodo para insertar un nuevo nodo al final
    def insertar_al_final(self, dato):
        nuevo_nodo = Nodo(dato)
        
        # Si la lista esta vacia
        if self.ultimo is None:
            # el nuevo nodo se apunta a si mismo
            self.ultimo = nuevo_nodo
            nuevo_nodo.siguiente = self.ultimo
        else:
            # El nuevo nodo apunta a la cabeza actual
            nuevo_nodo.siguiente = self.ultimo.siguiente
            # El ultimo nodo apunta al nuevo nodo
            self.ultimo.siguiente = nuevo_nodo
            # El ultimo ahora es el nuevo nodo
            self.ultimo = nuevo_nodo

    # Metodo para mostrar la lista
    def mostrar(self):
        if self.ultimo is None:
            print("La lista esta vacia.")
            return

        temp = self.ultimo.siguiente
        while True:
            print(temp.dato, end=" -> ")
            temp = temp.siguiente
            if temp == self.ultimo.siguiente:
                break
        print("...")

# Creamos una instancia de la lista y agregamos elementos
lista = ListaCircular()

lista.insertar_al_inicio(20)
lista.insertar_al_inicio(10)

print("La lista despues de insertar al inicio es:")
lista.mostrar()

lista.insertar_al_final(30)
lista.insertar_al_final(40)

print("\nLa lista despues de insertar al final es:")
lista.mostrar()