package ejercicios.capacho.sistemas_sid;

public class SistemasDeportivosSID {
    public static void main(String[] args) {

        // --- 1. Creación de los Objetos (Instanciación) ---
        // Aquí estamos creando "instancias" de nuestra clase Club.
        // Imagina que la clase Club es un "molde" para hacer clubes.
        // 'new Club()' crea un club nuevo, vacío y listo para usarse.
        // 'junior' y 'nacional' son las variables que guardan estos objetos.
        System.out.println("Creando los clubes Junior y Nacional...");
        Club junior = new Club();
        Club nacional = new Club();
        System.out.println("¡Clubes creados!\n");

        // --- 2. Poblando los Objetos con Datos ---
        // Ahora que los clubes existen, usamos su método 'agregar' para
        // inscribir jugadores en cada uno.
        // Cada vez que llamamos a 'junior.agregar(...)', estamos ejecutando
        // el código que programamos dentro de la clase Club para añadir un
        // NodoJugador a su lista circular interna.

        System.out.println("Inscribiendo jugadores en el Junior de Barranquilla...");
        junior.agregar(1, "Teo Gutiérrez");
        junior.agregar(2, "Miguel Borja");
        junior.agregar(3, "Sebastián Viera");
        junior.agregar(4, "Carlos Bacca");
        junior.agregar(5, "Luis Díaz");
        System.out.println("¡Jugadores inscritos!\n");

        System.out.println("Inscribiendo jugadores en Atlético Nacional...");
        nacional.agregar(1, "Andrés Escobar");
        nacional.agregar(2, "René Higuita");
        nacional.agregar(3, "Óscar Córdoba");
        nacional.agregar(4, "David Ospina");
        nacional.agregar(5, "James Rodríguez");
        System.out.println("¡Jugadores inscritos!\n");

        // --- 3. Usando los Métodos de los Objetos ---
        // Ahora vamos a pedirle a cada club que nos dé información.

        // Le pedimos al objeto 'junior' que ejecute su método 'contar'.
        // Este método recorrerá su lista interna y nos devolverá el total.
        System.out.println("--- Información del Junior ---");
        System.out.println("Número de jugadores en Junior: " + junior.contar());

        junior.mostrar();

        System.out.println("\n-------------------------------------------------\n");
        System.out.println("--- Información de Nacional ---");
        System.out.println("Número de jugadores en Nacional: " + nacional.contar());
        nacional.mostrar();


        // Buscando jugadores específicos por su código.
        int codigoABuscar = 3;
        System.out.println("\nBuscando jugador con código " + codigoABuscar + " en Junior:");
        NodoJugador jugadorJunior = junior.buscar(codigoABuscar);
        if (jugadorJunior != null) {
            System.out.println("Jugador encontrado: " + jugadorJunior.nombre);
        } else {
            System.out.println("Jugador no encontrado.");
        }

        // Ahora eliminamos un jugador y mostramos la lista actualizada.
        System.out.println("\nEliminando jugador con código 2 de Nacional...");
        NodoJugador eliminado = nacional.eliminar(2);
        if (eliminado != null) {
            System.out.println("Jugador eliminado exitosamente: " + eliminado.nombre);
        } else {
            System.out.println("No se encontró el jugador para eliminar.");
        }

        System.out.println("Jugadores actuales en Nacional:");
        nacional.mostrar();
    }
}