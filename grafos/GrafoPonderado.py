# Grafo Ponderado
# Un grafo ponderado es un grafo donde cada arista tiene un "peso" (número), representando costo, distancia o tiempo.
# Por qué es así: Permite modelar escenarios reales con valores cuantitativos, como distancias en mapas (km) o costos en redes (dinero).
# Requisitos:
# - Matriz usa enteros >0 para pesos (en vez de 1/0).
# - 0 significa no arista.
# - BFS/DFS se adaptan (ignoran pesos, solo conectividad).
# - En código: agregar_arista toma peso como parámetro.

V = 4
matriz = [[0 for _ in range(V)] for _ in range(V)]

def agregar_arista(a, b, peso):
    """Agrega una arista ponderada no dirigida entre a y b."""
    matriz[a][b] = peso
    matriz[b][a] = peso

def imprimir_matriz_con_bordes():
    """Imprime la matriz de adyacencia con bordes (como tabla)."""
    print("+", end="")
    for _ in range(V + 1):
        print("----+ ", end="")
    print()

    print("|    |", end="")
    for i in range(V):
        print(f" {i} |", end="")
    print()

    print("+", end="")
    for _ in range(V + 1):
        print("----+ ", end="")
    print()

    for i in range(V):
        print(f"| {i}  |", end="")
        for j in range(V):
            print(f" {matriz[i][j]:>2} |", end="")
        print()

        print("+", end="")
        for _ in range(V + 1):
            print("----+ ", end="")
        print()

def bfs(inicio):
    """BFS adaptado para grafo ponderado (ignora pesos, solo conectividad)."""
    visitado = [False] * V
    cola = [0] * V
    frente = 0
    fondo = 0
    cola[fondo] = inicio
    fondo += 1
    visitado[inicio] = True

    while frente < fondo:
        actual = cola[frente]
        frente += 1
        print(actual, end=" ")

        for vecino in range(V):
            if matriz[actual][vecino] > 0 and not visitado[vecino]:
                cola[fondo] = vecino
                fondo += 1
                visitado[vecino] = True

def dfs(inicio):
    """DFS adaptado para grafo ponderado (ignora pesos)."""
    visitado = [False] * V
    dfs_recursivo(inicio, visitado)
    print()

def dfs_recursivo(actual, visitado):
    visitado[actual] = True
    print(actual, end=" ")
    for vecino in range(V):
        if matriz[actual][vecino] > 0 and not visitado[vecino]:
            dfs_recursivo(vecino, visitado)

# --- Ejecución principal ---
if __name__ == "__main__":
    # Inicializar matriz
    for i in range(V):
        for j in range(V):
            matriz[i][j] = 0

    # Agregar aristas ponderadas
    agregar_arista(0, 1, 4)  # 0 —4— 1
    agregar_arista(0, 2, 2)  # 0 —2— 2
    agregar_arista(1, 3, 1)  # 1 —1— 3
    agregar_arista(2, 3, 3)  # 2 —3— 3

    # Mostrar resultados
    print("Matriz de Adyacencia (representación del grafo ponderado):")
    imprimir_matriz_con_bordes()
    print("BFS desde 0: ", end="")
    bfs(0)
    print("DFS desde 0: ", end="")
    dfs(0)