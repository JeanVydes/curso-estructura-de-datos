# 🥞 Pilas (Stacks)

## 📚 ¿Qué es una Pila?

Una **pila** es una estructura de datos lineal que sigue el principio **LIFO** (Last In, First Out), lo que significa que el último elemento en entrar es el primero en salir.

### Analogías de la vida real

**1. Pila de platos en cafetería:**
```
     ┌─────────────┐
     │   Plato 4   │ ← Solo puedes tomar este (TOP)
     ├─────────────┤
     │   Plato 3   │
     ├─────────────┤
     │   Plato 2   │
     ├─────────────┤
     │   Plato 1   │ ← Primer plato colocado (BOTTOM)
     └─────────────┘
```

**2. Pila de libros en tu escritorio:**
```
     📚 Libro C ← Lo último que pusiste
     📚 Libro B
     📚 Libro A ← Lo primero que pusiste
```

**3. Función "Deshacer" en un editor:**
```
Acciones realizadas:
┌─────────────┐
│ Borrar texto│ ← Última acción (se deshace primero)
├─────────────┤
│ Cambiar font│
├─────────────┤
│ Escribir "H"│ ← Primera acción
└─────────────┘
```

## 🔧 Operaciones Básicas (Explicación Detallada)

### 1. **PUSH** - Insertar elemento al tope

**¿Qué hace?** Agrega un elemento nuevo en la parte superior de la pila.
**¿Cómo?** Coloca el nuevo elemento encima de todos los existentes.

```
Estado inicial:           Ejecutar push(D):         Estado final:
┌─────┐                      ↓                    ┌─────┐
│  C  │ ← TOP actual        ┌─────┐                │  D  │ ← NUEVO TOP
├─────┤                     │  D  │                ├─────┤
│  B  │                     └─────┘                │  C  │
├─────┤                        │                   ├─────┤
│  A  │                        │                   │  B  │
└─────┘                        ▼                   ├─────┤
                          Se coloca encima         │  A  │
                                                   └─────┘
```

**Pseudocódigo:**
```
función push(elemento):
    colocar elemento en la posición TOP
    incrementar el tamaño de la pila
    actualizar TOP al nuevo elemento
```

### 2. **POP** - Extraer elemento del tope

**¿Qué hace?** Remueve y devuelve el elemento que está en la parte superior.
**¿Cómo?** Elimina el TOP actual y el elemento debajo se convierte en el nuevo TOP.

```
Estado inicial:           Ejecutar pop():           Estado final:
┌─────┐                      ↑                    ┌─────┐
│  D  │ ← Se extrae         ┌─────┐                │  C  │ ← NUEVO TOP
├─────┤                     │  D  │                ├─────┤
│  C  │                     └─────┘                │  B  │
├─────┤                   (retorna D)              ├─────┤
│  B  │                                            │  A  │
├─────┤                                            └─────┘
│  A  │
└─────┘
```

**Casos especiales:**
- Si la pila está vacía → **Error: Stack Underflow**
- El elemento extraído se **retorna** y se **elimina** de la pila

### 3. **PEEK/TOP** - Ver el elemento superior sin extraerlo

**¿Qué hace?** Permite ver qué elemento está en el tope sin modificar la pila.
**¿Cómo?** Lee el valor del TOP pero no lo elimina.

```
                         peek()
┌─────┐                    ↓                    ┌─────┐
│  C  │ ← Lee este valor  "C"                  │  C  │ ← Sigue igual
├─────┤                (retorna C)             ├─────┤
│  B  │                                        │  B  │
├─────┤                                        ├─────┤
│  A  │                La pila NO cambia       │  A  │
└─────┘                                        └─────┘
```

**Utilidad:** Útil para tomar decisiones sin modificar la pila.

### 4. **isEmpty()** - Verificar si la pila está vacía

```
Pila VACÍA:              Pila CON DATOS:
┌─────┐                  ┌─────┐
│     │ ← Sin elementos  │  A  │ ← Tiene elementos
│     │                  └─────┘
│     │
└─────┘

isEmpty() → true         isEmpty() → false
```

### 5. **size()** - Obtener cantidad de elementos

```
┌─────┐
│  D  │ ← Elemento 4
├─────┤
│  C  │ ← Elemento 3
├─────┤
│  B  │ ← Elemento 2
├─────┤
│  A  │ ← Elemento 1
└─────┘

size() → 4 elementos
```

## 📊 Ejemplo Completo Paso a Paso

Simulemos una sesión completa de operaciones:

```
INICIO: Pila vacía
┌─────┐
│     │  isEmpty() → true, size() → 0
└─────┘

PASO 1: push(5)
┌─────┐
│  5  │ ← TOP (único elemento)
└─────┘
Estado: isEmpty() → false, size() → 1, peek() → 5

PASO 2: push(10)
┌─────┐
│ 10  │ ← TOP (nuevo)
├─────┤
│  5  │
└─────┘
Estado: size() → 2, peek() → 10

PASO 3: push(3)
┌─────┐
│  3  │ ← TOP (nuevo)
├─────┤
│ 10  │
├─────┤
│  5  │
└─────┘
Estado: size() → 3, peek() → 3

PASO 4: peek() → retorna 3 (NO modifica pila)
┌─────┐
│  3  │ ← TOP (sigue igual)
├─────┤
│ 10  │
├─────┤
│  5  │
└─────┘
Resultado: peek() devuelve 3, pila sin cambios

PASO 5: pop() → extrae y retorna 3
┌─────┐
│ 10  │ ← TOP (ahora es 10)
├─────┤
│  5  │
└─────┘
Resultado: pop() devuelve 3, size() → 2

PASO 6: push(7)
┌─────┐
│  7  │ ← TOP (nuevo)
├─────┤
│ 10  │
├─────┤
│  5  │
└─────┘
Estado final: size() → 3, peek() → 7
```

## 🎯 Aplicaciones Detalladas

### 1. **Balanceo de Paréntesis/Símbolos (EXPLICACIÓN COMPLETA)**

**Problema:** Verificar si una expresión tiene símbolos de apertura y cierre correctamente balanceados.

**Símbolos válidos:** `()`, `[]`, `{}`

**¿Cómo funciona el algoritmo?**

**Reglas:**
1. Para cada símbolo de **apertura** `(`, `[`, `{` → **PUSH** a la pila
2. Para cada símbolo de **cierre** `)`, `]`, `}` → **POP** de la pila y verificar que coincida
3. Al final, la pila debe estar **vacía**

**Ejemplo 1: Expresión BALANCEADA `"{[()]}"`**

```
Expresión: { [ ( ) ] }
           ↑

Paso 1: Leer '{'
┌─────┐
│  {  │ ← push('{')
└─────┘

Paso 2: Leer '['
┌─────┐
│  [  │ ← push('[')
├─────┤
│  {  │
└─────┘

Paso 3: Leer '('
┌─────┐
│  (  │ ← push('(')
├─────┤
│  [  │
├─────┤
│  {  │
└─────┘

Paso 4: Leer ')' (símbolo de cierre)
┌─────┐
│  [  │ ← nuevo TOP
├─────┤     ↑
│  {  │   pop() devuelve '('
└─────┘   
¿'(' corresponde con ')'? ✅ SÍ

Paso 5: Leer ']' (símbolo de cierre)
┌─────┐
│  {  │ ← nuevo TOP
└─────┘     ↑
          pop() devuelve '['
¿'[' corresponde con ']'? ✅ SÍ

Paso 6: Leer '}' (símbolo de cierre)
┌─────┐
│     │ ← pila vacía
└─────┘     ↑
          pop() devuelve '{'
¿'{' corresponde con '}'? ✅ SÍ

RESULTADO: Pila vacía → ✅ BALANCEADO
```

**Ejemplo 2: Expresión NO BALANCEADA `"({[})]"`**

```
Expresión: ( { [ } ) ]
           ↑

Pasos 1-3: Igual que antes
┌─────┐
│  [  │ ← después de leer '(', '{', '['
├─────┤
│  {  │
├─────┤
│  (  │
└─────┘

Paso 4: Leer '}' (símbolo de cierre)
┌─────┐
│  {  │ ← nuevo TOP
├─────┤     ↑
│  (  │   pop() devuelve '['
└─────┘   
¿'[' corresponde con '}'? ❌ NO (error detectado)

RESULTADO: ❌ NO BALANCEADO
```

### 2. **Inversión de Cadenas**

**Problema:** Invertir el orden de caracteres en una palabra.

**¿Cómo funciona?**
1. **PUSH** cada carácter de la cadena original
2. **POP** todos los caracteres (salen en orden inverso)

```
Cadena original: "HOLA"

Fase 1 - Insertar caracteres:
Leer 'H': ┌─────┐
          │  H  │
          └─────┘

Leer 'O': ┌─────┐
          │  O  │
          ├─────┤
          │  H  │
          └─────┘

Leer 'L': ┌─────┐
          │  L  │
          ├─────┤
          │  O  │
          ├─────┤
          │  H  │
          └─────┘

Leer 'A': ┌─────┐
          │  A  │ ← TOP
          ├─────┤
          │  L  │
          ├─────┤
          │  O  │
          ├─────┤
          │  H  │
          └─────┘

Fase 2 - Extraer caracteres:
pop() → 'A'  ┌─────┐
             │  L  │ ← nuevo TOP
             ├─────┤
             │  O  │
             ├─────┤
             │  H  │
             └─────┘

pop() → 'L'  ┌─────┐
             │  O  │ ← nuevo TOP
             ├─────┤
             │  H  │
             └─────┘

pop() → 'O'  ┌─────┐
             │  H  │ ← último
             └─────┘

pop() → 'H'  ┌─────┐
             │     │ ← vacía
             └─────┘

Resultado: "A" + "L" + "O" + "H" = "ALOH"
```

### 3. **Transformación Infijo a Postfijo (Algoritmo Shunting Yard)**

**Problema:** Convertir expresiones matemáticas de notación infija a postfija.

**Notaciones:**
- **Infija:** `A + B * C` (operador entre operandos)
- **Postfija:** `A B C * +` (operador después de operandos)

**¿Por qué es útil?** Las expresiones postfijas son más fáciles de evaluar para las computadoras.

**Reglas del algoritmo:**
1. **Operandos** (A, B, C) → directamente a la salida
2. **Operadores** (+, -, *, /) → se manejan con la pila según precedencia
3. **Paréntesis** → controlan el orden de evaluación

**Precedencias:**
- `*`, `/` → Alta precedencia (se evalúan primero)
- `+`, `-` → Baja precedencia

**Ejemplo: `A + B * C`**

```
Expresión: A + B * C
Salida: ""
Pila: []

Paso 1: Leer 'A' (operando)
Salida: "A"
Pila: ┌─────┐
      │     │ (sin cambios)
      └─────┘

Paso 2: Leer '+' (operador)
Salida: "A"
Pila: ┌─────┐
      │  +  │ ← push('+')
      └─────┘

Paso 3: Leer 'B' (operando)
Salida: "A B"
Pila: ┌─────┐
      │  +  │ (sin cambios)
      └─────┘

Paso 4: Leer '*' (operador)
Precedencia: * > + → push('*')
Salida: "A B"
Pila: ┌─────┐
      │  *  │ ← push('*')
      ├─────┤
      │  +  │
      └─────┘

Paso 5: Leer 'C' (operando)
Salida: "A B C"
Pila: ┌─────┐
      │  *  │ (sin cambios)
      ├─────┤
      │  +  │
      └─────┘

Paso 6: Final - vaciar pila
pop('*'): Salida: "A B C *"
Pila: ┌─────┐
      │  +  │
      └─────┘

pop('+'): Salida: "A B C * +"
Pila: ┌─────┐
      │     │ (vacía)
      └─────┘

RESULTADO FINAL: "A B C * +"
```

**Verificación:** `A B C * +` significa:
1. Tomar B y C, aplicar * → `B * C`
2. Tomar A y el resultado anterior, aplicar + → `A + (B * C)`
3. ¡Equivale a la expresión original!

### 4. **Evaluación de Expresiones Postfijas**

**Problema:** Calcular el resultado de una expresión en notación postfija.

**Algoritmo:**
1. **Números** → PUSH a la pila
2. **Operadores** → POP dos números, operar, PUSH resultado

**Ejemplo: `"2 3 4 * +"`** (equivale a `2 + 3 * 4 = 14`)

```
Expresión: 2 3 4 * +

Paso 1: Leer '2'
┌─────┐
│  2  │ ← push(2)
└─────┘

Paso 2: Leer '3'
┌─────┐
│  3  │ ← push(3)
├─────┤
│  2  │
└─────┘

Paso 3: Leer '4'
┌─────┐
│  4  │ ← push(4)
├─────┤
│  3  │
├─────┤
│  2  │
└─────┘

Paso 4: Leer '*' (operador)
pop() → 4, pop() → 3
Calcular: 3 * 4 = 12
┌─────┐
│ 12  │ ← push(12)
├─────┤
│  2  │
└─────┘

Paso 5: Leer '+' (operador)
pop() → 12, pop() → 2
Calcular: 2 + 12 = 14
┌─────┐
│ 14  │ ← push(14)
└─────┘

RESULTADO: 14
```

### 5. **Gestión de Llamadas a Funciones (Call Stack)**

**Problema:** El sistema operativo necesita recordar qué función llamó a cuál.

```
main() llama a funcionA()
funcionA() llama a funcionB()
funcionB() llama a funcionC()

Call Stack:
┌─────────────┐
│ funcionC()  │ ← Ejecutándose ahora
├─────────────┤
│ funcionB()  │ ← Esperando
├─────────────┤
│ funcionA()  │ ← Esperando
├─────────────┤
│   main()    │ ← Esperando
└─────────────┘

Cuando funcionC() termina:
┌─────────────┐
│ funcionB()  │ ← Continúa ejecutándose
├─────────────┤
│ funcionA()  │
├─────────────┤
│   main()    │
└─────────────┘
```

## 🚨 Casos Límite y Errores Comunes

### 1. **Stack Overflow**
```
Capacidad máxima: 3 elementos

┌─────┐
│  C  │ ← Posición 3 (llena)
├─────┤
│  B  │ ← Posición 2
├─────┤
│  A  │ ← Posición 1
└─────┘

push(D) → ❌ ERROR: Stack Overflow!
```

### 2. **Stack Underflow**
```
┌─────┐
│     │ ← Pila completamente vacía
└─────┘

pop() → ❌ ERROR: Stack Underflow!
peek() → ❌ ERROR: No hay elementos!
```

### 3. **Errores en Balanceo**
```
❌ "(((" → Faltan símbolos de cierre
❌ ")))" → Faltan símbolos de apertura  
❌ "([)]" → Orden incorrecto de cierre
✅ "([])" → Correcto
```

## 💡 Complejidad Temporal y Espacial

| Operación | Tiempo | Espacio | Explicación |
|-----------|---------|---------|-------------|
| push()    | O(1)    | O(1)    | Insertar al final del array |
| pop()     | O(1)    | O(1)    | Eliminar del final del array |
| peek()    | O(1)    | O(1)    | Acceder al último elemento |
| isEmpty() | O(1)    | O(1)    | Verificar si tamaño es 0 |
| size()    | O(1)    | O(1)    | Retornar variable contador |

**Espacio total:** O(n) donde n es el número máximo de elementos.

## 🎮 Ejercicios Resueltos para Practicar

### Ejercicio 1: Traza Completa
**Realizar estas operaciones y mostrar el estado después de cada una:**

```
Operaciones: push(1), push(2), pop(), push(3), peek(), pop(), size()

Solución:
Inicial: []

1. push(1): [1] ← TOP
2. push(2): [1,2] ← TOP
3. pop(): [1] ← TOP (devuelve 2)
4. push(3): [1,3] ← TOP  
5. peek(): [1,3] ← TOP (devuelve 3, sin cambios)
6. pop(): [1] ← TOP (devuelve 3)
7. size(): [1] (devuelve 1)
```

### Ejercicio 2: Balanceo Detallado
**¿Están balanceadas? Mostrar el proceso:**

**a) `"((()))"`**
```
( → push: [(]
( → push: [(,(]  
( → push: [(,(,(]
) → pop: [(,(] ✓ coincide
) → pop: [(] ✓ coincide  
) → pop: [] ✓ coincide
Pila vacía → ✅ BALANCEADO
```

**b) `"({[})]"`**
```
( → push: [(]
{ → push: [(,{]
[ → push: [(,{,[]
} → pop: [(,{] devuelve [
¿[ corresponde con }? ❌ NO
RESULTADO: ❌ NO BALANCEADO
```

### Ejercicio 3: Postfijo Paso a Paso
**Evaluar `"5 3 2 * +"`:**

```
5 → push: [5]
3 → push: [5,3]
2 → push: [5,3,2]
* → pop(2), pop(3): 3*2=6, push: [5,6]
+ → pop(6), pop(5): 5+6=11, push: [11]
RESULTADO: 11
```

## 📝 Tips Importantes para Parciales

### ✅ Qué SÍ hacer:
- **Siempre dibujar** la pila antes de resolver
- **Verificar casos límite**: pila vacía, pila llena
- **Recordar que pop() RETORNA y ELIMINA** el elemento
- **En balanceo**: seguir el orden exacto de símbolos
- **En postfijo**: los operadores toman los dos últimos números

### ❌ Errores comunes:
- Confundir LIFO con FIFO
- Olvidar que peek() NO modifica la pila
- En balanceo: no verificar si la pila queda vacía al final
- En transformaciones: no respetar la precedencia de operadores
- Intentar hacer pop() en pila vacía
- No actualizar el TOP después de push() o pop()