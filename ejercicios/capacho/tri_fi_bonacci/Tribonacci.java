package ejercicios.capacho.tri_fi_bonacci;

public class Tribonacci {

    // 0 1 1
    public static int tribonacciRecursivo(int n) {
        if (n == 0) {
            return 0;
        }
        if (n <= 2) { // T(1) = 1, T(2) = 1
            return 1;
        }

        return tribonacciRecursivo(n - 1) + tribonacciRecursivo(n - 2) + tribonacciRecursivo(n - 3);
    }

    public static int tribonacciIterativo(int n) {
        // Casos base
        if (n == 0) {
            return 0;
        }
        if (n <= 2) {
            return 1;
        }
        
        // Se necesitan tres variables para almacenar los números anteriores
        int a = 0; // T(0)
        int b = 1; // T(1)
        int c = 1; // T(2)
        int d = 0; // Variable para almacenar el nuevo término
        
        // El bucle empieza en 3 porque ya tenemos los 3 primeros términos
        for (int i = 3; i <= n; i++) {
            d = a + b + c; // El nuevo término es la suma de los tres anteriores
            a = b;
            b = c;
            c = d;
        }
        return c;
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println("Tribonacci recursivo de " + n + " es: " + tribonacciRecursivo(n));
        System.out.println("Tribonacci iterativo de " + n + " es: " + tribonacciIterativo(n));
    }
}