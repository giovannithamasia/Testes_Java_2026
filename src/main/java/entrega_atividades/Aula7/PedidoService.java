package entrega_atividades.Aula7;

public class PedidoService {
    private final PagamentoGateway pagamentoGateway;
    private final PedidoRepository pedidoRepository;
    private final EmailService emailService;

    public PedidoService(PagamentoGateway pagamentoGateway,
                         PedidoRepository pedidoRepository,
                         EmailService emailService) {
        this.pagamentoGateway = pagamentoGateway;
        this.pedidoRepository = pedidoRepository;
        this.emailService = emailService;
    }

    public boolean finalizarPedido(Pedido pedido) {
        boolean pago = pagamentoGateway.cobrar(pedido.getId(), pedido.getValorTotal());

        if (pago) {
            pedido.setPago(true);
            pedidoRepository.salvar(pedido);
            emailService.enviarEmail(pedido.getEmailCliente(), "Seu pedido foi confirmado!");
            return true;
        }

        return false;
    }
}
