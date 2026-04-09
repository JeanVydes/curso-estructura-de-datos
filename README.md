# 📚 Estructuras de Datos - Guía Completa

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![C++](https://img.shields.io/badge/C++-00599C?logo=c%2B%2B&logoColor=white)](https://isocpp.org/)
[![Java](https://img.shields.io/badge/Java-ED8B00?logo=java&logoColor=white)](https://www.java.com/)
[![Python](https://img.shields.io/badge/Python-3776AB?logo=python&logoColor=white)](https://www.python.org/)

> **Curso completo de estructuras de datos con implementaciones en C++, Java y Python**. Esta guía proporciona explicaciones teóricas detalladas, ejemplos prácticos paso a paso y código totalmente documentado para reforzar tu comprensión de las estructuras de datos fundamentales en programación.

---

## 📑 Tabla de Contenidos

- [Introducción](#-introducción)
- [Estructuras de Datos Lineales](#-estructuras-de-datos-lineales)
  - [Listas](#1-listas)
  - [Pilas](#2-pilas-stacks)
  - [Colas](#3-colas-queues)
- [Estructuras de Datos No Lineales](#-estructuras-de-datos-no-lineales)
  - [Árboles](#1-árboles-trees)
  - [Grafos](#2-grafos-graphs)
- [Conceptos Avanzados](#-conceptos-avanzados)
- [Cómo Usar Este Repositorio](#-cómo-usar-este-repositorio)
- [Recursos Externos](#-recursos-externos)

---

## 🎯 Introducción

Las **estructuras de datos** son fundamentales en la programación, ya que nos permiten organizar, gestionar y manipular información de manera eficiente. Una buena elección de estructura de datos puede ser la diferencia entre un programa lento y uno extremadamente rápido.

Este repositorio está diseñado para estudiantes y desarrolladores que buscan:

- ✅ Comprender a fondo cómo funcionan las estructuras de datos
- ✅ Ver implementaciones reales en múltiples lenguajes
- ✅ Aprender con ejemplos visuales y explicaciones paso a paso
- ✅ Prepararse para entrevistas técnicas y exámenes

### Clasificación General

```
Estructuras de Datos
├── Lineales
│   ├── Listas (Estáticas, Enlazadas, Dobles, Circulares)
│   ├── Pilas (LIFO)
│   └── Colas (FIFO)
└── No Lineales
    ├── Árboles (Binarios, ABB)
    └── Grafos (Dirigidos, No Dirigidos, Ponderados)
```

---

## 📊 Estructuras de Datos Lineales

Las estructuras lineales organizan los elementos en una secuencia donde cada elemento (excepto el primero y el último) tiene un predecesor y un sucesor.

### 1. Listas

Las listas son colecciones ordenadas de elementos que permiten **inserción**, **eliminación** y **búsqueda** eficientes según su implementación.

#### 📋 Documentación Teórica

- **[Listas.md](Listas.md)** - Guía técnica completa con diagramas ASCII, complejidades y comparativas

#### 🔹 1.1 Listas Estáticas (Arreglos)

**Características:**

- Tamaño fijo definido en tiempo de compilación
- Acceso directo en O(1) mediante índices
- Memoria contigua
- Inserción/eliminación costosa O(n)

**Implementaciones:**

- [`lista_estatica/lista.cpp`](lista_estatica/lista.cpp) - Arreglo básico en C++
- [`lista_estatica/lista.java`](lista_estatica/lista.java) - Array en Java con explicación de archivadores
- [`lista_estatica/lista.py`](lista_estatica/lista.py) - Listas dinámicas de Python

**Aplicaciones:** Tablas de búsqueda, vectores matemáticos, buffers de tamaño conocido

---

#### 🔹 1.2 Listas Enlazadas Simples

**Características:**

- Tamaño dinámico
- Cada nodo contiene dato + puntero al siguiente
- Recorrido unidireccional
- Inserción/eliminación eficiente O(1) con referencia al nodo

**Implementaciones:**

- [`lista_enlazada/lista.cpp`](lista_enlazada/lista.cpp) - Implementación con punteros en C++
- [`lista_enlazada/lista.java`](lista_enlazada/lista.java) - Implementación detallada con analogía de tren
- [`lista_enlazada/lista.py`](lista_enlazada/lista.py) - Versión Python con comentarios paso a paso

**Operaciones clave:**

- `insertarAlInicio(dato)` - Agrega nodo al frente
- `insertarAlFinal(dato)` - Agrega nodo al final
- `insertarDespuesDeCualquierNodoQueSeaMayorQue(dato, condicion)` - Inserción condicional
- `mostrarLista()` - Recorrido y visualización

**Aplicaciones:** Implementación de pilas, colas, manejo de memoria dinámica

---

#### 🔹 1.3 Listas Doblemente Enlazadas

**Características:**

- Cada nodo tiene puntero al siguiente Y al anterior
- Navegación bidireccional
- Mayor consumo de memoria (2 punteros por nodo)
- Eliminación más eficiente (acceso directo a vecinos)

**Implementaciones:**

- [`lista_doblemente_enlazada/lista.cpp`](lista_doblemente_enlazada/lista.cpp) - Implementación en C++ con punteros dobles
- [`lista_doblemente_enlazada/lista.java`](lista_doblemente_enlazada/lista.java) - Versión Java con `insertarEntre()`
- [`lista_doblemente_enlazada/lista.py`](lista_doblemente_enlazada/lista.py) - Python con navegación bidireccional

**Operaciones especiales:**

- `mostrarListaAdelante()` - Recorrido de cabeza a cola
- `mostrarListaAtras()` - Recorrido de cola a cabeza
- `insertarEntre(dato, anterior, siguiente)` - Inserción entre nodos específicos

**Aplicaciones:** Sistemas de deshacer/rehacer (undo/redo), historial de navegación, editores de texto

---

#### 🔹 1.4 Listas Circulares

**Características:**

- El último nodo apunta al primero (formando un ciclo)
- No existe concepto de "final" (NULL)
- Recorrido infinito posible
- Útil para algoritmos round-robin

**Implementaciones:**

- [`lista_circular/lista.cpp`](lista_circular/lista.cpp) - Lista circular simple en C++
- [`lista_circular/lista.java`](lista_circular/lista.java) - Implementación con manejo de `ultimo`
- [`lista_circular/lista.py`](lista_circular/lista.py) - Versión Python circular

**Aplicaciones:** Planificadores de procesos (Round Robin), buffers circulares, carruseles de imágenes

---

### 2. Pilas (Stacks)

Las pilas siguen el principio **LIFO** (Last In, First Out) - el último elemento en entrar es el primero en salir.

#### 📋 Documentación Teórica

- **[Pilas.md](Pilas.md)** - Guía exhaustiva con diagramas detallados de operaciones, casos de uso y ejercicios resueltos

#### 🎯 Operaciones Fundamentales

- `push(elemento)` - Insertar en el tope (O(1))
- `pop()` - Extraer del tope (O(1))
- `peek()` - Ver el tope sin extraer (O(1))
- `isEmpty()` - Verificar si está vacía (O(1))

#### 💻 Implementaciones Básicas

- [`pilas/pila.cpp`](pilas/pila.cpp) - Implementación con nodos enlazados
- [`pilas/pila.java`](pilas/pila.java) - Versión Java con método `mergePilas()`
- [`pilas/pila.py`](pilas/pila.py) - Implementación Python minimalista

#### 🧮 Aplicaciones Avanzadas

**Balanceo de Paréntesis:**

- [`pilas/pilaCaracter.cpp`](pilas/pilaCaracter.cpp)
- [`pilas/pilaCaracter.java`](pilas/pilaCaracter.java)
- [`pilas/pilaCaracter.py`](pilas/pilaCaracter.py)

Verifican si símbolos `()`, `[]`, `{}` están correctamente emparejados usando pilas. Esencial para compiladores y parsers.

**Transformación de Notaciones (Algoritmo Shunting Yard):**

- [`pilas/infijoTransformador.cpp`](pilas/infijoTransformador.cpp)
- [`pilas/infijoTransformacion.java`](pilas/infijoTransformacion.java)
- [`pilas/infijoTransformador.py`](pilas/infijoTransformador.py)

Convierte expresiones **infijas** (`A + B * C`) a **postfijas** (`A B C * +`) y **prefijas** (`+ A * B C`), respetando precedencia de operadores.

**Casos de uso reales:**

- Call Stack (gestión de llamadas a funciones)
- Historial de navegación (botón "Atrás")
- Evaluación de expresiones matemáticas
- Algoritmos de backtracking

---

### 3. Colas (Queues)

Las colas siguen el principio **FIFO** (First In, First Out) - el primero en entrar es el primero en salir.

#### 📋 Documentación Teórica

- **[Colas.md](Colas.md)** - Explicación detallada con diagramas, ejercicios complementarios y analogías visuales

#### 🎯 Operaciones Fundamentales

- `enqueue(elemento)` - Insertar al final (O(1))
- `dequeue()` - Extraer del frente (O(1))
- `peek()` - Ver el frente sin extraer (O(1))
- `isEmpty()` - Verificar si está vacía (O(1))

#### 💻 Implementaciones

**Colas Básicas (sin límite):**

- [`colas/cola.cpp`](colas/cola.cpp) - Implementación con struct y punteros
- [`colas/cola.java`](colas/cola.java) - Versión Java orientada a objetos
- [`colas/cola.py`](colas/cola.py) - Python con clase Cola

**Colas con Límite (capacity):**

- [`colas/colaConLimite.cpp`](colas/colaConLimite.cpp) - Control de capacidad máxima
- [`colas/colaConLimite.java`](colas/colaConLimite.java) - Java con validación de límites
- [`colas/colaConLimite.py`](colas/colaConLimite.py) - Python con restricción de tamaño

#### 🧩 Operaciones Avanzadas

Las implementaciones incluyen:

- `invertir()` - Inversión recursiva de la cola
- `mostrar()` - Visualización sin modificar estructura
- `unirYOrdenarDosColas()` - Fusión y ordenamiento (Bubble Sort)
- `bubbleSortOrdenar()` - Ordenamiento in-place

**Casos de uso reales:**

- Colas de impresión
- Gestión de tareas en background
- Buffer de teclado
- Algoritmos BFS (Breadth-First Search)
- Sistemas de atención al cliente

---

## 🌳 Estructuras de Datos No Lineales

En las estructuras no lineales, los elementos no siguen una secuencia única sino que pueden tener múltiples conexiones, formando jerarquías o redes.

### 1. Árboles (Trees)

Los árboles son estructuras jerárquicas donde cada nodo tiene un padre (excepto la raíz) y cero o más hijos.

#### 📋 Documentación Teórica

- **[ArbolesLimites.md](ArbolesLimites.md)** - Explicación crucial sobre por qué son necesarios los límites [min, max] en la validación de ABB

#### 🎯 Árbol Binario de Búsqueda (ABB)

**Propiedades:**

- Cada nodo tiene máximo 2 hijos (izquierdo y derecho)
- **Subárbol izquierdo:** todos los valores < raíz
- **Subárbol derecho:** todos los valores > raíz
- Búsqueda, inserción y eliminación en O(log n) promedio

#### 💻 Implementaciones Recursivas

**Versiones Completas:**

- [`arboles/Arbol.cpp`](arboles/Arbol.cpp) - Implementación procedural recursiva en C++
- [`arboles/Arbol.java`](arboles/Arbol.java) - Versión orientada a objetos en Java
- [`arboles/Arbol.py`](arboles/Arbol.py) - Implementación recursiva en Python

**Operaciones implementadas:**

- `insertar(valor)` - Inserción recursiva manteniendo propiedad ABB
- `eliminar(valor)` - Eliminación con 3 casos (hoja, 1 hijo, 2 hijos)
- `encontrarMinimo(nodo)` - Encuentra el sucesor inorden
- Recorridos:
  - `inOrden()` - Izquierda → Raíz → Derecha (orden ascendente)
  - `preOrden()` - Raíz → Izquierda → Derecha
  - `postOrden()` - Izquierda → Derecha → Raíz
- `calcularAltura()` - Altura del árbol (camino más largo)
- `esArbolValido()` - Validación con límites [min, max] propagados

#### 💻 Implementaciones Iterativas

- [`arboles/ArbolBinarioBusquedaIterativo.cpp`](arboles/ArbolBinarioBusquedaIterativo.cpp) - Versión iterativa con algoritmo Morris
- [`arboles/ArbolBinarioBusquedaIterativo.java`](arboles/ArbolBinarioBusquedaIterativo.java) - Java iterativo con Morris
- [`arboles/ArbolBinarioBusquedaIterativo.py`](arboles/ArbolBinarioBusquedaIterativo.py) - Python iterativo con Morris

**Características especiales:**

- Implementa **Algoritmo de Morris** para recorridos sin pila ni recursión (O(1) memoria)
- Evita riesgo de Stack Overflow en árboles profundos
- Más eficiente en memoria para ciertos casos
- Mejor control del flujo de ejecución

**Casos de uso reales:**

- Bases de datos (índices B-Tree)
- Sistemas de archivos
- Autocompletado y diccionarios
- Compiladores (árboles de sintaxis)

---

### 2. Grafos (Graphs)

Los grafos modelan relaciones entre entidades mediante **vértices** (nodos) y **aristas** (conexiones).

#### 📋 Documentación Teórica Completa

- **[Grafos.md](Grafos.md)** - Guía exhaustiva con definiciones, tipos, algoritmos y diagramas visuales
- **[GrafosAlgoritmos.md](GrafosAlgoritmos.md)** - Simulaciones paso a paso de BFS, DFS, detección de ciclos, etc.
- **[FloydWarshall.md](FloydWarshall.md)** - Algoritmos de caminos mínimos (Floyd-Warshall y Warshall)

#### 🔷 Tipos de Grafos Implementados

**1. Grafo Simple (No Dirigido):**

- [`grafos/GrafoSimple.cpp`](grafos/GrafoSimple.cpp)
- [`grafos/GrafoSimple.java`](grafos/GrafoSimple.java)
- [`grafos/GrafoSimple.py`](grafos/GrafoSimple.py)
- [`grafos/GrafoSimpleConColoresSoloDidactico.java`](grafos/GrafoSimpleConColoresSoloDidactico.java) - Versión educativa con colores

**Características:** Aristas bidireccionales, representan relaciones simétricas (amistades, carreteras de doble sentido).

**2. Grafo Dirigido (Dígrafo):**

- [`grafos/GrafoDirigido.cpp`](grafos/GrafoDirigido.cpp)
- [`grafos/GrafoDirigido.java`](grafos/GrafoDirigido.java)
- [`grafos/GrafoDirigido.py`](grafos/GrafoDirigido.py)

**Características:** Aristas con dirección (A→B ≠ B→A), modelan relaciones asimétricas (seguir en Twitter, dependencias de tareas).

**3. Grafo Ponderado:**

- [`grafos/GrafoPonderado.cpp`](grafos/GrafoPonderado.cpp)
- [`grafos/GrafoPonderado.java`](grafos/GrafoPonderado.java)
- [`grafos/GrafoPonderado.py`](grafos/GrafoPonderado.py)

**Características:** Cada arista tiene un peso/costo, usado en problemas de optimización (GPS, logística).

**4. Grafo Conectado:**

- [`grafos/GrafoConectado.cpp`](grafos/GrafoConectado.cpp)
- [`grafos/GrafoConectado.java`](grafos/GrafoConectado.java)
- [`grafos/GrafoConectado.py`](grafos/GrafoConectado.py)

**Características:** Existe camino entre cualquier par de vértices, no hay "islas" aisladas.

#### 🎯 Algoritmos Implementados

**Recorridos:**

- **BFS** (Búsqueda en Anchura) - O(V + E) - Explora por niveles, encuentra camino más corto en grafos no ponderados
- **DFS** (Búsqueda en Profundidad) - O(V + E) - Explora a fondo antes de retroceder, detecta ciclos

**Análisis de Propiedades:**

- `tieneCiclo()` - Detección de ciclos usando DFS
- `contarComponentesConectadas()` - Cuenta "islas" del grafo
- `distanciaMasCorta(inicio, fin)` - BFS con conteo de distancias
- `gradoVertice(v)` - Número de conexiones de un vértice
- `esBipartito()` - Verificación con coloración (2 colores)
- `esArbol()` - Verifica si es conexo y acíclico

**Algoritmos de Caminos:**

- **Floyd-Warshall** - Caminos mínimos entre todos los pares de vértices - [`grafos/GrafoDirigidoFloydWarshall.java`](grafos/GrafoDirigidoFloydWarshall.java)
- **Warshall** - Cierre transitivo (existencia de caminos)

#### 📊 Representaciones

- **Matriz de Adyacencia:** Tabla V×V, rápida para verificar conexiones O(1), pero consume O(V²) memoria
- **Lista de Adyacencia:** Cada vértice tiene lista de vecinos, eficiente para grafos dispersos O(V + E)

**Casos de uso reales:**

- Redes sociales (amistades, seguidores)
- Sistemas GPS (rutas, mapas)
- Internet (páginas web, enlaces)
- Redes neuronales
- Análisis de dependencias (compilación, gestión de proyectos)

---

## 🚀 Conceptos Avanzados

### Recursividad

#### 📋 Documentación

- **[Recursividad.md](Recursividad.md)** - Guía completa con analogías de Inception, visualización de pila de llamadas

**Conceptos clave:**

- **Caso base:** Condición de parada
- **Paso recursivo:** Llamada con problema más pequeño
- Visualización con `factorial(3)` paso a paso
- Comparativa recursividad vs iteración

**Ejemplos en el repositorio:**

- Fibonacci y Tribonacci: [`capacho/tri_fi_bonacci/`](capacho/tri_fi_bonacci/)
  - Python: [`Fibonnaci.py`](capacho/tri_fi_bonacci/Fibonnaci.py), [`Tribonnaci.py`](capacho/tri_fi_bonacci/Tribonnaci.py)
  - Java: [`Fibonacci.java`](capacho/tri_fi_bonacci/Fibonacci.java), [`Tribonacci.java`](capacho/tri_fi_bonacci/Tribonacci.java)
  - Notebooks: [`Fibonnaci.ipynb`](capacho/tri_fi_bonacci/Fibonnaci.ipynb), [`Tribonnaci.ipynb`](capacho/tri_fi_bonacci/Tribonnaci.ipynb)
- Lista doblemente enlazada recursiva: [`recursividad/lista_doblemente_enlazada.java`](recursividad/lista_doblemente_enlazada.java)

---

## 💡 Cómo Usar Este Repositorio

### Para Estudiantes

1. **Lee la documentación teórica** (archivos `.md`) primero para entender los conceptos
2. **Estudia las implementaciones** en tu lenguaje preferido
3. **Ejecuta el código** y observa los resultados
4. **Modifica los ejemplos** para experimentar
5. **Consulta las guías visuales** cuando tengas dudas

### Estructura de Archivos

```
curso-estructura-de-datos/
├── README.md                          ← Estás aquí
├── Listas.md                          ← Teoría de listas
├── Recursividad.md                    ← Teoría de recursividad
├── Grafos.md                          ← Teoría de grafos
├── GrafosAlgoritmos.md               ← Simulaciones de algoritmos
├── ArbolesLimites.md                 ← Validación de ABB
├── FloydWarshall.md                  ← Algoritmos de caminos
│
├── lista_estatica/                    ← Arreglos
│   ├── lista.cpp
│   ├── lista.java
│   └── lista.py
│
├── lista_enlazada/                    ← Listas simples
│   ├── lista.cpp
│   ├── lista.java
│   └── lista.py
│
├── lista_doblemente_enlazada/         ← Listas dobles
│   ├── lista.cpp
│   ├── lista.java
│   └── lista.py
│
├── lista_circular/                    ← Listas circulares
│   ├── lista.cpp
│   ├── lista.java
│   └── lista.py
│
├── pilas/                             ← Pilas (LIFO)
│   ├── README.md                      ← Guía detallada
│   ├── pila.cpp
│   ├── pila.java
│   ├── pila.py
│   ├── pilaCaracter.*                 ← Balanceo de paréntesis
│   └── infijoTransformador.*          ← Conversión de notaciones
│
├── colas/                             ← Colas (FIFO)
│   ├── README.md                      ← Guía detallada
│   ├── cola.cpp
│   ├── cola.java
│   ├── cola.py
│   ├── colaConLimite.*                ← Colas con capacidad
│
├── arboles/                           ← Árboles binarios
│   ├── Arbol.cpp                      ← ABB recursivo C++
│   ├── Arbol.java                     ← ABB recursivo Java
│   ├── Arbol.py                       ← ABB recursivo Python
│   ├── ArbolBinarioBusquedaIterativo.cpp  ← ABB iterativo C++
│   ├── ArbolBinarioBusquedaIterativo.java ← ABB iterativo Java
│   └── ArbolBinarioBusquedaIterativo.py   ← ABB iterativo Python
│
└── grafos/                            ← Grafos
    ├── GrafoSimple.*                  ← No dirigido
    ├── GrafoDirigido.*                ← Dirigido
    ├── GrafoPonderado.*               ← Con pesos
    ├── GrafoConectado.*               ← Conexo
    ├── GrafoDirigidoFloydWarshall.java
    └── GrafoSimpleConColoresSoloDidactico.java
```

### Compilación y Ejecución

**C++:**

```bash
g++ -o programa archivo.cpp
./programa
```

**Java:**

```bash
javac archivo.java
java NombreClase
```

**Python:**

```bash
python archivo.py
# o
python3 archivo.py
```

---

## 📚 Recursos Externos

### Documentación Oficial

- [C++ Reference](https://en.cppreference.com/)
- [Java Documentation](https://docs.oracle.com/en/java/)
- [Python Documentation](https://docs.python.org/3/)

### Tutoriales y Guías

- [GeeksforGeeks - Data Structures](https://www.geeksforgeeks.org/data-structures/)
- [Visualgo - Visualización de Algoritmos](https://visualgo.net/)
- [Big-O Cheat Sheet](https://www.bigocheatsheet.com/)

### Lecturas Recomendadas

- [Techie Delight - Static Linked List in C](https://www.techiedelight.com/static-linked-list-c/)
- [Universidad de Cantabria - Listas y Colas](https://personales.unican.es/corcuerp/progcomp/slides/Listas_Colas.pdf)
- [GeeksforGeeks - Linked List](https://www.geeksforgeeks.org/data-structures/linked-list/)
- [W3Schools - C++ Data Structures](https://www.w3schools.com/cpp/cpp_data_structures.asp)

---

## 🤝 Contribuciones

Este repositorio es de código abierto y acepta contribuciones. Si encuentras errores o quieres agregar implementaciones:

1. Fork el repositorio
2. Crea una rama para tu feature
3. Haz commit de tus cambios
4. Envía un Pull Request

---

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Siéntete libre de usar, modificar y distribuir el código con fines educativos.

---

**¿Preguntas o sugerencias?** Abre un issue en el repositorio.

**⭐ Si este repositorio te ha sido útil, considera darle una estrella en GitHub.**
