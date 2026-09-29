package entrega_atividades.Aula7;

// [O QUE É]: Interface de persistência (Padrão Repository).
// [PARA A APRESENTAÇÃO]: "Define os métodos de acesso ao Banco de Dados.
// O PedidoService usa essa interface sem saber
// qual banco está por trás (MySQL, Postgres ou uma Fake na memória)."
public interface PedidoRepository {
    void salvar(Pedido pedido);
    Pedido buscarPorId(String id);
}
