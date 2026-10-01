import java.util.List;

public interface Crud<T> {
    void criar(T item);
    List<T> listar();
    boolean atualizar(T item);
    boolean remover(T item);
}
