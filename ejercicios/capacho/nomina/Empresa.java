package ejercicios.capacho.nomina;

// lista doblemente enlazada circular de sucursales
public class Empresa {
    Sucursal cabeza; // Siguiente sucursal en la empresa

    public void agregar(int codigo) {
        Sucursal nuevo = new Sucursal(codigo);

        if (cabeza == null) {
            cabeza = nuevo;
            cabeza.siguiente = cabeza;
            cabeza.anterior = cabeza;
        } else {
            Sucursal ultimo = cabeza.anterior;
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
        Sucursal actual = cabeza;
        do {
            System.out.println("Sucursal{codigo=" + actual.codigo + "}");
            actual = actual.siguiente;
        } while (actual != cabeza);
    }

    public Sucursal buscar(int codigo) {
        if (cabeza == null) {
            return null;
        }
        Sucursal actual = cabeza;
        do {
            if (actual.codigo == codigo) {
                return actual;
            }
            actual = actual.siguiente;
        } while (actual != cabeza);
        return null;
    }

    public Sucursal eliminar(int codigo) {
        if (cabeza == null) {
            return null;
        }
        Sucursal SucursalAEliminar = buscar(codigo);
        if (SucursalAEliminar == null) {
            return null;
        }

        if (SucursalAEliminar == cabeza && SucursalAEliminar.siguiente == cabeza) {
            cabeza = null;
        } else {
            SucursalAEliminar.anterior.siguiente = SucursalAEliminar.siguiente;
            if (SucursalAEliminar == cabeza) {
                cabeza = SucursalAEliminar.siguiente;
            }
        }
        return SucursalAEliminar;
    }
}
