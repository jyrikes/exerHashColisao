package hashColisao;

public interface EstruturaDeDados<T extends Comparable<T>> {
    public boolean insert(T chave);
    public boolean delete(T chave);
    public int search(T chave);
}
