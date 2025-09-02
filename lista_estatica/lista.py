# Crear una lista con 5 elementos
frutas = ["manzana", "banana", "cereza", "naranja", "uva"]

# Acceder al elemento 3 en la posicion 2
print("El tercer elemento es:", frutas[2])

# Recorrer la lista
print("Todas las frutas:")
for fruta in frutas:
    print(fruta)

# Las listas en Python son dinamicas, se puede anadir un elemento
# Python maneja la memoria de forma automatica
frutas.append("kiwi")
print("Lista despues de anadir 'kiwi':", frutas)
