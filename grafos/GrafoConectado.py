# Grafo Conectado
# Un grafo conectado es uno donde hay un camino entre cualquier par de vértices (todo unido).
# Por qué es así: Representa sistemas integrados, como redes completas sin islas aisladas.
# Requisitos:
# - Al menos un camino entre todos los vértices.
# - En código: El ejemplo es conectado. BFS/DFS desde cualquier vértice visita todos.
# - Si no es conectado, BFS/DFS no visitan todo desde un inicio.

V = 4
matriz = [[0 for _ in range(V)] for _ in range(V)]

def agregar_arista(a, b):
    """Agrega arista no dirigida (bidireccional) para mantener conectividad."""
    matriz[a][b] = 1
    matriz[b][a] = 1

def imprimir_matriz_con_bordes():
    """Imprime la matriz de adyacencia como tabla con bordes."""
    print("+", end="")
    for _ in range(V + 1):
        print("---+", end="")
    print()

    print("|   |", end="")
    for i in range(V):
        print(f" {i} |", end="")
    print()

    print("+", end="")
    for _ in range(V + 1):
        print("---+", end="")
    print()

    for i in range(V):
        print(f"| {i} |", end="")
        for j in range(V):
            print(f" {matriz[i][j]} |", end="")
        print()

        print("+", end="")
        for _ in range(V + 1):
            print("---+", end="")
        print()

def bfs(inicio):
    """BFS en grafo conectado: desde cualquier vértice, visita todos."""
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
            if matriz[actual][vecino] == 1 and not visitado[vecino]:
                cola[fondo] = vecino
                fondo += 1
                visitado[vecino] = True

def dfs(inicio):
    """DFS en grafo conectado: cubre todo el grafo."""
    visitado = [False] * V
    dfs_recursivo(inicio, visitado)
    print()

def dfs_recursivo(actual, visitado):
    visitado[actual] = True
    print(actual, end=" ")
    for vecino in range(V):
        if matriz[actual][vecino] == 1 and not visitado[vecino]:
            dfs_recursivo(vecino, visitado)

# --- Ejecución principal ---
if __name__ == "__main__":
    # Inicializar matriz
    for i in range(V):
        for j in range(V):
            matriz[i][j] = 0

    # Agregar aristas para asegurar conectividad
    agregar_arista(0, 1)  # Conecta 0-1
    agregar_arista(0, 2)  # Conecta 0-2
    agregar_arista(1, 3)  # Conecta 1-3
    agregar_arista(2, 3)  # Conecta 2-3
    # Nota: Con estas 4 aristas, todo está conectado.

    # Mostrar resultados
    print("Matriz de Adyacencia (representación del grafo conectado):")
    imprimir_matriz_con_bordes()
    print("BFS desde 0: ", end="")
    bfs(0)
    print("DFS desde 0: ", end="")
    dfs(0)