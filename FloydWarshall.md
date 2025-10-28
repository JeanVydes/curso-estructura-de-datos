# 🗺️ Algoritmos de Caminos en Dígrafos

Estos algoritmos se basan en el principio de **Programación Dinámica**, resolviendo un problema grande (encontrar todos los caminos) dividiéndolo en subproblemas sucesivos, donde cada solución depende de la anterior. La clave es el rol del nodo intermedio, **$k$**, que en nuestro código está manejado por la **recursión**.

---

## 1. Algoritmo de Floyd-Warshall Recursivo

Este algoritmo resuelve el problema de "Todos los Pares de Caminos Mínimos" en un dígrafo ponderado.

### Propósito: ¿Para qué sirve?

* **Finalidad:** Encontrar la **distancia más corta** (costo o peso mínimo) entre **cada par** de nodos ($i$ a $j$) en un grafo dirigido.
* **Aplicación:** Útil para sistemas de enrutamiento GPS, análisis de redes de transporte, o encontrar la ruta óptima en cualquier red con costos.

### Concepto Clave: El Principio de Optimalidad

La solución se construye gradualmente. En cada paso de la recursión ($k$), se comprueba si el camino más corto de $i$ a $j$ pasa por el nuevo nodo intermedio $k$.

$$\text{dist}[i][j] = \min(\text{dist}[i][j], \mathbf{\text{dist}[i][k] + \text{dist}[k][j]})$$

### Proceso Paso a Paso (La Recursión de $k$)

| Paso (Recursivo) | Rol de $k$ (La Escala) | Función y Lógica |
| :---: | :---: | :---: |
| **Paso Inicial** | $k=0$ | El algoritmo prueba a usar la Ciudad **0** como escala para todos los viajes $(i, j)$. Los caminos son: $i \rightarrow 0 \rightarrow j$. |
| **Paso $k$** | $k \rightarrow k+1$ | Se llama recursivamente a la función. Ahora, se garantiza que los caminos de $i$ a $j$ son óptimos usando escalas en $\{0, \dots, k-1\}$. Se busca si el nuevo nodo $\mathbf{k}$ mejora la distancia. |
| **Parada** | $k = N$ (ej. 3) | La recursión se detiene. Hemos utilizado todas las ciudades $\{0, 1, \dots, N-1\}$ como escalas. |

### ✅ Resultado Esperado

* **Matriz de Distancias Mínimas:** Una matriz final donde cada celda $\text{dist}[i][j]$ contiene el **costo mínimo absoluto** para ir del nodo $i$ al nodo $j$.
* **Detección de Ciclos Negativos:** Si la diagonal principal de la matriz final contiene valores negativos, indica la presencia de un ciclo de costo negativo, lo cual es relevante para la programación dinámica.

---

## 2. Algoritmo de Warshall Recursivo (Cierre Transitivo)

Este algoritmo es una versión booleana de Floyd-Warshall; solo se preocupa por la existencia de un camino.

### Propósito: ¿Para qué sirve?

* **Finalidad:** Determinar el **cierre transitivo** del dígrafo. Es decir, responder la pregunta: "¿Existe al menos **un camino** (sin importar la longitud ni el costo) de $i$ a $j$?" (La respuesta es True o False).
* **Aplicación:** Útil para saber si una tarea depende de otra, para verificar la conectividad de una red, o para validar la accesibilidad en un sistema.

### Concepto Clave: El Puente Lógico

En lugar de sumar costos, se usan operadores lógicos para determinar si un nuevo nodo $k$ crea un camino donde antes no existía.

$$\text{cierre}[i][j] = \text{cierre}[i][j] \vee (\mathbf{\text{cierre}[i][k] \wedge \text{cierre}[k][j]})$$

### Proceso Paso a Paso (La Recursión de $k$)

| Paso (Recursivo) | Rol de $k$ (El Puente) | Función y Lógica |
| :---: | :---: | :---: |
| **Paso Inicial** | $k=0$ | Se inicializa la matriz incluyendo las conexiones directas y la diagonal (todo nodo se conecta a sí mismo). Se busca si el Nodo **0** sirve como puente entre otros nodos ($i \rightarrow 0 \rightarrow j$). |
| **Paso $k$** | $k \rightarrow k+1$ | La recursión continúa. Se verifica si el nuevo nodo $\mathbf{k}$ completa un camino, es decir, si hay conexión de $i$ a $k$ **Y** de $k$ a $j$. Si es así, se establece $\text{cierre}[i][j] = \text{True}$. |
| **Parada** | $k = N$ (ej. 3) | La recursión se detiene. Se ha probado cada nodo como puente, y la matriz final contiene el estado de conectividad definitivo del dígrafo. |

### ✅ Resultado Esperado

* **Matriz Booleana (Cierre Transitivo):** Una matriz final donde cada celda $\text{cierre}[i][j]$ es:
    * **True (T):** Si existe un camino (directo o indirecto) del nodo $i$ al nodo $j$.
    * **False (F):** Si el nodo $j$ es inalcanzable desde el nodo $i$.