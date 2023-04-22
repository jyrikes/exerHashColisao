public class Celula <T> {


    private T value;
    private Boolean inTable;
    private Integer posColision ;

    public Celula(){
        this.value = null;
        this.inTable = null;
        this.posColision = null;

    }

    public void setValue(T value) {
        this.value = value;
    }
    public T getValue() {
        return value;
    }
    public void setInTable(Boolean inTable) {
        this.inTable = inTable;
    }
    public Boolean getInTable() {
        return inTable;
    }
    public void setPosColision(Integer posColision) {
        this.posColision = posColision;
    }
    public Integer getPosColision() {
        return posColision;
    }




}