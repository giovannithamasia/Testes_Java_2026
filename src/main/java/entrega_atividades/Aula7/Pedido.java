package entrega_atividades.Aula7;

public class Pedido {
    private String id;
    private double valorTotal;
    private String emailCliente;
    private boolean pago;

    public Pedido(String id, double valorTotal, String emailCliente) {
        this.id = id;
        this.valorTotal = valorTotal;
        this.emailCliente = emailCliente;
        this.pago = false;
    }

    public String getId() {
        return id;
    }
    public double getValorTotal() { return valorTotal; }
    public String getEmailCliente() { return emailCliente; }
    public boolean isPago() { return pago; }
    public void setPago(boolean pago) { this.pago = pago; }
}
