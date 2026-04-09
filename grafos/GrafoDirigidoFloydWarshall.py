import sys

class GrafoDirigidoFloydWarshall:
    INF = sys.maxsize
    N = 3

    @staticmethod
    def imprimir_matriz_costos(matriz):
        for i in range(GrafoDirigidoFloydWarshall.N):
            for j in range(GrafoDirigidoFloydWarshall.N):
                if matriz[i][j] == GrafoDirigidoFloydWarshall.INF:
                    print(f"{'INF':>5}", end="")
                else:
                    print(f"{matriz[i][j]:>5}", end="")
            print()

    @staticmethod
    def imprimir_matriz_bool(matriz):
        for i in range(GrafoDirigidoFloydWarshall.N):
            for j in range(GrafoDirigidoFloydWarshall.N):
                print(f"{'T' if matriz[i][j] else 'F':>5}", end="")
            print()

    @staticmethod
    def main():
        matriz_floyd = [
            [0, 3, GrafoDirigidoFloydWarshall.INF],
            [GrafoDirigidoFloydWarshall.INF, 0, 2],
            [1, 4, 0]
        ]
        
        print("==================================================================")
        print("--- ALGORITMO DE FLOYD-WARSHALL RECURSIVO (Caminos Mínimos) ---")
        print("==================================================================")
        print("\nMATRIZ INICIAL (Costos del Dígrafo de 3 Nodos):")
        GrafoDirigidoFloydWarshall.imprimir_matriz_costos(matriz_floyd)
        
        FloydWarshallExplicado.calcular(matriz_floyd, GrafoDirigidoFloydWarshall.N, GrafoDirigidoFloydWarshall.INF)
        
        print("\nMATRIZ FINAL (Distancias Mínimas después de probar todas las escalas):")
        GrafoDirigidoFloydWarshall.imprimir_matriz_costos(matriz_floyd)
        print("------------------------------------------------------------------")

        matriz_warshall = [
            [False, True, False],
            [False, False, True],
            [True, False, False]
        ]
        
        print("\n==================================================================")
        print("--- ALGORITMO DE WARSHALL RECURSIVO (Cierre Transitivo) ---")
        print("==================================================================")
        print("\nMATRIZ INICIAL (Conexiones Directas del Dígrafo de 3 Nodos):")
        GrafoDirigidoFloydWarshall.imprimir_matriz_bool(matriz_warshall)
        
        WarshallExplicado.calcular(matriz_warshall, GrafoDirigidoFloydWarshall.N)
        
        print("\nMATRIZ FINAL (Cierre Transitivo: ¿Hay un camino de i a j?):")
        GrafoDirigidoFloydWarshall.imprimir_matriz_bool(matriz_warshall)
        print("------------------------------------------------------------------")

class FloydWarshallExplicado:
    dist = []
    N_NODES = 0
    INF_VALUE = 0

    @staticmethod
    def calcular(matriz_inicial, n, inf):
        FloydWarshallExplicado.N_NODES = n
        FloydWarshallExplicado.INF_VALUE = inf
        FloydWarshallExplicado.dist = matriz_inicial
        FloydWarshallExplicado.floyd_recursivo(0)

    @staticmethod
    def floyd_recursivo(k):
        if k == FloydWarshallExplicado.N_NODES:
            return

        print(f"\n--- Procesando k = {k} (La Ciudad {k} es la escala obligatoria) ---")

        for i in range(FloydWarshallExplicado.N_NODES):
            for j in range(FloydWarshallExplicado.N_NODES):
                if FloydWarshallExplicado.dist[i][k] != FloydWarshallExplicado.INF_VALUE and \
                   FloydWarshallExplicado.dist[k][j] != FloydWarshallExplicado.INF_VALUE:
                    camino_actual = FloydWarshallExplicado.dist[i][j]
                    camino_nuevo = FloydWarshallExplicado.dist[i][k] + FloydWarshallExplicado.dist[k][j]
                    
                    if camino_nuevo < camino_actual:
                        FloydWarshallExplicado.dist[i][j] = camino_nuevo
                        print(f"  Mejora: de {i} a {j}. Antes: {camino_actual}, Ahora (vía {k}): {camino_nuevo}")
        
        FloydWarshallExplicado.floyd_recursivo(k + 1)

class WarshallExplicado:
    cierre = []
    N_NODES = 0

    @staticmethod
    def calcular(matriz_inicial, n):
        WarshallExplicado.N_NODES = n
        WarshallExplicado.cierre = matriz_inicial

        for i in range(WarshallExplicado.N_NODES):
            WarshallExplicado.cierre[i][i] = True

        WarshallExplicado.warshall_recursivo(0)

    @staticmethod
    def warshall_recursivo(k):
        if k == WarshallExplicado.N_NODES:
            return

        print(f"\n--- Procesando k = {k} (La Ciudad {k} es el puente de conexión) ---")

        for i in range(WarshallExplicado.N_NODES):
            for j in range(WarshallExplicado.N_NODES):
                hay_nuevo_enlace_via_k = WarshallExplicado.cierre[i][k] and WarshallExplicado.cierre[k][j]

                if hay_nuevo_enlace_via_k and not WarshallExplicado.cierre[i][j]:
                    WarshallExplicado.cierre[i][j] = True
                    print(f"  Nuevo Enlace: de {i} a {j} (Vía Puente {k})")

        WarshallExplicado.warshall_recursivo(k + 1)

if __name__ == "__main__":
    GrafoDirigidoFloydWarshall.main()
