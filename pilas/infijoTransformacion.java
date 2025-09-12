package pilas;


public class infijoTransformacion {

    // Clase interna para el nodo.
    private class Nodo {
        char dato;
        Nodo siguiente;

        public Nodo(char dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo cima;

    // Métodos esenciales de la pila.
    public void push(char dato) {
        Nodo n = new Nodo(dato);
        n.siguiente = this.cima;
        this.cima = n;
    }

    public char pop() {
        if (this.cima == null) {
            throw new IllegalStateException("La pila está vacía.");
        }
        
        char dato = this.cima.dato;
        this.cima = this.cima.siguiente;
        return dato;
    }

    public char peek() {
        if (this.cima == null) {
            throw new IllegalStateException("La pila está vacía.");
        }
        return this.cima.dato;
    }

    public boolean estaVacia() {
        return this.cima == null;
    }

    // Define la prioridad de los operadores. Mayor número = mayor prioridad.
    // Los paréntesis no tienen prioridad numérica en este contexto.
    private static int prioridad(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }

    // +A[B-C]

    // [A, +, B] -> A B +
    // a + b
    // Convierte una expresión de notación infija a postfija (operador después de operandos).
    public static String infijoAPostfijo(String expr) {
        // Pila para almacenar operadores y paréntesis.
        infijoTransformacion pila = new infijoTransformacion();
        StringBuilder postfijo = new StringBuilder();

        for (char c : expr.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                // Si es un operando, lo añade directamente a la salida.
                postfijo.append(c);
            } else if (c == '(') {
                // Si es un paréntesis de apertura, se apila.
                pila.push(c);
            } else if (c == ')') {
                // Si es un paréntesis de cierre, desapila todos los operadores
                // hasta encontrar el de apertura, agregándolos a la salida.
                while (!pila.estaVacia() && pila.peek() != '(') {
                    postfijo.append(pila.pop());
                }
                // Saca el '(' de la pila.
                if (!pila.estaVacia() && pila.peek() == '(') {
                    pila.pop();
                }
            } else { // Es un operador
                // Desapila y agrega a la salida los operadores con mayor o igual prioridad.
                // Esta lógica garantiza la precedencia de las operaciones.
                while (!pila.estaVacia() && prioridad(c) <= prioridad(pila.peek()) && pila.peek() != '(') {
                    postfijo.append(pila.pop());
                }
                // Apila el operador actual.
                pila.push(c);
            }
        }

        // Desapila los operadores restantes y los añade a la salida.
        while (!pila.estaVacia()) {
            postfijo.append(pila.pop());
        }

        return postfijo.toString();
    }

    // Convierte una expresión infija a prefija (operador antes de operandos).
    public static String infijoAPrefijo(String expr) {
        // Estrategia de inversión:
        // 1. Invertir la expresión infija y los paréntesis.
        StringBuilder exprInvertida = new StringBuilder();
        for (int i = expr.length() - 1; i >= 0; i--) {
            char c = expr.charAt(i);
            if (c == '(') {
                exprInvertida.append(')');
            } else if (c == ')') {
                exprInvertida.append('(');
            } else {
                exprInvertida.append(c);
            }
        }

        // 2. Convertir la nueva expresión invertida a postfija.
        String postfijoInvertido = infijoAPostfijo(exprInvertida.toString());

        // 3. Invertir el resultado para obtener la notación prefija.
        return new StringBuilder(postfijoInvertido).reverse().toString();
    }
    
    public static void main(String[] args) {
        String expresion1 = "A+B-C";
        System.out.println("Expresión infija: " + expresion1);
        System.out.println("Expresión postfija: " + infijoAPostfijo(expresion1));
        System.out.println("Expresión prefija: " + infijoAPrefijo(expresion1));

        String expresion2 = "(A+B)*C";
        System.out.println("\nExpresión infija: " + expresion2);
        System.out.println("Expresión postfija: " + infijoAPostfijo(expresion2));
        System.out.println("Expresión prefija: " + infijoAPrefijo(expresion2));

        String expresion3 = "(A*B)-(C+D)";
        System.out.println("\nExpresión infija: " + expresion3);
        System.out.println("Expresión postfija: " + infijoAPostfijo(expresion3));
        System.out.println("Expresión prefija: " + infijoAPrefijo(expresion3));
    }
}