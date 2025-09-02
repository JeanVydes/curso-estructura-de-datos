# Creamos la clase para el nodo. Un nodo es el bloque de construccion de la lista enlazada
class Nodo:
    def __init__(self, dato=None):
        # el dato que queremos guardar
        self.dato = dato
        # la referencia al siguiente nodo
        self.siguiente = None

# Creamos la clase para la lista enlazada, que maneja los nodos
class ListaEnlazada:
    def __init__(self):
        # la cabeza de la lista, al inicio no hay nodos, entonces es nula
        self.cabeza = None

    # Metodo para insertar un nuevo nodo al inicio de la lista
    def insertar_al_inicio(self, dato):
        # 1. Crear el nuevo nodo con el dato
        nuevo_nodo = Nodo(dato)
        
        # 2. Hacer que el siguiente del nuevo nodo apunte a la cabeza actual
        nuevo_nodo.siguiente = self.cabeza
        
        # 3. Mover la cabeza para que apunte al nuevo nodo
        self.cabeza = nuevo_nodo

    def insertar_al_final(self, dato):
        # 1. Crear el nuevo nodo con el dato
        nuevo_nodo = Nodo(dato)

        # 2. Hacer que el siguiente del nuevo nodo apunte a None
        nuevo_nodo.siguiente = None

        # 3. Si la lista está vacía, la cabeza es el nuevo nodo
        if self.cabeza is None:
            self.cabeza = nuevo_nodo
        else:
            # 4. Si no, encontramos el último nodo y lo enlazamos
            ultimo = self.cabeza
            while ultimo.siguiente:
                ultimo = ultimo.siguiente
            ultimo.siguiente = nuevo_nodo

    # Metodo para mostrar la lista
    def mostrar(self):
        # creamos una variable temporal para recorrer la lista, que comience en la cabeza
        temp = self.cabeza
        while temp:
            # al final se mostrara: 10 -> 20 -> 30 -> 40 -> 50 -> 60 -> null
            print(temp.dato, end=" -> ")
            # avanzamos al siguiente nodo, reasignando la variable local como el siguiente
            # cuando el nodo es 'None', el bucle acabara
            temp = temp.siguiente
        print("None")

# Creamos una instancia de la lista y agregamos elementos
lista = ListaEnlazada()
lista.insertar_al_inicio(30)
lista.insertar_al_inicio(20)
lista.insertar_al_inicio(10)
lista.insertar_al_final(40)
lista.insertar_al_final(50)
lista.insertar_al_final(60)

print("La lista enlazada es:")
lista.mostrar()
