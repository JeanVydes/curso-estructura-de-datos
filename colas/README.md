# 🚎 Colas (Queues)

## 📚 ¿Qué es una Cola?

Una **cola** es una estructura de datos lineal que sigue el principio **FIFO** (*First In, First Out*), lo que significa que **el primero en entrar es el primero en salir**.

En una cola los elementos **se agregan por un extremo (Final)** y **se eliminan por el otro (Frente)**.

---

### Analogías de la vida real

**1. Fila en el supermercado:**

```
Cliente 1  Cliente 2  Cliente 3
   ↑          ↑          ↑
 Atienden primero a Cliente 1 (Frente)
 Los nuevos llegan al Final de la fila
```

**2. Fila para abordar un bus:**

```
Persona A   Persona B   Persona C
   ↑
La Persona A sube primero al bus (Frente)
Las demás esperan su turno
```

**3. Impresora compartida en la oficina:**

```
Documentos en cola de impresión:
┌───────────────┐
│  Documento 1  │ ← Se imprime primero
├───────────────┤
│  Documento 2  │
├───────────────┤
│  Documento 3  │ ← Llegó último (Final)
└───────────────┘
```

---

## 🔧 Operaciones Básicas (Explicación Detallada)

### 1. **ENQUEUE** – Insertar elemento al final

**¿Qué hace?**
Agrega un nuevo elemento al final de la cola.

**¿Cómo?**
Se coloca el nuevo valor detrás de todos los que ya estaban en la fila.

```
Estado inicial:          Ejecutar enqueue(40):          Estado final:
┌─────┐                                                ┌─────┐
│ 10  │ ← Frente                                       │ 10  │ ← Frente
├─────┤                                                ├─────┤
│ 20  │                                                │ 20  │
├─────┤                                                ├─────┤
│ 30  │ ← Final actual                                 │ 30  │
└─────┘                                                ├─────┤
                                                       │ 40  │ ← NUEVO Final
                                                       └─────┘
```

---

### 2. **DEQUEUE** – Extraer elemento del frente

**¿Qué hace?**
Quita y devuelve el elemento que está en el frente.

**¿Cómo?**
El primer valor de la cola es eliminado, y el siguiente pasa a ser el nuevo frente.

```
Estado inicial:            Ejecutar dequeue():           Estado final:
┌─────┐                        ↑                       ┌─────┐
│ 10  │ ← Frente actual       ┌─────┐                  │ 20  │ ← NUEVO Frente
├─────┤  ← Se quita           │ 10  │                  ├─────┤
│ 20  │                       └─────┘                  │ 30  │ ← Final
├─────┤                     (retorna 10)               └─────┘
│ 30  │ ← Final
└─────┘
```

---

### 3. **PEEK/FRONT** – Ver el elemento del frente sin extraerlo

**¿Qué hace?**
Muestra quién está primero en la cola **sin sacarlo**.

```
Estado actual:                 Ejecutar front():          Estado final:
┌─────┐                           ↓                     ┌─────┐
│ 10  │ ← Frente                retorna "10"            │ 10  │ ← Frente
├─────┤                                                 ├─────┤
│ 20  │                                                 │ 20  │
├─────┤                                                 ├─────┤
│ 30  │ ← Final                                         │ 30  │ ← Final
└─────┘                                                 └─────┘
```

---

### 4. **isEmpty()** – Verificar si la cola está vacía

```
Cola VACÍA:                 Cola CON DATOS:
┌─────┐                     ┌─────┐
│     │ ← Sin elementos     │  A  │ ← Frente
│     │                     ├─────┤
│     │                     │  B  │ ← Final
└─────┘                     └─────┘

isEmpty() → true            isEmpty() → false
```

---

### 5. **size()** – Obtener cantidad de elementos

```
┌─────┐
│  A  │ ← Frente (1)
├─────┤
│  B  │ (2)
├─────┤
│  C  │ (3)
├─────┤
│  D  │ ← Final (4)
└─────┘

size() → 4 elementos
```

---

## 📊 Ejemplo Completo Paso a Paso

Simulemos una sesión con varias operaciones:

```
INICIO: Cola vacía
┌─────┐
│     │  isEmpty() → true, size() → 0
└─────┘
```

**PASO 1: enqueue(10)**

```
┌─────┐
│ 10  │ ← Frente y Final
└─────┘
size() → 1
```

**PASO 2: enqueue(20)**

```
┌─────┐
│ 10  │ ← Frente
├─────┤
│ 20  │ ← Final
└─────┘
size() → 2
```

**PASO 3: enqueue(30)**

```
┌─────┐
│ 10  │ ← Frente
├─────┤
│ 20  │
├─────┤
│ 30  │ ← Final
└─────┘
size() → 3
```

**PASO 4: front() → retorna 10 (NO modifica la cola)**

```
┌─────┐
│ 10  │ ← Frente
├─────┤
│ 20  │
├─────┤
│ 30  │ ← Final
└─────┘
```

**PASO 5: dequeue() → extrae 10**

```
┌─────┐
│ 20  │ ← Nuevo Frente
├─────┤
│ 30  │ ← Final
└─────┘
size() → 2
```

**PASO 6: enqueue(40)**

```
┌─────┐
│ 20  │ ← Frente
├─────┤
│ 30  │
├─────┤
│ 40  │ ← Final
└─────┘
size() → 3
```

---

## 🚨 Casos Límite y Errores Comunes

### 1. **Cola llena (con límite)**

```
Capacidad máxima = 3

┌─────┐
│  A  │
├─────┤
│  B  │
├─────┤
│  C  │ ← Final
└─────┘

enqueue(D) → ❌ ERROR: Cola llena
```

### 2. **Cola vacía**

```
┌─────┐
│     │ ← Sin elementos
└─────┘

dequeue() → ❌ ERROR: Cola vacía
front()   → ❌ ERROR: No hay elementos
```

Perfecto 🙌 ya entiendo: quieres un **complemento explicativo** al README de **colas**, pero enfocado en **estos ejercicios extra** (invertir, mostrar, unir y ordenar con bubble sort), sin código todavía, sino con explicaciones sencillas + dibujitos con cajitas como en el de pilas.

Aquí te dejo la propuesta:

---

# 🧩 Ejercicios Complementarios con Colas

Ahora que ya entendemos qué es una cola y cómo funcionan sus operaciones básicas, vamos a ver algunos **ejercicios prácticos clásicos** que se hacen con colas. Estos ejemplos te ayudarán a ver cómo se aplican las operaciones `enqueue` y `dequeue` en problemas reales.

---

## 🔄 1. Invertir una Cola (con Recursividad)

**Problema:**
Queremos que el **primer elemento** de la cola termine siendo el **último**, y el **último** pase a ser el **primero**.

**Idea principal:**

* Sacar (dequeue) todos los elementos, guardándolos en la memoria de la recursión.
* Cuando la recursión termina, volver a insertar (enqueue) los elementos en orden inverso.

**Ejemplo paso a paso:**

Cola original:

```
Frente → [10] → [20] → [30] → [40] ← Final
```

Proceso de extracción recursiva:

```
Quitar 10
Frente → [20] → [30] → [40]

Quitar 20
Frente → [30] → [40]

Quitar 30
Frente → [40]

Quitar 40
Frente → (vacío)
```

Ahora que la cola quedó **vacía**, se empiezan a reinsertar en orden inverso:

```
Insertar 40 → Frente → [40]
Insertar 30 → Frente → [40] → [30]
Insertar 20 → Frente → [40] → [30] → [20]
Insertar 10 → Frente → [40] → [30] → [20] → [10]
```

**Resultado final (invertida):**

```
Frente → [40] → [30] → [20] → [10] ← Final
```

---

## 👀 2. Mostrar la Cola

**Problema:**
Necesitamos **ver el contenido de la cola** de inicio a fin, sin modificarla.

**Idea principal:**

* Usar un puntero temporal (`actual`) para recorrer desde el frente hasta el final.
* Imprimir cada elemento en el orden en que está.

**Ejemplo:**

Cola original:

```
Frente → [5] → [8] → [12] → [20] ← Final
```

Recorrido:

```
Cola: 5 8 12 20
```

La cola **no cambia**, solo se observa.

---

## 🔗 3. Unir Dos Colas

**Problema:**
Queremos juntar el contenido de **dos colas diferentes** en una sola cola final.

**Idea principal:**

* Crear una nueva cola vacía.
* Pasar todos los elementos de la primera cola.
* Luego, pasar todos los elementos de la segunda cola.

**Ejemplo:**

Cola 1:

```
Frente → [1] → [3] → [5]
```

Cola 2:

```
Frente → [2] → [4] → [6]
```

Cola final unida:

```
Frente → [1] → [3] → [5] → [2] → [4] → [6] ← Final
```

---

## 📊 4. Ordenar una Cola con Bubble Sort

**Problema:**
Una vez que tenemos una cola llena, queremos **ordenar sus elementos** de menor a mayor (o al revés).

**Idea principal (burbuja):**

* Comparar pares de elementos consecutivos.
* Si están en el orden incorrecto, se intercambian.
* Repetir el proceso hasta que ya no haya cambios.

**Ejemplo:**

Cola original:

```
Frente → [5] → [1] → [4] → [2] ← Final
```

**Primera pasada (comparaciones):**

```
Comparo 5 y 1 → los intercambio → [1] [5] [4] [2]
Comparo 5 y 4 → los intercambio → [1] [4] [5] [2]
Comparo 5 y 2 → los intercambio → [1] [4] [2] [5]
```

**Segunda pasada:**

```
Comparo 1 y 4 → ok
Comparo 4 y 2 → intercambio → [1] [2] [4] [5]
Comparo 4 y 5 → ok
```

Ahora ya no hay más intercambios.
**Cola final ordenada:**

```
Frente → [1] → [2] → [4] → [5] ← Final
```

---

## 🚨 Nota Importante

El método de **Bubble Sort** no es muy eficiente para colas grandes porque requiere muchas comparaciones (O(n²)). Pero sirve para practicar cómo manipular los elementos dentro de una cola.