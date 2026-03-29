package dataAcces.model;

public class Categoria extends Base_Model {

    private String Categoria;


    public String getCategoria() {
        return Categoria;
    }



    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public Categoria(int ID, String Categoria) {
        super(ID);
        this.Categoria = Categoria;
    }
}
