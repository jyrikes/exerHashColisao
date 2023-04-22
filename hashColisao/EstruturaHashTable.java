package hashColisao;

public class EstruturaHashTable <T extends Comparable<T>> implements EstruturaDeDados<T>{
    private Celula<T>[] table;
    private Celula<T>[] bucket;
    private Celula<T>[][] hashTable;
    
    public EstruturaHashTable(int tableSize ,int bucketSize) {
        //esse número pode ser alterado
        table = new Celula<T>[tableSize];
        bucket = new Celula<T>[bucketSize];
        hashTable = new Celula<T>[table][bucket];

    }

    @Override
    public boolean insert(int chave) {
        // TODO quando inserir, se a posição estiver ocupada, o elemento NÃO é inserido e retorna false. Caso contrário, o elemento é inserido na posição calculada e retorna true.
        return false;
        
    }

    @Override
    public boolean delete(int chave) {
        // TODO quando inserir, se a posição estiver ocupada, torna o elemento da posição como null e retorna true. Caso contrário, retorna false.
        return false;
        
    }

    @Override
    public int search(int chave) {
        // TODO se o elemento estiver presente retorna a sua posição. Caso contrário, retorna -1.
        return -1;
    }
    

}
