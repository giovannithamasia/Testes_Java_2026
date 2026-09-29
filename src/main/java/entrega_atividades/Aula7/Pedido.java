package entrega_atividades.Aula7;

// [O QUE É]: Classe de modelo (Entidade de domínio).
// [PARA A APRESENTAÇÃO]: "Representa a estrutura de
// dados de um pedido na nossa aplicação."
public class Pedido {
    private String id;
    private double valorTotal;
    private String emailCliente;
    private boolean pago;

    public Pedido(String id, double valorTotal, String emailCliente) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.emailCliente = emailCliente;
        this.pago = false; // Todo pedido nasce como NÃO PAGO
    }

    public String getId() {
        return id;
    }
    public double getValorTotal() { return valorTotal; }
    public String getEmailCliente() { return emailCliente; }
    public boolean isPago() { return pago; }

    // Método chamado pelo PedidoService para alterar o estado do pedido após a cobrança
    public void setPago(boolean pago) { this.pago = pago; }
}
