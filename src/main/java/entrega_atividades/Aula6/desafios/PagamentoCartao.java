package entrega_atividades.Aula6.desafios;

public class PagamentoCartao extends Pagamento{
    public PagamentoCartao(double valor) {
        super(valor);
    }

    @Override
    public double calcularTaxa() {
        return this.getValor() * 0.025;
    }
}
