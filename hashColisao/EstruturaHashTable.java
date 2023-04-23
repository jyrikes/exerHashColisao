package hashColisao;

import javax.swing.text.html.parser.Element;

public class EstruturaHashTable implements EstruturaDeDados{
  private Celula[] table;
  private Celula[] bucket;
  public Celula[][] hashTable;
  private int tableSize;
  private int bucketSize;
  private int bucketLenght;
  private int tableLenght;

  
  public EstruturaHashTable(int tableSize, int bucketSize) {

      table = new Celula[tableSize];
      bucket = new Celula[bucketSize];
      hashTable = new Celula[2][];
      hashTable[0] = table;
      hashTable[1] = bucket;
      this.tableSize = tableSize;
      this.bucketSize = bucketSize;
      this.bucketLenght = bucketSize-1;
      this.tableLenght = tableSize-1;

  }

  public int hash(int chave){
    return chave % 1000;
  }

  @Override
  public boolean insert(int chave) {

     if(tableLenght >= 0 || bucketLenght>=0){

      // criar a céluca e aponta que ela está na tabela
      Celula cell = new Celula(chave);
      cell.setInTable(true);


      int key = hash(chave);

      Celula novaCelula = hashTable[0][key];
      if (novaCelula == null) {
        hashTable[0][key] = cell;
        tableLenght --;
        return true;
      } else {
        if (novaCelula.getInTable().equals(false)){
          hashTable[0][key] = cell;
          tableLenght --;
          return true;
        }else{
          if(novaCelula.getColidiu().equals(false)){
            hashTable[0][key].setPosColision(-bucketLenght);
          }

          novaCelula.setColidiu(true);
          colision(cell);
          return true;

        }
       
      }


     }
      return false;

  }


    private void colision(Celula cell) {
      if(bucketLenght >= 0){
        insertBucket(cell);
      }else{
        insertTable(cell);
      }
  }

    private void insertTable(Celula cell) {
      if(this.hashTable[0][tableSize -1] == null){
        this.hashTable[0][tableSize-1] = cell;
        tableLenght --;
      }else{
        colidirTable(cell);
      }
    }

    private void insertBucket(Celula cell) {

      if(bucketLenght == bucketSize-1 && this.hashTable[1][bucketLenght] == null){
        this.hashTable[1][bucketLenght] = cell;
        bucketLenght --;
      }else{
         colidirBucket(cell);  
      }

  }

    private void colidirBucket(Celula cell) {
      Celula colidido = this.hashTable[1][bucketLenght+1];
      colidido.setColidiu(true);
      colidido.setPosColision(-bucketLenght);
      this.hashTable[1][bucketLenght+1] = colidido;

      if(this.hashTable[1][bucketLenght] == null){
        this.hashTable[1][bucketLenght] = cell;

      }else{
        colision(cell);
      }
        bucketLenght --;
    }

    private void colidirTable(Celula cell) {
      Celula colidido = this.hashTable[1][bucketLenght+1];
      colidido.setColidiu(true);
      colidido.setPosColision(bucketLenght);
      this.hashTable[1][bucketLenght+1] = colidido;

      if(this.hashTable[1][bucketLenght] == null){
        this.hashTable[1][bucketLenght] = cell;

      }else{
        colision(cell);
      }
      
      
      
        bucketLenght --;
      
      
    }

      

    @Override
    public boolean delete(int chave) {
        int key = hash(chave);
        Celula elemento = this.hashTable[0][key];
        Celula celulaTabela;
        Celula celulaBucket;
    
        if(elemento != null){
            int indice = search(chave);
            if(indice == -1){
              return false;
            }
            switch (Integer.signum(indice)) {
                case 1:
                    celulaTabela = this.hashTable[0][indice];
                    celulaTabela.setInTable(false);
                    return true;
                case -1:
                    celulaBucket = this.hashTable[1][-indice];
                    celulaBucket.setInTable(false);
                    return true;
                case 0:
                    if(indice == 0){
                        celulaTabela = this.hashTable[0][indice];
                        celulaTabela.setInTable(false);
                        celulaBucket = this.hashTable[1][indice];
                        celulaBucket.setInTable(false);
                        return true;
                    }
                    if(indice == tableSize){
                        celulaTabela = this.hashTable[0][0];
                        celulaTabela.setInTable(false);
                        return true;
                    }
                    if(indice == bucketSize){
                        celulaBucket = this.hashTable[1][0];
                        celulaBucket.setInTable(false);
                        return true;
                    }
                    break;
                default:
                    return false;
            }
        }
    
        return false;
    }
    

    @Override
    public int search(int chave) {
        int key = hash(chave);
        Celula elemeto = this.hashTable[0][key];
        
        if(elemeto != null){
          Boolean elementoColidido = elemeto.getColidiu().equals(true);
          Boolean elementoNaoListado = elemeto.getInTable().equals(false);
          Boolean elementoListado = elemeto.getInTable().equals(true);
          Boolean elementoComparado = (elemeto.getValue() == chave);
          if(elementoListado &&  elementoComparado){
            return key;
          }
          if(elementoListado && !elementoComparado){
            if(elementoColidido){
              return serachColidido(elemeto,chave);
            }else{
              return -1;
            }

            }if(elementoNaoListado){
              if(elementoColidido){
                return serachColidido(elemeto,chave);
              }else{
                return -1;
              }

            }

           
          }

        return -1;
    }
    private int serachColidido(Celula elemento,int chave) {
      int posicao = elemento.getPosColision();

      Celula celulaPesquisada = null;

      if (posicao < 0){
        celulaPesquisada =this.hashTable[1][-posicao] ;
      }
      if(posicao > 0){
        celulaPesquisada = this.hashTable[0][posicao] ;
      }
      if(posicao ==0){
        Celula celulaTabela = this.hashTable[0][0];
        Celula celulaPorao = this.hashTable[1][0];
        int valorPorao =celulaPorao.getValue();
        int valorTabela =celulaTabela.getValue();
        if(valorPorao == valorTabela && valorPorao == chave){
          return 0;
        }
        if(valorPorao == chave){
          return bucketSize;
        }
        if(valorTabela == chave){
          return tableSize;
        }



      }
      

      int valorTestado = celulaPesquisada.getValue();
      Boolean existe = celulaPesquisada.getInTable();

      if(valorTestado == chave && existe){
        return elemento.getPosColision();
      }

      if( celulaPesquisada.getPosColision() == null && valorTestado != chave){
        return -1;
      }
      
      else{
        return serachColidido(celulaPesquisada, chave);
      }
    
    }

    @Override
    public String toString() {
      String r = "";
      for(int i = 0 ; i < bucketSize; i++){
        r = r+ hashTable[0][0].getValue();
      }
        
      
    
    return r;
    }

    
    
    public static void main(String[] args) {
      EstruturaHashTable has = new EstruturaHashTable(1000, 100);
      has.search(10);
      has.insert(10);
      has.insert(1010);
      has.insert(2010);
      System.out.println(has.search(1010));
      System.out.println(has.search(2010));
      has.delete(1010);
      has.delete(3010);
      has.delete(10);

      System.out.println(has.search(10));
      System.out.println(has.search(1010));
    

 
        

      
      



    }

}
