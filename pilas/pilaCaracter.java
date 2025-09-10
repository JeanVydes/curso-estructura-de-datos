package pilas;

public class pilaCaracter {
    // Clase interna para el nodo. La estructura es la misma que una pila normal,
    // pero almacena caracteres en lugar de números enteros.
    private class Nodo {
        char dato;
        Nodo siguiente;

        public Nodo(char dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    // Puntero a la cima de la pila, esencial para el funcionamiento.
    private Nodo cima;

    // --- Métodos de la Pila ---
    // Estos son los métodos estándar de una pila, adaptados para caracteres.
    // La lógica de `push`, `pop`, y `estaVacia` ya fue explicada en el ejemplo
    // anterior.
    public void push(char dato) {
        Nodo nodo = new Nodo(dato);
        nodo.siguiente = this.cima;
        this.cima = nodo;
    }

    public Nodo pop() {
        if (this.estaVacia())
            return null;
        Nodo temp = this.cima;
        this.cima = this.cima.siguiente;
        return temp;
    }

    public boolean estaVacia() {
        return this.cima == null;
    }

    public void vaciar() {
        this.cima = null;
    }

    // --- Lógica del Balanceo y la Inversión ---

    // Este método verifica si los paréntesis, corchetes y llaves en una expresión
    // están balanceados.
    // Utiliza una pila para asegurarse de que cada símbolo de apertura tenga su
    // correspondiente
    // símbolo de cierre en el orden correcto (el último que se abrió debe ser el
    // primero en cerrarse).
    public boolean estaBalanceado(String expresion) {
        // Recorremos la expresión de entrada, carácter por carácter.
        for (char c : expresion.toCharArray()) {
            // Si el carácter es un símbolo de apertura, lo apilamos.
            if (c == '(' || c == '[' || c == '{') {
                push(c);
                // Si es un símbolo de cierre, se realiza la verificación.
            } else if (c == ')' || c == ']' || c == '}') {
                // Primero, verificamos si la pila está vacía. Si lo está, significa que
                // encontramos un símbolo de cierre sin su correspondiente símbolo de apertura.
                // Por ejemplo, en "]a+b[", el primer carácter cierra algo que nunca se abrió.
                if (this.estaVacia()) {
                    return false;
                }

                // Sacamos el último símbolo de apertura de la pila.
                char ultimoAbierto = pop().dato;

                // Comparamos el símbolo de cierre actual con el último que se abrió.
                // Si no coinciden, la expresión no está balanceada.
                // Por ejemplo, si el último abierto fue '(' pero el actual es ']', no
                // coinciden.
                if (c == ')' && ultimoAbierto != '(') {
                    return false;
                }
                if (c == ']' && ultimoAbierto != '[') {
                    return false;
                }
                if (c == '}' && ultimoAbierto != '{') {
                    return false;
                }
            }
        }

        // Al final del recorrido, la pila debe estar completamente vacía.
        // Si no lo está, significa que hay símbolos de apertura que nunca se cerraron.
        // Por ejemplo, en "({[a+b]}", falta cerrar el "(".
        return estaVacia();
    }

    // Este método estático invierte una cadena de texto utilizando una pila.
    public static String invertir(String expresion) {
        // Se crea una nueva instancia de la pila para esta operación.
        pilaCaracter pila = new pilaCaracter();

        // Se recorre la cadena original y cada carácter se apila.
        // El primer carácter de la cadena original queda en el fondo de la pila.
        // El último carácter queda en la cima de la pila.
        for (char c : expresion.toCharArray()) {
            pila.push(c);
        }

        // Se usa un StringBuilder para construir la cadena invertida de manera
        // eficiente.
        StringBuilder invertida = new StringBuilder();

        // Se desapilan todos los caracteres y se añaden a la nueva cadena.
        // Debido a la naturaleza LIFO de la pila, los caracteres se sacan
        // en orden inverso al que se introdujeron, invirtiendo la cadena.
        while (!pila.estaVacia()) {
            invertida.append(pila.pop().dato);
        }

        return invertida.toString();
    }

    // --- Método Principal ---

    public static void main(String[] args) {
        pilaCaracter pila = new pilaCaracter();

        // Ejemplos de expresiones para probar el balanceo.
        String[] e = new String[4];
        e[0] = "{[()]}"; // balanceado
        e[1] = "{{{([{}])}}}"; // balanceado
        e[2] = "{[}]}"; // no balanceado
        e[3] = "}[{()}]"; // no balanceado

        for (String expr : e) {
            // Se vacía la pila antes de cada prueba para asegurar que el resultado no se
            // vea afectado.
            pila.vaciar();
            System.out.println(expr + " esta balanceado? " + pila.estaBalanceado(expr));
        }

        // Ejemplo para probar la inversión de una cadena.
        pila.vaciar();
        String original = "Hola Mundo!";
        System.out.println("Original: " + original);
        System.out.println("Invertida: " + pilaCaracter.invertir(original));
    }
}