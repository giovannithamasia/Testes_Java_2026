package entrega_atividades.Aula7;

public interface PedidoRepository {
    void salvar(Pedido pedido);
    Pedido buscarPorId(String id);
}
