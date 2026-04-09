#include <iostream>
#include <vector>

using namespace std;

class GrafoSimpleConColoresSoloDidactico {
private:
    static const int V = 4;
    static int matriz[V][V];

    static const string AZUL;
    static const string VERDE;
    static const string AMARILLO;
    static const string RESET;

public:
    static void agregarArista(int a, int b) {
        matriz[a][b] = 1;
        matriz[b][a] = 1;
    }

    static void imprimirMatrizConColores() {
        cout << AZUL << "+";
        for (int i = 0; i < V + 1; i++) cout << "---+";
        cout << RESET << endl;

        cout << AZUL << "|   |" << RESET;
        for (int i = 0; i < V; i++) cout << AMARILLO << " " << i << " |" << RESET;
        cout << endl;

        cout << AZUL << "+";
        for (int i = 0; i < V + 1; i++) cout << "---+";
        cout << RESET << endl;

        for (int i = 0; i < V; i++) {
            cout << AZUL << "| " << AMARILLO << i << RESET << AZUL << " |" << RESET;
            for (int j = 0; j < V; j++) {
                if (matriz[i][j] == 1) {
                    cout << VERDE << " " << matriz[i][j] << " " << RESET << AZUL << "|" << RESET;
                } else {
                    cout << " " << matriz[i][j] << " " << AZUL << "|" << RESET;
                }
            }
            cout << endl;

            cout << AZUL << "+";
            for (int k = 0; k < V + 1; k++) cout << "---+";
            cout << RESET << endl;
        }
    }

    static void bfs(int inicio) {
        vector<bool> visitado(V, false);
        vector<int> cola(V, 0);
        int frente = 0, fondo = 0;

        cola[fondo++] = inicio;
        visitado[inicio] = true;

        while (frente < fondo) {
            int actual = cola[frente++];
            cout << actual << " ";

            for (int vecino = 0; vecino < V; vecino++) {
                if (matriz[actual][vecino] == 1 && !visitado[vecino]) {
                    cola[fondo++] = vecino;
                    visitado[vecino] = true;
                }
            }
        }
    }

    static void dfsRecursivo(int actual, vector<bool>& visitado) {
        visitado[actual] = true;
        cout << actual << " ";

        for (int vecino = 0; vecino < V; vecino++) {
            if (matriz[actual][vecino] == 1 && !visitado[vecino]) {
                dfsRecursivo(vecino, visitado);
            }
        }
    }

    static void dfs(int actual) {
        vector<bool> visitado(V, false);
        dfsRecursivo(actual, visitado);
        cout << endl;
    }

    static void main() {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) matriz[i][j] = 0;
        }

        agregarArista(0, 1);
        agregarArista(0, 2);
        agregarArista(1, 3);
        agregarArista(2, 3);

        cout << AMARILLO << "=== MATRIZ DE ADYACENCIA (Grafo Visual) ===" << RESET << endl;
        imprimirMatrizConColores();

        cout << "\n" << AMARILLO << "BFS desde 0: " << RESET;
        bfs(0);

        cout << "\n" << AMARILLO << "DFS desde 0: " << RESET;
        dfs(0);
        cout << endl;
    }
};

int GrafoSimpleConColoresSoloDidactico::matriz[V][V] = {0};
const string GrafoSimpleConColoresSoloDidactico::AZUL = "\033[34m";
const string GrafoSimpleConColoresSoloDidactico::VERDE = "\033[32m";
const string GrafoSimpleConColoresSoloDidactico::AMARILLO = "\033[33m";
const string GrafoSimpleConColoresSoloDidactico::RESET = "\033[0m";

int main() {
    GrafoSimpleConColoresSoloDidactico::main();
    return 0;
}
