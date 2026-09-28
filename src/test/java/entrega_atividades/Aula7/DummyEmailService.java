package entrega_atividades.Aula7;

class DummyEmailService implements EmailService {
    @Override
    public void enviarEmail(String destinatario, String mensagem) {
        throw new UnsupportedOperationException("Este método nunca deveria ser chamado!");
    }
}
