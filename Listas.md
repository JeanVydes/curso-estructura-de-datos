# 📝 Listas

## 📚 ¿Qué es una Lista?

Una **lista** es una estructura de datos que permite almacenar una colección de elementos **en orden**.
A diferencia de un arreglo común, una lista puede crecer o modificarse según sea necesario (dependiendo de su tipo).

### Tipos principales:

1. **Listas estáticas (arreglos)**
2. **Listas enlazadas simples**
3. **Listas doblemente enlazadas**
4. **Listas circulares**

---

# 📦 1. Listas Estáticas

### 🔹 Definición

* Implementadas con un **arreglo de tamaño fijo**.
* La posición de cada elemento está determinada por un **índice numérico**.

### 🧩 Representación

```
Índices:    0       1       2       3
Elementos: "Ana"  "Juan" "María" "Pedro"
```

### 🔧 Características

* Acceso directo a cualquier elemento.
* Dificultad al insertar/eliminar (se deben mover elementos).
* No puede cambiar su tamaño una vez creada.

---

# 🔗 2. Listas Enlazadas Simples

### 🔹 Definición

Una **lista enlazada simple** está formada por nodos.
Cada nodo tiene:

* **Dato**
* **Apuntador al siguiente nodo**

### 🧩 Representación

```
┌─────┐    ┌─────┐    ┌─────┐
│ 10  │ →  │ 20  │ →  │ 30  │ → NULL
└─────┘    └─────┘    └─────┘
```

### 🔧 Características

* Crece de manera dinámica.
* Inserción y eliminación más eficientes que en listas estáticas.
* Recorrido solo en un sentido (de inicio a fin).

---

# 🔄 3. Listas Doblemente Enlazadas

### 🔹 Definición

Una **lista doblemente enlazada** permite recorrer los elementos **en ambos sentidos**.
Cada nodo contiene:

* **Dato**
* **Apuntador al siguiente nodo**
* **Apuntador al nodo anterior**

### 🧩 Representación

```
NULL ← ┌─────┐ ⇄ ┌─────┐ ⇄ ┌─────┐ → NULL
       │ 10  │   │ 20  │   │ 30  │
       └─────┘   └─────┘   └─────┘
```

### 🔧 Características

* Más flexibles que las listas simples.
* Se puede recorrer en ambas direcciones.
* Consumen más memoria por el doble de referencias.

---

# 🔁 4. Listas Circulares

### 🔹 Definición

En una **lista circular**, el último nodo no apunta a `NULL`, sino que **vuelve al primero**, formando un ciclo.

### 🧩 Representación

```
┌─────┐    ┌─────┐    ┌─────┐
│ 10  │ →  │ 20  │ →  │ 30  │
└─────┘    └─────┘    └─────┘
   ↑                        │
   └────────────────────────┘
```

### 🔧 Características

* No tienen un inicio/fin claramente definido (es circular).
* Se pueden recorrer infinitamente.
* Muy útiles en aplicaciones con ciclos repetitivos (ej: juegos, buffers).

---

# ⚖️ Comparación de Tipos de Listas

| Tipo de Lista       | Acceso            | Inserción al inicio     | Inserción al final    | Dirección      | Memoria |
| ------------------- | ----------------- | ----------------------- | --------------------- | -------------- | ------- |
| Estática (Arreglo)  | Rápido (índice)   | Lento (desplazamientos) | Rápido si hay espacio | Única          | Baja    |
| Enlazada Simple     | Lento (recorrido) | Rápido                  | Lento                 | Adelante       | Media   |
| Doblemente Enlazada | Lento (recorrido) | Rápido                  | Rápido                | Adelante/Atrás | Alta    |
| Circular            | Lento (recorrido) | Rápido                  | Rápido                | Cíclico        | Media   |

---

# 🎯 Aplicaciones de las Listas

* **Estáticas:** tablas, vectores de acceso rápido.
* **Enlazadas simples:** colas, estructuras dinámicas básicas.
* **Doblemente enlazadas:** historial de navegadores, editores de texto (deshacer/rehacer).
* **Circulares:** juegos por turnos, buffers circulares, relojes de CPU.