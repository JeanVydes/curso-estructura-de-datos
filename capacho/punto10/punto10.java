package capacho.punto10;

import capacho.Nodo;

public class punto10 {
    public class NodoNoticiasTelevisivas {
        int codigo;
        String nombre;
        String lenguaje;
        String pais;

        public NodoNoticiasTelevisivas siguiente;
        public NodoNoticiasTelevisivas anterior;

        public NodoUsuario cabezaUsuario;

        public NodoNoticiasTelevisivas(int codigo, String nombre, String lenguaje, String pais) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.lenguaje = lenguaje;
            this.pais = pais;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    public class NodoNoticiasPrensa {
        int codigo;
        String nombre;
        String lenguaje;
        String medio;

        public NodoNoticiasPrensa siguiente;
        public NodoNoticiasPrensa anterior;

        public NodoUsuario cabezaUsuario;

        public NodoNoticiasPrensa(int codigo, String nombre, String lenguaje, String medio) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.lenguaje = lenguaje;
            this.medio = medio;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    public class NodoMensajariaNoticias {
        int codigo;
        String nombre;
        String lenguaje;
        String horario;

        public NodoMensajariaNoticias siguiente;
        public NodoMensajariaNoticias anterior;

        public NodoUsuario cabezaUsuario;

        public NodoMensajariaNoticias(int codigo, String nombre, String lenguaje, String horario) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.lenguaje = lenguaje;
            this.horario = horario;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    public class NodoUsuario {
        int codigo;
        String nombre;
        String pais;
        String email;
        String telefono;
        String lenguaje;

        public NodoUsuario siguiente;
        public NodoUsuario anterior;

        public NodoUsuario(int codigo, String nombre, String pais, String email, String telefono, String lenguaje) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.pais = pais;
            this.email = email;
            this.telefono = telefono;
            this.lenguaje = lenguaje;
            this.siguiente = null;
            this.anterior = null;
        }
    }

    NodoNoticiasTelevisivas cabezaNoticiasTelevisivas = null;
    NodoNoticiasPrensa cabezaNoticiasPrensa = null;
    NodoMensajariaNoticias cabezaMensajariaNoticias = null;

    public void agregar(int codigo, String nombre, String lenguaje, String pais) {
        NodoNoticiasTelevisivas nuevo = new NodoNoticiasTelevisivas(codigo, nombre, lenguaje, pais);

        if (cabezaNoticiasTelevisivas == null) {
            cabezaNoticiasTelevisivas = nuevo;
            cabezaNoticiasTelevisivas.siguiente = cabezaNoticiasTelevisivas;
            cabezaNoticiasTelevisivas.anterior = cabezaNoticiasTelevisivas;
        } else {
            NodoNoticiasTelevisivas ultimo = cabezaNoticiasTelevisivas.anterior;
            ultimo.siguiente = nuevo;
            nuevo.anterior = ultimo;
            nuevo.siguiente = cabezaNoticiasTelevisivas;
            cabezaNoticiasTelevisivas.anterior = nuevo;
        }
    }

    // 1.
    public void mostrarRLinks() {
        NodoNoticiasTelevisivas actual = cabezaNoticiasTelevisivas;
        if (cabezaNoticiasTelevisivas == null) {
            System.out.println("La lista está vacía.");
            return;
        }
        do {
            System.out.println("NoticiasTelevisivas{codigo=" + actual.codigo + "}");
            actual = actual.siguiente;
        } while (actual != cabezaNoticiasTelevisivas);
    }

    public void mostrarLLinks() {
        NodoNoticiasTelevisivas actual = cabezaNoticiasTelevisivas;
        if (cabezaNoticiasTelevisivas == null) {
            System.out.println("La lista está vacía.");
            return;
        }
        do {
            System.out.println("NoticiasTelevisivas{nombre=" + actual.nombre + "}");
            actual = actual.anterior;
        } while (actual != cabezaNoticiasTelevisivas);
    }

    // 2.

    public int contarUsuario() {
        NodoUsuario actual = cabezaNoticiasTelevisivas.cabezaUsuario;
        if (actual == null) {
            return 0; // Si no hay cabeza, no hay jugadores.
        }

        int contandor = 0;
        do {
            if (actual != null) {
                contandor++;
            }
            actual = actual.siguiente;
        } while (actual != cabezaNoticiasTelevisivas.cabezaUsuario);
        return contandor;
    }

    // 5.
    public int contarUsuarioPorLenguajeEspanol() {
        NodoUsuario actual = cabezaNoticiasTelevisivas.cabezaUsuario;
        if (actual == null) {
            return 0; // Si no hay cabeza, no hay jugadores.
        }

        int contandor = 0;
        do {
            if (actual != null && actual.lenguaje.equals("Español")) {
                contandor++;
            }
            actual = actual.siguiente;
        } while (actual != cabezaNoticiasTelevisivas.cabezaUsuario);
        return contandor;
    }
}
