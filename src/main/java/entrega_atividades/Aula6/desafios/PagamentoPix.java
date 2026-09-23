package entrega_atividades.Aula6.desafios;

public class PagamentoPix extends Pagamento{
    public PagamentoPix(double valor) {
        super(valor);
    }

    @Override
    public double calcularTaxa() {
        return 0.0;
    }
}
