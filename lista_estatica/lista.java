public class lista {
    public static void main(String[] args) {
        // Declarar e inicializar un arreglo de 4 strings
        String[] nombres = new String[4];
        nombres[0] = "Ana";
        nombres[1] = "Juan";
        nombres[2] = "María";
        nombres[3] = "Pedro";

        // Acceder a un elemento, en este caso el tercero (índice 2), que es "María"
        System.out.println("El tercer nombre es: " + nombres[2]);

        // Recorrer el arreglo
        System.out.println("Todos los nombres:");

        for (int i = 0; i < nombres.length; i++) {
            System.out.println(nombres[i]);
        }
    }
}
