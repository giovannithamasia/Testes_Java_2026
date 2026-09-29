package entrega_atividades.Aula7;


// O SENTIDO DESTA INTERFACE:
// Ela é o contrato. Ela diz: "Não importa quem vai processar o pagamento,
// o método 'cobrar' só precisa me devolver true (se aprovou) ou false (se recusou)."
public interface PagamentoGateway {
    boolean cobrar(String pedidoId, double valor);
}
