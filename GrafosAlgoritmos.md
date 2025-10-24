## Algoritmos Esenciales para la Exploración de Grafos

Los algoritmos son el cerebro del grafo. Nos permiten recorrer la red (búsquedas), calcular propiedades (grado, distancia) o clasificar su estructura (ciclos, bipartición). Utilizaremos un grafo no dirigido de **$V=4$** vértices (0, 1, 2, 3) y la **Matriz de Adyacencia** como representación en memoria.

### Grafo de Ejemplo (No Dirigido)

|             | **0** | **1** | **2** | **3** |
| ----------- | ----------- | ----------- | ----------- | ----------- |
| **0** | 0           | 1           | 1           | 0           |
| **1** | 1           | 0           | 0           | 1           |
| **2** | 1           | 0           | 0           | 1           |
| **3** | 0           | 1           | 1           | 0           |

**Dibujo ASCII:**

**Code snippet**

```
       (0)
      /   \
     /     \
    (1)----- (2)
     \     /
      \   /
       (3)
```

---

### 1. BFS (Búsqueda en Anchura)

El BFS explora el grafo  **nivel por nivel** , como una onda expansiva. Garantiza encontrar el camino más corto en términos de número de aristas.

* **Herramienta Clave** : Una **Cola** (FIFO) para priorizar la exploración.

#### Simulación (Empezando en el Vértice  **0** )

1. **Inicio** :

* Cola: `[0]`
* Visitados: `{0}`
* Resultado:

1. **Paso 1** : Sacamos el  **0** . Buscamos sus vecinos NO visitados (1, 2).

* Cola: `[1, 2]`
* Visitados: `{0, 1, 2}`
* Resultado: `0 `

1. **Paso 2** : Sacamos el  **1** . Buscamos sus vecinos NO visitados (0 ya visitado,  **3** ).

* Cola: `[2, 3]`
* Visitados: `{0, 1, 2, 3}`
* Resultado: `0, 1 `

1. **Paso 3** : Sacamos el  **2** . Buscamos sus vecinos NO visitados (0 ya visitado, 3 ya visitado).

* Cola: `[3]`
* Visitados: `{0, 1, 2, 3}`
* Resultado: `0, 1, 2 `

1. **Paso 4** : Sacamos el  **3** . No tiene vecinos NO visitados.

* Cola: `[]`
* Visitados: `{0, 1, 2, 3}`
* Resultado: `0, 1, 2, 3 `

 **Recorrido Final (BFS)** : **$0 \rightarrow 1 \rightarrow 2 \rightarrow 3$** (o **$0 \rightarrow 2 \rightarrow 1 \rightarrow 3$**)

---

### 2. DFS (Búsqueda en Profundidad)

El DFS explora el grafo yendo tan **profundo** como pueda por un camino antes de retroceder.

* **Herramienta Clave** : La **Recursión** (o una Pila, LIFO) para hacer el  *backtracking* .

#### Simulación (Empezando en el Vértice  **0** )

1. **DFS(0)** : Marcamos **0** como visitado.

* Resultado: `0 `

1. Buscamos el primer vecino de 0:  **1** . Llamamos  **DFS(1)** .
   * Marcamos **1** como visitado. Resultado: `0, 1 `
2. Buscamos el primer vecino de 1: **0** (visitado).
3. Buscamos el siguiente vecino de 1:  **3** . Llamamos  **DFS(3)** .
   * Marcamos **3** como visitado. Resultado: `0, 1, 3 `
4. Buscamos los vecinos de 3: **1** (visitado), **2** (NO visitado). Llamamos  **DFS(2)** .
   * Marcamos **2** como visitado. Resultado: `0, 1, 3, 2 `
5. Buscamos los vecinos de 2:  **0, 3** . Ambos están visitados. Retorno (Backtracking).
6. Volvemos a  **3** . Ya no tiene vecinos NO visitados. Retorno.
7. Volvemos a  **1** . Ya no tiene vecinos NO visitados. Retorno.
8. Volvemos a  **0** . Buscamos el siguiente vecino: **2** (visitado). Retorno.

 **Recorrido Final (DFS)** : **$0 \rightarrow 1 \rightarrow 3 \rightarrow 2$** (La secuencia exacta depende del orden de iteración de los vecinos).

---

### 3. Detección de Ciclo

Usa una versión modificada de DFS que rastrea el **padre** (el nodo desde el que se llegó al nodo actual) para distinguir un retroceso en el camino de un verdadero ciclo.

* **Regla** : Hay ciclo si se encuentra un nodo **visitado** que **NO** es el padre del nodo actual.

#### Simulación (Grafo Acíclico vs. Cíclico)

**Grafo Cíclico de Ejemplo (0-1-2-0):**

**Code snippet**

```
      (0)
     /   \
    /     \
   (1)---(2)
```

1. **DFS(0, padre=-1)** : Visitado: `{0}`
2. Vecino  **1** . Llamamos  **DFS(1, padre=0)** . Visitado: `{0, 1}`
3. Vecino  **2** . Llamamos  **DFS(2, padre=1)** . Visitado: `{0, 1, 2}`
4. Vecino  **0** . El nodo **0** está visitado. ¿Es el padre de 2? No, el padre es 1. **¡Ciclo detectado!** (0-1-2-0).

**Grafo Acíclico (DAG) de Ejemplo (0→1→2):**

**Code snippet**

```
       (0)
        v
       (1)
        v
       (2)
```

1. **DFS(0, padre=-1)** : Visitado: `{0}`
2. Vecino  **1** . Llamamos  **DFS(1, padre=0)** . Visitado: `{0, 1}`
3. Vecino  **2** . Llamamos  **DFS(2, padre=1)** . Visitado: `{0, 1, 2}`
4. **2** no tiene vecinos no visitados. Retorno.
5. **1** no tiene más vecinos no visitados. Retorno.
6. **0** no tiene más vecinos no visitados. Retorno.  **No se encontró ningún ciclo** .

---

### 4. Conteo de Componentes Conectadas

Mide cuántas "islas" independientes hay en el grafo.

* **Funcionamiento** : Se itera sobre *todos* los vértices. Cada vez que se encuentra un vértice  **no visitado** , se inicia un DFS (o BFS). El inicio de cada DFS marca la detección de una nueva componente.

#### Simulación (Grafo Desconectado)

**Grafo Desconectado de Ejemplo:**

**Code snippet**

```
(0)---(1)         (2)---(3)
(Componente A)    (Componente B)
```

1. **i = 0** : Vértice 0 NO visitado.

* Iniciamos DFS(0).  **Componente = 1** .
* DFS(0) visita {0, 1}.

1. **i = 1** : Vértice 1 ya está visitado. (Ignorar).
2. **i = 2** : Vértice 2 NO visitado.

* Iniciamos DFS(2).  **Componente = 2** .
* DFS(2) visita {2, 3}.

1. **i = 3** : Vértice 3 ya está visitado. (Ignorar).

 **Resultado Final** : El grafo tiene **2** componentes conectadas.

---

### 5. Distancia Más Corta (Sin Pesos)

Usa **BFS** para encontrar la distancia mínima, midiendo el camino en número de pasos.

* **Herramienta Adicional** : Un *array* `distancia` para almacenar la longitud del camino desde el inicio.

#### Simulación (Distancia de **0** a  **3** )

1. **Inicio** (distancia = **$\infty$**, excepto 0):
   * Cola: `[0]`
   * Distancia: `[0, -1, -1, -1]` (-1 es no alcanzado)
2. **Paso 1** : Sacamos **0** (distancia 0). Vecinos: 1 y 2.

* **$dist[1] = dist[0] + 1 = 1$**.
* **$dist[2] = dist[0] + 1 = 1$**.
* Cola: `[1, 2]`
* Distancia: `[0, 1, 1, -1]`

1. **Paso 2** : Sacamos **1** (distancia 1). Vecino:  **3** .

* **$dist[3] = dist[1] + 1 = 2$**.
* **¡Llegamos al destino 3!** Distancia es  **2** .

 **Resultado Final** : El camino más corto de 0 a 3 es de **2 aristas** (0-1-3 o 0-2-3).

---

### 6. Verificación de Grafo Bipartito

Determina si se puede dividir el grafo en dos conjuntos sin que haya aristas dentro de un mismo conjunto.

* **Funcionamiento** : Se usa **BFS** para colorear el grafo. Al nodo inicial le asignamos color 0. A sus vecinos les asignamos color 1, a los vecinos de estos, color 0, y así sucesivamente (alternando los colores).

#### Simulación (Grafo de Ejemplo: 0-1, 1-3, 3-2, 2-0)

| **Vértice** | **Color Inicial** |
| ------------------ | ----------------------- |
| 0                  | 0                       |
| 1                  | -1                      |
| 2                  | -1                      |
| 3                  | -1                      |

1. **Inicio (Vértice 0)** : Asignamos  **Color 0** .

* Vecinos (1, 2) obtienen el color opuesto:  **Color 1** .
* Cola: `[1, 2]`
* Color: `[0, 1, 1, -1]`

1. **Sacamos 1** (Color 1). Vecinos: **0** y  **3** .
   * Vecino **0** tiene Color 0 (¡Diferente! Correcto).
   * Vecino **3** no tiene color. Asignamos el opuesto a 1:  **Color 0** .
   * Cola: `[2, 3]`
   * Color: `[0, 1, 1, 0]`
2. **Sacamos 2** (Color 1). Vecinos: **0** y  **3** .
   * Vecino **0** tiene Color 0 (¡Diferente! Correcto).
   * Vecino **3** tiene Color 0 (¡Diferente! Correcto).

 **Resultado Final** : El grafo es  **Bipartito** . Los conjuntos son **$\{0, 3\}$** y **$\{1, 2\}$**.

---

### 7. Verificación de Grafo Árbol

Un grafo es un Árbol si cumple dos condiciones:

1. Es **Conectado** (solo 1 componente conectada).
2. Es **Acíclico** (no tiene ciclos).

* **Funcionamiento** : Simplemente llama a los algoritmos **Conteo de Componentes Conectadas** y  **Detección de Ciclo** . Si el primero retorna 1 y el segundo retorna `false`, es un árbol.

#### Pseudocódigo Lógico

```
fun esArbol(Grafo G):
    // 1. Debe ser conectado
    Si ContarComponentesConectadas(G) != 1, entonces retornar FALSO.

    // 2. No debe tener ciclos
    Si TieneCiclo(G) == VERDADERO, entonces retornar FALSO.

    // Opcional: Para grafos conectados, V-1 aristas es una condición necesaria.
    // Aunque si las 2 anteriores son verdaderas, esta se cumple.
    // Si NumAristas(G) != NumVertices(G) - 1, entonces retornar FALSO.

    Retornar VERDADERO.
```
