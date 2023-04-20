package hashColisao;

public class EstruturaHashTable implements EstruturaDeDados{
    private Integer tabela[];
    //TODO adicionar elementos necessários para implementar o porão

    public EstruturaHashTable() {
        //esse número pode ser alterado
        tabela = new Integer[1000];
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
