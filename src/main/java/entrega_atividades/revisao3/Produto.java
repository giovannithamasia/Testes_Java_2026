package entrega_atividades.revisao3;

public class Produto {

    private String nome;
    private double preco;
    private int quantidade;

    public Produto(String nome, double preco, int quantidade) {
        if (nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome inválido");
        }

        if (preco <= 0){
            throw new IllegalArgumentException("Preço não pode ser negativo");
        }

        if (quantidade < 0){
            throw new IllegalArgumentException("Quantidade não pode ser negativa");
        }

        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public double calcularValorEstoque(){
        return preco*quantidade;
    }

    public double calcularPrecoComDesconto(double percentual){
        if (percentual < 0 || percentual > 100){
            throw new IllegalArgumentException("Percentual inválido");
        }

        double preco = getPreco();
        double desconto = percentual/100;

        return preco - (preco*desconto);
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
