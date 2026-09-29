package entrega_atividades.Aula7;

// [O QUE É]: DUBLÊ TIPO DUMMY.
// [PARA A APRESENTAÇÃO]: "O Dummy é um 'objeto tapa-buraco'.
// Ele só serve para preencher a lista de parâmetros
// do construtor do PedidoService.
// Lançamos uma UnsupportedOperationException para provar que,
// no cenário de falha, este serviço NUNCA é acionado."
class DummyEmailService implements EmailService {
    @Override
    public void enviarEmail(String destinatario, String mensagem) {
        throw new UnsupportedOperationException("Este método nunca deveria ser chamado!");
    }
}
