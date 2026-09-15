package entrega_atividades.Aula6.desafios;

public abstract class Pagamento {
    private double valor;

    public Pagamento(double valor) {
        if (valor==0 || valor<0){
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }
        this.valor = valor;
    }

    public abstract double calcularTaxa();

    public double getValor() {
        return valor;
    }

}
