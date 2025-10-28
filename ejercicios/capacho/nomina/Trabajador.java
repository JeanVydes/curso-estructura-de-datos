package ejercicios.capacho.nomina;

public class Trabajador {
    public int codigo;
    public double salario;
    public Trabajador siguiente; // Siguiente trabajador en la sucursal
    public Trabajador anterior; // Trabajador anterior en la sucursal

    public Trabajador(int codigo, double salario) {
        this.codigo = codigo;
        this.salario = salario;
        this.siguiente = null;
        this.anterior = null;
    }
}
