import sys

V = 4
matriz = [[0 for _ in range(V)] for _ in range(V)]

def agregar_arista(a, b, peso):
    matriz[a][b] = peso
    matriz[b][a] = peso

def imprimir_matriz_con_bordes():
    print("+", end="")
    for i in range(V + 1):
        print("---+", end="")
    print()

    print("|   |", end="")
    for i in range(V):
        print(f" {i} |", end="")
    print()

    print("+", end="")
    for i in range(V + 1):
        print("---+", end="")
    print()

    for i in range(V):
        print(f"| {i} |", end="")
        for j in range(V):
            print(f" {matriz[i][j]} |", end="")
        print()

        print("+", end="")
        for k in range(V + 1):
            print("---+", end="")
        print()

def bfs(inicio):
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

def dfs(actual):
    visitado = [False] * V
    dfs_recursivo(actual, visitado)
    print()

def dfs_recursivo(actual, visitado):
    visitado[actual] = True
    print(actual, end=" ")
    for vecino in range(V):
        if matriz[actual][vecino] > 0 and not visitado[vecino]:
            dfs_recursivo(vecino, visitado)

def tiene_ciclo():
    visitado = [False] * V
    for i in range(V):
        if not visitado[i]:
            if tiene_ciclo_dfs(i, visitado, -1):
                return True
    return False

def tiene_ciclo_dfs(actual, visitado, padre):
    visitado[actual] = True
    for vecino in range(V):
        if matriz[actual][vecino] > 0:
            if not visitado[vecino]:
                if tiene_ciclo_dfs(vecino, visitado, actual):
                    return True
            elif vecino != padre:
                return True
    return False

def contar_componentes_conectadas():
    visitado = [False] * V
    count = 0
    for i in range(V):
        if not visitado[i]:
            dfs_recursivo(i, visitado)
            count += 1
    return count

def distancia_mas_corta(inicio, fin):
    if inicio == fin:
        return 0
    visitado = [False] * V
    distancia = [0] * V
    cola = [0] * V
    frente = 0
    fondo = 0
    cola[fondo] = inicio
    fondo += 1
    visitado[inicio] = True
    distancia[inicio] = 0
    while frente < fondo:
        actual = cola[frente]
        frente += 1
        for vecino in range(V):
            if matriz[actual][vecino] > 0 and not visitado[vecino]:
                cola[fondo] = vecino
                fondo += 1
                visitado[vecino] = True
                distancia[vecino] = distancia[actual] + 1
                if vecino == fin:
                    return distancia[vecino]
    return -1

def grado_vertice(vertice):
    grado = 0
    for j in range(V):
        if matriz[vertice][j] > 0:
            grado += 1
    return grado

def es_bipartito():
    color = [-1] * V
    for i in range(V):
        if color[i] == -1:
            if not es_bipartito_bfs(i, color):
                return False
    return True

def es_bipartito_bfs(inicio, color):
    cola = [0] * V
    frente = 0
    fondo = 0
    cola[fondo] = inicio
    fondo += 1
    color[inicio] = 0
    while frente < fondo:
        actual = cola[frente]
        frente += 1
        for vecino in range(V):
            if matriz[actual][vecino] > 0:
                if color[vecino] == -1:
                    color[vecino] = 1 - color[actual]
                    cola[fondo] = vecino
                    fondo += 1
                elif color[vecino] == color[actual]:
                    return False
    return True

def es_arbol():
    if contar_componentes_conectadas() != 1:
        return False
    if tiene_ciclo():
        return False
    return True

def dijkstra(inicio, fin):
    dist = [sys.maxsize // 2] * V
    visitado = [False] * V
    dist[inicio] = 0
    for count in range(V - 1):
        min_dist = sys.maxsize // 2
        u = -1
        for v in range(V):
            if not visitado[v] and dist[v] < min_dist:
                min_dist = dist[v]
                u = v
        if u == -1:
            break
        visitado[u] = True
        for v in range(V):
            if not visitado[v] and matriz[u][v] > 0 and dist[u] + matriz[u][v] < dist[v]:
                dist[v] = dist[u] + matriz[u][v]
    return -1 if dist[fin] == sys.maxsize // 2 else dist[fin]

def kruskal():
    aristas = []
    for i in range(V):
        for j in range(i + 1, V):
            if matriz[i][j] > 0:
                aristas.append((i, j, matriz[i][j]))
    aristas.sort(key=lambda x: x[2])
    parent = list(range(V))
    def find(p, i):
        if p[i] != i:
            p[i] = find(p, p[i])
        return p[i]
    def union(p, x, y):
        px = find(p, x)
        py = find(p, y)
        p[px] = py
    costo = 0
    aristas_usadas = 0
    for u, v, peso in aristas:
        root_u = find(parent, u)
        root_v = find(parent, v)
        if root_u != root_v:
            union(parent, root_u, root_v)
            costo += peso
            aristas_usadas += 1
            if aristas_usadas == V - 1:
                break
    return costo

# Ejecución principal
agregar_arista(0, 1, 4)
agregar_arista(0, 2, 2)
agregar_arista(1, 3, 1)
agregar_arista(2, 3, 3)
print("Matriz de Adyacencia (representación del grafo):")
imprimir_matriz_con_bordes()
print("BFS desde 0: ", end="")
bfs(0)
print("\nDFS desde 0: ", end="")
dfs(0)
print("\n--- Ejercicios Renumerados ---")
print("Ejercicio 1: ¿Hay ciclo? ", tiene_ciclo())
print("Ejercicio 2: Número de componentes conectadas: ", contar_componentes_conectadas())
print("Ejercicio 3: Distancia más corta (en aristas) de 0 a 3: ", distancia_mas_corta(0, 3))
print("Ejercicio 4: Grado del vértice 0: ", grado_vertice(0))
print("Ejercicio 5: ¿Es bipartito? ", es_bipartito())
print("Ejercicio 6: ¿Es un árbol? ", es_arbol())
print("Ejercicio 7: Distancia ponderada más corta (Dijkstra) de 0 a 3: ", dijkstra(0, 3))
print("Ejercicio 8: Costo del Árbol de Expansión Mínima (Kruskal): ", kruskal())