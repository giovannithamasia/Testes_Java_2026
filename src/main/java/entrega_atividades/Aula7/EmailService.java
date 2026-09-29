package entrega_atividades.Aula7;

// [O QUE É]: Interface para o serviço de e-mail.
// [PARA A APRESENTAÇÃO]: "Esta interface define o contrato de envio de e-mails.
// Usamos uma interface para que o nosso PedidoService não
// dependa de um servidor real de e-mails (como Gmail ou AWS SES)."
public interface EmailService {
    void enviarEmail(String destinatario, String mensagem);
}
