# Grafo Dirigido
# Un grafo dirigido (o dígrafo) es un grafo donde las aristas tienen dirección, como flechas.
# Esto significa que una conexión de A a B no implica automáticamente B a A.
# Por qué es así: Modela relaciones unidireccionales, como enlaces web, dependencias de tareas o flujo en calles de un solo sentido.
# Requisitos:
# - Aristas no simétricas: matriz[a][b] = 1 pero NO necesariamente matriz[b][a] = 1.
# - Puede tener ciclos o ser acíclico (DAG).
# - En código: agregar_arista solo pone 1 en una dirección.
# - BFS y DFS siguen solo direcciones salientes.

V = 4
matriz = [[0 for _ in range(V)] for _ in range(V)]

def agregar_arista(origen, destino):
    """Agrega una arista dirigida de origen → destino."""
    matriz[origen][destino] = 1  # Solo en una dirección

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
    """BFS en grafo dirigido: sigue solo direcciones salientes."""
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
    """DFS en grafo dirigido: sigue solo direcciones salientes."""
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

    # Agregar aristas dirigidas
    agregar_arista(0, 1)  # 0 → 1
    agregar_arista(0, 2)  # 0 → 2
    agregar_arista(1, 3)  # 1 → 3
    agregar_arista(2, 3)  # 2 → 3

    # Mostrar resultados
    print("Matriz de Adyacencia (representación del grafo dirigido):")
    imprimir_matriz_con_bordes()
    print("BFS desde 0: ", end="")
    bfs(0)
    print("DFS desde 0: ", end="")
    dfs(0)