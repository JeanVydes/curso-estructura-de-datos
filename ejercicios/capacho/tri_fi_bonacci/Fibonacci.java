package ejercicios.capacho.tri_fi_bonacci;

public class Fibonacci {

    /**
     * Calcula el n-ésimo número de Fibonacci utilizando un enfoque recursivo.
     * La recursividad es elegante pero puede ser ineficiente para valores grandes de 'n'
     * debido a que recalcula los mismos valores múltiples veces.
     *
     * @param n La posición en la secuencia de Fibonacci que se desea calcular.
     * @return El n-ésimo número de Fibonacci.
     */
    public static int recursiva(int n) {
        // Caso base: si n es 0 o 1, el resultado es n.
        // Esto es crucial para detener la recursión.
        // F(0) = 0, F(1) = 1.
        if (n <= 1) {
            return n;
        }
        // Paso recursivo: el número de Fibonacci es la suma de los dos números anteriores.
        // La función se llama a sí misma con argumentos más pequeños (n-1 y n-2).
        return recursiva(n - 1) + recursiva(n - 2);
    }

    /**
     * Calcula el n-ésimo número de Fibonacci utilizando un enfoque iterativo (con un bucle).
     * Este método es más eficiente en términos de tiempo y memoria que la versión recursiva,
     * ya que no repite cálculos y no llena la pila de llamadas.
     *
     * @param n La posición en la secuencia de Fibonacci que se desea calcular.
     * @return El n-ésimo número de Fibonacci.
     */
    public static int iterativa(int n) {
        // Caso base: si n es 0 o 1, el resultado es n, igual que en la versión recursiva.
        if (n <= 1) {
            return n;
        }
        // Inicializamos las variables para guardar los dos números anteriores de la secuencia.
        int a = 0; // Representa F(n-2), comenzando con F(0)
        int b = 1; // Representa F(n-1), comenzando con F(1)
        int c = 0; // Almacenará el resultado actual F(n)

        // Iteramos desde 2 hasta n, ya que los casos 0 y 1 ya están cubiertos.
        for (int i = 2; i <= n; i++) {
            // El número actual 'c' es la suma de los dos anteriores 'a' y 'b'.
            c = a + b;
            // Actualizamos los valores para la siguiente iteración.
            // El que era F(n-1) ahora se convierte en F(n-2).
            a = b;
            // El resultado actual F(n) se convierte en el nuevo F(n-1) para el siguiente cálculo.
            b = c;
        }
        // Al final del bucle, 'c' (o 'b') contiene el n-ésimo número de Fibonacci.
        return c;
    }

    /**
     * El método principal que sirve como punto de entrada para la ejecución del programa.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        // Definimos el número para el cual queremos calcular la secuencia de Fibonacci.
        int n = 4;

        // Llamamos al método recursivo e imprimimos el resultado.
        System.out.println("Fibonacci recursivo de " + n + " es: " + recursiva(n));

        // Llamamos al método iterativo e imprimimos el resultado.
        System.out.println("Fibonacci iterativo de " + n + " es: " + iterativa(n));
    }
}