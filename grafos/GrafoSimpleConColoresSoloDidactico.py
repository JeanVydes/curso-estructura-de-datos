class GrafoSimpleConColoresSoloDidactico:
    V = 4
    matriz = [[0 for _ in range(4)] for _ in range(4)]

    AZUL = "\033[34m"
    VERDE = "\033[32m"
    AMARILLO = "\033[33m"
    RESET = "\033[0m"

    @staticmethod
    def agregar_arista(a, b):
        GrafoSimpleConColoresSoloDidactico.matriz[a][b] = 1
        GrafoSimpleConColoresSoloDidactico.matriz[b][a] = 1

    @staticmethod
    def imprimir_matriz_con_colores():
        print(f"{GrafoSimpleConColoresSoloDidactico.AZUL}+", end="")
        for _ in range(GrafoSimpleConColoresSoloDidactico.V + 1):
            print("---+", end="")
        print(GrafoSimpleConColoresSoloDidactico.RESET)

        print(f"{GrafoSimpleConColoresSoloDidactico.AZUL}|   |{GrafoSimpleConColoresSoloDidactico.RESET}", end="")
        for i in range(GrafoSimpleConColoresSoloDidactico.V):
            print(f"{GrafoSimpleConColoresSoloDidactico.AMARILLO} {i} |{GrafoSimpleConColoresSoloDidactico.RESET}", end="")
        print()

        print(f"{GrafoSimpleConColoresSoloDidactico.AZUL}+", end="")
        for _ in range(GrafoSimpleConColoresSoloDidactico.V + 1):
            print("---+", end="")
        print(GrafoSimpleConColoresSoloDidactico.RESET)

        for i in range(GrafoSimpleConColoresSoloDidactico.V):
            print(f"{GrafoSimpleConColoresSoloDidactico.AZUL}| {GrafoSimpleConColoresSoloDidactico.AMARILLO}{i}{GrafoSimpleConColoresSoloDidactico.RESET}{GrafoSimpleConColoresSoloDidactico.AZUL} |{GrafoSimpleConColoresSoloDidactico.RESET}", end="")
            for j in range(GrafoSimpleConColoresSoloDidactico.V):
                if GrafoSimpleConColoresSoloDidactico.matriz[i][j] == 1:
                    print(f"{GrafoSimpleConColoresSoloDidactico.VERDE} {GrafoSimpleConColoresSoloDidactico.matriz[i][j]} {GrafoSimpleConColoresSoloDidactico.RESET}{GrafoSimpleConColoresSoloDidactico.AZUL}|{GrafoSimpleConColoresSoloDidactico.RESET}", end="")
                else:
                    print(f" {GrafoSimpleConColoresSoloDidactico.matriz[i][j]} {GrafoSimpleConColoresSoloDidactico.AZUL}|{GrafoSimpleConColoresSoloDidactico.RESET}", end="")
            print()

            print(f"{GrafoSimpleConColoresSoloDidactico.AZUL}+", end="")
            for _ in range(GrafoSimpleConColoresSoloDidactico.V + 1):
                print("---+", end="")
            print(GrafoSimpleConColoresSoloDidactico.RESET)

    @staticmethod
    def bfs(inicio):
        visitado = [False] * GrafoSimpleConColoresSoloDidactico.V
        cola = [0] * GrafoSimpleConColoresSoloDidactico.V
        frente, fondo = 0, 0

        cola[fondo] = inicio
        fondo += 1
        visitado[inicio] = True

        while frente < fondo:
            actual = cola[frente]
            frente += 1
            print(f"{actual} ", end="")

            for vecino in range(GrafoSimpleConColoresSoloDidactico.V):
                if GrafoSimpleConColoresSoloDidactico.matriz[actual][vecino] == 1 and not visitado[vecino]:
                    cola[fondo] = vecino
                    fondo += 1
                    visitado[vecino] = True

    @staticmethod
    def dfs_recursivo(actual, visitado):
        visitado[actual] = True
        print(f"{actual} ", end="")
        for vecino in range(GrafoSimpleConColoresSoloDidactico.V):
            if GrafoSimpleConColoresSoloDidactico.matriz[actual][vecino] == 1 and not visitado[vecino]:
                GrafoSimpleConColoresSoloDidactico.dfs_recursivo(vecino, visitado)

    @staticmethod
    def dfs(actual):
        visitado = [False] * GrafoSimpleConColoresSoloDidactico.V
        GrafoSimpleConColoresSoloDidactico.dfs_recursivo(actual, visitado)
        print()

    @staticmethod
    def main():
        for i in range(GrafoSimpleConColoresSoloDidactico.V):
            for j in range(GrafoSimpleConColoresSoloDidactico.V):
                GrafoSimpleConColoresSoloDidactico.matriz[i][j] = 0

        GrafoSimpleConColoresSoloDidactico.agregar_arista(0, 1)
        GrafoSimpleConColoresSoloDidactico.agregar_arista(0, 2)
        GrafoSimpleConColoresSoloDidactico.agregar_arista(1, 3)
        GrafoSimpleConColoresSoloDidactico.agregar_arista(2, 3)

        print(f"{GrafoSimpleConColoresSoloDidactico.AMARILLO}=== MATRIZ DE ADYACENCIA (Grafo Visual) ==={GrafoSimpleConColoresSoloDidactico.RESET}")
        GrafoSimpleConColoresSoloDidactico.imprimir_matriz_con_colores()

        print(f"\n{GrafoSimpleConColoresSoloDidactico.AMARILLO}BFS desde 0: {GrafoSimpleConColoresSoloDidactico.RESET}", end="")
        GrafoSimpleConColoresSoloDidactico.bfs(0)

        print(f"\n{GrafoSimpleConColoresSoloDidactico.AMARILLO}DFS desde 0: {GrafoSimpleConColoresSoloDidactico.RESET}", end="")
        GrafoSimpleConColoresSoloDidactico.dfs(0)
        print()

if __name__ == "__main__":
    GrafoSimpleConColoresSoloDidactico.main()
