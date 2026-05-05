### Identificar Recorrido

* Dado el siguiente árbol binario y la salida del recorrido indique a cuál de los recorridos revisados corresponde:

    *`[20, 30, 35, 40, 45, 50, 60, 65, 70, 75, 80, 85, 90]`*

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


*   a) Pre-orden
*   b) Post-orden
*   c) In-orden


**Respuesta correcta: c) In-orden**

*Al observar la salida, los valores están organizados de menor a mayor de forma estrictamente ascendente. Esta es la característica principal del recorrido **In-orden** en un Árbol Binario de Búsqueda (Izquierda -> Raíz -> Derecha).*