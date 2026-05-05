### Identificar Código

Selecciona la opción correcta para cada fragmento de código basado en su estructura lógica.

#### 1. Identificar Fragmento A

```python
def operacion(self, nodo):
    if nodo is None:
        return
    self.operacion(nodo.izquierdo)
    print(nodo.dato, end=' ')
    self.operacion(nodo.derecho)
```

```java
private void operacion(Nodo<T> nodo) {
    if (nodo == null) return;
    operacion(nodo.izquierdo);
    System.out.print(nodo.dato + " ");
    operacion(nodo.derecho);
}
```

*   a) Recorrido Preorden
*   b) Recorrido Inorden
*   c) Recorrido Postorden

**Respuesta correcta: b) Recorrido Inorden**
*La lógica sigue el orden Izquierda -> Raíz (procesamiento/print) -> Derecha. En un ABB, este recorrido devuelve los elementos en orden ascendente.*

---

#### 2. Identificar Fragmento B

```python
def operacion(self, nodo):
    if nodo is None:
        return
    print(nodo.dato, end=' ')
    self.operacion(nodo.izquierdo)
    self.operacion(nodo.derecho)
```

```java
private void operacion(Nodo<T> nodo) {
    if (nodo == null) return;
    System.out.print(nodo.dato + " ");
    operacion(nodo.izquierdo);
    operacion(nodo.derecho);
}
```

*   a) Recorrido Preorden
*   b) Recorrido Inorden
*   c) Recorrido Postorden

**Respuesta correcta: a) Recorrido Preorden**
*Se procesa primero la Raíz (print) y luego se visitan los subárboles Izquierdo y Derecho de forma recursiva.*

---

#### 3. Identificar Fragmento C

```python
def operacion(self, nodo):
    if nodo is None:
        return
    self.operacion(nodo.izquierdo)
    self.operacion(nodo.derecho)
    print(nodo.dato, end=' ')
```

```java
private void operacion(Nodo<T> nodo) {
    if (nodo == null) return;
    operacion(nodo.izquierdo);
    operacion(nodo.derecho);
    System.out.print(nodo.dato + " ");
}
```

*   a) Recorrido Inorden
*   b) Recorrido Postorden
*   c) Cantidad de nodos

**Respuesta correcta: b) Recorrido Postorden**
*Se visitan primero todos los hijos (Izquierda y Derecha) antes de procesar o imprimir el nodo actual (Raíz).*

---

#### 4. Identificar Fragmento D

```python
def operacion(self, nodo):
    if nodo is None:
        return 0
    res_izq = self.operacion(nodo.izquierdo)
    res_der = self.operacion(nodo.derecho)
    return 1 + max(res_izq, res_der)
```

```java
private int operacion(Nodo<T> nodo) {
    if (nodo == null) return 0;
    int resIzq = operacion(nodo.izquierdo);
    int resDer = operacion(nodo.derecho);
    return 1 + Math.max(resIzq, resDer);
}
```

*   a) Cantidad de nodos
*   b) Cantidad de hojas
*   c) Altura del árbol

**Respuesta correcta: c) Altura del árbol**
*El algoritmo calcula la profundidad máxima comparando las ramas izquierda y derecha, sumando 1 por el nivel actual. En el árbol de la imagen referenciada, este cálculo resultaría en 4.*

---

#### 5. Identificar Fragmento E

```python
def operacion(self, nodo):
    if nodo is None:
        return 0
    return 1 + self.operacion(nodo.izquierdo) + self.operacion(nodo.derecho)
```

```java
private int operacion(Nodo<T> nodo) {
    if (nodo == null) return 0;
    return 1 + operacion(nodo.izquierdo) + operacion(nodo.derecho);
}
```

*   a) Altura del árbol
*   b) Cantidad total de nodos
*   c) Cantidad de nodos hoja

**Respuesta correcta: b) Cantidad total de nodos**
*Realiza un conteo recursivo sumando 1 por cada nodo existente en el árbol, recorriendo todas las ramas hasta llegar a los nulos.*

---

#### 6. Identificar Fragmento F

```python
def operacion(self, nodo):
    if nodo is None:
        return 0
    if nodo.izquierdo is None and nodo.derecho is None:
        return 1
    return self.operacion(nodo.izquierdo) + self.operacion(nodo.derecho)
```

```java
private int operacion(Nodo<T> nodo) {
    if (nodo == null) return 0;
    if (nodo.izquierdo == null && nodo.derecho == null) return 1;
    return operacion(nodo.izquierdo) + operacion(nodo.derecho);
}
```

*   a) Cantidad de nodos hoja
*   b) Cantidad de nodos rama
*   c) Altura del árbol

**Respuesta correcta: a) Cantidad de nodos hoja**
*La condición central verifica si un nodo no tiene hijos (`izquierdo == null` y `derecho == null`). Si es así, se cuenta como una hoja retornando 1. En caso contrario, sigue explorando el árbol.*