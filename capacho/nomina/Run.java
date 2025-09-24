package capacho.nomina;

public class Run {
    public static void main(String[] args) {
        Empresa empresa = new Empresa();

        // Agregar sucursales
        empresa.agregar(101);
        empresa.agregar(102);
        empresa.agregar(103);

        // Mostrar sucursales
        System.out.println("Sucursales en la empresa:");
        empresa.mostrar();

        // Agregar trabajadores a la sucursal 101
        Sucursal sucursal101 = empresa.buscar(101);
        if (sucursal101 != null) {
            sucursal101.agregar(1, 50000);
            sucursal101.agregar(2, 60000);
        }

        // Agregar trabajadores a la sucursal 102
        Sucursal sucursal102 = empresa.buscar(102);
        if (sucursal102 != null) {
            sucursal102.agregar(3, 55000);
            sucursal102.agregar(4, 65000);
        }

        // Mostrar trabajadores en la sucursal 101
        System.out.println("\nTrabajadores en la sucursal 101:");
        if (sucursal101 != null) {
            sucursal101.mostrar();
        }

        // Mostrar trabajadores en la sucursal 102
        System.out.println("\nTrabajadores en la sucursal 102:");
        if (sucursal102 != null) {
            sucursal102.mostrar();
        }

        // Eliminar un trabajador de la sucursal 101
        if (sucursal101 != null) {
            Trabajador eliminado = sucursal101.eliminar(1);
            if (eliminado != null) {
                System.out.println("\nTrabajador eliminado de la sucursal 101: " + eliminado.codigo);
            }
        }

        // Mostrar trabajadores en la sucursal 101 después de eliminación
        System.out.println("\nTrabajadores en la sucursal 101 después de eliminación:");
        if (sucursal101 != null) {
            sucursal101.mostrar();
        }

        Trabajador mejorPagado = null;
        Sucursal sucursalTrabajadorMejorPagado = null;
        Sucursal sucursalActual = empresa.cabeza;
        if (sucursalActual != null) {
            do {
                Trabajador trabajadorActual = sucursalActual.cabeza;
                if (trabajadorActual != null) {
                    do {
                        if (mejorPagado == null || trabajadorActual.salario > mejorPagado.salario) {
                            mejorPagado = trabajadorActual;
                            sucursalTrabajadorMejorPagado = sucursalActual;
                        }
                    } while ((trabajadorActual = trabajadorActual.siguiente) != sucursalActual.cabeza);
                }
            } while ((sucursalActual = sucursalActual.siguiente) != empresa.cabeza);
        }

        if (mejorPagado != null) {
            System.out.println(
                    "\nTrabajador mejor pagado: " + mejorPagado.codigo + " con salario " + mejorPagado.salario
                            + " en la sucursal " + sucursalTrabajadorMejorPagado.codigo);
        } else {
            System.out.println("\nNo hay trabajadores en la empresa.");
        }
    }
}
