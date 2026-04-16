# 🌲 ¿Qué son los Árboles en Estructuras de Datos?

Así como las listas o las pilas organizan datos de forma lineal (uno detrás de otro), un **árbol** es una estructura de datos **jerárquica** y **no lineal**. Piensa en un árbol genealógico o en la estructura de carpetas de tu computadora: hay un ancestro común desde el cual se ramifican diferentes descendientes.

Todo árbol está compuesto por **Nodos**.

### 🧱 Anatomía de un Árbol

1. **Raíz (Root):** El nodo superior del árbol. Es el único nodo que no tiene un "padre". Toda la estructura nace de aquí.
2. **Hijos y Padres:** Si un nodo se conecta hacia abajo con otro nodo, el primero es el padre y el segundo es el hijo.
3. **Hojas (Leaves):** Nodos que se encuentran en el "fondo" del árbol. No tienen hijos. Son los callejones sin salida.
4. **Nivel / Profundidad:** La distancia de un nodo respecto a la raíz. La raíz está en el nivel 0. Sus hijos directos en el nivel 1, y así sucesivamente.
5. **Altura:** El camino más largo desde la raíz hasta la hoja más lejana.

---

## 🤯 Tipos de Árboles

Dependiendo de las reglas con las que se conecten los nodos, los árboles reciben distintos nombres:

* **Árbol N-ario / General:** Un nodo puede tener cualquier cantidad de hijos (0, 1, 5, 100...). Son útiles para sistemas de archivos o representar jerarquías corporativas.
* **Árbol Binario:** Cada nodo puede tener **como máximo dos hijos** (referidos comúnmente como *izquierdo* y *derecho*).

Dentro de los árboles binarios existe una sub-categoría vital para la informática:

### 🔎 Árbol Binario de Búsqueda (ABB o BST - Binary Search Tree)

El **ABB** impone una regla estricta sobre cómo se ordenan los datos, permitiendo que la búsqueda de cualquier elemento sea ridículamente rápida (similar a buscar en un diccionario físico abriendo el libro a la mitad).

**¿Cómo identificar o construir un ABB? La regla de oro:**
* Todo lo que esté en el **subárbol izquierdo** de un nodo debe ser **estrictamente menor** que el valor del nodo.
* Todo lo que esté en el **subárbol derecho** de un nodo debe ser **estrictamente mayor** que el valor del nodo.

*(Nota: Estas reglas no aplican solo para el hijo directo, ¡aplican para todos los descendientes en ese lado! Si la raíz es 50, absolutamente ningún nodo a su izquierda puede ser mayor a 50, sin importar qué tan profundo esté).*

#### ¿Cómo identificar algorítmicamente un ABB? (Validación de Límites)
A simple vista, un ABB parece válido si cada hijo izquierdo es menor a su padre y el derecho es mayor. Sin embargo, **esto no es suficiente**.

Para validar o identificar correctamente un ABB completo mediante código, no basta con mirar al padre directo. Debemos arrastrar los "límites" a medida que bajamos por el árbol:

1. Comenzamos en la raíz con el rango `[-Infinito, +Infinito]`.
2. Cuando pasamos al **hijo izquierdo**, su **límite máximo** se vuelve el valor del padre.
3. Cuando pasamos al **hijo derecho**, su **límite mínimo** se vuelve el valor del padre.
4. Si algún nodo de ese subárbol viola este rango, **no es un ABB**.

*(Si quieres ver esto más a fondo, revisa el archivo `ArbolesLimites.md` donde se detalla el por qué un "simple vistazo" no basta).*

#### Otros Árboles Notables:
* **AVL / Red-Black Trees:** Son Árboles Binarios de Búsqueda "Autobalanceados". Evitan que el árbol se convierta en una línea recta (una lista) si insertas números ordenados secuencialmente (1, 2, 3... desencadenando en un árbol degenerado).

---

## 🛤️ Recorridos de un Árbol Binario (Inorden, Preorden, Postorden)

A diferencia de un arreglo donde solo puedes ir del índice 0 al final, en un árbol tienes que decidir en qué orden visitar la "Raíz" en comparación con sus subárboles "Izquierdo" y "Derecho". Esa decisión te da tres tipos de recorridos diferentes.

### 1. In-Orden (Izquierda -> Raíz -> Derecha)
Resuelves primero todo el camino izquierdo, luego visitas el nodo actual, y finalmente resuelves el derecho.

* **Caso de Uso:** Si aplicas este recorrido a un Árbol Binario de Búsqueda (ABB), ¡te devolverá los elementos **ordenados de menor a mayor**! Es su característica más útil.
* **A fondo:** Primero se sumerge hasta el valor más pequeño absoluto (extremo izquierdo), lo imprime, luego imprime el padre, luego va ligeramente a la derecha, y así sucesivamente cruzando el árbol de izquierda a derecha.

### 2. Pre-Orden (Raíz -> Izquierda -> Derecha)
"Visitas" o imprimes el nodo actual **antes** de revisar a sus hijos.

* **Caso de Uso:** Es perfecto para **clonar o copiar un árbol** a otra ubicación, o para guardar el árbol en un archivo y luego reconstruirlo exactamente con la misma estructura. Como revisas la raíz antes que a los descendientes, te aseguras de tener el "pilar" antes de colgar cosas de él.

### 3. Post-Orden (Izquierda -> Derecha -> Raíz)
Exploras completamente a los hijos izquierdo y derecho antes de finalmente operar con el nodo actual (la raíz de ese subárbol).

* **Caso de Uso:** Se utiliza clásicamente para **eliminar o destruir un árbol** de la memoria. ¡No puedes borrar al padre si aún necesitas acceder a sus hijos! Con *Post-Orden*, te aseguras de borrar las hojas y luego vas borrando los padres gradualmente hacia arriba hasta destruir la raíz principal de último. También se usa en calculadoras para evaluar árboles de expresiones matemáticas.

---

### Un ejemplo visual simplificado:

```text
       (B)
      /   \
    (A)   (C)
```

1. **In-Orden:** `A` -> `B` -> `C` (Orden ascendente tradicional).
2. **Pre-Orden:** `B` -> `A` -> `C` (Padre de primero).
3. **Post-Orden:** `A` -> `C` -> `B` (Hijos primero, padre al final).
