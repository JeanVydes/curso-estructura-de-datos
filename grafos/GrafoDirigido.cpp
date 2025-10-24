#include <iostream>
#include <iomanip>

const int V = 4;
int matriz[V][V];

void agregar_arista(int origen, int destino) {
    // Agrega una arista dirigida de origen → destino
    matriz[origen][destino] = 1;  // Solo en una dirección
}

void imprimir_matriz_con_bordes() {
    // Imprime la matriz como tabla con bordes
    std::cout << "+";
    for (int i = 0; i < V + 1; ++i) {
        std::cout << "---+";
    }
    std::cout << std::endl;

    std::cout << "|   |";
    for (int i = 0; i < V; ++i) {
        std::cout << " " << i << " |";
    }
    std::cout << std::endl;

    std::cout << "+";
    for (int i = 0; i < V + 1; ++i) {
        std::cout << "---+";
    }
    std::cout << std::endl;

    for (int i = 0; i < V; ++i) {
        std::cout << "| " << i << " |";
        for (int j = 0; j < V; ++j) {
            std::cout << " " << matriz[i][j] << " |";
        }
        std::cout << std::endl;

        std::cout << "+";
        for (int k = 0; k < V + 1; ++k) {
            std::cout << "---+";
        }
        std::cout << std::endl;
    }
}

void bfs(int inicio) {
    // BFS en grafo dirigido: sigue solo direcciones salientes
    bool visitado[V] = {false};
    int cola[V];
    int frente = 0;
    int fondo = 0;
    cola[fondo++] = inicio;
    visitado[inicio] = true;

    while (frente < fondo) {
        int actual = cola[frente++];
        std::cout << actual << " ";

        for (int vecino = 0; vecino < V; ++vecino) {
            if (matriz[actual][vecino] == 1 && !visitado[vecino]) {
                cola[fondo++] = vecino;
                visitado[vecino] = true;
            }
        }
    }
}

void dfs(int inicio) {
    // DFS en grafo dirigido: sigue solo direcciones salientes
    bool visitado[V] = {false};
    dfs_recursivo(inicio, visitado);
    std::cout << std::endl;
}

void dfs_recursivo(int actual, bool visitado[]) {
    visitado[actual] = true;
    std::cout << actual << " ";
    for (int vecino = 0; vecino < V; ++vecino) {
        if (matriz[actual][vecino] == 1 && !visitado[vecino]) {
            dfs_recursivo(vecino, visitado);
        }
    }
}

int main() {
    // Inicializar matriz
    for (int i = 0; i < V; ++i) {
        for (int j = 0; j < V; ++j) {
            matriz[i][j] = 0;
        }
    }

    // Agregar aristas dirigidas
    agregar_arista(0, 1);  // 0 → 1
    agregar_arista(0, 2);  // 0 → 2
    agregar_arista(1, 3);  // 1 → 3
    agregar_arista(2, 3);  // 2 → 3

    // Mostrar resultados
    std::cout << "Matriz de Adyacencia (representación del grafo dirigido):\n";
    imprimir_matriz_con_bordes();
    std::cout << "BFS desde 0: ";
    bfs(0);
    std::cout << "DFS desde 0: ";
    dfs(0);

    return 0;
}