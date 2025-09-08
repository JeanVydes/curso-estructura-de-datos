package pilas;
/*
 * infijo son las expresiones comunes que usamos normalmente
 * postfijo son las expresiones donde el operador va despues de los operandos
 * prefijo son las expresiones donde el operador va antes de los operandos
 */
public class infijoTransformacion {
    private class Nodo {
        char dato;
        Nodo siguiente;

        public Nodo(char dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo cima;

    public void push(char dato) {
        Nodo n = new Nodo(dato);
        n.siguiente = this.cima;
        this.cima = n;
    }

    public Nodo pop() {
        if (this.cima == null) {
            return null;
        }   

        Nodo temp = this.cima;
        this.cima = this.cima.siguiente;
        return temp;
    }

    public Nodo peek() {
        return this.cima;
    }

    // entre mayor sea el numero, mayor es la prioridad
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

    public static String infijoAPostfijo(String expr) {
        // esta pila solo almacenara operadores y parentesis
        infijoTransformacion pila = new infijoTransformacion();
        StringBuilder postfijo = new StringBuilder();

        // partimos la expresion en un array de caracteres
        for (char c : expr.toCharArray()) {
            // si es una letra o numero, lo agregamos a la salida directamente
            if (Character.isLetterOrDigit(c)) {
                postfijo.append(c);
            // si es un parentesis de apertura, lo apilamos
            } else if (c == '(') {
                pila.push(c);
            } else if (c == ')') {
                // si es un parentesis de cierre, desapilamos hasta encontrar el de apertura
                // porque todo lo que esta dentro de parentesis va primero en la salida
                /* ejemplo paso a paso para este while
                (A+B)*C
                (: apilamos
                A: salida
                +: apilamos
                B: salida
                ): desapilamos hasta encontrar ( y lo sacamos sin agregar a la salida, en este caso desapilamos + y luego ( sin agregarlo a la salida
                ...
                 */
                while (pila.cima != null && pila.peek().dato != '(') {
                    postfijo.append(pila.pop().dato);
                }

                // sacamos el '(' de la pila pero no lo agregamos a la salida
                if (pila.cima != null && pila.peek().dato == '(') {
                    pila.pop(); // Sacar el '(' de la pila
                }
            } else { // Es un operador
                // desapilamos mientras la pila no este vacia y el operador en la cima tenga mayor o igual prioridad
                // porque esos van primero en la salida
                /*
                 * ejemplo paso a paso para este while
                 * A*B+C
                 * A: salida
                 * *: apilamos
                 * B: salida
                 * +: desapilamos * porque tiene mayor prioridad que + y lo agregamos a la salida, luego apilamos +
                 * C: salida
                 */
                while (pila.cima != null && prioridad(c) <= prioridad(pila.peek().dato)) {
                    postfijo.append(pila.pop().dato);
                }
                pila.push(c);
            }
        }

        // al final, desapilamos todo lo que quede en la pila, que seran los operadores restantes
        while (pila.cima != null) {
            postfijo.append(pila.pop().dato);
        }

        return postfijo.toString();
    }

    public static String infijoAPrefijo(String expr) {
        // Invertir la expresión y cambiar paréntesis
        StringBuilder exprInvertida = new StringBuilder();
        // Recorremos la expresión de derecha a izquierda
        // y cambiamos '(' por ')' y viceversa
        // Esto se hace para reutilizar la función de infijo a postfijo
        for (int i = expr.length() - 1; i >= 0; i--) {
            // obtener el caracter actual
            char c = expr.charAt(i);

            // cambiar paréntesis
            if (c == '(') {
                exprInvertida.append(')');
            } else if (c == ')') {
                exprInvertida.append('(');
            } else {
                // otros caracteres se agregan tal cual
                exprInvertida.append(c);
            }
        }

        // Convertir la expresión invertida a postfija
        String postfijoInvertido = infijoAPostfijo(exprInvertida.toString());

        // Invertir la expresión postfija para obtener la prefija
        return new StringBuilder(postfijoInvertido).reverse().toString();
    }

    public static void main(String[] args) {
        String expresion = "A+B-C";
        System.out.println("Expresión postfija: " + infijoAPostfijo(expresion));
        System.out.println("Expresión prefija: " + infijoAPrefijo(expresion));
        /*
        Cómo funciona para "A+B(C-D)"

        A: Es un operando. postfijo = "A"

        +: Es un operador. Se apila. pila = ["+"]

        B: Es un operando. postfijo = "AB"

        *: Es un operador con mayor prioridad que +. Se apila. pila = ["+", "*"]

        (: Es un paréntesis. Se apila. pila = ["+", "*", "("]

        C: Es un operando. postfijo = "ABC"

        -: Es un operador. Se apila. pila = ["+", "*", "(", "-"]

        D: Es un operando. postfijo = "ABCD"

        ): Es un paréntesis de cierre. Se desapila - y se agrega a la salida. Luego se saca el (. postfijo = "ABCD-", pila = ["+", "*"]

        Fin de la expresión: Se desapilan los operadores restantes y se agregan a la salida. Primero *, luego +. postfijo = "ABCD-*+"

        El resultado final es ABCD-*+. El código funciona como se espera.
         */
    }
}