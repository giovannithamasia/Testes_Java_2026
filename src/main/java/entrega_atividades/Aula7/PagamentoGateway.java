package entrega_atividades.Aula7;

public interface PagamentoGateway {
    boolean cobrar(String pedidoId, double valor);
}
