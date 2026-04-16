# 📚 ¿Qué es la Recursividad?

La **recursividad** es un paradigma de programación donde una función se resuelve a sí misma en una versión más pequeña. Es como el concepto de la película *Inception*: para resolver un sueño, te sumerges en otro sueño más profundo, hasta llegar a uno tan simple que puedes resolverlo directamente y luego "despertar" en cada nivel, trayendo contigo la solución.

Toda función recursiva se sostiene sobre dos pilares fundamentales:

1. **Caso Base:** La condición que detiene la "inmersión". Es el sueño más simple, el problema tan pequeño que tiene una respuesta inmediata. Sin esto, la función se llamaría a sí misma infinitamente, causando un **desbordamiento de pila (Stack Overflow)**.
2. **Paso Recursivo:** La parte de la función que se llama a sí misma, pero con un problema ligeramente más pequeño, acercándose cada vez más al caso base.

## ⚙️ Anatomía de una Llamada Recursiva: `factorial(4)`

Para visualizarlo, analizaremos la función matemática `factorial(n)`, que calcula la multiplicación de todos los números desde `n` hasta `1`.

### 🔹 Definición Técnica

La función se define por dos reglas fundamentales:

- **Paso Recursivo:** `factorial(n) = n * factorial(n - 1)`
- **Caso Base:** `factorial(1) = 1`

Vamos a seguir el viaje de `factorial(4)` paso a paso, viendo cómo se sumerge hasta el fondo y cómo regresa con la solución.

```text
DIAGRAMA DEL PROCESO (Factorial de 4):
=======================================================
factorial(4)
 |__ devuelve: 4 * factorial(3)
                   |__ devuelve: 3 * factorial(2)
                                     |__ devuelve: 2 * factorial(1)
                                                       |__ devuelve: 1 (Caso Base)

EL REGRESO (Desenrollando la pila de llamadas):
2 * 1 = 2
3 * 2 = 6
4 * 6 = 24  <-- Resultado Final
```

### 🚀 Fase 1: La Inmersión (Apilando Llamadas)

Cada llamada a la función es un nuevo "nivel de sueño" (un marco en la pila de llamadas del sistema) que queda en pausa, esperando a que el nivel más profundo se resuelva.

- **Paso 1:** `factorial(4)` no sabe su respuesta. Llama a `factorial(3)` y se pausa.
- **Paso 2:** `factorial(3)` tampoco lo sabe. Llama a `factorial(2)` y se pausa.
- **Paso 3:** `factorial(2)` hace lo mismo llamando a `factorial(1)`.
- **Paso 4 (Caso Base):** `factorial(1)` sí sabe la respuesta. No necesita llamar a nadie más, simplemente **devuelve 1**. ¡Aquí tocamos fondo y empieza el regreso!

### ✨ Fase 2: El Desenlace (Desenrollando la pila)

La solución del nivel más profundo permite que cada nivel superior "despierte", complete su multiplicación pendiente y pase el resultado hacia arriba.

- **Primer despertar:** `factorial(2)` recibe el `1`. Multiplica `2 * 1 = 2` y lo devuelve.
- **Segundo despertar:** `factorial(3)` recibe el `2`. Multiplica `3 * 2 = 6` y lo devuelve.
- **Regreso final:** `factorial(4)` recibe el `6`. Multiplica `4 * 6 = 24` y entrega el resultado final al programa principal. La pila de llamadas vuelve a quedar vacía.

---

## 🤯 Tipos de Recursividad

No todas las funciones recursivas se comportan igual. Dependiendo de dónde y cómo se haga la llamada, se clasifican en:

1. **Recursividad Lineal (Simple):** La función se llama a sí misma **una sola vez** por cada ejecución. El ejemplo del `factorial` y recorrer una lista es recursividad lineal.
2. **Recursividad Múltiple (Ramificada):** La función hace **dos o más llamadas** a sí misma por ejecución. Esto genera un *árbol de llamadas* en lugar de una línea recta (por ejemplo, recorrer un árbol binario o la sucesión de Fibonacci).
3. **Recursividad de Cola (Tail Recursion):** Es una optimización vital en programación funcional. Ocurre cuando la llamada recursiva es **absolutamente la última operación** que se ejecuta, sin dejar ningún cálculo pendiente (como la multiplicación en el factorial). Algunos lenguajes compilan esto como un bucle tradicional, salvando toda la memoria de la Pila de Llamadas.

---

## 📝 Ejercicios Prácticos y Modelos Comunes

### Ejercicio 1: Sucesión de Fibonacci (Recursividad Múltiple)

La serie de Fibonacci es: `0, 1, 1, 2, 3, 5, 8, 13...` 
Cada número es la suma de los dos anteriores. Su fórmula recursiva es:
`fib(n) = fib(n-1) + fib(n-2)`
*Con dos casos base:* `fib(0) = 0` y `fib(1) = 1`

```text
DIAGRAMA DEL PROCESO (Fibonacci de 3):
=======================================================
fib(3)
 |__ pide sumar: fib(2) + fib(1)
                   |         |
                   |         |__ devuelve: 1 (Caso Base)
                   |
                   |__ pide sumar: fib(1) + fib(0)
                                     |        |
                                     |        |__ devuelve: 0 (Caso Base)
                                     |
                                     |__ devuelve: 1 (Caso Base)

EL REGRESO (Subiendo por las ramas):
- fib(2) resuelve: 1 + 0 = 1
- fib(3) resuelve: 1 (de fib(2)) + 1 (de fib(1)) = 2 <-- Resultado Final
```
*A diferencia del factorial, esta estrategia "múltiple" consume mucho más tiempo si no se memorizan los resultados anteriores, porque recalcula las mismas ramas docenas de veces (por ejemplo, `fib(1)` y `fib(0)` se calcularían muchísimas veces en `fib(50)`).*

### Ejercicio 2: Invertir una Palabra (Manejo de la pila)

Queremos invertir un string, por ejemplo: `"HOLA" -> "ALOH"`.
**Paso recursivo:** Toma el primer carácter, invierte el resto, y pon ese primer carácter al final.
**Caso base:** Si la palabra está vacía o tiene un solo carácter, devuélvela igual.

```text
DIAGRAMA DEL PROCESO: invert("HOLA")
=======================================================
invert("HOLA")
 |__ devuelve: invert("OLA") + "H"
                 |__ devuelve: invert("LA") + "O"
                                 |__ devuelve: invert("A") + "L"
                                                 |__ devuelve: "A" (Caso Base)

EL REGRESO:
- "A" + "L" = "AL"
- "AL" + "O" = "ALO"
- "ALO" + "H" = "ALOH" <-- Resultado Final
```

---

## ⚠️ Errores Típicos en Recursividad

1. **Olvidar el Caso Base:** Produce el temido error infinito *Stack Overflow*. La recursión literalmente nunca para.
2. **Paso Recursivo estancado:** Hacer la llamada a la función pero con exactamente los mismos parámetros (ej. `factorial(n)` llama a `factorial(n)` en vez de `n-1`). Esto nunca avanza al caso base y también causa overflow.
3. **No propagar la devolución (return):** Confundir el retorno de la llamada profunda y "perder" el resultado olvidando poner la palabra de retorno antes de la propia llamada a la función.

---

### 🔧 Características Clave

* **Pila de Llamadas (Call Stack):** La recursividad depende internamente de una pila para gestionar las llamadas pendientes y sus variables locales.
* **Consumo de Memoria:** Cada llamada recursiva consume espacio en la pila. Una "inmersión" demasiado profunda sin un caso base adecuado puede agotarla.
* **Elegancia vs. Rendimiento:** El código recursivo puede ser muy limpio y elegante, pero a menudo es menos eficiente en tiempo y memoria que su contraparte iterativa (usando bucles).

## ⚖️ Comparativa: Recursividad vs. Iteración

| Característica                | Recursividad                                                                     | Iteración (Bucles)                                                            |
| :----------------------------- | :------------------------------------------------------------------------------- | :----------------------------------------------------------------------------- |
| **Claridad del Código** | **Muy alta** para problemas de naturaleza recursiva (árboles, fractales). | **Muy alta** para tareas secuenciales y repetitivas (recorrer arreglos). |
| **Rendimiento**          | Más lento debido a la sobrecarga de las llamadas a funciones.                   | **Más rápido**, sin sobrecarga de llamadas.                            |
| **Uso de Memoria**       | Alto, usa la pila de llamadas. Riesgo de*Stack Overflow*.                      | **Bajo**, usa memoria para variables de control del bucle.               |
| **Complejidad**          | El estado se gestiona implícitamente en la pila.                                | Requiere gestionar el estado manualmente con variables (contadores, etc.).     |

## 🎯 Aplicaciones Técnicas

* **Recorrido de Estructuras de Datos No Lineales:** Es la forma más natural de navegar **Árboles** (recorrido in-order, pre-order, post-order) y **Grafos** (Búsqueda en Profundidad - DFS).
* **Algoritmos de "Divide y Vencerás":** Problemas que se dividen en subproblemas idénticos, como los algoritmos de ordenamiento **Merge Sort** y **Quick Sort**.
* **Backtracking:** Para resolver problemas de búsqueda de caminos, como laberintos o Sudokus, donde se exploran opciones y se "retrocede" si un camino no lleva a una solución.
* **Generación de Fractales:** Figuras geométricas que son recursivas por naturaleza, como el copo de nieve de Koch o el triángulo de Sierpinski.
