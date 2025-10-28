package ejercicios.capacho.nomina;

public class Sucursal {
    public int codigo;

    public Trabajador cabeza; // Primer trabajador en la sucursal

    public Sucursal siguiente; // Siguiente sucursal en la empresa
    public Sucursal anterior;  // Sucursal anterior en la empresa

    public Sucursal(int codigo) {
        this.codigo = codigo;
        this.cabeza = null;
        this.siguiente = null;
        this.anterior = null;
    }

    public void agregar(int codigo, double salario) {
        Trabajador nuevo = new Trabajador(codigo, salario);

        if (cabeza == null) {
            cabeza = nuevo;
            cabeza.siguiente = cabeza;
            cabeza.anterior = cabeza;
        } else {
            Trabajador ultimo = cabeza.anterior;
            ultimo.siguiente = nuevo;
            nuevo.anterior = ultimo;
            nuevo.siguiente = cabeza;
            cabeza.anterior = nuevo;
        }
    }
    
    public void mostrar() {
        if (cabeza == null) {
            System.out.println("La lista está vacía.");
            return;
        }
        Trabajador actual = cabeza;
        do {
            System.out.println("Trabajador{codigo=" + actual.codigo + ", salario=" + actual.salario + "}");
            actual = actual.siguiente;
        } while (actual != cabeza);
    }

    public Trabajador buscar(int codigo) {
        if (cabeza == null) {
            return null;
        }
        Trabajador actual = cabeza;
        do {
            if (actual.codigo == codigo) {
                return actual;
            }
            actual = actual.siguiente;
        } while (actual != cabeza);
        return null;
    }

    public Trabajador eliminar(int codigo) {
        if (cabeza == null) {
            return null;
        }
        Trabajador TrabajadorAEliminar = buscar(codigo);
        if (TrabajadorAEliminar == null) {
            return null;
        }

        if (TrabajadorAEliminar == cabeza && TrabajadorAEliminar.siguiente == cabeza) {
            cabeza = null;
        } else {
            TrabajadorAEliminar.anterior.siguiente = TrabajadorAEliminar.siguiente;
            if (TrabajadorAEliminar == cabeza) {
                cabeza = TrabajadorAEliminar.siguiente;
            }
        }
        return TrabajadorAEliminar;
    }
}
