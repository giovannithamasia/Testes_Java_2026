package entrega_atividades.Aula7;

import java.util.HashMap;
import java.util.Map;

// [O QUE É]: DUBLÊ TIPO FAKE.
// [PARA A APRESENTAÇÃO]: "O Fake é uma implementação funcional e simplificada.
// Em vez de conectar em um banco MySQL,
// ele usa um HashMap na memória RAM. É leve, rápido e funciona de verdade.
class PedidoRepositoryFake implements PedidoRepository {

    // [EXPLICAÇÃO TÉCNICA - HASHMAP]:
    // Estrutura de dados no formato Chave-Valor
    // (Chave = ID do pedido em String, Valor = Objeto Pedido).
    // Funciona como um dicionário: busca ultrarrápida pela chave
    // sem precisar de banco de dados.
    private final Map<String, Pedido> bancoEmMemoria = new HashMap<>();

    @Override
    public void salvar(Pedido pedido) {
        // .put(chave, valor): Insere ou atualiza o pedido no Map
        bancoEmMemoria.put(pedido.getId(), pedido);
    }

    @Override
    public Pedido buscarPorId(String id) {
        // .get(chave): Busca e devolve o Pedido diretamente da memória RAM
        return bancoEmMemoria.get(id);
    }
}
