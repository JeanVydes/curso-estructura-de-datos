#include <iostream>
#include <vector>
#include <iomanip>

using namespace std;

const int INF = 1e9; // representing infinity
const int N = 3;

class FloydWarshallExplicado {
private:
    static vector<vector<int>> dist;
    static int N_NODES;
    static int INF_VALUE;

    static void floydRecursivo(int k) {
        if (k == N_NODES) {
            return;
        }

        cout << "\n--- Procesando k = " << k << " (La Ciudad " << k << " es la escala obligatoria) ---" << endl;

        for (int i = 0; i < N_NODES; i++) {
            for (int j = 0; j < N_NODES; j++) {
                if (dist[i][k] != INF_VALUE && dist[k][j] != INF_VALUE) {
                    int caminoActual = dist[i][j];
                    int caminoNuevo = dist[i][k] + dist[k][j];

                    if (caminoNuevo < caminoActual) {
                        dist[i][j] = caminoNuevo;
                        cout << "  Mejora: de " << i << " a " << j << ". Antes: " << caminoActual
                             << ", Ahora (via " << k << "): " << caminoNuevo << endl;
                    }
                }
            }
        }
        floydRecursivo(k + 1);
    }

public:
    static void calcular(vector<vector<int>>& matrizInicial, int n, int inf) {
        N_NODES = n;
        INF_VALUE = inf;
        dist = matrizInicial;
        floydRecursivo(0);
        // update original
        matrizInicial = dist;
    }
};

vector<vector<int>> FloydWarshallExplicado::dist;
int FloydWarshallExplicado::N_NODES = 0;
int FloydWarshallExplicado::INF_VALUE = 0;

class WarshallExplicado {
private:
    static vector<vector<bool>> cierre;
    static int N_NODES;

    static void warshallRecursivo(int k) {
        if (k == N_NODES) {
            return;
        }

        cout << "\n--- Procesando k = " << k << " (La Ciudad " << k << " es el puente de conexion) ---" << endl;

        for (int i = 0; i < N_NODES; i++) {
            for (int j = 0; j < N_NODES; j++) {
                bool hayNuevoEnlaceViaK = cierre[i][k] && cierre[k][j];
                if (hayNuevoEnlaceViaK && !cierre[i][j]) {
                    cierre[i][j] = true;
                    cout << "  Nuevo Enlace: de " << i << " a " << j << " (Via Puente " << k << ")" << endl;
                }
            }
        }
        warshallRecursivo(k + 1);
    }

public:
    static void calcular(vector<vector<bool>>& matrizInicial, int n) {
        N_NODES = n;
        cierre = matrizInicial;
        for (int i = 0; i < N_NODES; i++) {
            cierre[i][i] = true;
        }
        warshallRecursivo(0);
        matrizInicial = cierre;
    }
};

vector<vector<bool>> WarshallExplicado::cierre;
int WarshallExplicado::N_NODES = 0;


void imprimirMatriz(const vector<vector<int>>& matriz) {
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (matriz[i][j] == INF) {
                cout << setw(5) << "INF";
            } else {
                cout << setw(5) << matriz[i][j];
            }
        }
        cout << endl;
    }
}

void imprimirMatrizBool(const vector<vector<bool>>& matriz) {
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cout << setw(5) << (matriz[i][j] ? "T" : "F");
        }
        cout << endl;
    }
}

int main() {
    vector<vector<int>> matrizFloyd = {
        {0, 3, INF},
        {INF, 0, 2},
        {1, 4, 0}
    };

    cout << "==================================================================" << endl;
    cout << "--- ALGORITMO DE FLOYD-WARSHALL RECURSIVO (Caminos Minimos) ---" << endl;
    cout << "==================================================================" << endl;
    cout << "\nMATRIZ INICIAL (Costos del Digrafo de 3 Nodos):" << endl;
    imprimirMatriz(matrizFloyd);

    FloydWarshallExplicado::calcular(matrizFloyd, N, INF);

    cout << "\nMATRIZ FINAL (Distancias Minimas despues de probar todas las escalas):" << endl;
    imprimirMatriz(matrizFloyd);
    cout << "------------------------------------------------------------------" << endl;

    vector<vector<bool>> matrizWarshall = {
        {false, true, false},
        {false, false, true},
        {true, false, false}
    };

    cout << "\n==================================================================" << endl;
    cout << "--- ALGORITMO DE WARSHALL RECURSIVO (Cierre Transitivo) ---" << endl;
    cout << "==================================================================" << endl;
    cout << "\nMATRIZ INICIAL (Conexiones Directas del Digrafo de 3 Nodos):" << endl;
    imprimirMatrizBool(matrizWarshall);

    WarshallExplicado::calcular(matrizWarshall, N);

    cout << "\nMATRIZ FINAL (Cierre Transitivo: ¿Hay un camino de i a j?):" << endl;
    imprimirMatrizBool(matrizWarshall);
    cout << "------------------------------------------------------------------" << endl;

    return 0;
}
