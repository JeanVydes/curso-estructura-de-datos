#include <iostream>
#include <climits>
#include <vector>
#include <algorithm>

const int V = 4;
int matriz[V][V];

void agregar_arista(int a, int b, int peso) {
    matriz[a][b] = peso;
    matriz[b][a] = peso;
}

void imprimir_matriz_con_bordes() {
    std::cout << "+";
    for (int i = 0; i < V + 1; ++i) {
        std::cout << "---+" ;
    }
    std::cout << std::endl;

    std::cout << "|   |";
    for (int i = 0; i < V; ++i) {
        std::cout << " " << i << " |";
    }
    std::cout << std::endl;

    std::cout << "+";
    for (int i = 0; i < V + 1; ++i) {
        std::cout << "---+" ;
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
            std::cout << "---+" ;
        }
        std::cout << std::endl;
    }
}

void bfs(int inicio) {
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
            if (matriz[actual][vecino] > 0 && !visitado[vecino]) {
                cola[fondo++] = vecino;
                visitado[vecino] = true;
            }
        }
    }
}

void dfs(int actual) {
    bool visitado[V] = {false};
    dfs_recursivo(actual, visitado);
    std::cout << std::endl;
}

void dfs_recursivo(int actual, bool visitado[]) {
    visitado[actual] = true;
    std::cout << actual << " ";
    for (int vecino = 0; vecino < V; ++vecino) {
        if (matriz[actual][vecino] > 0 && !visitado[vecino]) {
            dfs_recursivo(vecino, visitado);
        }
    }
}

bool tiene_ciclo() {
    bool visitado[V] = {false};
    for (int i = 0; i < V; ++i) {
        if (!visitado[i]) {
            if (tiene_ciclo_dfs(i, visitado, -1)) {
                return true;
            }
        }
    }
    return false;
}

bool tiene_ciclo_dfs(int actual, bool visitado[], int padre) {
    visitado[actual] = true;
    for (int vecino = 0; vecino < V; ++vecino) {
        if (matriz[actual][vecino] > 0) {
            if (!visitado[vecino]) {
                if (tiene_ciclo_dfs(vecino, visitado, actual)) {
                    return true;
                }
            } else if (vecino != padre) {
                return true;
            }
        }
    }
    return false;
}

int contar_componentes_conectadas() {
    bool visitado[V] = {false};
    int count = 0;
    for (int i = 0; i < V; ++i) {
        if (!visitado[i]) {
            dfs_recursivo(i, visitado);
            ++count;
        }
    }
    return count;
}

int distancia_mas_corta(int inicio, int fin) {
    if (inicio == fin) return 0;
    bool visitado[V] = {false};
    int distancia[V] = {0};
    int cola[V];
    int frente = 0, fondo = 0;
    cola[fondo++] = inicio;
    visitado[inicio] = true;
    distancia[inicio] = 0;
    while (frente < fondo) {
        int actual = cola[frente++];
        for (int vecino = 0; vecino < V; ++vecino) {
            if (matriz[actual][vecino] > 0 && !visitado[vecino]) {
                cola[fondo++] = vecino;
                visitado[vecino] = true;
                distancia[vecino] = distancia[actual] + 1;
                if (vecino == fin) return distancia[vecino];
            }
        }
    }
    return -1;
}

int grado_vertice(int vertice) {
    int grado = 0;
    for (int j = 0; j < V; ++j) {
        if (matriz[vertice][j] > 0) {
            ++grado;
        }
    }
    return grado;
}

bool es_bipartito() {
    int color[V];
    std::fill(color, color + V, -1);
    for (int i = 0; i < V; ++i) {
        if (color[i] == -1) {
            if (!es_bipartito_bfs(i, color)) return false;
        }
    }
    return true;
}

bool es_bipartito_bfs(int inicio, int color[]) {
    int cola[V];
    int frente = 0, fondo = 0;
    cola[fondo++] = inicio;
    color[inicio] = 0;
    while (frente < fondo) {
        int actual = cola[frente++];
        for (int vecino = 0; vecino < V; ++vecino) {
            if (matriz[actual][vecino] > 0) {
                if (color[vecino] == -1) {
                    color[vecino] = 1 - color[actual];
                    cola[fondo++] = vecino;
                } else if (color[vecino] == color[actual]) {
                    return false;
                }
            }
        }
    }
    return true;
}

bool es_arbol() {
    if (contar_componentes_conectadas() != 1) return false;
    if (tiene_ciclo()) return false;
    return true;
}

int dijkstra(int inicio, int fin) {
    int dist[V];
    bool visitado[V] = {false};
    std::fill(dist, dist + V, INT_MAX / 2);
    dist[inicio] = 0;
    for (int count = 0; count < V - 1; ++count) {
        int min_dist = INT_MAX / 2;
        int u = -1;
        for (int v = 0; v < V; ++v) {
            if (!visitado[v] && dist[v] < min_dist) {
                min_dist = dist[v];
                u = v;
            }
        }
        if (u == -1) break;
        visitado[u] = true;
        for (int v = 0; v < V; ++v) {
            if (!visitado[v] && matriz[u][v] > 0 && dist[u] + matriz[u][v] < dist[v]) {
                dist[v] = dist[u] + matriz[u][v];
            }
        }
    }
    return (dist[fin] == INT_MAX / 2) ? -1 : dist[fin];
}

struct Edge {
    int u, v, peso;
};

bool compare_edges(const Edge& a, const Edge& b) {
    return a.peso < b.peso;
}

int find(int parent[], int i) {
    if (parent[i] != i) {
        parent[i] = find(parent, parent[i]);
    }
    return parent[i];
}

void union_sets(int parent[], int x, int y) {
    parent[x] = y;
}

int kruskal() {
    std::vector<Edge> aristas;
    for (int i = 0; i < V; ++i) {
        for (int j = i + 1; j < V; ++j) {
            if (matriz[i][j] > 0) {
                aristas.push_back({i, j, matriz[i][j]});
            }
        }
    }
    std::sort(aristas.begin(), aristas.end(), compare_edges);

    int parent[V];
    for (int i = 0; i < V; ++i) parent[i] = i;

    int costo = 0;
    int aristas_usadas = 0;
    for (const auto& edge : aristas) {
        int root_u = find(parent, edge.u);
        int root_v = find(parent, edge.v);
        if (root_u != root_v) {
            union_sets(parent, root_u, root_v);
            costo += edge.peso;
            ++aristas_usadas;
            if (aristas_usadas == V - 1) break;
        }
    }
    return costo;
}

int main() {
    for (int i = 0; i < V; ++i) {
        for (int j = 0; j < V; ++j) {
            matriz[i][j] = 0;
        }
    }
    agregar_arista(0, 1, 4);
    agregar_arista(0, 2, 2);
    agregar_arista(1, 3, 1);
    agregar_arista(2, 3, 3);
    std::cout << "Matriz de Adyacencia (representación del grafo):" << std::endl;
    imprimir_matriz_con_bordes();
    std::cout << "BFS desde 0: ";
    bfs(0);
    std::cout << "\nDFS desde 0: ";
    dfs(0);
    std::cout << "\n--- Ejercicios Renumerados ---" << std::endl;
    std::cout << "Ejercicio 1: ¿Hay ciclo? " << tiene_ciclo() << std::endl;
    std::cout << "Ejercicio 2: Número de componentes conectadas: " << contar_componentes_conectadas() << std::endl;
    std::cout << "Ejercicio 3: Distancia más corta (en aristas) de 0 a 3: " << distancia_mas_corta(0, 3) << std::endl;
    std::cout << "Ejercicio 4: Grado del vértice 0: " << grado_vertice(0) << std::endl;
    std::cout << "Ejercicio 5: ¿Es bipartito? " << es_bipartito() << std::endl;
    std::cout << "Ejercicio 6: ¿Es un árbol? " << es_arbol() << std::endl;
    std::cout << "Ejercicio 7: Distancia ponderada más corta (Dijkstra) de 0 a 3: " << dijkstra(0, 3) << std::endl;
    std::cout << "Ejercicio 8: Costo del Árbol de Expansión Mínima (Kruskal): " << kruskal() << std::endl;
    return 0;
}