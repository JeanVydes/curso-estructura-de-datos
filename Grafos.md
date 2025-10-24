## 📚 ¿Qué es un Grafo?

Un **grafo** es una estructura de datos abstracta que modela relaciones entre entidades mediante un conjunto de **vértices** (nodos) y **aristas** (conexiones). Su propósito principal es representar redes complejas, como rutas en mapas, conexiones sociales o dependencias en sistemas, permitiendo operaciones como búsqueda de caminos, detección de ciclos y optimización de rutas. A diferencia de estructuras lineales como listas o árboles, los grafos son no lineales y pueden ser cíclicos o acíclicos, dirigidos o no, lo que los hace versátiles para problemas del mundo real.

### Conceptos Básicos

- **Vértices (Nodos)**: Representan entidades (ej. ciudades, personas).
- **Aristas (Edges)**: Representan relaciones (ej. carreteras, amistades).
- **Grado de un Vértice**: Número de aristas conectadas a él.
- **Camino**: Secuencia de vértices conectados por aristas.
- **Ciclo**: Camino que regresa al vértice inicial.
- **Representaciones Comunes**:
  - **Matriz de Adyacencia**: Tabla 2D donde fila/columna indican conexiones (eficiente para grafos densos).
  - **Lista de Adyacencia**: Cada vértice tiene una lista de vecinos (eficiente para grafos dispersos).

### Algoritmos Esenciales

- **BFS (Búsqueda en Anchura)**: Explora nivel por nivel; ideal para caminos más cortos en grafos no ponderados (O(V + E)).
- **DFS (Búsqueda en Profundidad)**: Explora lo más profundo posible; útil para detectar ciclos o componentes conectadas (O(V + E)).
- **Dijkstra**: Camino más corto en ponderados con pesos positivos.
- **Kruskal/Prim**: Árbol de expansión mínima.

### Tipos principales que exploraremos:

1. **Grafos No Dirigidos**
2. **Grafos Dirigidos**
3. **Grafos Ponderados**
4. **Grafos Conectados**
5. **Grafos Acíclicos (DAGs)**

---

# 🔗 1. Grafos No Dirigidos

### 🔹 Definición Técnica

Es un grafo donde las aristas no tienen dirección: una conexión entre A y B es mutua y bidireccional. Matemáticamente, se define como G = (V, E), donde E es un conjunto de pares no ordenados {u, v}.

### 🧩 Representación Visual en Memoria (Usando Lista de Adyacencia)

```ascii
Vértices: 0, 1, 2, 3

Lista de Adyacencia:
0 → 1 → 2
1 → 0 → 3
2 → 0 → 3
3 → 1 → 2

Dibujo ASCII:
       0
      / \
     /   \
    1     2
     \   /
      \ /
       3
```

### 🔧 Características Clave

* **Simetría en Relaciones (O(1) para Verificar Conexión)**: Si A está conectado a B, B lo está a A automáticamente. Ideal para modelar amistades mutuas o carreteras bidireccionales.
* **Sin Dirección**: No hay concepto de "entrada/salida"; el recorrido es libre en ambas direcciones.
* **Eficiencia en Inserción/Eliminación de Aristas (O(1) en Lista de Adyacencia)**: Agregar una arista solo requiere actualizar listas de ambos vértices.
* **Uso Común**: Redes sociales (amistades), mapas de rutas sin sentido único.
* **Desventaja**: No modela flujos unidireccionales; para eso, usa grafos dirigidos.

---

# ➡️ 2. Grafos Dirigidos (Dígrafos)

### 🔹 Definición Técnica

Es un grafo donde las aristas tienen una dirección explícita: una arista de A a B no implica B a A. Matemáticamente, G = (V, E), donde E es un conjunto de pares ordenados (u, v).

### 🧩 Representación Visual en Memoria (Usando Lista de Adyacencia)

```ascii
Vértices: 0, 1, 2, 3

Lista de Adyacencia:
0 → 1 → 2
1 → 3
2 → 3
3 → (ninguno)

Dibujo ASCII:
       0
      / \
     /   \
    1     2
     \   /
      \ /
       3
   (Flechas: 0→1, 0→2, 1→3, 2→3)
```

### 🔧 Características Clave

* **Direccionalidad (O(1) para Verificar Sentido)**: Permite modelar relaciones asimétricas, como "A sigue a B" en redes sociales o dependencias "A precede a B" en tareas.
* **Posibilidad de Ciclos Dirigidos**: Pueden existir ciclos (ej. A→B→C→A), pero también DAGs (sin ciclos).
* **Grados Divididos**: Grado de salida (aristas salientes) y entrada (entrantes); útil para análisis de flujo.
* **Uso Común**: Páginas web (enlaces), flujos de trabajo, grafos de dependencias en software.
* **Desventaja**: Mayor complejidad en algoritmos (ej. topological sort solo en DAGs); consume más memoria si se simulan bidireccionales.

---

# ⚖️ 3. Grafos Ponderados

### 🔹 Definición Técnica

Es un grafo (dirigido o no) donde cada arista tiene un **peso** asociado, representando un costo, distancia o valor cuantitativo. Matemáticamente, G = (V, E, W), donde W es una función de pesos en E.

### 🧩 Representación Visual en Memoria (Usando Matriz de Adyacencia con Pesos)

```ascii
Matriz de Adyacencia (Pesos):
  0 1 2 3
0 0 4 2 0
1 4 0 0 1
2 2 0 0 3
3 0 1 3 0

Dibujo ASCII:
       0
     4/ \2
     /   \
    1     2
     \1 /3
      \ /
       3
```

### 🔧 Características Clave

* **Pesos en Aristas (O(1) Acceso en Matriz)**: Permite optimizaciones cuantitativas, como caminos más cortos por distancia real.
* **Adaptable a Dirigido/No Dirigido**: Pesos se aplican en ambas variantes; en dirigidos, pesos pueden diferir por sentido.
* **Algoritmos Específicos**: Requiere Dijkstra o Bellman-Ford para caminos mínimos; BFS/DFS ignoran pesos.
* **Uso Común**: GPS (distancias en km), redes de costos (envíos), análisis de eficiencia en circuitos.
* **Desventaja**: Mayor consumo de memoria (pesos ocupan espacio); algoritmos más complejos (O(E log V) para Dijkstra).

---

# 🌐 4. Grafos Conectados

### 🔹 Definición Técnica

Es un grafo (no dirigido) donde existe al menos un camino entre cualquier par de vértices. En dirigidos, se habla de "fuertemente conectado" si hay caminos bidireccionales.

### 🧩 Representación Visual en Memoria (Usando Lista de Adyacencia)

```ascii
Vértices: 0, 1, 2, 3 (Todos conectados)

Lista de Adyacencia:
0 → 1 → 2
1 → 0 → 3
2 → 0 → 3
3 → 1 → 2

Dibujo ASCII:
       0
      / \
     /   \
    1-----3
     \   /
      \ /
       2
   (Nota: Añadí 1-3 y 2-3 para conectar todo)
```

### 🔧 Características Clave

* **Conectividad Total (O(V + E) para Verificar)**: BFS/DFS desde cualquier vértice visita todos; no hay componentes aisladas.
* **Componentes Conectadas**: En no conectados, se divide en subgrafos; aquí, solo una.
* **Propiedades**: Mínimo V-1 aristas; útil para redes integradas.
* **Uso Común**: Redes eléctricas (todo debe estar unido), análisis de conectividad en internet.
* **Desventaja**: Si se desconecta (por falla de arista), requiere algoritmos para reconectar (ej. MST).

---

# 🔄 5. Grafos Acíclicos (DAGs)

### 🔹 Definición Técnica

Es un grafo dirigido sin ciclos: no hay caminos que regresen al vértice inicial. Matemáticamente, un DAG permite un orden topológico lineal de vértices.

### 🧩 Representación Visual en Memoria (Usando Lista de Adyacencia)

```ascii
Vértices: 0, 1, 2, 3 (Sin ciclos)

Lista de Adyacencia:
0 → 1 → 2
1 → 3
2 → 3
3 → (ninguno)

Dibujo ASCII:
   0
  / \
 /   \
1     2
 \   /
  \ /
   3
   (Flechas dirigidas, sin loops de regreso)
```

### 🔧 Características Clave

* **Ausencia de Ciclos (O(V + E) para Verificar)**: Usa DFS para detectar back-edges; si ninguno, es DAG.
* **Orden Topológico**: Vértices se ordenan para que aristas vayan de izquierda a derecha (útil para scheduling).
* **No Recursión Infinita**: Ideal para dependencias sin bucles.
* **Uso Común**: Planificación de proyectos (PERT), compiladores (dependencias de código), blockchain (transacciones).
* **Desventaja**: No modela ciclos reales (ej. procesos repetitivos); para eso, usa grafos con ciclos.

---

# ⚖️ Comparativa Técnica de Tipos de Grafos

| Tipo                      | Dirección | Pesos    | Conectividad | Ciclos Permitidos | Representación Preferida | Complejidad BFS/DFS | Usos Típicos                  |
| ------------------------- | ---------- | -------- | ------------ | ----------------- | ------------------------- | ------------------- | ------------------------------ |
| **No Dirigido**     | No         | No       | Variable     | Sí               | Lista Adyacencia          | O(V + E)            | Redes sociales, mapas básicos |
| **Dirigido**        | Sí        | No       | Variable     | Sí               | Lista Adyacencia          | O(V + E)            | Enlaces web, flujos de datos   |
| **Ponderado**       | Variable   | Sí      | Variable     | Sí               | Matriz o Lista con Pesos  | O(V + E) (BFS)      | GPS, optimización de costos   |
| **Conectado**       | No         | No       | Total        | Sí               | Cualquiera                | O(V + E)            | Redes integradas, circuitos    |
| **Acíclico (DAG)** | Sí        | Variable | Variable     | No                | Lista Adyacencia          | O(V + E)            |                                |
