import java.util.ArrayList;
import java.util.List;

public class MaterialCRUD implements Crud<Material> {
    @Override public void criar(Material material) {
        DadosSistema.materiais.add(material);
        DadosSistema.salvarMateriais();
    }
    @Override public List<Material> listar() { return new ArrayList<>(DadosSistema.materiais); }
    @Override public boolean atualizar(Material material) {
        if (!DadosSistema.materiais.contains(material)) return false;
        DadosSistema.salvarMateriais();
        return true;
    }
    @Override public boolean remover(Material material) {
        boolean removido = DadosSistema.materiais.remove(material);
        if (removido) DadosSistema.salvarMateriais();
        return removido;
    }
}
