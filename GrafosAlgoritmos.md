# 🧩 Representación y Recorridos de Grafos en Java

---

## 📊 Grafo Base

```
       (0)
      /   \
     /     \
   (1)----- (2)
     \     /
      \   /
       (3)
```

### Matriz de Adyacencia

|     | 0 | 1 | 2 | 3 |
|-----|---|---|---|---|
| **0** | 0 | 1 | 1 | 0 |
| **1** | 1 | 0 | 0 | 1 |
| **2** | 1 | 0 | 0 | 1 |
| **3** | 0 | 1 | 1 | 0 |

---

## 1️⃣ Búsqueda en Anchura (BFS)

```java
static void bfs(int inicio) {
    boolean[] visitado = new boolean[V];
    int[] cola = new int[V];
    int frente = 0, fondo = 0;
    cola[fondo++] = inicio;
    visitado[inicio] = true;

    while (frente < fondo) {
        int actual = cola[frente];
        frente++;
        System.out.print(actual + " ");
        for (int vecino = 0; vecino < V; vecino++) {
            if (matriz[actual][vecino] == 1 && !visitado[vecino]) {
                cola[fondo++] = vecino;
                visitado[vecino] = true;
            }
        }
    }
}
```

### Simulación paso a paso (inicio = **0**)

**Estado inicial (después de encolar el inicio):**  
- Cola: `[0]` (`frente=0`, `fondo=1`)  
- Visitados: `{0}`

**Paso 1 — Procesar actual = 0**  
- Se extrae `actual = cola[frente]` → `actual = 0` (`frente` pasa a 1).  
- Se imprime `0`.  
- Se lee la fila `matriz[0] = [0, 1, 1, 0]`.  
- Para `vecino=1`: `matriz[0][1] == 1` y `visitado[1]==false` → se encola `1` (`cola[fondo]=1; fondo++`) y se marca `visitado[1]=true`.  
- Para `vecino=2`: `matriz[0][2] == 1` y `visitado[2]==false` → se encola `2` y se marca `visitado[2]=true`.  
- Acciones exactas en el código: comprobación `if (matriz[actual][vecino] == 1 && !visitado[vecino])`, `cola[fondo++] = vecino; visitado[vecino] = true;`.  
- Cola después del paso: `[1, 2]`.  
- Visitados: `{0,1,2}`.

**Paso 2 — Procesar actual = 1**  
- Se extrae `actual = 1` (`frente` pasa a 2).  
- Se imprime `1`.  
- Se lee la fila `matriz[1] = [1,0,0,1]`.  
- Vecino `0` está marcado, se ignora.  
- Vecino `3`: `matriz[1][3] == 1` y `visitado[3]==false` → se encola `3` y `visitado[3]=true`.  
- Cola después del paso: `[2, 3]`.  
- Visitados: `{0,1,2,3}`.

**Paso 3 — Procesar actual = 2**  
- Se extrae `actual = 2` (`frente` pasa a 3).  
- Se imprime `2`.  
- Se lee la fila `matriz[2] = [1,0,0,1]`.  
- Vecinos `0` y `3` ya están marcados; no se encola nada.  
- Cola después del paso: `[3]`.  
- Visitados: `{0,1,2,3}`.

**Paso 4 — Procesar actual = 3**  
- Se extrae `actual = 3` (`frente` pasa a 4).  
- Se imprime `3`.  
- Se lee la fila `matriz[3] = [0,1,1,0]`.  
- Vecinos `1` y `2` ya están marcados; no se encola nada.  
- Cola vacía → termina bucle.  
- Visitados finales: `{0,1,2,3}`.

**Resultado (orden de impresión):** `0 1 2 3`  
**Resumen de estados (por pasos):**

| Paso | Cola antes | Nodo actual | Cola después | Visitados |
|------|------------|-------------|--------------|-----------|
| 1 | `[0]` | 0 | `[1,2]` | `{0,1,2}` |
| 2 | `[1,2]` | 1 | `[2,3]` | `{0,1,2,3}` |
| 3 | `[2,3]` | 2 | `[3]` | `{0,1,2,3}` |
| 4 | `[3]` | 3 | `[]` | `{0,1,2,3}` |

---

## 2️⃣ Búsqueda en Profundidad (DFS)

```java
static void dfs(int actual) {
    boolean[] visitado = new boolean[V];
    dfsRecursivo(actual, visitado);
    System.out.println();
}

static void dfsRecursivo(int actual, boolean[] visitado) {
    visitado[actual] = true;
    System.out.print(actual + " ");
    for (int vecino = 0; vecino < V; vecino++) {
        if (matriz[actual][vecino] == 1 && !visitado[vecino]) {
            dfsRecursivo(vecino, visitado);
        }
    }
}
```

### Simulación paso a paso (inicio = **0**)

**Estado inicial:**  
- Pila de llamadas (recursión): `[]`  
- Visitados: `{}`

**Paso 1 — dfsRecursivo(0)**  
- Marca `visitado[0]=true`.  
- Imprime `0`.  
- Pila: `[0]`.  
- Lee `matriz[0] = [0,1,1,0]`. El primer vecino no visitado es `1` → llama `dfsRecursivo(1)`.

**Paso 2 — dfsRecursivo(1)**  
- Marca `visitado[1]=true`.  
- Imprime `1`.  
- Pila: `[0,1]`.  
- Lee `matriz[1] = [1,0,0,1]`. Vecinos no visitados: `3` (nota: `0` ya está visitado; `2` no es vecino en esta matriz). Llama `dfsRecursivo(3)`.

**Paso 3 — dfsRecursivo(3)**  
- Marca `visitado[3]=true`.  
- Imprime `3`.  
- Pila: `[0,1,3]`.  
- Lee `matriz[3] = [0,1,1,0]`. Vecino no visitado es `2` → llama `dfsRecursivo(2)`.

**Paso 4 — dfsRecursivo(2)**  
- Marca `visitado[2]=true`.  
- Imprime `2`.  
- Pila: `[0,1,3,2]`.  
- Lee `matriz[2] = [1,0,0,1]`. Todos sus vecinos están visitados → retorna.

**Backtracking y retornos:**  
- `dfsRecursivo(2)` retorna a `dfsRecursivo(3)` → `3` no tiene más vecinos no visitados → retorna a `dfsRecursivo(1)` → `1` no tiene más → retorna a `dfsRecursivo(0)` → `0` tiene `2` pero ya visitado → retorna y finaliza.

**Orden de visita (impreso):** `0 1 3 2`

**Resumen de estados (por pasos):**

| Paso | Llamada actual | Pila antes | Visitados | Acción |
|------|----------------|-----------:|----------:|--------|
| 1 | dfs(0) | `[]` | `{}` | marcar 0; push 0; llamar dfs(1) |
| 2 | dfs(1) | `[0]` | `{0}` | marcar 1; push 1; llamar dfs(3) |
| 3 | dfs(3) | `[0,1]` | `{0,1}` | marcar 3; push 3; llamar dfs(2) |
| 4 | dfs(2) | `[0,1,3]` | `{0,1,3}` | marcar 2; push 2; retornar |
| 5 | retorno | `[0,1,3,2] -> ... -> []` | `{0,1,2,3}` | fin |

---

## 3️⃣ Detección de Ciclos

```java
static boolean tieneCiclo() {
    boolean[] visitado = new boolean[V];
    for (int i = 0; i < V; i++) {
        if (!visitado[i]) {
            if (tieneCicloDFS(i, visitado, -1))
                return true;
        }
    }
    return false;
}
```

```java
private static boolean tieneCicloDFS(int actual, boolean[] visitado, int padre) {
    visitado[actual] = true;
    for (int vecino = 0; vecino < V; vecino++) {
        if (matriz[actual][vecino] == 1) {
            if (!visitado[vecino]) {
                if (tieneCicloDFS(vecino, visitado, actual))
                    return true;
            } else if (vecino != padre) {
                return true;
            }
        }
    }
    return false;
}
```

### Simulación (traza)

**Paso 1** — `tieneCicloDFS(0, visitado, -1)`  
- Marca `0` visitado → `visitado = {0}`.  
- Vecino encontrado: `1` → llamar `tieneCicloDFS(1, visitado, 0)`.

**Paso 2** — `tieneCicloDFS(1, visitado, 0)`  
- Marca `1` → `visitado = {0,1}`.  
- Vecino `3` → llamar `tieneCicloDFS(3, visitado, 1)`.

**Paso 3** — `tieneCicloDFS(3, visitado, 1)`  
- Marca `3` → `visitado = {0,1,3}`.  
- Vecino `2` → llamar `tieneCicloDFS(2, visitado, 3)`.

**Paso 4** — `tieneCicloDFS(2, visitado, 3)`  
- Marca `2` → `visitado = {0,1,2,3}`.  
- Revisa vecinos: encuentra `vecino = 0` con `visitado[0] == true` y `vecino != padre (3)` → condición de ciclo se cumple → retorna `true` hacia arriba.

**Resultado:** `tieneCiclo()` devuelve `true`.  
**Ciclo detectado:** 0 → 1 → 3 → 2 → 0

---

## 4️⃣ Componentes Conectadas

```java
static int contarComponentesConectadas() {
    boolean[] visitado = new boolean[V];
    int count = 0;
    for (int i = 0; i < V; i++) {
        if (!visitado[i]) {
            dfsRecursivo(i, visitado);
            count++;
        }
    }
    return count;
}
```

### Simulación

- Inicio con `i = 0`: `visitado` vacío → llama `dfsRecursivo(0, visitado)` → tras la recursión `visitado = {0,1,2,3}`.  
- `count` incrementado a `1`.  
- Siguientes `i = 1,2,3` están ya visitados → no se inicia nueva DFS.

**Resultado:** `contarComponentesConectadas()` = **1**

---

## 5️⃣ Distancia Más Corta (BFS con distancias)

```java
static int distanciaMasCorta(int inicio, int fin) {
    if (inicio == fin) return 0;
    boolean[] visitado = new boolean[V];
    int[] distancia = new int[V];
    int[] cola = new int[V];
    int frente = 0, fondo = 0;

    cola[fondo++] = inicio;
    visitado[inicio] = true;
    distancia[inicio] = 0;

    while (frente < fondo) {
        int actual = cola[frente++];
        for (int vecino = 0; vecino < V; vecino++) {
            if (matriz[actual][vecino] == 1 && !visitado[vecino]) {
                cola[fondo++] = vecino;
                visitado[vecino] = true;
                distancia[vecino] = distancia[actual] + 1;
                if (vecino == fin) return distancia[vecino];
            }
        }
    }
    return -1;
}
```

### Simulación 0 → 3 (detalle)

**Estado inicial:**  
- Cola: `[0]`  
- Visitados: `{0}`  
- Distancia: `[0, -, -, -]` (uso `-` para indicar no asignado)

**Paso 1 — Procesar actual = 0**  
- Vecinos: 1 y 2 → ambos encolados.  
- Cola: `[1,2]`  
- Visitados: `{0,1,2}`  
- Distancias: `[0,1,1,-]` (distancia[1] = 1, distancia[2] = 1)

**Paso 2 — Procesar actual = 1**  
- Vecino 3 no visitado → encolar 3  
- Cola: `[2,3]`  
- Visitados: `{0,1,2,3}`  
- Distancias: `[0,1,1,2]` (distancia[3] = distancia[1] + 1 = 2)  
- Como `vecino == fin (3)`, el método retorna **2** inmediatamente.

**Resultado:** Distancia mínima = **2**

---

## 6️⃣ Grado de un Vértice

```java
static int gradoVertice(int vertice) {
    int grado = 0;
    for (int j = 0; j < V; j++) {
        grado += matriz[vertice][j];
    }
    return grado;
}
```

### Valores

| Vértice | Fila (matriz) | Suma (grado) |
|---------|---------------|--------------|
| 0 | [0,1,1,0] | 2 |
| 1 | [1,0,0,1] | 2 |
| 2 | [1,0,0,1] | 2 |
| 3 | [0,1,1,0] | 2 |

**Todos los vértices tienen grado = 2.**

---

## 7️⃣ Grafo Bipartito

```java
static boolean esBipartito() {
    int[] color = new int[V];
    for (int i = 0; i < V; i++) color[i] = -1;
    for (int i = 0; i < V; i++) {
        if (color[i] == -1) {
            if (!esBipartitoBFS(i, color)) return false;
        }
    }
    return true;
}
```

```java
private static boolean esBipartitoBFS(int inicio, int[] color) {
    int[] cola = new int[V];
    int frente = 0, fondo = 0;
    cola[fondo++] = inicio;
    color[inicio] = 0;

    while (frente < fondo) {
        int actual = cola[frente++];
        for (int vecino = 0; vecino < V; vecino++) {
            if (matriz[actual][vecino] == 1) {
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
```

### Simulación de coloreado (inicio = 0)

**Estado inicial:** `color = [-1,-1,-1,-1]`

**Paso 1 — colorear 0**  
- `color[0] = 0`  
- Cola: `[0]`

**Paso 2 — procesar 0**  
- Vecinos 1 y 2 → `color[1]=1`, `color[2]=1`; Cola: `[1,2]`  
- `color = [0,1,1,-1]`

**Paso 3 — procesar 1**  
- Vecinos: 0 (ya color 0 ok), 3 (sin color) → `color[3] = 0`; Cola: `[2,3]`  
- `color = [0,1,1,0]`

**Paso 4 — procesar 2**  
- Vecinos: 0 (color 0 ok), 3 (ya color 0)  
- Observación: 1 y 2 son vecinos entre sí? En este grafo concreto **no** (según la matriz actual no hay arista 1–2), por tanto no hay conflicto en este procedimiento. *(Si hubiera arista 1–2, ambos tendrían color 1 y se detectaría conflicto.)*

**Resultado final del algoritmo:** `esBipartito()` devuelve **true** o **false** según la matriz; con la matriz usada en el código (sin 1–2) el coloreado no encuentra conflicto → **bipartito = true**.

---

## 8️⃣ Verificación de Árbol

```java
static boolean esArbol() {
    if (contarComponentesConectadas() != 1) return false;
    if (tieneCiclo()) return false;
    return true;
}
```

### Evaluación

- `contarComponentesConectadas()` = 1 ✅  
- `tieneCiclo()` = true (según traza de detección de ciclos) ❌

**Resultado:** No es árbol.

---

## 🧾 Resumen Final

| Propiedad | Valor |
|-----------|-------|
| Conexo | Sí |
| Tiene ciclo | Sí |
| Bipartito | Depende de la arista 1–2 (según la matriz actual en el código: **true**) |
| Árbol | No |
| Componentes | 1 |
| Distancia(0→3) | 2 |
| Grado promedio | 2 |

---