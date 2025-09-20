package ejercicios.nallig;

public class LlamadasCola1 {
    static Cola1 cola = new Cola1(5);
    static final double posibilidadLlamada = 0.6;
    static final double posibilidadAtencion = 0.4;

    public static void simularLlamadas() {
        int minutos = 0;
        int maximoSimulacion = 60;

        while (minutos < maximoSimulacion) {
            System.out.println("Minuto: " + minutos);

            // Math.random() genera un número entre 0.0 y 1.0

            if (Math.random() < posibilidadLlamada) {
                if (!cola.estaLlena()) {
                    cola.insertar(minutos);
                    System.out.println("Llamada entrante en el minuto " + minutos);
                } else {
                    System.out.println("Llamada perdida en el minuto " + minutos + " (cola llena)");
                }
            }

            if (Math.random() < posibilidadAtencion && !cola.estaVacia()) {
                // Math.random() genera un número entre 0.0 y 1.0, luego se multiplica por 6
                // para generar
                // un número entre 0.0 incluido y 6.0 no incluido, y finalmente se le suma 3
                // para desplazar el rango a
                // entre 3.0 incluido y 8.0. El numero en double puede dar 8.9999, pero al
                // castear a int se trunca a 8.
                int tiempoAtencion = (int) (Math.random() * 6) + 3; // Tiempo de atención entre 3 y 8 minutos
                int tiempoEspera = minutos - cola.eliminar(); // porque el valor eliminado es el minuto en que llegó

                if (tiempoEspera < 0) {
                    tiempoEspera = 0; // Asegurarse de que el tiempo de espera no sea negativo
                }

                System.out.println("Llamada atendida en el minuto " + minutos + " (tiempo de espera: "
                        + tiempoEspera + " minutos, tiempo de atención: " + tiempoAtencion + " minutos)");
            }

            cola.mostrar();

            System.out.println("Número de llamadas en espera: " + cola.numElem());
            System.out.println("Promedio de espera: " + promedioEspera(minutos) + " minutos");

            minutos++;
        }
    }

    public static int promedioEspera(int minutos) {
        int totalNodos = 0;
        int sumaEspera = 0;

        int i = 0;
        while (i < cola.numElem()) {
            int valor = cola.eliminar();
            // valor es el minuto en que llegó la llamada

            // Acumulamos los minutos de espera y contamos los nodos

            sumaEspera += minutos - valor;
            totalNodos = totalNodos + 1;
            i++;

            cola.insertar(valor);
        }

        if (totalNodos > 0) {
            return sumaEspera / totalNodos;
        }

        return 0;
    }

    public static void main(String[] args) {
        simularLlamadas();
    }
}
