package entrega_atividades.Aula7;

import java.util.ArrayList;
import java.util.List;

class EmailServiceSpy implements EmailService {
    private final List<String> emailsEnviados = new ArrayList<>();

    @Override
    public void enviarEmail(String destinatario, String mensagem) {
        emailsEnviados.add(destinatario + ":" + mensagem);
    }

    public int getQuantidadeEmailsEnviados() {
        return emailsEnviados.size();
    }

    public boolean contemEmailPara(String email) {
        return emailsEnviados.stream().anyMatch(e -> e.startsWith(email));
    }
}