package capacho.tri_fi_bonacci;

public class Fibonacci {
    public static int recursiva(int n) {
        if (n <= 1) {
            return n;
        }
        return recursiva(n - 1) + recursiva(n - 2);
    }

    public static int iterativa(int n) {
        if (n <= 1) {
            return n;
        }
        int a = 0, b = 1, c = 0;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println("Fibonacci recursivo de " + n + " es: " + recursiva(n));
        System.out.println("Fibonacci iterativo de " + n + " es: " + iterativa(n));
    }
}
