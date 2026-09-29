package entrega_atividades.Aula7;

// [O QUE É]: A classe principal do sistema (SUT - System Under Test).
// [PARA A APRESENTAÇÃO]: "Esta é a classe que estamos testando de verdade.
// Ela usa Injeção de Dependência no construtor,
// o que nos permite passar qualquer Dublê de Teste para ela."
public class PedidoService {
    private final PagamentoGateway pagamentoGateway;
    private final PedidoRepository pedidoRepository;
    private final EmailService emailService;

    // Construtor: Recebe as dependências externas.
    public PedidoService(PagamentoGateway pagamentoGateway,
                         PedidoRepository pedidoRepository,
                         EmailService emailService) {
        this.pagamentoGateway = pagamentoGateway;
        this.pedidoRepository = pedidoRepository;
        this.emailService = emailService;
    }

    // Regra de Negócio principal
    public boolean finalizarPedido(Pedido pedido) {
        // 1. Tenta cobrar no Gateway de Pagamento
        boolean pago = pagamentoGateway.cobrar(pedido.getId(), pedido.getValorTotal());

        if (pago) {
            // Se aprovado: altera estado, salva no repositório e envia e-mail
            pedido.setPago(true);
            pedidoRepository.salvar(pedido);
            emailService.enviarEmail(pedido.getEmailCliente(), "Seu pedido foi confirmado!");
            return true;
        }

        // Se recusado: não faz nada e retorna falso
        return false;
    }
}
