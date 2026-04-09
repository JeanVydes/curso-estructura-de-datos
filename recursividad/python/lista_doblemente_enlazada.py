class Nodo:
    def __init__(self, dato):
        self.dato = dato
        self.anterior = None
        self.siguiente = None

class ListaDoblementeEnlazada:
    def __init__(self):
        self.cabeza = None

    def insertar_al_inicio(self, nuevo_dato):
        nuevo_nodo = Nodo(nuevo_dato)
        nuevo_nodo.siguiente = self.cabeza
        nuevo_nodo.anterior = None
        if self.cabeza is not None:
            self.cabeza.anterior = nuevo_nodo
        self.cabeza = nuevo_nodo

    def insertar_al_final(self, nuevo_dato):
        nuevo_nodo = Nodo(nuevo_dato)
        nuevo_nodo.siguiente = None
        
        if self.cabeza is None:
            nuevo_nodo.anterior = None
            self.cabeza = nuevo_nodo
            return
            
        ultimo = self.cabeza
        while ultimo.siguiente is not None:
            ultimo = ultimo.siguiente
            
        ultimo.siguiente = nuevo_nodo
        nuevo_nodo.anterior = ultimo

    def mostrar_lista_adelante(self):
        nodo = self.cabeza
        print("Lista Hacia Adelante: ", end="")
        while nodo is not None:
            print(f"{nodo.dato} <-> ", end="")
            nodo = nodo.siguiente
        print("NULL")

    def contar_recursivo(self, nodo):
        if nodo is None:
            return 0
        return 1 + self.contar_recursivo(nodo.siguiente)

    @staticmethod
    def buscar_recursivo(nodo, valor):
        if nodo is None:
            return None
        if nodo.dato == valor:
            return nodo
        return ListaDoblementeEnlazada.buscar_recursivo(nodo.siguiente, valor)

    def imprimir_adelante_recursivo(self, nodo):
        if nodo is None:
            print("NULL")
            return
        print(f"{nodo.dato} <-> ", end="")
        self.imprimir_adelante_recursivo(nodo.siguiente)

    def imprimir_atras_recursivo(self, nodo):
        if nodo is None:
            print("NULL <-> ", end="")
            return
        self.imprimir_atras_recursivo(nodo.siguiente)
        print(f"{nodo.dato} ", end="")

if __name__ == "__main__":
    lista = ListaDoblementeEnlazada()
    lista.insertar_al_inicio('A')
    lista.insertar_al_inicio('B')
    lista.insertar_al_inicio('D')
    lista.insertar_al_inicio('E')
    
    nodo_c = ListaDoblementeEnlazada.buscar_recursivo(lista.cabeza, 'C')
    if nodo_c is None:
        print("Nodo 'C' no encontrado. Insertando 'C' al final.")
        lista.insertar_al_final('C')
    else:
        print("Nodo 'C' ya existe en la lista.")
        
    print("\nImprimiendo hacia adelante usando forma recursiva:")
    lista.imprimir_adelante_recursivo(lista.cabeza)
    
    print("\nImprimiendo hacia atrás (en reversa) usando forma recursiva:")
    lista.imprimir_atras_recursivo(lista.cabeza)
    print()
