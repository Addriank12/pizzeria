package dataAcces.model;

public class Persona extends Base_Model {
    protected String identificacion;
    protected String nombre_Completo;
    protected String direccion;
    protected String telefono;
    protected String mail;

    public String getIdentificacion() {
        return identificacion;
    }
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    public String getNombre_Completo() {
        return nombre_Completo;
    }
    public void setNombre_Completo(String nombre_Completo) {
        this.nombre_Completo = nombre_Completo;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public Persona(int iD, String identificacion, String nombre_Completo, String direccion, String telefono,
            String mail) {
        super(iD);
        this.identificacion = identificacion;
        this.nombre_Completo = nombre_Completo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.mail = mail;
    }

    

    
}
