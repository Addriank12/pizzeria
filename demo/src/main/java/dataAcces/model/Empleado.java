package dataAcces.model;

public class Empleado extends Persona {
    private Empleado_Cargo cargo;

    public Empleado_Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Empleado_Cargo cargo) {
        this.cargo = cargo;
    }

    public Empleado(int iD, String identificacion, String nombre_Completo, String direccion, String telefono,
            String mail, Empleado_Cargo cargo) {
        super(iD, identificacion, nombre_Completo, direccion, telefono, mail);
        this.cargo = cargo;
    }
    

    
}
