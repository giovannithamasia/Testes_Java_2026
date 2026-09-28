package entrega_atividades.Aula7;

import java.util.HashMap;
import java.util.Map;

class PedidoRepositoryFake implements PedidoRepository {
    private final Map<String, Pedido> bancoEmMemoria = new HashMap<>();

    @Override
    public void salvar(Pedido pedido) {
        bancoEmMemoria.put(pedido.getId(), pedido);
    }

    @Override
    public Pedido buscarPorId(String id) {
        return bancoEmMemoria.get(id);
    }
}
