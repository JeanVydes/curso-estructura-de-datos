package recursividad.java;

public class aplicaciones {

    /**
     * APLICACIÓN 1: Factorial de un número.
     * El factorial de un entero positivo n (denotado como n!) es el producto
     * de todos los números enteros positivos desde 1 hasta n.
     * 
     * Matemáticamente: n! = n * (n-1)!
     * Caso base: 0! = 1 o 1! = 1
     * 
     * DIAGRAMA DEL PROCESO (Factorial de 4):
     * =======================================================
     * factorial(4)
     *  |__ devuelve: 4 * factorial(3)
     *                    |__ devuelve: 3 * factorial(2)
     *                                      |__ devuelve: 2 * factorial(1)
     *                                                        |__ devuelve: 1 (Caso Base)
     * 
     * EL REGRESO (Desenrollando la pila de llamadas):
     * 2 * 1 = 2
     * 3 * 2 = 6
     * 4 * 6 = 24  <-- Resultado Final
     * =======================================================
     */
    public static int factorial(int n) {
        // CASO BASE: Si n es 0 o 1, su factorial es 1.
        if (n <= 1) {
            return 1;
        }
        // PASO RECURSIVO: multiplicamos n por el factorial de n-1.
        return n * factorial(n - 1);
    }

    /**
     * APLICACIÓN 2: Secuencia de Fibonacci.
     * Sucesión donde cada número es la suma de los dos anteriores: 0, 1, 1, 2, 3, 5, 8...
     * 
     * Matemáticamente: F(n) = F(n-1) + F(n-2)
     * Casos base: F(0) = 0, F(1) = 1
     * 
     * DIAGRAMA DE ÁRBOL (Fibonacci de 4):
     * La recursión aquí no es lineal, se ramifica (hace dos llamadas por paso).
     * =======================================================
     *                      fib(4) => 3
     *                    /            \
     *              fib(3)=>2          fib(2)=>1
     *             /        \          /       \
     *        fib(2)=>1   fib(1)=>1 fib(1)=>1 fib(0)=>0
     *        /      \
     *   fib(1)=>1 fib(0)=>0
     * =======================================================
     */
    public static int fibonacci(int n) {
        // CASOS BASE: Los dos primeros números de la secuencia
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        // PASO RECURSIVO: Se bifurca en DOS llamadas recursivas
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * APLICACIÓN 3: Suma de los dígitos de un número.
     * Ejemplo: 123 -> 1 + 2 + 3 = 6.
     * 
     * Para extraer los dígitos numéricamente usamos módulo (%) y división (/):
     * 123 % 10 = 3 (último dígito)
     * 123 / 10 = 12 (el número restante)
     * 
     * DIAGRAMA DEL PROCESO (sumaDigitos de 123):
     * =======================================================
     * sumaDigitos(123)
     *  |__ extrae '3' y devuelve: 3 + sumaDigitos(12)
     *                                 |__ extrae '2' y devuelve: 2 + sumaDigitos(1)
     *                                                                |__ extrae '1' (Caso base)
     * 
     * EL REGRESO: 
     * 2 + 1 = 3
     * 3 + 3 = 6 <-- Resultado Final
     * =======================================================
     */
    public static int sumaDigitos(int n) {
        // CASO BASE: Si el número es de un solo dígito (menor que 10) retorna a sí mismo.
        if (n < 10) {
            return n;
        }
        
        // PASO RECURSIVO: Sumamos el último dígito más la suma recursiva del resto del número.
        return (n % 10) + sumaDigitos(n / 10);
    }

    /**
     * APLICACIÓN 4: Exponenciación (Potencia).
     * Calcula una "base" elevada a un "exponente" (base ^ exp).
     * 
     * Matemáticamente: base^exp = base * base^(exp-1)
     * 
     * DIAGRAMA DEL PROCESO (2 elevado a 3):
     * =======================================================
     * potencia(2, 3) 
     *  |__ devuelve: 2 * potencia(2, 2)
     *                    |__ devuelve: 2 * potencia(2, 1)
     *                                      |__ devuelve: 2 * potencia(2, 0)
     *                                                        |__ devuelve: 1 (Caso base)
     * =======================================================
     */
    public static int potencia(int base, int exp) {
        // CASO BASE: Todo número elevado a la potencia 0 es 1
        if (exp == 0) {
            return 1;
        }
        
        // PASO RECURSIVO: Multiplicamos la base por la llamada recursiva disminuyendo la potencia
        return base * potencia(base, exp - 1);
    }

    public static void main(String[] args) {
        System.out.println("=== APLICACIÓN 1: FACTORIAL ===");
        System.out.println("Factorial de 4 es: " + factorial(4)); // 24
        System.out.println("Factorial de 5 es: " + factorial(5)); // 120

        System.out.println("\n=== APLICACIÓN 2: FIBONACCI ===");
        System.out.println("Fibonacci (posición 4) es: " + fibonacci(4)); // 3
        System.out.println("Fibonacci (posición 6) es: " + fibonacci(6)); // 8
        System.out.print("Secuencia de Finobacci hasta la posición 6: ");
        for (int i = 0; i <= 6; i++) {
            System.out.print(fibonacci(i) + " ");
        }
        System.out.println();

        System.out.println("\n=== APLICACIÓN 3: SUMA DE DÍGITOS ===");
        int num = 12345;
        System.out.println("Suma de los dígitos de " + num + " es: " + sumaDigitos(num)); // 15

        System.out.println("\n=== APLICACIÓN 4: POTENCIA ===");
        System.out.println("2 elevado a 3 es: " + potencia(2, 3)); // 8
        System.out.println("5 elevado a 3 es: " + potencia(5, 3)); // 125
    }
}
