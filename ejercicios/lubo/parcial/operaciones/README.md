Describa los pasos para realizar las siguientes operaciones en un ABB dado:

* Busqueda de un elemento específico.

    1. Comenzar en la raíz del árbol.
    2. Comparar el valor buscado con el valor del nodo actual.
    3. Si el valor buscado es igual al valor del nodo actual, se ha encontrado el elemento.
    4. Si el valor buscado es menor que el valor del nodo actual, continuar la búsqueda en el subárbol izquierdo.
    5. Si el valor buscado es mayor que el valor del nodo actual, continuar la búsqueda en el subárbol derecho.
    6. Repetir los pasos 2-5 hasta encontrar el elemento o llegar a un nodo nulo (lo que indica que el elemento no está presente en el árbol).

* Inserción de un nuevo elemento.

    1. Comenzar en la raíz del árbol.
    2. Comparar el valor a insertar con el valor del nodo actual.
    3. Si el valor a insertar es menor que el valor del nodo actual, continuar la inserción en el subárbol izquierdo.
    4. Si el valor a insertar es mayor que el valor del nodo actual, continuar la inserción en el subárbol derecho.
    5. Si se llega a un nodo nulo, insertar el nuevo elemento en esa posición.

* Eliminación de un elemento existente.

    1. Comenzar en la raíz del árbol.
    2. Comparar el valor a eliminar con el valor del nodo actual.
    3. Si el valor a eliminar es menor que el valor del nodo actual, continuar la eliminación en el subárbol izquierdo.
    4. Si el valor a eliminar es mayor que el valor del nodo actual, continuar la eliminación en el subárbol derecho.
    5. Si se encuentra el nodo con el valor a eliminar, hay tres casos a considerar:
        a. El nodo es una hoja (no tiene hijos): simplemente eliminarlo.
        b. El nodo tiene un solo hijo: reemplazar el nodo por su hijo.
        c. El nodo tiene dos hijos: encontrar el sucesor (el nodo más pequeño en el subárbol derecho) o el predecesor (el nodo más grande en el subárbol izquierdo), reemplazar el valor del nodo a eliminar con el valor del sucesor o predecesor, y luego eliminar el sucesor o predecesor de su posición original.