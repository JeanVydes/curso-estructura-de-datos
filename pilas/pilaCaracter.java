package pilas;

public class pilaCaracter {
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
        Nodo nodo = new Nodo(dato);

        nodo.siguiente = this.cima;
        this.cima = nodo;
    }

    public Nodo pop() {
        if (this.estaVacia()) return null;

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

    public boolean estaBalanceado(String expresion) {
        // partimos la expresiones en un array de caracteres
        // String -> char[]
        // {()} -> ['{', '(', ')', '}']
        for (char c : expresion.toCharArray()) {
            // identificamos los caracteres de apertura
            // y los agregamos a la pila
            if (c == '(' || c == '[' || c == '{') {
                push(c);
            // para los de cierre
            // primero vemos si la pila esta vacia, ya este es un indicador de que esta no balanceada (nunca se abrio)
            // despues obtenemos la cima de la pila, este nodo tendra el ultimo simbolo de apertura, por lo tanto ahora tiene que venir el que lo cierra
            } else if (c == ')' || c == ']' || c == '}') {
                if (this.estaVacia()) {
                    return false;
                }

                // obtenemos el dato de la cima
                char ultimoAbierto = pop().dato;

                // y los comparamos para ver si coinciden, si no, es porque no esta balanceada
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

        // debe estar vacia al final del proceso para que quede balanceada
        // si quedo un elemento, falto cerrar algun simbolo
        return estaVacia();
    }

    public String invertir(String expresion) {
        pilaCaracter pila = new pilaCaracter();

        for (char c : expresion.toCharArray()) {
            pila.push(c);
        }

        StringBuilder invertida = new StringBuilder();

        while (!pila.estaVacia()) {
            invertida.append(pila.pop().dato);
        }

        return invertida.toString();
    }

    public static void main(String[] args) {
        pilaCaracter pila= new pilaCaracter();

        String[] e = new String[4];
        e[0] = "{[()]}"; // balanceado
        e[1] = "{{{([{}])}}}"; // balanceado
        e[2] = "{[}]}"; // no balanceado
        e[3] = "}[{()}]"; // no balanceado

        for (String expr : e) {
            pila.vaciar();
            System.out.println(expr + " esta balanceado? " + pila.estaBalanceado(expr));
        }


        pila.vaciar();
        String original = "Hola Mundo!";
        System.out.println("Original: " + original);
        System.out.println("Invertida: " + pila.invertir(original));
   }
}
