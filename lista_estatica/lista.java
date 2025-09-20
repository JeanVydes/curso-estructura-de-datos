/**
 * 🗄️ Demostración de un Arreglo (Array) en Java.
 * Un arreglo es una de las estructuras de datos más fundamentales. Podemos
 * imaginarlo como un **archivador con un número fijo de cajones numerados**.
 *
 * Características Clave:
 * 1. **Tamaño Fijo:** Una vez que creas el archivador con 4 cajones, no puedes
 * agregar un quinto cajón. Su tamaño es estático.
 * 2. **Acceso Directo:** Si quieres el contenido del cajón N° 2, puedes ir
 * directamente a él sin abrir los anteriores. Esto es muy rápido.
 * 3. **Índices Basados en Cero:** ¡Muy importante! La numeración de los
 * "cajones"
 * siempre empieza en 0. Un archivador de 4 cajones tendrá los índices 0, 1, 2 y
 * 3.
 * 
 */
public class lista {
    public static void main(String[] args) {

        // --- 1. Declaración e Inicialización ---
        // Aquí le decimos a Java: "Necesito un archivador nuevo llamado 'nombres'
        // que tendrá exactamente 4 cajones para guardar Strings".
        String[] nombres = new String[4];

        // --- 2. Asignación de Valores ---
        // Ahora, vamos a guardar datos en cada "cajón" usando su número de índice.
        // Recuerda: el primer cajón es el 0.
        nombres[0] = "Ana"; // Guardar en el cajón 0
        nombres[1] = "Juan"; // Guardar en el cajón 1
        nombres[2] = "María"; // Guardar en el cajón 2
        nombres[3] = "Pedro"; // Guardar en el cajón 3

        // --- 3. Acceso a un Elemento Específico ---
        // Para leer un dato, simplemente vamos al archivador y abrimos el cajón
        // con el número de índice que queremos. El acceso es instantáneo.
        System.out.println("El tercer nombre es: " + nombres[2]);

        // --- 4. Recorrer el Arreglo Completo ---
        // Para ver todo lo que hay en el archivador, necesitamos visitar
        // cada cajón, uno por uno, desde el primero hasta el último.
        System.out.println("\nTodos los nombres:");

        // Usamos un bucle 'for' que funciona como un "inspector":
        // - 'int i = 0': El inspector empieza en el cajón 0.
        // - 'i < nombres.length': Continuará mientras no se le acaben los cajones
        // (nombres.length es el tamaño total, en este caso, 4).
        // - 'i++': Después de revisar un cajón, pasa al siguiente.
        for (int i = 0; i < nombres.length; i++) {
            // Imprimimos el contenido del cajón que el inspector 'i' está viendo en este
            // momento.
            System.out.println("- " + nombres[i]);
        }
    }
}