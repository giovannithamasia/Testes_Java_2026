package entrega_atividades.revisao2;

public class ContaBancaria {

    private String titular;
    private double saldo;

    public ContaBancaria(String titular) {
        this.titular = titular;
        this.saldo = 100.00;
    }

    public void depositar(double valor){
        if (valor<=0){
            throw new IllegalArgumentException("Valor inválido");
        }

        this.saldo+=valor;
    }

    public void sacar(double valor) {
        if (valor<=0) {
            throw new IllegalArgumentException("O valor do saque deve ser maior que zero");
        }

        if (valor > saldo) {
            throw new IllegalStateException("Saldo insuficiente");
        }
        this.saldo -= valor;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }
}
