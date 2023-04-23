package hashColisao;

public class Celula {


    private Integer value;
    private Boolean inTable;
    private Boolean colidiu;
    private Integer posColision ;

    public Celula(int value){
        this.value = value;
        this.inTable = false;
        this.posColision = null;
        this.colidiu = false;

    }
    public Celula(){
      this.value = null;
      this.inTable = null;
      this.posColision = null;
      this.colidiu = false;

  }

    public void setValue(int value) {
        this.value = value;
    }
    public int getValue() {
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
    public Boolean getColidiu() {
      return colidiu;
    }
    public void setColidiu(Boolean colidiu) {
      this.colidiu = colidiu;
    }




}